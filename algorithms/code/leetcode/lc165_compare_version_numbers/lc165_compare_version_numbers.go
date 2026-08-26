// Package lc165 implements https://leetcode.com/problems/compare-version-numbers/
// #medium
package lc165

import (
	"strconv"
	"strings"
)

func compareVersion(version1 string, version2 string) int {
	parts1 := strings.Split(version1, ".")
	parts2 := strings.Split(version2, ".")
	for i := range max(len(parts1), len(parts2)) {
		level1 := 0
		if i < len(parts1) {
			level1, _ = strconv.Atoi(parts1[i])
		}
		level2 := 0
		if i < len(parts2) {
			level2, _ = strconv.Atoi(parts2[i])
		}
		if level1 < level2 {
			return -1
		}
		if level1 > level2 {
			return 1
		}
	}
	return 0
}
