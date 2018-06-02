import os
import sys

for path, subdirs, files in os.walk(sys.argv[0]):
    for name in files:
        print(os.path.join(path, name))