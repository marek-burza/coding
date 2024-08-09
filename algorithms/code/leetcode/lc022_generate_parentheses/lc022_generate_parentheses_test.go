package lc022

import (
	"sort"
	"testing"
)

func generic(t *testing.T, expected []string, n int) {
	result := generateParenthesis(n)
	sort.Strings(result)
	sort.Strings(expected)
	var maximum = len(result)
	if maximum < len(expected) {
		maximum = len(expected)
	}
	for i := 0; i < maximum; i++ {
		if result[i] != expected[i] {
			t.Errorf("GenerateParenthesis - Expected %s, got %s!", expected[i], result[i])
		}
	}
}

func Test0(t *testing.T) {
	expected := []string{}
	generic(t, expected, 0)
}

func Test1(t *testing.T) {
	expected := []string{"()"}
	generic(t, expected, 1)
}

func Test2(t *testing.T) {
	expected := []string{"()()", "(())"}
	generic(t, expected, 2)
}

func Test3(t *testing.T) {
	expected := []string{"((()))", "(()())", "(())()", "()(())", "()()()"}
	generic(t, expected, 3)
}

func Test4(t *testing.T) {
	expected := []string{
		"(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())", "(()())()", "(())(())",
		"(())()()", "()((()))", "()(()())", "()(())()", "()()(())", "()()()()"}
	generic(t, expected, 4)
}
