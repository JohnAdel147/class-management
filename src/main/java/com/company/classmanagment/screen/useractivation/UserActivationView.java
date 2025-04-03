package com.company.classmanagment.screen.useractivation;

import com.company.classmanagment.entity.Grade;
import com.company.classmanagment.entity.User;
import com.company.classmanagment.service.RegistrationService;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.*;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.navigation.UrlParamsChangedEvent;
import io.jmix.ui.screen.*;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("UserActivationView")
@UiDescriptor("user-activation-view.xml")
@Route(value = "activate")
public class UserActivationView extends Screen {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private Label<Object> welcomeHeader;
    @Autowired
    private Form form;
    @Autowired
    private Button activeBtn;
    @Autowired
    private Label<Object> notFoundHeader;
    @Autowired
    private Button returnToLoginBtn;
    @Autowired
    private ScreenValidation screenValidation;
    @Autowired
    private PasswordField passwordField;
    @Autowired
    private ComboBox<Grade> gradeField;

    private User user;
    private boolean initialized = false;
    @Autowired
    private PasswordField confirmPasswordField;
    @Autowired
    private Notifications notifications;

    @Subscribe
    public void onUrlParamsChanged(final UrlParamsChangedEvent event) {

        String receivedActivationToken = event.getParams().get("token");

        if (StringUtils.isNotEmpty(receivedActivationToken)) {
            user = registrationService.loadUserByActivationToken(receivedActivationToken);
        }
        else {
            user = null;
        }
        
        updateUI();

    }

    @Subscribe("activeBtn")
    public void onActiveBtnClick(final Button.ClickEvent event) {
        if (!validateFields()) {
            return;
        }

        String password = passwordField.getValue();
        Grade grade = gradeField.getValue();

        if(password != null && !password.equals(confirmPasswordField.getValue())){
            notifications.create(Notifications.NotificationType.ERROR)
                    .withDescription("confirm password doesn't match password")
                    .show();
            return;
        }

        registrationService.ActivateUser(user, password, grade);
        notifications.create(Notifications.NotificationType.TRAY)
                .withDescription("the account activate successful")
                .show();
        form.setEnabled(false);
        activeBtn.setEnabled(false);
    }
    
    

    @Subscribe
    public void onAfterShow(final AfterShowEvent event) {
        updateUI();
    }
    
    
    private void updateUI() {
        boolean success = user != null;

        welcomeHeader.setVisible(success);
        form.setEnabled(success);
        activeBtn.setVisible(success);

        notFoundHeader.setVisible(!success);
        returnToLoginBtn.setVisible(!success);

        if (user != null) {
            welcomeHeader.setValue("Welcome "+ user.getFirstName() + " " + user.getLastName());
        }
        initialized = true;

    }

    private boolean validateFields () {
        ValidationErrors errors = screenValidation.validateUiComponents(form);
        if (!errors.isEmpty()) {
            screenValidation.showValidationErrors(this, errors);
            return false;
        }
        return true;
    }


}