package kangaroo

// https://www.hackerrank.com/challenges/kangaroo

// Kangaroo - implements the solution to the problem
func Kangaroo(x1 int32, v1 int32, x2 int32, v2 int32) string {
	if v1 == v2 {
		if x1 == x2 {
			return "YES"
		}
		return "NO"
	}
	if 0 == (x2-x1)%(v2-v1) && 0 > (x2-x1)/(v2-v1) {
		return "YES"
	}
	return "NO"
}
