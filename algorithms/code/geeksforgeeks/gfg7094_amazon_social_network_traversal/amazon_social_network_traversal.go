package gfg7094

// https://web.archive.org/web/20180317031648/http://qa.geeksforgeeks.org/7094/amazon-social-network-traversal

import (
	"sort"
)

var exists = struct{}{}

// Network structure
type Network struct {
	friendships map[string][]string
	attendances map[string][]string
}

func (network *Network) GetDirectFriendsForUser(user string) []string {
	return (*network).friendships[user]
}

func (network *Network) GetAttendedCoursesForUser(user string) []string {
	return (*network).attendances[user]
}

func sliceToSet(slice []string) map[string]struct{} {
	set := make(map[string]struct{})
	for _, item := range slice {
		set[item] = exists
	}
	return set
}

func setToSlice(set map[string]struct{}) []string {
	slice := make([]string, 0)
	for item := range set {
		slice = append(slice, item)
	}
	return slice
}

func (network *Network) getFriendsOfFriends(user string) []string {
	friends := (*network).GetDirectFriendsForUser(user)
	circleSet := make(map[string]struct{})
	for _, friend := range friends {
		circleSet[friend] = exists
		for _, second := range (*network).GetDirectFriendsForUser(friend) {
			circleSet[second] = exists
		}
	}
	delete(circleSet, user)
	circle := setToSlice(circleSet)
	return circle
}

func (network *Network) countCircleCoursesWithoutOwn(circle []string, own map[string]struct{}) map[string]int {
	countedCourses := make(map[string]int)
	for _, user := range circle {
		for _, course := range (*network).GetAttendedCoursesForUser(user) {
			_, attended := own[course]
			if attended {
				continue
			}
			count, counted := countedCourses[course]
			if !counted {
				count = 0
			}
			countedCourses[course] = count + 1
		}
	}
	return countedCourses
}

func orderCoursesByCount(countedCourses map[string]int) []string {
	orderedCourses := make([]string, 0)
	for course := range countedCourses {
		orderedCourses = append(orderedCourses, course)
	}
	sort.Slice(orderedCourses, func(i, j int) bool { return countedCourses[orderedCourses[i]] > countedCourses[orderedCourses[j]] })
	return orderedCourses
}

// GetRankedCourses recommends courses attended by 2nd level network sorted by popularity
func (network *Network) GetRankedCourses(user string) []string {
	circle := (*network).getFriendsOfFriends(user)
	own := sliceToSet((*network).GetAttendedCoursesForUser(user))
	countedCourses := (*network).countCircleCoursesWithoutOwn(circle, own)
	orderedCourses := orderCoursesByCount(countedCourses)
	return orderedCourses
}
