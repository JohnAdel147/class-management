package com.company.classmanagment.screen.graduationproject;

import com.company.classmanagment.entity.GraduationProject;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.StandardLookup;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("GraduationProject.browse")
@UiDescriptor("graduation-project-browse.xml")
@LookupComponent("graduationProjectsTable")
public class GraduationProjectBrowse extends StandardLookup<GraduationProject> {
}