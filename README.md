# CGOLThreading
A Conway's Game of Life simulator using multi-threading to speed up calculations for large scale simulations.

## Explanation
This program was a successful attempt at making a Conway's Game of Life simulatior that did each generation in O(n) instead of O(n^2) by using threads to iterate trhough each row at once before drawing that generation.

## Control
 - R - randomize board
 - (space) - toggle continuous evolving
 - N - advance 1 generation

To be implemented:
 - Add clock speed control
 - Add generations per frame control
 - Allow saving/loading of the board
