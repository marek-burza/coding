// Package lc187 implements https://leetcode.com/problems/repeated-dna-sequences/
// #medium
package lc187

import "strings"

func compress(nucleotide byte) int {
	return map[byte]int{'A': 0, 'C': 1, 'G': 2, 'T': 3}[nucleotide]
}

func encode(sequence int, compressed int) int {
	return (compressed << 18) | (sequence >> 2)
}

func decode(sequence int) string {
	var decoded strings.Builder
	for range 10 {
		nucleotide := sequence & 0x3
		decoded.WriteByte(map[int]byte{0: 'A', 1: 'C', 2: 'G', 3: 'T'}[nucleotide])
		sequence >>= 2
	}
	return decoded.String()
}

func findRepeatedDNASequences(s string) []string {
	var listed []string
	if len(s) < 10 {
		return listed
	}
	seen := make(map[int]struct{})
	now := 0
	for i := range 9 {
		now = encode(now, compress(s[i]))
	}
	for i := 9; i < len(s); i++ {
		now = encode(now, compress(s[i]))
		if _, found := seen[now|0xFFF00000]; found {
			continue
		}
		if _, found := seen[now]; found {
			delete(seen, now)
			seen[now|0xFFF00000] = struct{}{}
			continue
		}
		seen[now] = struct{}{}
	}
	for sequence := range seen {
		if sequence&0x80000000 != 0 {
			listed = append(listed, decode(sequence&0xFFFFF))
		}
	}
	return listed
}
