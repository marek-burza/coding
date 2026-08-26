// Package lc372 implements https://leetcode.com/problems/super-pow/
// #medium
package lc372

const modulo1337 = 1337

func findPowerLoop(value int) []int {
	var modulos []int
	lut := make([]bool, modulo1337)
	current := value
	for !lut[current] {
		lut[current] = true
		modulos = append(modulos, current)
		current = (current * value) % modulo1337
	}
	return modulos
}

func moduloOf(dividend []int, divisor int) int {
	length := len(dividend)
	result := 0
	for i := range length {
		result = (result*10 + dividend[i]) % divisor
	}
	return result
}

func superPow(a int, b []int) int {
	// Assume: a = (1337 * n + m) where 0 <= m < 1337
	// Then: a^b mod 1337 = (1337 * n + m)^n mod 1337 == m^b mod 1337
	// This multiplication will cycle through certain 'digits' of base 1337
	// You can search for the loop by iterating
	m := a % modulo1337
	modulos := findPowerLoop(m)
	// Get rid of loops from the power
	length := len(modulos)
	index := moduloOf(b, length)
	// Look-up the power modulo
	index = (index - 1 + length) % length
	return modulos[index]
}
