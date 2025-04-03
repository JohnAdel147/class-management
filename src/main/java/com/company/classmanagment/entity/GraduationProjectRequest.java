package com.company.classmanagment.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "GRADUATION_PROJECT_REQUEST", indexes = {
        @Index(name = "IDX_GRADUATION_PROJECT_REQUEST_STUDENT", columnList = "STUDENT_ID"),
        @Index(name = "IDX_GRADUATION_PROJECT_REQUEST_FIRST_PROJECT", columnList = "FIRST_PROJECT_ID"),
        @Index(name = "IDX_GRADUATION_PROJECT_REQUEST_SECOND_PROJECT", columnList = "SECOND_PROJECT_ID"),
        @Index(name = "IDX_GRADUATION_PROJECT_REQUEST_THIRD_PROJECT", columnList = "THIRD_PROJECT_ID")
})
@Entity
public class GraduationProjectRequest {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "STUDENT_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private User student;
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @JoinColumn(name = "FIRST_PROJECT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GraduationProject firstProject;
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @JoinColumn(name = "SECOND_PROJECT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GraduationProject secondProject;
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @JoinColumn(name = "THIRD_PROJECT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GraduationProject thirdProject;
    @Column(name = "NOTES")
    @Lob
    private String notes;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "ACCEPTED_PROJECT")
    private String acceptedProject;

    public String getAcceptedProject() {
        return acceptedProject;
    }

    public void setAcceptedProject(String acceptedProject) {
        this.acceptedProject = acceptedProject;
    }

    public Status getStatus() {
        return status == null ? null : Status.fromId(status);
    }

    public void setStatus(Status status) {
        this.status = status == null ? null : status.getId();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public GraduationProject getThirdProject() {
        return thirdProject;
    }

    public void setThirdProject(GraduationProject thirdProject) {
        this.thirdProject = thirdProject;
    }

    public GraduationProject getSecondProject() {
        return secondProject;
    }

    public void setSecondProject(GraduationProject secondProject) {
        this.secondProject = secondProject;
    }

    public GraduationProject getFirstProject() {
        return firstProject;
    }

    public void setFirstProject(GraduationProject firstProject) {
        this.firstProject = firstProject;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}