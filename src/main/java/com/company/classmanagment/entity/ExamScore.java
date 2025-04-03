package com.company.classmanagment.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "EXAM_SCORE", indexes = {
        @Index(name = "IDX_EXAM_SCORE_SUBJECT", columnList = "SUBJECT"),
        @Index(name = "IDX_EXAM_SCORE_STUDENT", columnList = "STUDENT_ID"),
        @Index(name = "IDX_EXAM_SCORE_EXAM", columnList = "EXAM_ID")
})
@Entity
public class ExamScore {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "EXAM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Exam exam;
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "STUDENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User student;
    @Column(name = "SUBJECT")
    private String subject;
    @Column(name = "SCORE")
    private Double score;
    @Column(name = "PASSED")
    private Boolean passed;
    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}