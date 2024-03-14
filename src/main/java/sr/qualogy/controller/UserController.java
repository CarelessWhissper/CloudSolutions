package sr.qualogy.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sr.qualogy.entity.User;
import sr.qualogy.service.UserService;

import java.util.List;

@Path("/user")
public class UserController {

    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    @Path("/hello")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String helloWorld(){
        return "Hello World";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @Path("/user/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUser(User user) {
        user = userService.saveUser(user);
        return Response.ok(user, MediaType.APPLICATION_JSON_TYPE).build();
    }



    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") Integer id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User with ID " + id + " not found").build();
        }
        return Response.ok(user, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("/{id}")
    @DELETE
    public Response deleteUserById(@PathParam("id") Integer id) {
        userService.deleteUserById(id);
        return Response.noContent().build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserById(@PathParam("id") Integer id, User user) {
        user.setId(id);
        User updatedUser = userService.updateUser(user);
        return Response.ok(updatedUser, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("/signIn")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signIn(User credentials) {
        User authenticatedUser = userService.authenticateUser(credentials.getEmail(), credentials.getPassword());
        if (authenticatedUser == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid email or password").build();
        }
        return Response.ok(authenticatedUser, MediaType.APPLICATION_JSON_TYPE).build();
    }
}
