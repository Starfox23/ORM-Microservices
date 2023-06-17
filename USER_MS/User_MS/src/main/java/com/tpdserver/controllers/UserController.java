package com.tpdserver.controllers;

import com.tpdserver.models.User;
import com.tpdserver.services.UserService;
import jakarta.ws.rs.*;
import java.util.ArrayList;

@Path("/user")
public class UserController {
    private final UserService userService = new UserService();

    @GET
    @Produces("application/json")
    public ArrayList<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Produces("application/json")
    @Path("/{username}/{password}")
    public User getUserByUsernameAndPassword(@PathParam("username") String username, @PathParam("password") String password) {
        return userService.getUserByUsernameAndPassword(username, password);
    }

    @POST
    @Consumes("application/json")
    public boolean addUser(String response) {
        return userService.addUser(response);
    }
}
