#!/usr/bin/env python3

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

    def show(self):
        if None == self:
            return '()'
        return '(%d->%d)' % (self.from_f, self.to_f)

class Lift:
    def __init__(self):
        self.position = 0
        self.passengers = []
        self.direction = 0

class ElevatorControlSystemBase:
    def __init__(self, lift_cnt, floor_cnt, capacity):
        self.lift_cnt = lift_cnt
        self.floor_cnt = floor_cnt
        self.capacity = capacity
        self.lifts = [Lift() for i in range(lift_cnt)]

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

    def step(self):
        for i, lift in enumerate(self.lifts):
            # Let passengers out
            lift.passengers = [passenger for passenger in lift.passengers if passenger.to_f != lift.position]
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
            # Update position
            lift.position += lift.direction

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

def simulation(lift_cnt, floor_cnt, capacity, request_probability):
    system = ElevatorControlSystem(lift_cnt, floor_cnt, capacity)
    while True:
        system.show()
        if random.random() < request_probability:
            pickup_floor = random.randint(0, floor_cnt - 1)
            goal_floor = random.randint(0, floor_cnt - 1)
            if pickup_floor != goal_floor:
                system.pickup(Request(pickup_floor, goal_floor))
        system.step()
        input()

if __name__ == "__main__":
    # Example
    simulation(4, 7, 4, 0.75)
