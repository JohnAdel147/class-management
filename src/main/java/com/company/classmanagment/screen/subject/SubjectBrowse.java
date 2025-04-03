package com.company.classmanagment.screen.subject;

import io.jmix.core.common.util.ParamsMap;
import io.jmix.ui.WebBrowserTools;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.*;
import com.company.classmanagment.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Subject.browse")
@UiDescriptor("subject-browse.xml")
@LookupComponent("subjectsTable")
public class SubjectBrowse extends StandardLookup<Subject> {
    @Autowired
    private WebBrowserTools webBrowserTools;



    @Subscribe("subjectsTable.materials")
    public void onSubjectsTableMaterialsClick(final Table.Column.ClickEvent<Subject> event) {
        webBrowserTools.showWebPage(event.getItem().getMaterials(),ParamsMap.of("blank", "_self"));
    }


}