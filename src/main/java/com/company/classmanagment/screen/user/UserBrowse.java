package com.company.classmanagment.screen.user;

import com.company.classmanagment.entity.ExamScore;
import com.company.classmanagment.entity.Grade;
import com.company.classmanagment.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.SaveContext;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@UiController("User.browse")
@UiDescriptor("user-browse.xml")
@LookupComponent("usersTable")
@Route("users")
public class UserBrowse extends StandardLookup<User> {
    @Autowired
    private CollectionContainer<User> usersDc;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private CollectionLoader<User> usersDl;
    @Autowired
    private UnconstrainedDataManager unconstrainedDataManager;


    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        List<User> users = dataManager.load(User.class).all().list();
        updateUsersTotalsAttendanceScore(users);
        updateUsersAttendanceScore(users);
        updateUsersExamScore(users);
        updateUserPassedExam(users);
//        updateUsersAttendanceRate(users);

        SaveContext saveContext = new SaveContext();
        users.forEach(saveContext::saving);
        dataManager.save(saveContext);

//        users.forEach( user -> {
//            dataManager.save(user);
//        });
//        usersDl.load();
//        System.out.println(getUserAttendanceRate(users.get(3)));



    }

    private void updateUsersAttendanceRate(List<User> users) {
        users.forEach( user -> {
            if (user.getStudentAttendanceScore() !=null && user.getTotalGradeScore() != null){
                System.out.println(user.getStudentAttendanceScore() / user.getTotalGradeScore());
            }

        });
    }


    private void updateUserPassedExam(List<User> users) {
        users.forEach(user -> {
            if(user.getSemester() != null){
                user.setPassedExams(getUserPassedExam(user));
//                dataManager.save(user);
            }
        });
    }

    private Integer getUserPassedExam(User user) {
        return dataManager.loadValue("select COUNT(e.exam.subject) from ExamScore e " +
                                "where e.student.id = :studentId1 "+
                "and e.passed = :pass",
                        Integer.class)
                .parameter("studentId1", user.getId())
                .parameter("pass",true)
                .optional().orElse(0);
    }

    private void updateUsersExamScore(List<User> users) {
        users.forEach(user -> {
            if (user.getSemester() != null) {
                user.setStudentExamsScore(getUserExamScore(user));
//                dataManager.save(user);
            }
        });
    }

    private Double getUserExamScore(User user) {
        return dataManager.loadValue(
                        "select SUM(e.score) from ExamScore e " +
                                "where e.student.id = :userId",
                        Double.class
                )
                .parameter("userId", user.getId())
                .one();
    }

    private void updateUsersAttendanceScore(List<User> users) {
        users.forEach(user -> {
            if(user.getSemester() != null){
                user.setStudentAttendanceScore(getStudentAttendance(user));
//                dataManager.save(user);
            }
        });
    }

    private Double getStudentAttendance(User user) {
        return dataManager.loadValue(
                        "select SUM(a.earnedScore) from Attendance a " +
                                "where a.user.id = :userId",
                        Double.class)
                .parameter("userId", user.getId())
                .one();
    }

    private void updateUsersTotalsAttendanceScore(List<User> users) {
        Double totalGrade1 = getAllWorkingDaysScore(Grade.GRADE_1);
        Double totalGrade2 = getAllWorkingDaysScore(Grade.GRADE_2);
        users.forEach(user -> {
            if (user.getSemester() != null) {
                if (user.getSemester() == Grade.GRADE_1) {
                    user.setTotalGradeScore(totalGrade1);
                }
                else if (user.getSemester() == Grade.GRADE_2) {
                    user.setTotalGradeScore(totalGrade2);
                }
                else {
                    user.setTotalGradeScore(null);
                }
//                dataManager.save(user);
            }
            else {
                user.setTotalGradeScore(null);
//                dataManager.save(user);
            }
        });
    }

    private double getAllWorkingDaysScore(Grade grade) {
        String gradeField = (grade == Grade.GRADE_1) ? "grade_2" : "grade_1";
        return dataManager.loadValue("select sum(w.attendanceScore) from WorkingDays w " +
                                "where w." + gradeField + " is null or w." + gradeField + " = false",
                        Double.class).optional().orElse(0.0);
    }


//    private double getAllWorkingDaysScore(Grade grade) {
//        String gradeField = (grade == Grade.GRADE_1) ? "grade_1" : "grade_2";
//        List<WorkingDays> workingDays = dataManager.load(WorkingDays.class)
//                .query("select w from WorkingDays w " +
//                        "where w." + gradeField + " is null or w." + gradeField + " = false")
//                .list();
//
//        return workingDays.stream()
//                .mapToDouble(w -> {
//                    Double score = w.getAttendanceScore();
//                    return score != null ? score : 0.0;
//                })
//                .sum();
//    }



}