#!/usr/bin/env python3

import collections
import math
import random

def direction(origin, goal):
    delta = goal - origin
    return 0 if 0 == delta else delta / abs(delta)

def towards(lift, floor):
    if lift.position == floor:
        return True
    orientation = direction(lift.position, floor)
    return orientation == lift.direction

def max_distance(lift, floor, floor_cnt):
    apart = abs(lift.position - floor)
    if towards(lift, floor):
        return apart
    else:
        return 2 * (floor_cnt - 1) - apart

class Request:
    def __init__(self, from_f, to_f):
        self.from_f = from_f
        self.to_f = to_f
        self.waited = 0
        self.ridden = 0

    def waiting(self):
        self.waited += 1

    def riding(self):
        self.ridden += 1

    def goal(self):
        return self.to_f

    def show(self):
        if None == self:
            return '()'
        return '(%d->%d)' % (self.from_f, self.to_f)

class Lift:
    def __init__(self):
        self.position = 0
        self.passengers = []
        self.direction = 0

    def enter(self, request):
        self.passengers.append(request)

    def leave(self, really=False):
        leaving = []
        staying = []
        for passenger in self.passengers:
            which = leaving if passenger.to_f == self.position else staying
            which.append(passenger)
        if really:
            self.passengers = staying
        return leaving

    def step(self):
        if self.direction != 0:
            for passenger in self.passengers:
                passenger.riding()

class ElevatorControlSystemBase:
    def __init__(self, lift_cnt, floor_cnt, capacity):
        self.lift_cnt = lift_cnt
        self.floor_cnt = floor_cnt
        self.capacity = capacity
        self.lifts = [Lift() for i in range(lift_cnt)]
        self.through = []
        self.cycles = 0

    def dropoff(self, request):
        self.through.append(request)

    def step(self):
        for i, lift in enumerate(self.lifts):
            # Let passengers out
            left = lift.leave(True)
            # Update statistics for each leaving passenger
            for each in left:
                self.dropoff(each)
            # Run the logic
            self.step_lift(i, lift, left)
            # Update position
            lift.position += lift.direction
            # Update statistics for the riding passengers
            lift.step()
        # Update statistics for the waiting passengers
        self.step_waiting()
        # Update number of cycles
        self.cycles += 1

    def show(self):
        for i in range(self.floor_cnt - 1, -1, -1):
            line = ''
            for lift in self.lifts:
                if lift.position == i:
                    line += str(len(lift.passengers))
                else:
                    line += '.'
            print(line)
        lut = {-1: 'v', 0: '-', 1: '^'}
        print(''.join([lut[lift.direction] for lift in self.lifts]))
        print('')
        for i, lift in enumerate(self.lifts):
            print('L%d %s' % (i, ' '.join([request.show() for request in lift.passengers])))

    def stats(self, title):
        print('--------')
        print(title)
        print('Throughput: %f' % (len(self.through) / self.cycles))
        avg_wait = sum([request.waited for request in self.through]) / self.cycles
        std_wait = math.sqrt(sum([math.pow(request.waited - avg_wait, 2) for request in self.through]) / self.cycles)
        print('Average wait time: %f' % avg_wait)
        print('Std. dev. wait time: %f' % std_wait)
        avg_ride = sum([request.ridden for request in self.through]) / self.cycles
        std_ride = math.sqrt(sum([math.pow(request.ridden - avg_wait, 2) for request in self.through]) / self.cycles)
        print('Average ride time: %f' % avg_ride)
        print('Std. dev. ride time: %f' % std_ride)

class FCFS(ElevatorControlSystemBase):
    def __init__(self, lift_cnt, floor_cnt, capacity):
        ElevatorControlSystemBase.__init__(self, lift_cnt, floor_cnt, capacity)
        self.queue = collections.deque()
        self.assignments = [None for i in range(self.lift_cnt)]

    def pickup(self, request):
        self.queue.append(request)

    def step_lift(self, i, lift, left):
            assignment = self.assignments[i]
            if 0 == len(lift.passengers):
                if None == assignment:
                    if len(self.queue) > 0:
                        assignment = self.queue.popleft()
                        self.assignments[i] = assignment
                        lift.direction = direction(lift.position, assignment.from_f)
                    else:
                        lift.direction = 0
                else:
                    if lift.position == assignment.from_f:
                        lift.enter(assignment)
                        self.assignments[i] = None
                        lift.direction = direction(lift.position, assignment.to_f)

    def step_waiting(self):
        for request in self.queue:
            request.waiting()

    def show(self):
        ElevatorControlSystemBase.show(self)
        print('A %s' % ' '.join([Request.show(assignment) for assignment in self.assignments]))
        print('Q %s' % ' '.join([request.show() for request in self.queue]))

