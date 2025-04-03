package com.company.classmanagment.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum TrainingStatus implements EnumClass<String> {

    IN_PROGRASS("A"),
    COMPLETED("B"),
    ABSENCE("C");

    private final String id;

    TrainingStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TrainingStatus fromId(String id) {
        for (TrainingStatus at : TrainingStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}