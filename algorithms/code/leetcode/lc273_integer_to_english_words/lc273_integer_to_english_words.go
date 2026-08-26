// Package lc273 implements https://leetcode.com/problems/integer-to-english-words/
package lc273

var magnitude = []string{
	"",
	" Thousand",
	" Million",
	" Billion",
	" Trillion",
	" Quadrillion",
	" Quintillion",
	" Sextillion",
	" Septillion",
	" Octillion",
	" Nonillion",
}

var tens = []string{
	"",
	"Ten",
	"Twenty",
	"Thirty",
	"Forty",
	"Fifty",
	"Sixty",
	"Seventy",
	"Eighty",
	"Ninety",
}

var digits = []string{
	"",
	"One",
	"Two",
	"Three",
	"Four",
	"Five",
	"Six",
	"Seven",
	"Eight",
	"Nine",
	"Ten",
	"Eleven",
	"Twelve",
	"Thirteen",
	"Fourteen",
	"Fifteen",
	"Sixteen",
	"Seventeen",
	"Eighteen",
	"Nineteen",
}

func tripleToWords(i int) string {
	result := ""
	if i >= 100 {
		result += digits[i/100]
		result += " Hundred"
		i %= 100
	}
	if i != 0 && len(result) != 0 {
		result += " "
	}
	if i <= 19 {
		result += digits[i]
	} else {
		result += tens[i/10]
		i %= 10
		if i != 0 {
			result += " "
			result += digits[i]
		}
	}
	return result
}

func numberToWords(i int) string {
	if i == 0 {
		return "Zero"
	}
	result := ""
	position := 0
	for i != 0 {
		vocalization := tripleToWords(i % 1000)
		if len(vocalization) != 0 {
			if len(result) != 0 {
				result = " " + result
			}
			result = magnitude[position] + result
			result = vocalization + result
		}
		i /= 1000
		position++
	}
	return result
}
