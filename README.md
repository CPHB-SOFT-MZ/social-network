# Social Network
This application runs a benchmark comparing Neo4j and Postgresql for a specific task best suited for Neo4j.
It finds endorsements between persons, down to depth 5. Postgresql runs out of memory on depth 5 and therefore the 
results are somehow incomplete.

## Prerequisites
1. Java 8
2. PostgreSQL running on port 5432 with the role 'appdev' 
   (Hint: the container from Jens' docker container `data` has the settings and roles required)
3. Neo4j running on port 7474.
4. It's important that you have imported the data into PostgreSQL and Neo4j. You can see the data model used by this 
project in the `model` package. (Note: the project assumes that the ID of the Person entity is a String and not a number).

## How to run
1. Clone this project
2. After that you should just be able to run the project. From commandline: `mvn spring-boot:run -DskipTests`


## Results
The results of the benchmark is presented here. Note that the depth of 5 is not presented for PostgreSQL,
since it ran out of memory no matter how I tuned it. 


| depth | PostgreSQL Average | PostgreSQL Median | Neo4j Average | Neo4j Median |
| --- | --- | ----- | --- | --- |
| 1   | 31  | 23    | 296 | 273 |
| 2   | 33  | 33    | 339 | 329 |
| 3   | 309 | 141   | 988 | 575 |
| 4   | 3159| 1350  | 2160| 1129|
| 5   | -   | -     | 25000 | 15397 |

What we can conclude from these results is, that PostgreSQL is quite fast until you 
reach depth 4 and 5. Neo4j does of course also get slower, but it doesn't crash and 
it can actually fetch huge amounts of data in a reasonable amount of time.  



