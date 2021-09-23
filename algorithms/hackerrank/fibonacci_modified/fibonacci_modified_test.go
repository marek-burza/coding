package fibonaccimodified

import (
	"math/big"
	"testing"
)

func TestExample1(t *testing.T) {
	if 0 != new(big.Int).SetInt64(5).Cmp(FibonacciModified(0, 1, 5)) {
		t.Errorf("FibonacciModified failed with 0, 1, 5!")
	}
}

func TestExample2(t *testing.T) {
	expected, _ := new(big.Int).SetString("84266613096281243382112", 10)
	if 0 != expected.Cmp(FibonacciModified(0, 1, 10)) {
		t.Errorf("FibonacciModified failed with 0, 1, 10!")
	}
}
