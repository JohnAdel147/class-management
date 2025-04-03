package com.company.classmanagment.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "GRADUATION_PROJECT", indexes = {
        @Index(name = "IDX_GRADUATION_PROJECT_SUPERVISOR", columnList = "SUPERVISOR_ID")
})
@Entity
public class GraduationProject {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME")
    private String name;
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @JoinColumn(name = "SUPERVISOR_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User supervisor;
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @OneToMany(mappedBy = "graduationProject", cascade = CascadeType.ALL)
    private List<User> students;
    @Lob
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "Score")
    private Double score;

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }

    public User getSupervisor() {
        return supervisor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}