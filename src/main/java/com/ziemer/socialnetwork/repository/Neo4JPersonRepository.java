package com.ziemer.socialnetwork.repository;

import com.ziemer.socialnetwork.model.neo4j.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Neo4JPersonRepository extends CrudRepository<Person, Long> {

    @Query("MATCH (a) RETURN a, rand() as r ORDER BY r LIMIT 20;")
    List<Person> find20randomPersons();

    @Query("MATCH ({name:{personName}})-[:ENDORSES]->(other) RETURN other;")
    List<Person> findEndorsements(@Param("personName") String name);

    @Query("MATCH ({name:{personName}})-[:ENDORSES*2]->(other) RETURN other;")
    List<Person> findEndorsementsDepth2(@Param("personName") String name);

    @Query("MATCH ({name:{personName}})-[:ENDORSES*3]->(other) RETURN other;")
    List<Person> findEndorsementsDepth3(@Param("personName") String name);

    @Query("MATCH ({name:{personName}})-[:ENDORSES*4]->(other) RETURN other;")
    List<Person> findEndorsementsDepth4(@Param("personName") String name);

    @Query("MATCH ({name:{personName}})-[:ENDORSES*5]->(other) RETURN other;")
    List<Person> findEndorsementsDepth5(@Param("personName") String name);
}
