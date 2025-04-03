package com.company.classmanagment.screen.subjects;

import com.company.classmanagment.entity.Grade;
import com.company.classmanagment.entity.Subject;
import com.company.classmanagment.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.action.list.RefreshAction;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.List;

@UiController("Subject.edit")
@UiDescriptor("subjects-edit.xml")
@EditedEntityContainer("subjectsDc")
public class SubjectsEdit extends StandardEditor<Subject> {
    @Autowired
    private Notifications notifications;
    @Autowired
    private UnconstrainedDataManager unconstrainedDataManager;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Table<User> usersTable;
    @Named("usersTable.refresh")
    private RefreshAction usersTableRefresh;

//    @Transactional
//    @Subscribe("usersTable.addAll")
//    public void onUsersTableAddAll(final Action.ActionPerformedEvent event) {
//        if(getEditedEntity().getSemester() == null || getEditedEntity().getName() == null) {
//            notifications.create(Notifications.NotificationType.ERROR)
//                    .withCaption("Invalid Input")
//                    .withDescription("you have to identify the subject name and semester first")
//                    .show();
//            return;
//        }
//
//        List<User> users = getAllUser(getEditedEntity().getSemester());
//        if(users == null || users.isEmpty()) {
//            notifications.create(Notifications.NotificationType.ERROR)
//                    .withCaption("No Data")
//                    .withDescription("There are no students in semester " + getEditedEntity().getSemester())
//                    .show();
//            return;
//        }
//
//        // First save the new subject
//        Subject savedSubject = dataManager.save(getEditedEntity());
//        commit(event);
//
//        for (User user:users){
//                    User userWithSubject =dataManager.load(User.class)
//                            .id(user.getId())
//                            .fetchPlanProperties("subjects")
//                            .one();
//                    userWithSubject.getSubjects().add(savedSubject);
//                    dataManager.save(userWithSubject);
//                }
//
//
//    }


    @Subscribe("usersTable.addAll")
    public void onUsersTableAddAll(final Action.ActionPerformedEvent event) {
        if(getEditedEntity().getSemester() == null || getEditedEntity().getName() == null) {
            notifications.create(Notifications.NotificationType.ERROR)
                    .withCaption("Invalid Input")
                    .withDescription("you have to identify the subject name and semester first")
                    .show();
            return;
        }

        List<User> users = getAllUser(getEditedEntity().getSemester());
        if(users.isEmpty()) {
            notifications.create(Notifications.NotificationType.ERROR)
                    .withCaption("No Data")
                    .withDescription("There are no students in semester " + getEditedEntity().getSemester())
                    .show();
            return;
        }

        getEditedEntity().setUsers(users);

    }

    private List<User> getAllUser(Grade semester) {
        List<User> users =  dataManager.load(User.class)
                .query("select u from User u " +
                        "where u.semester = :semester1")
                .parameter("semester1", semester)
                .list();
        System.out.println("*****************************************************************");
        System.out.println(users);
        return users;
    }



}