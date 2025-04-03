package com.company.classmanagment.screen.user;

import com.company.classmanagment.entity.User;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.StandardLookup;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("Student.browse")
@UiDescriptor("student-browse.xml")
@LookupComponent("usersTable")
public class StudentBrowse extends StandardLookup<User> {

}