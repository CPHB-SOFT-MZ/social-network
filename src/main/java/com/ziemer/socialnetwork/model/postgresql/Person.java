package com.ziemer.socialnetwork.model.postgresql;

import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "social_network_nodes")
public class Person implements Serializable {
    @Id
    @Column(name = "node_id")
    private long id;

    private String name;

    private String job;

    private LocalDate birthday;

    @OneToMany
    public Set<Person> endorsements;

    public Person() {
    }

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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Set<Person> getEndorsements() {
        return endorsements;
    }

    public void setEndorsements(Set<Person> endorsements) {
        this.endorsements = endorsements;
    }
}
