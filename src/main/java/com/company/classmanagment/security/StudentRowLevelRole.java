package com.company.classmanagment.security;

import com.company.classmanagment.entity.*;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "StudentRowLevel", code = StudentRowLevelRole.CODE)
public interface StudentRowLevelRole {
    String CODE = "student-row-level";

    @JpqlRowLevelPolicy(entityClass = Training.class, where = "{E}.trainee.id=:current_user_id")
    void training();

    @JpqlRowLevelPolicy(
            entityClass = Classes.class,
            join = "Training t",
            where = "{E}.name = t.trainingClass.name AND t.trainee.id=:current_user_id"
    )
    void classes();


//    @JpqlRowLevelPolicy(entityClass = Subject.class, join = "Subject.users s", where = "s.id =:current_user_id")
//    void subject();




    @JpqlRowLevelPolicy(entityClass = Exam.class, join = "Exam.user u", where = "u.id = :current_user_id")
    void exam();


    @JpqlRowLevelPolicy(entityClass = ExamScore.class, where = "{E}.student.id = :current_user_id")
    void examScore();

    @JpqlRowLevelPolicy(entityClass = Subject.class, where = "{E}.semester = :current_user_semester")
    void subject();
}