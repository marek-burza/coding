import sys

def main(path_in: str, path_out: str) -> None:
    with open(path_in, 'r') as handle:
        body = handle.readlines()
    for i, line in enumerate(body):
        if line.startswith('     '):
            body = body[i:]
            break
    dna = ''.join(body)
    body = [item for item in dna if item in ['a', 'c', 'g', 't']]
    joined = "".join(body)
    # counter = 0
    buffer = bytearray()
    octet = 0
    busy = 0
    lut = {'a': 0, 'c': 1, 'g': 2, 't': 3}
    for item in body:
        octet = (octet << 2) | lut[item]
        busy += 1
        # counter += 1
        if busy == 4:
            buffer.append(octet)
            octet = 0
            busy = 0
    if busy != 0:
        buffer.append(octet)
    else:
        busy = 4
    buffer.append(busy)
    with open(path_out, 'wb') as handle:
        handle.write(buffer)
    with open(path_out, 'rb') as handle:
        body = handle.read()
    lut = {0: 'a', 1: 'c', 2: 'g', 3: 't'}
    result = ''
    for octet in body[:-1]:
        for position in range(3, -1, -1):
            item = lut[(octet >> (2 * position)) & 3]
            result += item
            sys.stdout.write(item)
    assert joined == result


if __name__ == '__main__':
    main('/home/coderpad/data/input.in', 'ouput.out')
