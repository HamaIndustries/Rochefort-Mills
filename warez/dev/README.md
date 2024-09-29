This folder contains the source code used for compiling FOXTROT.jpg.
Compile it using the command:

`python3 make.py floppy.jpg FoxTrotProgram.class FOXTROT.jpg SinisterFox.class SinisterGoal.class`

Compiled class files must be remapped, members of symbolics.division.flopster package, 
and the main class must implement `FlopsterProgram`.

Because these classes are hidden, a few considerations must be made while designing them:
- No references to them may be made directly. This means that certain standard java
language features are not normally available, namely lambda functions.
- In order to use auxiliary classes, they must be provided to the main class implementing
`FlopsterProgram`. Siltware will provide these in the form of Class<?> objects through
`FlopsterProgram#configure`.
- Loading a disk automatically loads a completely new class instance, which is garbage
collected once all references to it are out of scope. Loading a disk twice will create two new
unequal classes with identical code but different memory locations.
