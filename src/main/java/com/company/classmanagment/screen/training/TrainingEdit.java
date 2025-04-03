package com.company.classmanagment.screen.training;

import com.company.classmanagment.entity.Training;
import com.company.classmanagment.entity.TrainingStatus;
import io.jmix.ui.screen.*;

@UiController("Training.edit")
@UiDescriptor("training-edit.xml")
@EditedEntityContainer("trainingDc")
public class TrainingEdit extends StandardEditor<Training> {
    @Subscribe
    public void onInitEntity(final InitEntityEvent<Training> event) {
        event.getEntity().setStatus(TrainingStatus.IN_PROGRASS);
    }

}