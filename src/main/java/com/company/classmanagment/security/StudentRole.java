package com.company.classmanagment.security;

import com.company.classmanagment.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.UiMinimalRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "Student", code = StudentRole.CODE)
public interface StudentRole extends UiMinimalRole {
    String CODE = "student";

    @MenuPolicy(menuIds = {"Classes.browse", "Training.browse", "Exam.browse", "ExamScore.browse", "Subject.browse", "WorkingDays.browse", "Attendance.browse", "GraduationProject.browse", "GraduationProjectRequest.browse"})
    @ScreenPolicy(screenIds = {"Classes.browse", "Training.browse", "Classes.edit", "Training.edit", "Exam.browse", "ExamScore.browse", "Subject.browse", "WorkingDays.browse", "Attendance.browse", "Attendance.edit", "Exam.edit", "ExamScore.edit", "WorkingDays.edit", "GraduationProject.browse", "GraduationProjectRequest.browse", "GraduationProjectRequest.edit", "GraduationProject.edit"})
    void screens();

    @EntityPolicy(entityClass = Classes.class, actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityClass = Classes.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void classes();

    @EntityAttributePolicy(entityClass = Training.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Training.class, actions = EntityPolicyAction.READ)
    void training();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    void user();

    @EntityAttributePolicy(entityClass = Exam.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Exam.class, actions = EntityPolicyAction.READ)
    void exam();

    @EntityAttributePolicy(entityClass = ExamScore.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = ExamScore.class, actions = EntityPolicyAction.READ)
    void examScore();

    @EntityAttributePolicy(entityClass = Subject.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Subject.class, actions = EntityPolicyAction.READ)
    void subject();

    @EntityAttributePolicy(entityClass = Attendance.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Attendance.class, actions = EntityPolicyAction.READ)
    void attendance();

    @EntityAttributePolicy(entityClass = WorkingDays.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = WorkingDays.class, actions = EntityPolicyAction.READ)
    void workingDays();

    @EntityAttributePolicy(entityClass = GraduationProjectRequest.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = GraduationProjectRequest.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE, EntityPolicyAction.CREATE})
    void graduationProjectRequest();

    @EntityAttributePolicy(entityClass = GraduationProject.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = GraduationProject.class, actions = EntityPolicyAction.READ)
    void graduationProject();
}