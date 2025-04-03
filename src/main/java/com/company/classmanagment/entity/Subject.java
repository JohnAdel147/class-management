package com.company.classmanagment.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "SUBJECTS")
@Entity
public class Subject {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @InstanceName
    @Column(name = "NAME")
    private String name;
    @Column(name = "SUBJECT_SCORE")
    private Integer subjectScore;
    @Column(name = "MATERIALS")
    private String materials;
    @Column(name = "LESSONS_NUM")
    private String lessonsNum;
    @Column(name = "SEMESTER")
    private Integer semester;
    @JoinTable(name = "USER_SUBJECTS_LINK",
            joinColumns = @JoinColumn(name = "SUBJECTS_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<User> users;

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public Integer getSubjectScore() {
        return subjectScore;
    }

    public void setSubjectScore(Integer subjectScore) {
        this.subjectScore = subjectScore;
    }

    public void setSemester(Grade semester) {
        this.semester = semester == null ? null : semester.getId();
    }

    public Grade getSemester() {
        return semester == null ? null : Grade.fromId(semester);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getLessonsNum() {
        return lessonsNum;
    }

    public void setLessonsNum(String lessonsNum) {
        this.lessonsNum = lessonsNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}