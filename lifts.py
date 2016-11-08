#!/usr/bin/env python3

import collections
import math
import random
import sys
import unittest

def direction(origin, goal):
    if goal == origin:
        return 0
    elif goal < origin:
        return -1
    elif goal > origin:
        return 1
    return None

def towards(lift, floor):
    if lift.floor == floor:
        return True
    orientation = direction(lift.floor, floor)
    return orientation == lift.direction

def max_distance(lift, floor, floor_cnt):
    apart = abs(lift.floor - floor)
    if towards(lift, floor):
        return apart
    else:
        return 2 * (floor_cnt - 1) - apart

class Request:
    def __init__(self, origin, goal):
        self.origin = origin
        self.goal = goal
        self.waited = 0
        self.ridden = 0

    def waiting(self):
        self.waited += 1

    def riding(self):
        self.ridden += 1

    def goal(self):
        return self.goal

    def show(self):
        if None == self:
            return '()'
        return '(%d->%d)' % (self.origin, self.goal)

class Lift:
    def __init__(self, capacity):
        self.capacity = capacity
        self.floor = 0
        self.passengers = []
        self.direction = 0

    def enter(self, request):
        self.passengers.append(request)

    def leave(self, really=False):
        leaving = []
        staying = []
        for passenger in self.passengers:
            which = leaving if passenger.goal == self.floor else staying
            which.append(passenger)
        if really:
            self.passengers = staying
        return leaving

    def empty(self):
        return not self.passengers

    def full(self):
        return len(self.passengers) >= self.capacity

    def step(self):
        if self.direction != 0:
            for passenger in self.passengers:
                passenger.riding()

class ElevatorControlSystemBase:
    def __init__(self, lift_cnt, floor_cnt, capacity):
        self.lift_cnt = lift_cnt
        self.floor_cnt = floor_cnt
        self.capacity = capacity
        self.lifts = [Lift(capacity) for i in range(lift_cnt)]
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
            # Update
            lift.floor += lift.direction
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
                if lift.floor == i:
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
            if lift.empty():
                if None == assignment:
                    if self.queue:
                        assignment = self.queue.popleft()
                        self.assignments[i] = assignment
                        lift.direction = direction(lift.floor, assignment.origin)
                    else:
                        lift.direction = 0
                else:
                    if lift.floor == assignment.origin:
                        lift.enter(assignment)
                        self.assignments[i] = None
                        lift.direction = direction(lift.floor, assignment.goal)

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
        origin = request.origin
        goal = request.goal
        available = []
        # Sort by distance the lifts which are approaching and head in the same direction
        for i, lift in enumerate(self.lifts):
            idle = lift.direction == 0
            along = direction(origin, goal) == lift.direction
            approaching = towards(lift, origin)
            if idle or (along and towards):
                available.append(i)
        available = sorted(available, key=lambda i: abs(self.lifts[i].floor - origin))
        # Otherwise sort all lifts by distance
        if len(available) == 0:
            available = sorted(range(self.lift_cnt), key=lambda i: max_distance(self.lifts[i], origin, self.floor_cnt))
        # Pick closest lift
        self.queues[available[0]].append(request)

    def step_lift(self, i, lift, left):
        # Let passengers in
        for request in self.queues[i][:]:
            heading_direction = direction(request.origin, request.goal)
            if lift.empty():
                if request.origin != lift.floor:
                    heading_direction = direction(lift.floor, request.origin)
                lift.direction = heading_direction
            if request.origin == lift.floor:
                self.queues[i].remove(request)
                if heading_direction == lift.direction and not lift.full():
                    lift.passengers.append(request)
                else:
                    self.pickup(request)
        # If empty then set idle
        if lift.empty() and len(self.queues[i]) == 0:
            lift.direction = 0

    def step_waiting(self):
        for queue in self.queues:
            for request in queue:
                request.waiting()

    def status(self):
        result = []
        for i, lift in enumerate(self.lifts):
            if lift.direction == 0:
                result.append((i, lift.floor, lift.floor))
            elif lift.direction == 1:
                goal = sorted(lift.passengers, key=lambda passenger: passenger.goal, reverse=True)[0].goal
            elif lift.direction == -1:
                goal = sorted(lift.passengers, key=lambda passenger: passenger.goal)[0].goal
        return result

    def update(self, id, floor, goal):
        lift = self.lifts[id]
        lift.floor = floor
        direction = goal - floor
        if direction != 0:
            direction = int(direction / abs(direction))
        lift.direction = direction

    def show(self):
        ElevatorControlSystemBase.show(self)
        for i, lift in enumerate(self.lifts):
            q = ' '.join([request.show() for request in self.queues[i]])
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

class Tests(unittest.TestCase):
    def test_randomized(self):
        lift_cnt = 4
        floor_cnt = 14
        capacity = 1
        request_probability = 1
        system = FCFS(lift_cnt, floor_cnt, capacity)
        simulation(system, 'FCFS', floor_cnt, request_probability, 5000)
        system = ElevatorControlSystem(lift_cnt, floor_cnt, capacity)
        simulation(system, 'Improved', floor_cnt, request_probability, 5000)
        self.assertTrue(True)

if __name__ == "__main__":
    if len(sys.argv) > 1:
        sys.argv = [sys.argv[0]]
        # Run unattended test to gather statistics
        unittest.main()
    else:
        # Run interactive test
        lift_cnt = 4
        floor_cnt = 14
        capacity = 4
        request_probability = 0.75
        system = ElevatorControlSystem(lift_cnt, floor_cnt, capacity)
        simulation(system, 'Interactive', floor_cnt, request_probability, None)
