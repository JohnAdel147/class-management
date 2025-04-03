package com.company.classmanagment.screen.classes;

import com.company.classmanagment.entity.Classes;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.StandardEditor;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("Classes.edit")
@UiDescriptor("classes-edit.xml")
@EditedEntityContainer("classesDc")
public class ClassesEdit extends StandardEditor<Classes> {
}