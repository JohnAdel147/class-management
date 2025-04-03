package com.company.classmanagment.screen.exam;

import com.company.classmanagment.entity.Exam;
import com.company.classmanagment.entity.Grade;
import com.company.classmanagment.entity.Subject;
import com.company.classmanagment.entity.User;
import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("Exam.edit")
@UiDescriptor("exam-edit.xml")
@EditedEntityContainer("examDc")
public class ExamEdit extends StandardEditor<Exam> {
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Notifications notifications;

    @Subscribe("userTable.addAll")
    public void onUserTableAddAll(final Action.ActionPerformedEvent event) {
        if(getEditedEntity().getSubject() == null || getEditedEntity().getName() == null) {
            notifications.create(Notifications.NotificationType.ERROR)
                    .withCaption("Invalid Input")
                    .withDescription("you have to identify the subject and exam name first")
                    .show();
            return;
        }

        List<User> users = getAllUser(getEditedEntity().getSubject());
        if(users.isEmpty()) {
            notifications.create(Notifications.NotificationType.ERROR)
                    .withCaption("No Data")
                    .withDescription("There are no students with subject " + getEditedEntity().getSubject())
                    .show();
            return;
        }

        getEditedEntity().setUser(users);
    }

    private List<User> getAllUser(Subject subject) {
        List<User> users =  dataManager.load(User.class)
                .query("select distinct u from User u " +
                        "join u.subjects s " +
                        "where s = :subject")
                .parameter("subject", subject)
                .list();
        System.out.println("*****************************************************************");
        System.out.println(users);
        return users;
    }
}

