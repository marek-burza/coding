package camelcase

import (
	"testing"
)

func TestExample(t *testing.T) {
	if 5 != Camelcase("saveChangesInTheEditor") {
		t.Errorf("Camelcase failed with saveChangesInTheEditor!")
	}
}
