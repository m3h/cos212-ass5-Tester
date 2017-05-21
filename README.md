# cos212-ass5-Tester
A Tester for assignment 5 of COS212

To use it--

clone or download the repository...
make a new folder.

copy your code into the new folder, and copy this repo into the new folder.

javac Tester.java
java Tester > output.txt

diff output.txt expectedOutput.txt

this should show you if there's a difference... good luck finding why


# Assumptions
To make this work more easily, I assumed your functions could gracefully handle being called with non-existant vertices. You
Functions called with non-existant vertices must return the following exactly:

     * Every function should be able to be called with vertices that don't exist, with the following output
     * numEdges -- return 0;
     * getDegree -- return 0;
     * changeLabel -- return false
     * depthFirstTraversal -- return "";
     * getPath -- return "";
     * getChinesePostmanRoute -- return "";
