package com.ziemer.socialnetwork.repository;

import com.ziemer.socialnetwork.model.postgresql.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostgresRepository extends CrudRepository<Person, Long> {

    @Query(value = "SELECT * FROM social_network.social_network_nodes AS sn " +
            "JOIN social_network.social_network_edges AS se ON sn.name = ?1 " +
            "AND sn.node_id = se.source_node_id;", nativeQuery = true)
    List<Person> findEndorsementsByName(String name);

    @Query(value = "SELECT * FROM social_network.social_network_nodes AS sn " +
    "JOIN social_network.social_network_edges AS se ON sn.name = ?1 " +
    "AND sn.node_id = se.source_node_id JOIN social_network.social_network_edges AS se2 " +
    "ON se.target_node_id = se2.source_node_id;", nativeQuery = true)
    List<Person> findEndorsementsByNameDepth2(String name);

    @Query(value = "SELECT * FROM social_network.social_network_nodes AS sn " +
            "JOIN social_network.social_network_edges AS se ON sn.name = ?1 " +
            "AND sn.node_id = se.source_node_id JOIN social_network.social_network_edges AS se2 " +
            "ON se.target_node_id = se2.source_node_id JOIN social_network.social_network_edges AS se3 " +
            "ON se2.target_node_id = se3.source_node_id;", nativeQuery = true)
    List<Person> findEndorsementsByNameDepth3(String name);

    @Query(value = "SELECT * FROM social_network.social_network_nodes AS sn " +
            "JOIN social_network.social_network_edges AS se ON sn.name = ?1 " +
            "AND sn.node_id = se.source_node_id JOIN social_network.social_network_edges AS se2 " +
            "ON se.target_node_id = se2.source_node_id JOIN social_network.social_network_edges AS se3 " +
            "ON se2.target_node_id = se3.source_node_id JOIN social_network.social_network_edges AS se4 " +
            "ON se3.target_node_id = se4.source_node_id;", nativeQuery = true)
    List<Person> findEndorsementsByNameDepth4(String name);


    @Query(value = "SELECT * FROM social_network.social_network_nodes AS sn " +
            "JOIN social_network.social_network_edges AS se ON sn.name = ?1 " +
            "AND sn.node_id = se.source_node_id JOIN social_network.social_network_edges AS se2 " +
            "ON se.target_node_id = se2.source_node_id JOIN social_network.social_network_edges AS se3 " +
            "ON se2.target_node_id = se3.source_node_id JOIN social_network.social_network_edges AS se4 " +
            "ON se3.target_node_id = se4.source_node_id JOIN social_network.social_network_edges AS se5 " +
            "ON se4.target_node_id = se5.source_node_id;", nativeQuery = true)
    List<Person> findEndorsementsByNameDepth5(String name);
}
