package com.company.classmanagment.screen.attendance;

import com.company.classmanagment.entity.Attendance;
import com.company.classmanagment.entity.User;
import com.company.classmanagment.entity.WorkingDays;
import com.company.classmanagment.screen.classes.ClassesBrowse;
import io.jmix.core.DataManager;
import io.jmix.ui.Dialogs;
import io.jmix.ui.Notifications;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.action.list.RefreshAction;
import io.jmix.ui.app.inputdialog.DialogActions;
import io.jmix.ui.app.inputdialog.DialogOutcome;
import io.jmix.ui.app.inputdialog.InputParameter;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@UiController("Attendance.browse")
@UiDescriptor("attendance-browse.xml")
@LookupComponent("attendancesTable")
public class AttendanceBrowse extends StandardLookup<Attendance> {
    @Named("attendancesTable.refresh")
    private RefreshAction attendancesTableRefresh;
    @Autowired
    private GroupTable<User> usersTable;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private Notifications notifications;
    @Autowired
    private Dialogs dialogs;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private CollectionLoader<User> usersDl;


    @Subscribe
    public void onAfterShow(final AfterShowEvent event) {
        usersDl.setParameter("student",true);
        usersDl.load();
    }

    @Install(to = "attendancesTable.create", subject = "afterCloseHandler")
    private void attendancesTableCreateAfterCloseHandler(final AfterCloseEvent afterCloseEvent) {
        attendancesTableRefresh.execute();
    }

    @Subscribe("usersTable.createAttendance")
    public void onUsersTableCreateAttendance(final Action.ActionPerformedEvent event) {
        dialogs.createInputDialog(this)
            .withCaption("choose Event")
            .withParameters(
                InputParameter.entityParameter("workingDay", WorkingDays.class)
                        .withCaption("event")
                        .withRequired(true),
                InputParameter.booleanParameter("late")
                        .withCaption("late")
            )
                .withActions(DialogActions.OK_CANCEL)
                    .withCloseListener(inputDialogCloseEvent ->{
                        if(inputDialogCloseEvent.closedWith(DialogOutcome.OK)){
                            WorkingDays workingDays = inputDialogCloseEvent.getValue("workingDay");
                            boolean late =  inputDialogCloseEvent.getValue("late");
                            usersTable.getSelected().forEach(student -> {
                                Attendance newAttendance = dataManager.create(Attendance.class);
                                newAttendance.setUser(student);
                                newAttendance.setEvent(workingDays);
                                assert workingDays != null;
                                if(late){
                                    newAttendance.setEarnedScore(workingDays.getAttendanceScore()/2);
                                }
                                else {
                                    newAttendance.setEarnedScore(workingDays.getAttendanceScore());
                                }
                                dataManager.save(newAttendance);
                            });
                            notifications.create(Notifications.NotificationType.TRAY)
                                    .withDescription("Successful")
                                    .show();
                        }
                    })
            .show();
    }

}