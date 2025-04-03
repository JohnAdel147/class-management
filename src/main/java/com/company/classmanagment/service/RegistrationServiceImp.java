package com.company.classmanagment.service;

import com.company.classmanagment.entity.Grade;
import com.company.classmanagment.entity.User;
import com.company.classmanagment.security.StudentRole;
import com.company.classmanagment.security.StudentRowLevelRole;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.security.role.assignment.RoleAssignmentRoleType;
import io.jmix.securitydata.entity.RoleAssignmentEntity;
import io.jmix.securityui.role.UiMinimalRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RegistrationServiceImp implements RegistrationService{
    private final UnconstrainedDataManager unconstrainedDataManager;
    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImp(UnconstrainedDataManager unconstrainedDataManager, PasswordEncoder passwordEncoder) {
        this.unconstrainedDataManager = unconstrainedDataManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean checkUserAlreadyExist(String email) {
        List<User> list =  unconstrainedDataManager.load(User.class)
                .query("select u from User u where u.email = :email "
                        + "or u.username = :email")
                .parameter("email", email)
                .list();
        return !list.isEmpty();
    }

    @Override
    public User regesterNewUser(String email, String firstName, String lastName) {
        User user = unconstrainedDataManager.create(User.class);
        user.setUsername(email);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setStudent(true);
        user.setActive(false);
        user.setNeedActivation(true);

        return unconstrainedDataManager.save(user);
    }

    @Override
    public String generateRandomActivationToken() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;

        ThreadLocalRandom current = ThreadLocalRandom.current();
        String generatedString = current.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    @Override
    public void saveActivationToken(User user, String token) {
        user = unconstrainedDataManager.load(User.class)
                .id(user.getId())
                .one();
        user.setActivationToken(token);
        unconstrainedDataManager.save(user);
    }


    @Override
    public void sendActivationEmail(User user) {
        user = unconstrainedDataManager.load(User.class)
                .id(user.getId())
                .one();

        String activationLink = "http://localhost:8080/#activate?token=" +
                user.getActivationToken();

        String subject = "Jmix PM activation";
        String body = String.format("Hello, %s %s \n Please finish your Registration \n Activation link: %s",
                user.getFirstName(),
                user.getLastName(),
                activationLink);

        System.out.println(activationLink);

    }

    @Override
    public User loadUserByActivationToken(String token) {
        User user = unconstrainedDataManager.load(User.class)
                .query("select u from User u " +
                        "where u.activationToken = :Token")
                .parameter("Token", token)
                .optional()
                .orElse(null);
        return user;
    }

    @Override
    public void ActivateUser(User user, String password, Grade grade) {
        String encodedPassword = passwordEncoder.encode(password);

        user.setSemester(grade);
        user.setPassword(encodedPassword);
        user.setNeedActivation(false);
        user.setActivationToken(null);
        user.setActive(true);

        RoleAssignmentEntity assignment1 = unconstrainedDataManager.create(RoleAssignmentEntity.class);
        assignment1.setUsername(user.getUsername());
        assignment1.setRoleType(RoleAssignmentRoleType.RESOURCE);
        assignment1.setRoleCode( StudentRole.CODE);


        RoleAssignmentEntity assignment2 = unconstrainedDataManager.create(RoleAssignmentEntity.class);
        assignment2.setUsername(user.getUsername());
        assignment2.setRoleType(RoleAssignmentRoleType.ROW_LEVEL);
        assignment2.setRoleCode( StudentRowLevelRole.CODE);

//        RoleAssignmentEntity assignment3 = unconstrainedDataManager.create(RoleAssignmentEntity.class);
//        assignment3.setUsername(user.getUsername());
//        assignment3.setRoleType(RoleAssignmentRoleType.RESOURCE);
//        assignment3.setRoleCode(UiMinimalRole.CODE);

        unconstrainedDataManager.save(user, assignment1, assignment2);
    }

}
