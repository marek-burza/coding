package leetcode.lc273_integer_to_english_words;

/**
 * https://leetcode.com/problems/integer-to-english-words/ #hard
 */
public final class LC273IntegerToEnglishWords {
    private static final String[] MAGNITUDE = { "", " Thousand", " Million", " Billion", " Trillion", " Quadrillion",
            " Quintillion", " Sextillion", " Septillion", " Octillion", " Nonillion" };

    private static final String[] TENS = { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
            "Eighty", "Ninety" };

    private static final String[] DIGITS = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen" };

    private String tripleToWords(final int iValue) {
        int i = iValue;
        StringBuilder result = new StringBuilder();
        if (i >= 100) {
            result.append(DIGITS[i / 100]);
            result.append(" Hundred");
            i %= 100;
        }
        if (0 != i && result.length() != 0) {
            result.append(" ");
        }
        if (i <= 19) {
            result.append(DIGITS[i]);
        } else {
            result.append(TENS[i / 10]);
            i %= 10;
            if (0 != i) {
                result.append(" ");
                result.append(DIGITS[i]);
            }
        }
        return result.toString();
    }

    public String numberToWords(final int iValue) {
        int i = iValue;
        if (i == 0) {
            return "Zero";
        }
        StringBuilder result = new StringBuilder();
        int position = 0;
        while (0 != i) {
            String vocalization = tripleToWords(i % 1000);
            if (vocalization.length() != 0) {
                if (result.length() != 0) {
                    result.insert(0, " ");
                }
                result.insert(0, MAGNITUDE[position]);
                result.insert(0, vocalization);
            }
            i /= 1000;
            position++;
        }
        return result.toString();
    }
}
package leetcode.lc273_integer_to_english_words;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC273IntegerToEnglishWordsTests {
    @Test
    public void test123() {
        assertEquals("One Hundred Twenty Three", new LC273IntegerToEnglishWords().numberToWords(123));
    }

    @Test
    public void test12345() {
        assertEquals("Twelve Thousand Three Hundred Forty Five", new LC273IntegerToEnglishWords().numberToWords(12345));
    }

    @Test
    public void test1234567() {
        assertEquals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven",
                new LC273IntegerToEnglishWords().numberToWords(1234567));
    }

    @Test
    public void test91() {
        assertEquals("Ninety One", new LC273IntegerToEnglishWords().numberToWords(91));
    }

    @Test
    public void test19() {
        assertEquals("Nineteen", new LC273IntegerToEnglishWords().numberToWords(19));
    }

    @Test
    public void test100() {
        assertEquals("One Hundred", new LC273IntegerToEnglishWords().numberToWords(100));
    }

    @Test
    public void test0() {
        assertEquals("Zero", new LC273IntegerToEnglishWords().numberToWords(0));
    }

    @Test
    public void test1000() {
        assertEquals("One Thousand", new LC273IntegerToEnglishWords().numberToWords(1000));
    }

    @Test
    public void test20() {
        assertEquals("Twenty", new LC273IntegerToEnglishWords().numberToWords(20));
    }
}
```rust
#[derive(Debug)]
enum Scale {
    None,
    Billion,
    Million,
    Thousand,
}

fn digit_to_word(i: u32) -> String {
    let mut word = match i {
        0 => "Zero".to_string(),
        _ if i < 10 => format!("{} One", DIGITS[i as usize]),
        _ if i < 20 => format!("{} Ten", TENS[i / 10]),
        _ => format!("{} {}", TENS[i / 10], DIGITS[(i - (i / 10) * 10) as usize]),
    };

    word
}

fn triple_to_word(i: u32) -> String {
    let mut result = String::new();
    if i >= 100 {
        result.push_str(&format!("{} Hundred", DIGITS[i / 100]));
        i %= 100;
    }
    if !result.is_empty() && i > 0 {
        result.push(' ');
    }

    match i {
        11 => word,
        12 => format!("{} Twelve", TENS[1]),
        13 => format!("{} Thirteen", TENS[1]),
        14 => format!("{} Fourteen", TENS[1]),
        15 => format!("{} Fifteen", TENS[1]),
        16 => format!("{} Sixteen", TENS[1]),
        17 => format!("{} Seventeen", TENS[1]),
        18 => format!("{} Eighteen", TENS[1]),
        19 => word,
        _ if i < 20 => digit_to_word(i),
        _ => {
            result.push_str(&format!("{} {}", TENS[i / 10], digit_to_word(i % 10)));
        }
    }
}

fn number_to_words(mut value: u32) -> String {
    let mut result = String::new();
    let mut scale = Scale::None;

    while value > 0 {
        let triple = value % 1000;
        match &scale {
            Scale::Billion => {
                if !triple_to_word(triple).is_empty() {
                    result.push_str(&format!("{} Billion", MAGNITUDE[4]));
                    scale = Scale::Million;
                } else {
                    break;
                }
            }
            Scale::Million => {
                if !triple_to_word(triple).is_empty() {
                    result.push_str(&format!(" {} Million", MAGNITUDE[3]));
                    scale = Scale::Thousand;
                } else {
                    value /= 1000;
                }
            }
            Scale::Thousand => {
                if !triple_to_word(triple).is_empty() {
                    result.push_str(&format!(" Thousand", MAGNITUDE[2]));
                    scale = Scale::None;
                } else {
                    value /= 1000;
                }
            }
            Scale::None => {},
        }

        match &scale {
            Scale::None => {},
            _ => {
                if !triple_to_word(triple).is_empty() {
                    result.push_str(&format!(" {}", triple_to_word(triple)));
                } else {
                    value /= 1000;
                }
            }
        }
    }

    result
}

const DIGITS: [String; 10] = [
    "".to_string(),
    "One".to_string(),
    "Two".to_string(),
    "Three".to_string(),
    "Four".to_string(),
    "Five".to_string(),
    "Six".to_string(),
    "Seven".to_string(),
    "Eight".to_string(),
    "Nine".to_string(),
];
const TENS: [String; 10] = [
    "".to_string(),
    "Ten".to_string(),
    "Twenty".to_string(),
    "Thirty".to_string(),
    "Forty".to_string(),
    "Fifty".to_string(),
    "Sixty".to_string(),
    "Seventy".to_string(),
    "Eighty".to_string(),
    "Ninety".to_string(),
];

const MAGNITUDE: [String; 10] = [
    "".to_string(),
    "Thousand".to_string(),
    "Million".to_string(),
    "Billion".to_string(),
    "Trillion".to_string(),
    "Quadrillion".to_string(),
    "Quintillion".to_string(),
    "Sextillion".to_string(),
    "Septillion".to_string(),
    "Octillion".to_string(),
];
```