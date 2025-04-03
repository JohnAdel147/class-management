package com.company.classmanagment.screen.training;

import com.company.classmanagment.entity.Training;
import io.jmix.core.DataManager;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("Training.browse")
@UiDescriptor("training-browse.xml")
@LookupComponent("trainingsTable")
public class TrainingBrowse extends StandardLookup<Training> {


    @Autowired
    private CollectionContainer<Training> trainingsDc;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private CollectionLoader<Training> trainingsDl;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        updateTrainingStatuses();
    }

    private void updateTrainingStatuses() {
        List<Training> trainings = dataManager.load(Training.class)
                        .all().list();

        boolean hasChanges = false;

        for (Training training : trainings) {
            String oldStatus = training.getStatus().toString();
            training.updateStatus();
            if (!oldStatus.equals(training.getStatus().toString())) {
                hasChanges = true;
                dataManager.save(training);
            }
        }
        if (hasChanges) {
            trainingsDl.load();
        }
    }

}