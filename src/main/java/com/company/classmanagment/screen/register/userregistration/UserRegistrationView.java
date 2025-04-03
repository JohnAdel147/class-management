package com.company.classmanagment.screen.register.userregistration;

import com.company.classmanagment.entity.User;
import com.company.classmanagment.service.RegistrationService;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Form;
import io.jmix.ui.component.TextField;
import io.jmix.ui.component.ValidationErrors;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("UserRegistrationView")
@UiDescriptor("user-registration-view.xml")
@Route(value = "user-registration")
public class UserRegistrationView extends Screen {

    @Autowired
    private ScreenValidation screenValidation;
    @Autowired
    private Form registerForm;
    @Autowired
    private TextField<String> emailField;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private Notifications notifications;
    @Autowired
    private TextField<String> firstNameField;
    @Autowired
    private TextField<String> lastNameField;
    @Autowired
    private Button registerBtn;

    @Subscribe("registerBtn")
    public void onRegisterBtnClick(final Button.ClickEvent event) {
        if(!validateFields()){
            return;
        }

        User user = registrationService.regesterNewUser(emailField.getValue(), firstNameField.getValue(), lastNameField.getValue());
        String activationToken = registrationService.generateRandomActivationToken();
        registrationService.saveActivationToken(user, activationToken);
        registrationService.sendActivationEmail(user);

        notifications.create()
                .withDescription("User registered successfully. Check your email inbox.")
                .show();

        registerForm.setEnabled(false);
        registerBtn.setEnabled(false);


    }

    private boolean validateFields (){
        ValidationErrors errors = screenValidation.validateUiComponents(registerForm);
        if(!errors.isEmpty()){
            screenValidation.showValidationErrors(this, errors);
            return false;
        }
        String email = emailField.getValue();

        if(registrationService.checkUserAlreadyExist(email)){
            notifications.create(Notifications.NotificationType.ERROR)
                    .withDescription("User with this email already exists")
                    .show();
            return false;
        }

        return true;
    }


}