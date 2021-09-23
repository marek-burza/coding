package cavitymap

// https://www.hackerrank.com/challenges/taum-and-bday

// TaumBday - implements the solution to the problem
func TaumBday(b int32, w int32, bc int32, wc int32, z int32) int64 {
	var xAdjusted, yAdjusted int64
	if bc <= wc+z {
		xAdjusted = int64(bc)
	} else {
		xAdjusted = int64(wc + z)
	}
	if wc <= bc+z {
		yAdjusted = int64(wc)
	} else {
		yAdjusted = int64(bc + z)
	}
	return int64(b)*xAdjusted + int64(w)*yAdjusted
}
