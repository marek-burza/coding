import unittest


if __name__ == "__main__":
    loader = unittest.TestLoader()
    suite = loader.discover('.', pattern='*.py')
    runner = unittest.TextTestRunner(verbosity=2)
    runner.run(suite)
