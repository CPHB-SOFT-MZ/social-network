# Social Network
This application runs a benchmark comparing Neo4j and Postgresql for a specific task best suited for Neo4j.
It finds endorsements between persons, down to depth 5. Postgresql runs out of memory on depth 5 and therefore the 
results are somehow incomplete.

## How to run
1. Clone this project
2. Make sure you have an instance of PostgreSQL running on port 5432 with the role 'appdev' 
(Hint: the container from Jens' docker container `data` has the settings and roles required)
3. Make sure you have an instance of Neo4j running on port 7474.
4. It's important that you have imported the data into PostgreSQL and Neo4j. You can see the data model used by this 
project in the `model` package. (Note: the project assumes that the ID of the Person entity is a String and not a number).
5. After that you should just be able to run the project. From commandline: `mvn spring-boot:run`


## Results
The results of the benchmark is presented here. Note that the depth of 5 is not presented for PostgreSQL,
since it ran out of memory no matter how I tuned it. 
 