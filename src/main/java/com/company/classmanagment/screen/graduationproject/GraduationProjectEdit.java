package com.company.classmanagment.screen.graduationproject;

import com.company.classmanagment.entity.GraduationProject;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.StandardEditor;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("GraduationProject.edit")
@UiDescriptor("graduation-project-edit.xml")
@EditedEntityContainer("graduationProjectDc")
public class GraduationProjectEdit extends StandardEditor<GraduationProject> {
}