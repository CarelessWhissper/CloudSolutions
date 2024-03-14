package sr.qualogy.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sr.qualogy.entity.Location;
import sr.qualogy.service.LocationService;

import java.util.List;

@Path("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController() {
        this.locationService = new LocationService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocationById(@PathParam("id") int id) {
        Location location = locationService.getLocationById(id);
        if (location != null) {
            return Response.ok(location, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveLocation(Location location) {
        location = locationService.saveLocation(location);
        return Response.ok(location, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLocationById(@PathParam("id") int id) {
        locationService.deleteLocationById(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLocation(@PathParam("id") int id, Location updatedLocation) {
        if (updatedLocation.getId() != id) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Location location = locationService.updateLocation(updatedLocation);
        return Response.ok(location, MediaType.APPLICATION_JSON_TYPE).build();
    }
}
