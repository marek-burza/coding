package gfg6310

// Coordinates structure
type Coordinates struct {
	x int
	y int
}

func initCity(sizex int, sizey int) [][]uint64 {
	city := make([][]uint64, sizex)
	for x := range city {
		city[x] = make([]uint64, sizey)
	}
	return city
}

func fillCity(sizex int, sizey int, city [][]uint64, value uint64) {
	for x := 0; x < sizex; x++ {
		for y := 0; y < sizey; y++ {
			city[x][y] = value
		}
	}
}

func markLockers(sizex int, sizey int, city [][]uint64, lockers []Coordinates) {
	for locker := range lockers {
		city[locker.x][locker.y] = 0
	}
}

// LockerDistances creates a city grid with distances to nearest lockers
func LockerDistances(sizex int, sizey int, lockers []Coordinates) [][]uint64 {
	if sizex < 0 || sizey < 0 {
		return nil
	}
	city := initCity(sizex, sizey)
	return city
}
