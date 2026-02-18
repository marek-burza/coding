// Package acmicpcteam implements https://www.hackerrank.com/challenges/acm-icpc-team
package acmicpcteam

// AcmTeam - looks for highest topic coverage and its incidence
func AcmTeam(topic []string) []int32 {
	n := len(topic)
	m := len(topic[0])
	coverageCount := 0
	teamsCount := 0
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			count := 0
			for k := range m {
				if topic[i][k] == '1' || topic[j][k] == '1' {
					count++
				}
			}
			if count > coverageCount {
				coverageCount = count
				teamsCount = 1
			} else if count == coverageCount {
				teamsCount++
			}
		}
	}
	return []int32{int32(coverageCount), int32(teamsCount)}
}
