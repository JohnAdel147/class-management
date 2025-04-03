package com.company.classmanagment.screen.classes;

import com.company.classmanagment.entity.Classes;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.StandardLookup;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("Classes.browse")
@UiDescriptor("classes-browse.xml")
@LookupComponent("classesesTable")
public class ClassesBrowse extends StandardLookup<Classes> {
}