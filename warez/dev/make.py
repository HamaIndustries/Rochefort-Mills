import sys
"""
# of entries (1 byte, min 1)
main size (2 bytes, up to 65535)
entry 1 size 
entry 2 size
# of entries again for scanner
CAFEBABE
"""

def sizeof(b: bytes) -> bytes:
    s = len(b)
    if (s > 65535):
        raise Exception("file too large! ")
    return bytes([(0xff00 & s) >> 8, 0xff & s])
    

def main(jpgf, prif, outf, *deps):
    with open(jpgf, 'br') as jpg:
        n = len(deps) + 1
        sizes = b''
        data = b''
        for f in (prif, *deps):
            with open(f, 'br') as cls:
                datum = cls.read()
                sizes += sizeof(datum)
                data += datum
        with open(outf, "wb") as out:
            out.write(jpg.read() + bytes([n]) + sizes + bytes([n]) + data)
            print("wrote to " + outf)
        
            



if __name__ == "__main__":
    main(*sys.argv[1:])