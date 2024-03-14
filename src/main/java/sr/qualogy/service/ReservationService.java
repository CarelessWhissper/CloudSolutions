package sr.qualogy.service;

import sr.qualogy.configuration.JPAConfiguration;
import sr.qualogy.entity.Reservation;
import sr.qualogy.repository.ReservationRepository;

import java.util.List;

public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService() {
        this.reservationRepository = new ReservationRepository(JPAConfiguration.getEntityManager());
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.getAllReservations();
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.saveReservation(reservation);
    }

    public Reservation getReservationById(long id) {
        return reservationRepository.getReservationById(id);
    }

    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.updateReservation(reservation);
    }

    public void deleteReservationById(long id) {
        reservationRepository.deleteReservationById(id);
    }
}
