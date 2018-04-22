package com.ziemer.socialnetwork.repository;

import com.ziemer.socialnetwork.model.postgresql.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostgresRepository extends CrudRepository<Person, Long> {

    Person findByName(String name);
}
