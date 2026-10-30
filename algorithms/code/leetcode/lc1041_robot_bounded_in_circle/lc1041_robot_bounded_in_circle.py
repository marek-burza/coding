class Solution:
    def isRobotBounded(self, instructions: str) -> bool:
        orientation = 0
        x = y = 0
        for instruction in instructions:
            if instruction == "G":
                x += {0: 0, 90: 1, 180: 0, 270: -1}[orientation]
                y += {0: 1, 90: 0, 180: -1, 270: 0}[orientation]
            elif instruction == "R":
                orientation = (orientation + 90) % 360
            elif instruction == "L":
                orientation = (orientation + 270) % 360
        return not (orientation == 0 and (x != 0 or y != 0))


def test_example_1() -> None:
    assert Solution().isRobotBounded("GGLLGG")


def test_example_2() -> None:
    assert not Solution().isRobotBounded("GG")


def test_example_3() -> None:
    assert Solution().isRobotBounded("GL")


def test_g() -> None:
    assert not Solution().isRobotBounded("G")


def test_l() -> None:
    assert Solution().isRobotBounded("L")


def test_rgrg() -> None:
    assert Solution().isRobotBounded("RGRG")
