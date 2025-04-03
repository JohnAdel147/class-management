package com.company.classmanagment.screen.workingdays;

import com.company.classmanagment.entity.WorkingDays;
import io.jmix.ui.action.list.RefreshAction;
import io.jmix.ui.screen.*;

import javax.inject.Named;

@UiController("WorkingDays.browse")
@UiDescriptor("working-days-browse.xml")
@LookupComponent("workingDaysTable")
public class WorkingDaysBrowse extends StandardLookup<WorkingDays> {
    @Named("workingDaysTable.refresh")
    private RefreshAction workingDaysTableRefresh;

    @Install(to = "workingDaysTable.create", subject = "afterCloseHandler")
    private void workingDaysTableCreateAfterCloseHandler(final AfterCloseEvent afterCloseEvent) {
        workingDaysTableRefresh.execute();
    }

}