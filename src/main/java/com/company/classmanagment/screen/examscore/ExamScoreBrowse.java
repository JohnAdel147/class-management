package com.company.classmanagment.screen.examscore;

import com.company.classmanagment.entity.ExamScore;
import io.jmix.ui.action.list.RefreshAction;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@UiController("ExamScore.browse")
@UiDescriptor("exam-score-browse.xml")
@LookupComponent("examScoresTable")
public class ExamScoreBrowse extends StandardLookup<ExamScore> {
    @Autowired
    private CollectionLoader<ExamScore> examScoresDl;
    @Autowired
    private CollectionContainer<ExamScore> examScoresDc;
    @Autowired
    private DataContext dataContext;
    @Named("examScoresTable.refresh")
    private RefreshAction examScoresTableRefresh;

    @Install(to = "examScoresTable.create", subject = "afterCloseHandler")
    private void examScoresTableCreateAfterCloseHandler(final AfterCloseEvent afterCloseEvent) {
        examScoresTableRefresh.execute();
    }

}