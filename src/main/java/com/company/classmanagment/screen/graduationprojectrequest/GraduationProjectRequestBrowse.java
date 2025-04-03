package com.company.classmanagment.screen.graduationprojectrequest;

import com.company.classmanagment.entity.GraduationProjectRequest;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.StandardLookup;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("GraduationProjectRequest.browse")
@UiDescriptor("graduation-project-request-browse.xml")
@LookupComponent("graduationProjectRequestsTable")
public class GraduationProjectRequestBrowse extends StandardLookup<GraduationProjectRequest> {
}