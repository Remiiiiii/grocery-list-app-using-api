package com.freelance.controllers;

import com.freelance.models.JsonResponse;
import com.freelance.models.User;
import com.freelance.services.UserService;

import io.javalin.http.Context;

public class UserController {

    UserService userService = new UserService();

    public void register(Context ctx) {

        User userCredentials = ctx.bodyAsClass(User.class);

        User userFromDb = userService.createUser(userCredentials);

        if (userFromDb == null) {

            ctx.json(new JsonResponse(false,
                    "Username already exists", null));

        } else {

            ctx.json(new JsonResponse(true, "User created", userFromDb));

        }

    }

}
