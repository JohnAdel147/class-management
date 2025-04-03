package com.company.classmanagment.security;

import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "AnonymousRole", code = AnonymousRole.CODE)
public interface AnonymousRole {
    String CODE = "anonymous-role";

    @MenuPolicy(menuIds = "UserRegistrationView")
    @ScreenPolicy(screenIds = {"UserRegistrationView", "LoginScreen", "UserActivationView"})
    void screens();


}