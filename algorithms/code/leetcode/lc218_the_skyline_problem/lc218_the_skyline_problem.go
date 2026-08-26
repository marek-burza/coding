// Package lc218 implements https://leetcode.com/problems/the-skyline-problem/
package lc218

import (
	"container/heap"
	"maps"
	"slices"
)

type building struct {
	left   int
	right  int
	height int
}

type view []*building

func (v view) Len() int           { return len(v) }
func (v view) Less(i, j int) bool { return v[i].height > v[j].height }
func (v view) Swap(i, j int)      { v[i], v[j] = v[j], v[i] }
func (v *view) Push(value any)    { *v = append(*v, value.(*building)) }
func (v *view) Pop() any {
	old := *v
	value := old[len(old)-1]
	*v = old[:len(old)-1]
	return value
}

func getSkyline(buildings [][]int) [][]int {
	var skyline [][]int
	if len(buildings) == 0 {
		return skyline
	}
	// Build list of spots
	spots := make(map[int][]*building)
	for _, current := range buildings {
		if current[0] == current[1] {
			continue
		}
		entry := &building{current[0], current[1], current[2]}
		for _, spot := range current[0:2] {
			spots[spot] = append(spots[spot], entry)
		}
	}
	sortedSpots := slices.Sorted(maps.Keys(spots))
	// Prepare view
	ground := &building{0, sortedSpots[len(sortedSpots)-1], 0}
	seen := &view{}
	*seen = append(*seen, ground)
	// Check all spots and build skyline
	current := 0
	for _, at := range sortedSpots {
		for _, buildingObj := range spots[at] {
			if at == buildingObj.left {
				// Building entering the view
				heap.Push(seen, buildingObj)
			} else {
				// Building leaving the view
				index := slices.Index(*seen, buildingObj)
				heap.Remove(seen, index)
			}
		}
		following := (*seen)[0].height
		if current != following {
			point := []int{at, following}
			skyline = append(skyline, point)
		}
		current = following
	}
	return skyline
}
