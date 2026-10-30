class Solution:
    def isRobotBounded(self, instructions: str) -> bool:
        orientation = x = y = 0
        for instruction in instructions:
            match instruction:
                case "G":
                    x += [0, 1, 0, -1][orientation]
                    y += [1, 0, -1, 0][orientation]
                case "R":
                    orientation = (orientation + 1) % 4
                case "L":
                    orientation = (orientation + 3) % 4
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
