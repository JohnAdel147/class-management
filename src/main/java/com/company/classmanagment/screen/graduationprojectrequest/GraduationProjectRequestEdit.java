package com.company.classmanagment.screen.graduationprojectrequest;

import com.company.classmanagment.entity.GraduationProject;
import com.company.classmanagment.entity.GraduationProjectRequest;
import com.company.classmanagment.entity.Status;
import com.company.classmanagment.entity.User;
import com.company.classmanagment.security.StudentRole;
import io.jmix.core.DataManager;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.core.impl.method.UserArgumentResolver;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.ComboBox;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@UiController("GraduationProjectRequest.edit")
@UiDescriptor("graduation-project-request-edit.xml")
@EditedEntityContainer("graduationProjectRequestDc")
public class GraduationProjectRequestEdit extends StandardEditor<GraduationProjectRequest> {
    @Autowired
    private ComboBox<Object> acceptedProject;
    @Autowired
    private EntityPicker<GraduationProject> firstProjectField;
    @Autowired
    private EntityPicker<GraduationProject> secondProjectField;
    @Autowired
    private EntityPicker<GraduationProject> thirdProjectField;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private Button approveBtn;
    @Autowired
    private Button rejectBtn;
    @Autowired
    private EntityPicker<User> studentField;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private UnconstrainedDataManager unconstrainedDataManager;
    @Autowired
    private UserArgumentResolver userArgumentResolver;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        if(currentAuthentication.getUser().getAuthorities().stream().anyMatch(
                a ->a.getAuthority().contains(StudentRole.CODE))){
            acceptedProject.setVisible(false);
            approveBtn.setVisible(false);
            rejectBtn.setVisible(false);
            studentField.setEditable(false);
        }

    }


    @Subscribe
    public void onAfterShow(final AfterShowEvent event) {
        if(studentField.isEmpty()){
            studentField.setValue((User) currentAuthentication.getUser());
        }

        if(firstProjectField.getValue() != null &&
                secondProjectField.getValue() !=null &&
                thirdProjectField.getValue() !=null )
        {
            List<Object> graduationProjectList = new ArrayList<>();
            graduationProjectList.add(firstProjectField.getValue().getName());
            graduationProjectList.add(secondProjectField.getValue().getName());
            graduationProjectList.add(thirdProjectField.getValue().getName());
            acceptedProject.setOptionsList(graduationProjectList);
        }

    }

    @Subscribe
    public void onBeforeCommitChanges(final BeforeCommitChangesEvent event) {
        if(getEditedEntity().getStatus() == null){
            getEditedEntity().setStatus(Status.PENDING);
        }
    }

    @Subscribe("approve")
    public void onApprove(final Action.ActionPerformedEvent event) {
        getEditedEntity().setStatus(Status.ACCEPTED);
        GraduationProject graduationProject = findGraduationProject(acceptedProject.getValue());
        User student = studentField.getValue();
        if (graduationProject != null && student != null){
            student.setGraduationProject(graduationProject);
            graduationProject.getStudents().add(student);
            commitAndClose(event);
        }
    }

    @Subscribe("reject")
    public void onReject(final Action.ActionPerformedEvent event) {
        getEditedEntity().setStatus(Status.REJECTED);
        GraduationProject graduationProject = findGraduationProject(acceptedProject.getValue());
        User student = studentField.getValue();
        if (graduationProject != null && student != null){
            student.setGraduationProject(null);
            graduationProject.getStudents().remove(student);
            acceptedProject.setValue(null);
            commitAndClose(event);
        }
    }

    private GraduationProject findGraduationProject(Object project) {

        return dataManager.load(GraduationProject.class)
                .query("select g from GraduationProject g left join fetch g.students where g.name = :project")
                .parameter("project", project)
                .optional()
                .orElse(null);

    }
    
    
}