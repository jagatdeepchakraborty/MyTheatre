# MyTheatre
Program to parse a theater layout and a list of ticket requests and produce a list of tickets or explanations in the same order as the requests
 
## Prerequisites
IDE capable with Java 8 and Gradle.

## How to Run
* Run the Main file.
* Paste input in console in the below format
* The theater layout is made up of 1 or more rows.  Each row is made up of 1 or more sections separated by a space.
* After the theater layout, there is one empty line, followed by 1 or more theater requests.  The theater request is made up of a name followed by a space and the number of requested tickets.
* The Input Sequence should end with 2 lines of ## to let the program know that its the end of input.
 ```
 6 6
3 5 5 3
4 6 6 4
2 8 8 2
6 6

Smith 2
Jones 5
Davis 6
Wilson 100
Johnson 3
Williams 4
Brown 8
Miller 12
##
##
```

## Running Tests
* Tests can be run by the command ```gradle test``` from terminal.
* The tests cover 100% of the lines in TheatreSeating class.
* 3 different input formats are tested by using GoodInput1, GoodInput2 and GoodInput3.
