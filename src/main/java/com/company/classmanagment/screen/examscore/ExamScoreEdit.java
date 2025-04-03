package com.company.classmanagment.screen.examscore;

import com.company.classmanagment.entity.Exam;
import com.company.classmanagment.entity.ExamScore;
import com.company.classmanagment.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.*;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@UiController("ExamScore.edit")
@UiDescriptor("exam-score-edit.xml")
@EditedEntityContainer("examScoreDc")
public class ExamScoreEdit extends StandardEditor<ExamScore> {
    @Autowired
    private Metadata metadata;
    @Autowired
    private InstanceContainer<ExamScore> examScoreDc;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private DataContext dataContext;
    @Autowired
    private CheckBox passedField;
    @Autowired
    private TextField<String> subjectField;
    @Autowired
    private ComboBox<User> studentField;
    @Autowired
    private Notifications notifications;



    @Subscribe("windowCommitAndClear")
    public void onWindowCommitAndClear(final Action.ActionPerformedEvent event) {
        if(examScoreExist(studentField.getValue()) != null){
            notifications.create(Notifications.NotificationType.ERROR)
                    .withDescription("this student already have score for this exam")
                    .show();
            return;
        }
//        dataManager.save(getEditedEntity());
        commitChanges().then( () -> {

            ExamScore newExamScore = metadata.create(ExamScore.class);
            newExamScore.setExam(getEditedEntity().getExam());
            newExamScore.setSubject(getEditedEntity().getExam().getSubject().getName());
            examScoreDc.setItem(newExamScore);

        });

    }

    @Subscribe("examField")
    public void onExamFieldValueChange(final HasValue.ValueChangeEvent<Exam> event) {
        if(event.getValue() != null){
            subjectField.setValue(Objects.requireNonNull(event.getValue()).getSubject().getName());
        }
    }



    @Subscribe("scoreField")
    public void onScoreFieldValueChange(final HasValue.ValueChangeEvent<Double> event) {
        if (event.getValue() != null && getEditedEntity().getExam().getTotalScore()!=null){
            if (( event.getValue()  / getEditedEntity().getExam().getTotalScore())>=0.5){
                passedField.setValue(true);
            }
            else{
                passedField.setValue(false);
            }
        }

    }

    @Subscribe("examField")
    public void onExamFieldValueChange1(final HasValue.ValueChangeEvent<Exam> event) {
        if (event.getValue() != null){
            studentField.setEnabled(true);
            studentField.setOptionsList(loadStudentsForExam(event.getValue()));
        }
        else {
            studentField.setEnabled(false);
        }
    }

    private List<User> loadStudentsForExam(Exam value) {
        return dataManager.load(User.class)
                .query("select u from User u " +
                        "join u.exams e " +
                        "where e.id = :examsId")
                .parameter("examsId", value.getId())
                .list();

    }


    private ExamScore examScoreExist(User student) {
        ExamScore examScore = dataManager.load(ExamScore.class)
                .query("select e from ExamScore e where e.student = :student")
                .parameter("student", student)
                .optional()
                .orElse(null);
        return examScore;
    }


}