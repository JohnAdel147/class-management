package com.company.classmanagment.service;

import com.company.classmanagment.entity.Grade;
import com.company.classmanagment.entity.User;

public interface RegistrationService {

    boolean checkUserAlreadyExist(String email);
    User regesterNewUser (String email, String firstName, String lastName);
    String generateRandomActivationToken();
    void saveActivationToken(User user, String token);
    void sendActivationEmail(User user);
    User loadUserByActivationToken(String token);
    void ActivateUser(User user, String password, Grade grade);
}
