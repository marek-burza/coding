# Tree

`tree` is a command-line tool for pretty-printing the directory structure of the current working directory.

```text
> tree
.
├── pyproject.toml
├── README.md
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

5 directories, 9 files
```

Implement a simple program that runs `tree` on a mocked file system, printing the presented output. Mocking the file system is left up to you.