class ElevatorControlSystem(ElevatorControlSystemBase):
    def __init__(self, lift_cnt, floor_cnt, capacity):
        ElevatorControlSystemBase.__init__(self, lift_cnt, floor_cnt, capacity)
        self.queues = [[] for i in range(lift_cnt)]

    def pickup(self, request):
        pickup_floor = request.from_f
        goal_floor = request.to_f
        available = []
        # Sort by distance the lifts which are approaching and head in the same direction
        for i, lift in enumerate(self.lifts):
            if lift.direction == 0:
                available.append(i)
            else:
                direction = goal_floor - pickup_floor
                direction = int(direction / abs(direction))
                if direction == lift.direction:
                    if (direction == 1 and lift.position <= pickup_floor) or (direction == -1 and lift.position >= pickup_floor):
                        available.append(i)
        available = sorted(available, key=lambda i: abs(self.lifts[i].position - pickup_floor))
        # Otherwise sort all lifts by distance
        if len(available) == 0:
            available = sorted(range(self.lift_cnt), key=lambda i: max_distance(self.lifts[i], pickup_floor, self.floor_cnt))
        # Pick closest lift
        self.queues[available[0]].append(request)

    def step_lift(self, i, lift, left):
        # Let passengers in
        for request in self.queues[i][:]:
            direction = request.to_f - request.from_f
            direction = int(direction / abs(direction))
            if len(lift.passengers) == 0:
                direction = request.from_f - lift.position
                if direction == 0:
                    direction = request.to_f - request.from_f
                direction = int(direction / abs(direction))
                lift.direction = direction
            if request.from_f == lift.position:
                self.queues[i].remove(request)
                if direction == lift.direction and self.capacity - len(lift.passengers) > 0:
                    lift.passengers.append(request)
                else:
                    self.pickup(request)
        # If empty then set idle
        if len(lift.passengers) == 0 and len(self.queues[i]) == 0:
            lift.direction = 0

    def step_waiting(self):
        for queue in self.queues:
            for request in queue:
                request.waiting()

    def status(self):
        result = []
        for i, lift in enumerate(self.lifts):
            if lift.direction == 0:
                result.append((i, lift.position, lift.position))
            elif lift.direction == 1:
                goal = sorted(lift.passengers, key=lambda passenger: passenger.to_f, reverse=True)[0].to_f
            elif lift.direction == -1:
                goal = sorted(lift.passengers, key=lambda passenger: passenger.to_f)[0].to_f
        return result

    def update(self, id, position, goal):
        lift = self.lifts[id]
        lift.position = position
        direction = goal - position
        if direction != 0:
            direction = int(direction / abs(direction))
        lift.direction = direction

    def show(self):
        ElevatorControlSystemBase.show(self)
        for i, lift in enumerate(self.lifts):
            q = ','.join(['(%d,%d)' % (request.from_f, request.to_f) for request in self.queues[i]])
            print('Q%d %s' % (i, q))

def simulation(system, title, floor_cnt, request_probability, cycles=None):
    interactive = None == cycles
    while interactive or cycles > 0:
        if not interactive and cycles > 0:
            cycles -= 1
        if interactive:
            system.show()
        if random.random() < request_probability:
            pickup_floor = random.randint(0, floor_cnt - 1)
            goal_floor = random.randint(0, floor_cnt - 1)
            if pickup_floor != goal_floor:
                system.pickup(Request(pickup_floor, goal_floor))
        system.step()
        if interactive:
            input()
    system.stats(title)

if __name__ == "__main__":
    # Run unattended test to gather statistics
    lift_cnt = 4
    floor_cnt = 14
    capacity = 1
    request_probability = 1
    system = FCFS(lift_cnt, floor_cnt, capacity)
    simulation(system, 'FCFS', floor_cnt, request_probability, 5000)
    system = ElevatorControlSystem(lift_cnt, floor_cnt, capacity)
    simulation(system, 'Improved', floor_cnt, request_probability, 5000)
    # Run interactive test
    capacity = 4
    request_probability = 0.75
    system = ElevatorControlSystem(lift_cnt, floor_cnt, capacity)
    simulation(system, 'Interactive', floor_cnt, request_probability, None)
