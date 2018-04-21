package com.ziemer.socialnetwork.repository;

import com.ziemer.socialnetwork.model.postgresql.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostgresRepository extends CrudRepository<Person, Long> {

    @Query(value = "SELECT * FROM social_network.social_network_nodes AS sn JOIN social_network.social_network_edges AS se ON sn.name = ?1 AND sn.node_id = se.source_node_id;",
            nativeQuery = true)
    List<Person> findEndorsements(String name);

    @Query(value = "",
            nativeQuery = true)
    List<Person> findEndorsementsDepth2(@Param("personName") String name);
//
//    List<Person> findEndorsementsDepth3(@Param("personName") String name);
//
//    List<Person> findEndorsementsDepth4(@Param("personName") String name);
//
//    List<Person> findEndorsementsDepth5(@Param("personName") String name);
}
