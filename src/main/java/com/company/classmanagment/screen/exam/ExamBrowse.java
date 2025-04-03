package com.company.classmanagment.screen.exam;

import com.company.classmanagment.entity.Exam;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.StandardLookup;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("Exam.browse")
@UiDescriptor("exam-browse.xml")
@LookupComponent("examsTable")
public class ExamBrowse extends StandardLookup<Exam> {
}