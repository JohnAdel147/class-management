package com.company.classmanagment.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "EXAM", indexes = {
        @Index(name = "IDX_EXAM_SUBJECT", columnList = "SUBJECT_ID")
})
@Entity
public class Exam {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @Column(name = "GRADE")
    private Integer grade;
    @InstanceName
    @Column(name = "NAME")
    private String name;
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "SUBJECT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;
    @JoinTable(name = "EXAM_USER_LINK",
            joinColumns = @JoinColumn(name = "EXAM_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<User> user;
    @Column(name = "DATE_")
    private LocalDateTime date;
    @Column(name = "PLACE")
    private String place;
    @Column(name = "NOTES")
    @Lob
    private String notes;
    @Column(name = "TOTAL_SCORE")
    private Integer totalScore;

    public Grade getGrade() {
        return grade == null ? null : Grade.fromId(grade);
    }

    public void setGrade(Grade grade) {
        this.grade = grade == null ? null : grade.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<User> getUser() {
        return user;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}