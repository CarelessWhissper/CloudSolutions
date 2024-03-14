package sr.qualogy.repository;

import jakarta.persistence.EntityManager;
import sr.qualogy.entity.Reservation;

import java.util.List;

public class ReservationRepository {

    private EntityManager entityManager;

    public ReservationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Reservation saveReservation(Reservation reservation) {
        entityManager.getTransaction().begin();
        entityManager.persist(reservation);
        entityManager.getTransaction().commit();
        return reservation;
    }

    public List<Reservation> getAllReservations() {
        String sql = "SELECT r FROM Reservation r";
        return entityManager.createQuery(sql, Reservation.class).getResultList();
    }

    public Reservation getReservationById(long id) {
        return entityManager.find(Reservation.class, id);
    }

    public Reservation updateReservation(Reservation updatedReservation) {
        entityManager.getTransaction().begin();
        Reservation reservation = entityManager.find(Reservation.class, updatedReservation.getId());
        if (reservation != null) {
            // Update reservation attributes

        }
        entityManager.getTransaction().commit();
        return reservation;
    }

    public void deleteReservationById(long id) {
        entityManager.getTransaction().begin();
        Reservation reservation = entityManager.find(Reservation.class, id);
        if (reservation != null) {
            entityManager.remove(reservation);
        }
        entityManager.getTransaction().commit();
    }
}
