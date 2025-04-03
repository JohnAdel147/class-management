package com.company.classmanagment.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "WORKING_DAYS")
@Entity
public class WorkingDays {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @Column(name = "GRADE_1")
    private Boolean grade_1;
    @Column(name = "GRADE_2")
    private Boolean grade_2;
    @InstanceName
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    @Lob
    private String description;
    @Column(name = "START_DATE")
    private LocalDateTime startDate;
    @Column(name = "END_DATE")
    private LocalDateTime endDate;
    @Column(name = "ATTENDANCE_SCORE")
    private Double attendanceScore;
    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    public Boolean getGrade_2() {
        return grade_2;
    }

    public void setGrade_2(Boolean grade_2) {
        this.grade_2 = grade_2;
    }

    public Boolean getGrade_1() {
        return grade_1;
    }

    public void setGrade_1(Boolean grade_1) {
        this.grade_1 = grade_1;
    }

    public Double getAttendanceScore() {
        return attendanceScore;
    }

    public void setAttendanceScore(Double attendanceScore) {
        this.attendanceScore = attendanceScore;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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