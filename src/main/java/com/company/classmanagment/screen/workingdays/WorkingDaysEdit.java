package com.company.classmanagment.screen.workingdays;

import com.company.classmanagment.entity.WorkingDays;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.StandardEditor;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("WorkingDays.edit")
@UiDescriptor("working-days-edit.xml")
@EditedEntityContainer("workingDaysDc")
public class WorkingDaysEdit extends StandardEditor<WorkingDays> {
}