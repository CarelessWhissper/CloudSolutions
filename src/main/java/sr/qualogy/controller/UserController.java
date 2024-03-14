package sr.qualogy.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sr.qualogy.entity.User;
import sr.qualogy.service.WerknemerService;

import java.util.List;

@Path("/werknemer")
public class WerknemerController {

    private final WerknemerService werknemerService;

    public WerknemerController() {
        this.werknemerService = new WerknemerService();
    }

    @Path("/hello")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String helloWorld(){
        return "Hello World";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getWerknemers(){
        return werknemerService.getWerknemers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveWerknemer(User user) {
        user = werknemerService.saveWerknemer(user);
        return Response.ok(user, MediaType.APPLICATION_JSON_TYPE).build();
    }
}
