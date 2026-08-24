// Package lc093 implements https://leetcode.com/problems/restore-ip-addresses/
// #medium
package lc093

import (
	"strconv"
	"strings"
)

func partial(s string, count int, ip *[]string, listed *[]string) {
	if len(s) < count || (s[0] == '0' && count > 1) {
		return
	}
	prefix := s[0:count]
	part, _ := strconv.Atoi(prefix)
	if part <= 255 { // 0 <= part <= 255
		*ip = append(*ip, prefix)
		restore(s[count:], ip, listed)
		*ip = (*ip)[:len(*ip)-1]
	}
}

func restore(s string, ip *[]string, listed *[]string) {
	if len(*ip) >= 4 {
		if len(s) == 0 {
			var str strings.Builder
			for i := range 4 {
				if i > 0 {
					str.WriteString(".")
				}
				str.WriteString((*ip)[i])
			}
			*listed = append(*listed, str.String())
		}
	} else {
		partial(s, 1, ip, listed)
		partial(s, 2, ip, listed)
		partial(s, 3, ip, listed)
	}
}

func restoreIPAddresses(s string) []string {
	var ip []string
	var listed []string
	restore(s, &ip, &listed)
	return listed
}
