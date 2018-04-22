package com.ziemer.socialnetwork.model.postgresql;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "social_network_nodes")
public class Person implements Serializable {
    @Id
    @Column(name = "node_id")
    private String id;

    private String name;

    private String job;

    private LocalDate birthday;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "social_network_edges", joinColumns = @JoinColumn(name = "source_node_id"), inverseJoinColumns = @JoinColumn(name = "target_node_id"))
    public List<Person> endorsements;

    @ManyToMany
    @JoinTable(name = "social_network_edges", joinColumns = @JoinColumn(name = "target_node_id"), inverseJoinColumns = @JoinColumn(name = "source_node_id"))
    public List<Person> endorsedBy;

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

    public List<Person> getEndorsements() {
        return endorsements;
    }

    public void setEndorsements(List<Person> endorsements) {
        this.endorsements = endorsements;
    }
}
