package com.company.classmanagment.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "TRAINING", indexes = {
        @Index(name = "IDX_TRAINING_TRAINEE", columnList = "TRAINEE_ID"),
        @Index(name = "IDX_TRAINING_TRAINING_CLASS", columnList = "TRAINING_CLASS_ID")
})
@Entity
public class Training {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "TRAINEE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User trainee;
    @OnDeleteInverse(DeletePolicy.UNLINK)
    @JoinColumn(name = "TRAINING_CLASS_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Classes trainingClass;
    @Column(name = "START_DATE")
    private LocalDate startDate;
    @Column(name = "END_DATE")
    private LocalDate endDate;
    @Column(name = "STATUS")
    private String status;
    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Classes getTrainingClass() {
        return trainingClass;
    }

    public void setTrainingClass(Classes trainingClass) {
        this.trainingClass = trainingClass;
    }

    public User getTrainee() {
        return trainee;
    }

    public void setTrainee(User trainee) {
        this.trainee = trainee;
    }

    public TrainingStatus getStatus() {
        return status == null ? null : TrainingStatus.fromId(status);
    }

    public void setStatus(TrainingStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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

    @InstanceName
    @DependsOnProperties({"trainee", "trainingClass"})
    public String getDisplayName() {
        return String.format("%s %s", (trainee),
                (trainingClass != null ? trainingClass : "")).trim();
    }
    public void updateStatus() {
        if (endDate != null && LocalDate.now().isAfter(endDate)) {
            setStatus(TrainingStatus.COMPLETED); // or whatever status you want
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}