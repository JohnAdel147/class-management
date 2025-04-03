package com.company.classmanagment.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum Grade implements EnumClass<Integer> {

    GRADE_1(10),
    GRADE_2(20);

    private final Integer id;

    Grade(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static Grade fromId(Integer id) {
        for (Grade at : Grade.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}