package com.company.classmanagment.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum Days implements EnumClass<Integer> {

    SUN(10),
    MON(20),
    TUE(30),
    WED(40),
    THU(50),
    FRI(60),
    SAT(70);

    private final Integer id;

    Days(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static Days fromId(Integer id) {
        for (Days at : Days.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}