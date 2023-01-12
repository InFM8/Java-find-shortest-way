## Escape from the Woods

Escape from the woods is a task that finds the fastest way out of a forest and returns the number of steps taken as the answer.

### Task

* Input a map file containing the forest layout
* The task will return the number of steps taken to find the exit
* The goal is to find the fastest way out of the forest


### Map Characteristics

* The map is a rectangular shape
* Map consists of symbols " ", "1", and "X":
*   " " represents empty spaces where players can move
*   "1" represents forest/trees
*   "X" represents the starting position

### Examples

* map1.txt returns 4 steps
* map2.txt returns 13 steps
* map3.txt returns 0 (no exit found)...


### Requirements

* The input file should be in a format that follows the map characteristics.
* If the file is corrupted or there is no exit, the task should return 0.
* Java 8 or later

### Usage

// Create a new instance of the GameImpl class
GameImpl gameImpl = new GameImpl();

// Input a map file as a ClassPathResource
ClassPathResource map1 = new ClassPathResource("map1.txt");

// Run the escapeFromTheWoods method and store the result in a variable
int steps = gameImpl.escapeFromTheWoods(map1);

// Print the number of steps taken to find the exit
System.out.println("Steps taken to find the exit: " + steps);