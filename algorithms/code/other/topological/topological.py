def topo_sort(tasks: list[tuple[str, list[str]]]) -> list[str] | None:
    lut = {key: set(deps) for key, deps in tasks}
    result = []
    visited: set[str] = set()
    while lut:
        usable = [key for key in lut if not (lut[key] - visited)]
        if not usable:
            return None
        result.extend(usable)
        for key in usable:
            del lut[key]
            visited.add(key)
    return result


def topo_sort_parallel(
    tasks: list[tuple[str, list[str]]],
) -> list[str | tuple[str, ...]] | None:
    lut = {key: set(deps) for key, deps in tasks}
    result = []
    visited: set[str] = set()
    while lut:
        usable = [key for key in lut if not (lut[key] - visited)]
        if not usable:
            return None
        result.append(usable[0] if len(usable) == 1 else tuple(usable))
        for key in usable:
            del lut[key]
            visited.add(key)
    return result


LINEAR = [("a", []), ("b", ["a"]), ("c", ["b"]), ("d", ["c"])]
BRANCHING = [("d", ["b", "c"]), ("b", ["a"]), ("c", ["a"]), ("a", [])]
INDEPENDENT = [("a", []), ("b", []), ("c", ["a"]), ("d", ["b"])]
DIAMOND = [("a", []), ("b", ["a"]), ("c", ["a"]), ("d", ["b", "c"]), ("e", ["d"])]

NO_CYCLE = [("c", ["b"]), ("b", ["a"]), ("a", [])]
SIMPLE_CYCLE = [("a", ["b"]), ("b", ["a"])]
SELF_LOOP = [("a", []), ("b", ["b"]), ("c", ["a"])]

PARALLEL_LINEAR = [("a", []), ("b", ["a"]), ("c", ["b"])]
PARALLEL_DIAMOND = [("a", []), ("b", ["a"]), ("c", ["a"]), ("d", ["b", "c"])]
PARALLEL_WIDE = [("a", []), ("b", []), ("c", []), ("d", ["a", "b", "c"])]


class TestCode:
    def test_linear(self) -> None:
        result = topo_sort(LINEAR)
        assert result == ["a", "b", "c", "d"]

    def test_branching(self) -> None:
        result = topo_sort(BRANCHING)
        assert result == ["a", "b", "c", "d"] or result == ["a", "c", "b", "d"]

    def test_independent(self) -> None:
        result = topo_sort(INDEPENDENT)
        assert result == ["a", "b", "c", "d"] or result == ["a", "b", "c", "d"]

    def test_diamond(self) -> None:
        result = topo_sort(DIAMOND)
        assert result == ["a", "b", "c", "d", "e"]

    def test_no_cycle(self) -> None:
        result = topo_sort(NO_CYCLE)
        assert result == ["a", "b", "c"]

    def test_simple_cycle(self) -> None:
        result = topo_sort(SIMPLE_CYCLE)
        assert result is None

    def test_self_loop(self) -> None:
        result = topo_sort(SELF_LOOP)
        assert result is None

    def test_parallel_linear(self) -> None:
        result = topo_sort_parallel(PARALLEL_LINEAR)
        assert result == ["a", "b", "c"]

    def test_parallel_diamond(self) -> None:
        result = topo_sort_parallel(PARALLEL_DIAMOND)
        assert result == ["a", ("b", "c"), "d"]

    def test_parallel_wide(self) -> None:
        result = topo_sort_parallel(PARALLEL_WIDE)
        assert result == [("a", "b", "c"), "d"]

    def test_parallel_loop(self) -> None:
        result = topo_sort_parallel(SIMPLE_CYCLE)
        assert result is None
        result = topo_sort_parallel(SELF_LOOP)
        assert result is None


# kahn algorithm
