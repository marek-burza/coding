package camelcase

import (
	"testing"
)

func TestExample(t *testing.T) {
	if Camelcase("saveChangesInTheEditor") != 5 {
		t.Errorf("Camelcase failed with saveChangesInTheEditor!")
	}
}
