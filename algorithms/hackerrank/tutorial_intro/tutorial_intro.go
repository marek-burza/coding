package tutorialintro

// https://www.hackerrank.com/challenges/tutorial-intro

// IntroTutorial - implements the solution to the problem
func IntroTutorial(v int32, arr []int32) int32 {
	a := 0
	z := len(arr) - 1
	for a <= z {
		m := (a + z) >> 1
		if arr[m] == v {
			return int32(m)
		}
		if arr[m] < v {
			a = m + 1
		}
		if arr[m] > v {
			z = m - 1
		}
	}
	return -1
}
