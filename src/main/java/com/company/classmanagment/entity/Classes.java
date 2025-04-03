package com.company.classmanagment.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "CLASSES")
@Entity
public class Classes {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;
    @Column(name = "HEAD")
    private String head;
    @Column(name = "VICE_HEAD")
    private String viceHead;
    @Column(name = "CLASS_TIME")
    private LocalTime classTime;
    @Column(name = "CLASS_DAY")
    private Integer classDay;
    @Column(name = "NOTES")
    @Lob
    private String notes;
    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    public Days getClassDay() {
        return classDay == null ? null : Days.fromId(classDay);
    }

    public void setClassDay(Days classDay) {
        this.classDay = classDay == null ? null : classDay.getId();
    }

    public void setClassTime(LocalTime classTime) {
        this.classTime = classTime;
    }

    public LocalTime getClassTime() {
        return classTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getViceHead() {
        return viceHead;
    }

    public void setViceHead(String viceHead) {
        this.viceHead = viceHead;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
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