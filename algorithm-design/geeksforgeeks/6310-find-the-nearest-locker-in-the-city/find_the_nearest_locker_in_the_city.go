package gfg6310

// https://web.archive.org/web/20170803130133/http://qa.geeksforgeeks.org/6310/find-the-nearest-locker-in-the-city

import (
	"math"
)

// Coordinates structure
type Coordinates struct {
	x int
	y int
}

var directions = [...]Coordinates{
	Coordinates{0, 1}, Coordinates{0, -1}, Coordinates{1, 0}, Coordinates{-1, 0},
}

func initCity(sizex int, sizey int) [][]uint64 {
	city := make([][]uint64, sizex)
	for x := range city {
		city[x] = make([]uint64, sizey)
	}
	return city
}

func fillCity(sizex int, sizey int, city *[][]uint64, value uint64) {
	for x := 0; x < sizex; x++ {
		for y := 0; y < sizey; y++ {
			(*city)[x][y] = value
		}
	}
}

func markLockers(sizex int, sizey int, city *[][]uint64, lockers *[]Coordinates) {
	for _, locker := range *lockers {
		(*city)[locker.x][locker.y] = 0
	}
}

func initBFS(lockers *[]Coordinates, queue *[]*Coordinates) {
	for index := range *lockers {
		locker := &(*lockers)[index]
		*queue = append(*queue, locker)
	}
}

func progressBFS(sizex int, sizey int, city *[][]uint64, queue *[]*Coordinates) {
	position := (*queue)[0]
	(*queue)[0] = nil
	*queue = (*queue)[1:]
	distance := (*city)[position.x][position.y] + 1
	for _, direction := range directions {
		x := position.x + direction.x
		y := position.y + direction.y
		if 0 <= x && x < sizex && 0 <= y && y < sizey && distance < (*city)[x][y] {
			(*city)[x][y] = distance
			*queue = append(*queue, &Coordinates{x, y})
		}
	}
}

// LockerDistances creates a city grid with distances to nearest lockers
func LockerDistances(sizex int, sizey int, lockers []Coordinates) [][]uint64 {
	if sizex < 0 || sizey < 0 {
		return nil
	}
	city := initCity(sizex, sizey)
	fillCity(sizex, sizey, &city, math.MaxUint64)
	markLockers(sizex, sizey, &city, &lockers)
	queue := make([]*Coordinates, 0)
	initBFS(&lockers, &queue)
	for len(queue) > 0 {
		progressBFS(sizex, sizey, &city, &queue)
	}
	return city
}
