import unittest


def main():
    return 'Hello, World!'


class TestCode(unittest.TestCase):
    def test_hello_world(self):
        self.assertEqual(main(), 'Hello, World!')


if __name__ == "__main__":
    print(main())
