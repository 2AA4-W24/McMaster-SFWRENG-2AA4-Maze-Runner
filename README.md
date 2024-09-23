# Assignment A3 - Maze Runner, Take Two

  * **Student**: [Andreas Sideris](siderisa@mcmaster.ca)
  * **Program**: B. Eng. In Software Engineering
  * **Course code**: SFWRENG 2AA4
  * **Course Title**: Software Design I - Introduction to Software Development 
  * Term: *Level II - Winter 2024*

## Business Logic Specification

This program explores a maze, finding a path from an entry point to an exit one.

- The maze is stored in a text file, with `#` representing walls and `␣` (_empty space_) representing passages.
- You’ll find examples of such mazes that can be used to test the program in the [`examples`](./examples) directory.
- The Maze is surrounded by walls on its four borders, except for its entry/exit points.
    - Entry and exit points are always located on the East and West border.
    - The maze is not directed. As such, exit and entry can be interchanged.
- At the beginning of the exploration, we're located on the entry tile, facing the opposite side (e.g., if entering by the eastern entry, you're facing West).
- The program generates a sequence of instructions to reach the opposite exit (i.e., a "path"):
    - `F` means 'move forward' according to your current direction
    - `R` means 'turn right' (does not move, just change direction), and `L` means ‘turn left’. 
- A canonical path contains only `F`, `R` and `L` symbols
- A factorized path squashes together similar instructions (i.e., `FFF` = `3F`, `LL` = `2L`).
- Spaces are ignored in the instruction sequence (only for readability: `FFLFF` = `FF L FF`)
- The program takes as input a maze and print the path on the standard output.

## How to run this software?

To build the program, simply package it with Maven:

```
mvn package 
```
Then, a path through an example maze using the 'right-hand method' can be found with these flags, while executing the .jar file:

```
java -jar target/mazerunner.jar -i examples/medium.maz.txt

java -jar target/mazerunner.jar --input examples/medium.maz.txt

java -jar target/mazerunner.jar -i examples/medium.maz.txt -method righthand
```
To use the faster breadth-first search algorithm:

```
java -jar target/mazerunner.jar -i examples/medium.maz.txt -method bfs
```
Additionally, the validity of any path can be checked using the -p flag. 
Any path to be checked must be surrounded by quotes ("") and only contain F, R, and L symbols along with the number of times they are used, if more than once (ex. 4F).

```
java -jar target/mazerunner.jar -i examples/straight.maz.txt -p "4F"
```
Finally, the amount (calculated by (number of moves for right-hand)/(number of moves for bfs))
the breadth-first search algorithm is faster than the right-hand method for any given maze can be found,
as well as the time the program takes to run each algorithm and load the maze file.

```
java -jar target/mazerunner.jar -i examples/medium.maz.txt -method bfs -baseline righthand
```

#### Examples

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt
4F
mosser@azrael A1-Template %
```

If a given path is correct, the program prints the message `correct path` on the standard output.

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -p 4F
correct path
mosser@azrael A1-Template %
```

If a given path is incorrect, the program prints the message `incorrect path` on the standard output.

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -p 3F
inccorrect path
mosser@azrael A1-Template %
```

