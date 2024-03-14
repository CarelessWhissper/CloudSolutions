package sr.qualogy.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sr.qualogy.entity.Reservation;
import sr.qualogy.service.ReservationService;

import java.util.List;

@Path("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController() {
        this.reservationService = new ReservationService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveReservation(Reservation reservation) {
        reservation = reservationService.saveReservation(reservation);
        return Response.ok(reservation, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservationById(@PathParam("id") long id) {
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation != null) {
            return Response.ok(reservation, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateReservation(@PathParam("id") long id, Reservation reservation) {
        reservation.setId(id);
        reservation = reservationService.updateReservation(reservation);
        if (reservation != null) {
            return Response.ok(reservation, MediaType.APPLICATION_JSON_TYPE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReservationById(@PathParam("id") long id) {
        reservationService.deleteReservationById(id);
        return Response.noContent().build();
    }
}
