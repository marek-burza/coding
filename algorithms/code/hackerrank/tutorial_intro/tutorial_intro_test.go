package tutorialintro

import (
	"testing"
)

func TestExample(t *testing.T) {
	if IntroTutorial(4, []int32{1, 4, 5, 7, 9, 12}) != 1 {
		t.Errorf("IntroTutorial failed with 4 and 1, 4, 5, 7, 9, 12!")
	}
}

func TestAnother(t *testing.T) {
	if IntroTutorial(7, []int32{1, 4, 5, 7, 9, 12}) != 3 {
		t.Errorf("IntroTutorial failed with 7 and 1, 4, 5, 7, 9, 12!")
	}
}

func TestInvalid(t *testing.T) {
	if IntroTutorial(20, []int32{1, 4, 5, 7, 9, 12}) != -1 {
		t.Errorf("IntroTutorial failed with 20 and 1, 4, 5, 7, 9, 12!")
	}
}
