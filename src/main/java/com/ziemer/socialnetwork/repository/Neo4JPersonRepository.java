package com.ziemer.socialnetwork.repository;

import com.ziemer.socialnetwork.model.neo4j.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Neo4JPersonRepository extends CrudRepository<Person, Long> {

    @Query("MATCH (a) RETURN a, rand() as r ORDER BY r LIMIT 20")
    List<Person> find10randomPersons();

    @Query("MATCH (:Person {name:{personName}})-[:ENDORSES]->(p:Person) RETURN p")
    List<Person> findEndorsements(@Param("personName") String name);

    @Query("MATCH (:Person {name:{personName}})-[:ENDORSES*2]->(p:Person) RETURN p")
    List<Person> findEndorsementsDepth2(@Param("personName") String name);

    @Query("MATCH (:Person {name:{personName}})-[:ENDORSES*3]->(p:Person) RETURN p")
    List<Person> findEndorsementsDepth3(@Param("personName") String name);

    @Query("MATCH (:Person {name:{personName}})-[:ENDORSES*4]->(p:Person) RETURN p")
    List<Person> findEndorsementsDepth4(@Param("personName") String name);

    @Query("MATCH (:Person {name:{personName}})-[:ENDORSES*5]->(p:Person) RETURN p")
    List<Person> findEndorsementsDepth5(@Param("personName") String name);
}
