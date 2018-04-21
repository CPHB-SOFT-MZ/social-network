package com.ziemer.socialnetwork.model.neo4j;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDate;
import java.util.Set;

@NodeEntity
public class Person {
    @Id private long id;

    private String name;

    private String job;

    private LocalDate bithday;

    private Person() {};

    @Relationship(type = "ENDORSES", direction = Relationship.DIRECTION)
    public Set<Person> endorsements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public LocalDate getBithday() {
        return bithday;
    }

    public void setBithday(LocalDate bithday) {
        this.bithday = bithday;
    }
}
