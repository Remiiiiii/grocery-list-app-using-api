package com.freelance.controllers;

import com.freelance.models.JsonResponse;
import com.freelance.models.User;
import com.freelance.services.UserService;

import io.javalin.http.Context;

public class SessionController {

    UserService userService = new UserService();

    public void login(Context ctx) {
        User credentials = ctx.bodyAsClass(User.class);

        if (userService.validateCredentials(credentials)) {
            User userFromDb = userService.getUserGivenUsername(credentials.getUsername());

            ctx.sessionAttribute("user", userFromDb);

            ctx.json(new JsonResponse(true, "login successful", credentials));
        } else {
            ctx.json(new JsonResponse(false, "invalid username or password", null));
        }
    }

    public void logout(Context ctx) {
        // setting the user object to null to invalidate session
        ctx.sessionAttribute("user", null);

        ctx.json(new JsonResponse(true, "logging out and invalidating session", null));
    }

    public void checkSession(Context ctx) {
        User user = ctx.sessionAttribute("user");

        if (user == null) {
            ctx.json(new JsonResponse(false, "no session found", null));
        } else {
            ctx.json(new JsonResponse(true, "session found", user));
        }
    }
}
