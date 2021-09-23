package tutorialintro

// https://www.hackerrank.com/challenges/plus-minus

// PlusMinus - implements the solution to the problem
func PlusMinus(arr []int32) []float64 {
	n := float64(len(arr))
	counts := []float64{0.0, 0.0, 0.0}
	for _, value := range arr {
		if value > 0 {
			counts[0]++
		} else if value < 0 {
			counts[1]++
		} else {
			counts[2]++
		}
	}
	counts[0] /= n
	counts[1] /= n
	counts[2] /= n
	return counts
}
