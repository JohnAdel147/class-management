package com.company.classmanagment.screen.attendance;

import com.company.classmanagment.entity.Attendance;
import com.company.classmanagment.entity.User;
import com.company.classmanagment.entity.WorkingDays;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.TextField;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.model.ScreenData;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UiController("Attendance.edit")
@UiDescriptor("attendance-edit.xml")
@EditedEntityContainer("attendanceDc")
public class AttendanceEdit extends StandardEditor<Attendance> {
    @Autowired
    private TextField<Double> earnedScoreField;
    @Autowired
    private InstanceContainer<Attendance> attendanceDc;
    @Autowired
    private EntityPicker<User> userField;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Metadata metadata;
    @Autowired
    private ScreenData screenData;

    @Subscribe("eventField")
    public void onEventFieldValueChange(final HasValue.ValueChangeEvent<WorkingDays> event) {
        if (event.getValue() != null){
            earnedScoreField.setValue(event.getValue().getAttendanceScore());
        }
    }

    @Subscribe("lateField")
    public void onLateFieldValueChange(final HasValue.ValueChangeEvent<Boolean> event) {
        if (earnedScoreField.getValue() != null){
            Double score= earnedScoreField.getValue();
            if (Boolean.TRUE.equals(event.getValue())){
//                getEditedEntity().setEarnedScore(score/2);
                earnedScoreField.setValue(score/2);
            }
            else{
//                getEditedEntity().setEarnedScore(score*2);
                earnedScoreField.setValue(score*2);
            }

        }
    }

//    @Subscribe
//    public void onInit(final InitEvent event) {
//        Map<String, Object> params = this.getEditedEntityLoader().getParameters();
//        List<User> selectedUsers = (List<User>) params.get("selectedUsers");
//        if (selectedUsers != null && !selectedUsers.isEmpty()) {
//            // If multiple users are passed, disable user selection
//            userField.setEditable(false);
//        }
//    }
//
//
//    @Subscribe("commitAndCloseBtn")
//    public void onCommitAndCloseBtnClick(final Button.ClickEvent event) {
//
//        Map<String, Object> params = this.getEditedEntityLoader().getParameters();
//        List<User> selectedUsers = (List<User>) params.get("selectedUsers");
//
//        WorkingDays dayEvent = getEditedEntity().getEvent();
//
//        if (selectedUsers != null && !selectedUsers.isEmpty()) {
//            List<Attendance> attendanceRecords = selectedUsers.stream()
//                    .map(user -> {
//                        Attendance attendance = metadata.create(Attendance.class);
//                        attendance.setUser(user);
//                        attendance.setEvent(dayEvent);
//                        attendance.setEarnedScore(getEditedEntity().getEarnedScore());
//                        attendance.setLate(getEditedEntity().getLate());
//                        return attendance;
//                    })
//                    .collect(Collectors.toList());
//
//            dataManager.save(attendanceRecords);
//            close(StandardOutcome.valueOf(StandardEditor.WINDOW_COMMIT_AND_CLOSE));
//        }
//    }
//



}