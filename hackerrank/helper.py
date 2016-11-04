import io
import os
import sys
import unittest


def io_checker(case, file, selection, main):
    name = os.path.basename(file)
    test_in = file.replace('.py', '.%s.in' % selection)
    test_out = file.replace('.py', '.%s.out' % selection)
    undo_in = sys.stdin
    undo_out = sys.stdout
    with open(test_out, 'r') as handle:
        original = expected = handle.read()
    sys.stdin = open(test_in, 'r')
    sys.stdout = io.StringIO()
    try:
        main()
        expected = list(map(lambda line: line.strip(), expected.split('\n')))
        result = list(map(lambda line: line.strip(), sys.stdout.getvalue().split('\n')))
        case.assertEqual(len(result), len(expected))
        for line in range(len(expected)):
            case.assertEqual(result[line], expected[line])
    except:
        sys.stdin.close()
        sys.stdin = undo_in
        sys.stdout = undo_out
        print('Failed in %s at line: %d' % (name, line))
        raise
    sys.stdin.close()
    sys.stdin = undo_in
    sys.stdout = undo_out

#if __name__ == '__main__':
#    path = os.path.dirname(__file__)
#    content = os.listdir(path)
#    scripts = [item for item in content if os.path.isfile(os.path.join(path, item)) and item.endswith('.py')]
#    prefixes = [os.path.join(path, item.replace('.py', '')) for item in scripts]
#    print(prefixes)
