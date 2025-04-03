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
@Table(name = "ATTENDANCE", indexes = {
        @Index(name = "IDX_ATTENDANCE_USER", columnList = "USER_ID"),
        @Index(name = "IDX_ATTENDANCE_EVENT", columnList = "EVENT_ID")
})
@Entity
public class Attendance {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "EVENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private WorkingDays event;
    @Column(name = "EARNED_SCORE")
    private Double earnedScore;
    @Column(name = "LATE")
    private Boolean late;
    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    public Boolean getLate() {
        return late;
    }

    public void setLate(Boolean late) {
        this.late = late;
    }

    public void setEarnedScore(Double earnedScore) {
        this.earnedScore = earnedScore;
    }

    public Double getEarnedScore() {
        return earnedScore;
    }

    public WorkingDays getEvent() {
        return event;
    }

    public void setEvent(WorkingDays event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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