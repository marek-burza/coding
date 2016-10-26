#!/usr/bin/env python3

import random

class Passenger:
    def __init__(self, from_f, to_f):
        self.from_f = from_f
        self.to_f = to_f

class Lift:
    def __init__(self):
        self.position = 0
        self.passengers = []
        self.direction = 0
        self.queue = []

class ElevatorControlSystem:
    def __init__(self, lift_cnt, floor_cnt, capacity):
        self.lift_cnt = lift_cnt
        self.floor_cnt = floor_cnt
        self.capacity = capacity
        self.lifts = [Lift() for i in range(lift_cnt)]

    def pickup(self, pickup_floor, goal_floor):
        available = []
        # Sort by distance the lifts which are approaching and head in the same direction
        for lift in self.lifts:
            if lift.direction == 0:
                available.append(lift)
            else:
                direction = goal_floor - pickup_floor
                direction = int(direction / abs(direction))
                if direction == lift.direction:
                    if (direction == 1 and lift.position <= pickup_floor) or (direction == -1 and lift.position >= pickup_floor):
                        available.append(lift)
        available = sorted(available, key=lambda lift: abs(lift.position - pickup_floor))
        # Otherwise sort all lifts by distance
        if len(available) == 0:
            available = sorted(self.lifts, key=lambda lift: abs(lift.position - pickup_floor))
        # Pick closest lift
        available[0].queue.append(Passenger(pickup_floor, goal_floor))

    def step(self):
        for lift in self.lifts:
            # Let passengers out
            lift.passengers = [passenger for passenger in lift.passengers if passenger.to_f != lift.position]
            # Let passengers in
            for request in lift.queue[:]:
                direction = request.to_f - request.from_f
                direction = int(direction / abs(direction))
                if len(lift.passengers) == 0:
                    direction = request.from_f - lift.position
                    if direction == 0:
                        direction = request.to_f - request.from_f
                    direction = int(direction / abs(direction))
                    lift.direction = direction
                if request.from_f == lift.position:
                    lift.queue.remove(request)
                    if direction == lift.direction and self.capacity - len(lift.passengers) > 0:
                        lift.passengers.append(request)
                    else:
                        self.pickup(request.from_f, request.to_f)
            # If empty then set idle
            if len(lift.passengers) == 0 and len(lift.queue) == 0:
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
        for i in range(self.floor_cnt - 1, -1, -1):
            line = ''
            for lift in self.lifts:
                if lift.position == i:
                    line += str(len(lift.passengers))
                else:
                    line += '.'
            print(line)
        print('')
        for lift in self.lifts:
            q = ','.join(['(%d,%d)' % (request.from_f, request.to_f) for request in lift.queue])
            l = ','.join(['(%d,%d)' % (request.from_f, request.to_f) for request in lift.passengers])
            print('L %s Q %s' % (l, q))

def simulation(lift_cnt, floor_cnt, capacity, request_probability):
    system = ElevatorControlSystem(lift_cnt, floor_cnt, capacity)
    while True:
        system.show()
        if random.random() < request_probability:
            pickup_floor = random.randint(0, floor_cnt - 1)
            goal_floor = random.randint(0, floor_cnt - 1)
            if pickup_floor != goal_floor:
                system.pickup(pickup_floor, goal_floor)
        system.step()
        input()

if __name__ == "__main__":
    # Example
    simulation(4, 7, 4, 0.75)
