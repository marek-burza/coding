import glob
import io
import os
import sys
import unittest


def go(name):
    with open(name, 'rb') as handle:
        body = handle.read()
    context = {'__file__': name, '__name__': '__main__'}
    exec(compile(body, name, 'exec'), context)


class TestSingle(unittest.TestCase):
    def __init__(self, name, selection):
        unittest.TestCase.__init__(self, 'test')
        self.name = name
        self.selection = selection

    def test(self):
        name = os.path.basename(self.name)
        test_in = self.name.replace('.py', '.%s.in' % self.selection)
        test_out = self.name.replace('.py', '.%s.out' % self.selection)
        undo_in = sys.stdin
        undo_out = sys.stdout
        with open(test_out, 'r') as handle:
            expected = handle.read()
        sys.stdin = open(test_in, 'r')
        sys.stdout = io.StringIO()
        at = -1
        try:
            go(self.name)
            expected = list(map(lambda line: line.strip(), expected.split('\n')))
            result = list(map(lambda line: line.strip(), sys.stdout.getvalue().split('\n')))
            self.assertEqual(len(result), len(expected))
            for index, item in enumerate(expected):
                at = index
                self.assertEqual(result[index], item)
        except:
            sys.stdin.close()
            sys.stdin = undo_in
            sys.stdout = undo_out
            print('Failed in %s at line: %d' % (name, at))
            raise
        sys.stdin.close()
        sys.stdin = undo_in
        sys.stdout = undo_out


def is_python_file(directory, name):
    return os.path.isfile(os.path.join(directory, name)) and name.endswith('.py')


def join_without_extension(directory, name):
    return os.path.join(directory, name.replace('.py', ''))

if __name__ == '__main__':
    path = os.path.dirname(__file__)
    loader = unittest.TestLoader()
    suite = loader.discover(path)
    content = os.listdir(path)
    scripts = [item for item in content if is_python_file(path, item)]
    prefixes = [join_without_extension(path, item) for item in scripts if item.find('test_') != -1]
    for prefix in prefixes:
        script = '%s.py' % prefix
        tests = []
        for test in glob.glob('%s.*.in' % prefix):
            suite.addTest(TestSingle(script, test.split('.')[-2]))
    unittest.TextTestRunner(verbosity=1).run(suite)
