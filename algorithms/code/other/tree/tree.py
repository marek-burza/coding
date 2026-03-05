from __future__ import annotations

import unittest
from dataclasses import dataclass, field


@dataclass
class File:
    name: str


@dataclass
class Directory:
    name: str
    files: list[File | Directory] = field(default_factory=list)


@dataclass
class Stats:
    directory_count: int = 0
    file_count: int = 0


def tree(
    file_system: list[File | Directory],
    depth: int = 0,
    prefix: str = '',
    stats: Stats | None = None,
) -> str:
    output = ''
    if depth == 0:
        output += '.\n'
    if stats is None:
        stats = Stats()
    for i, item in enumerate(sorted(file_system, key=lambda item: item.name)):
        lines = f'{"└" if i == len(file_system) - 1 else "├"}── '
        output += f'{prefix}{lines}{item.name}\n'
        if isinstance(item, Directory):
            indent = '    ' if i == len(file_system) - 1 else '│   '
            output += tree(item.files, depth + 1, prefix + indent, stats)
            stats.directory_count += 1
        else:
            stats.file_count += 1
    if depth == 0:
        directories = stats.directory_count
        files = stats.file_count
        output += f'\n{directories} directories, {files} files\n'
    return output


class TestTree(unittest.TestCase):
    FILE_SYSTEM = [
        File('pyproject.toml'),
        File('README.md'),
        Directory(
            'src',
            [
                Directory(
                    'package',
                    [
                        File('__init__.py'),
                        File('main.py'),
                        File('py.typed'),
                        Directory(
                            'testing',
                            [
                                File('__init__.py'),
                                Directory(
                                    '__pycache__',
                                    [
                                        File('__init__.cpython-313.pyc'),
                                    ],
                                ),
                                File('tests.py'),
                            ],
                        ),
                        File('utils.py'),
                    ],
                ),
            ],
        ),
    ]
    EXPECTED = """\
.
├── README.md
├── pyproject.toml
└── src
    └── package
        ├── __init__.py
        ├── main.py
        ├── py.typed
        ├── testing
        │   ├── __init__.py
        │   ├── __pycache__
        │   │   └── __init__.cpython-313.pyc
        │   └── tests.py
        └── utils.py

4 directories, 9 files
"""

    def test_tree_output(self):
        assert tree(TestTree.FILE_SYSTEM) == TestTree.EXPECTED
