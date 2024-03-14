package sr.qualogy.repository;


import jakarta.persistence.EntityManager;
import sr.qualogy.entity.Location;

import java.util.List;

public class LocationRepository {

    private EntityManager entityManager;

    public LocationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Location saveLocation(Location location) {
        entityManager.getTransaction().begin();
        entityManager.persist(location);
        entityManager.getTransaction().commit();
        return location;
    }

    public List<Location> getAllLocations() {
        String sql = "SELECT l FROM Location l";
        return entityManager.createQuery(sql, Location.class).getResultList();
    }

    public Location getLocationById(int id) {
        return entityManager.find(Location.class, id);
    }

    public void deleteLocationById(int id) {
        entityManager.getTransaction().begin();
        Location location = entityManager.find(Location.class, id);
        if (location != null) {
            entityManager.remove(location);
        }
        entityManager.getTransaction().commit();
    }

    public Location updateLocation(Location updatedLocation) {
        entityManager.getTransaction().begin();
        Location location = entityManager.find(Location.class, updatedLocation.getId());
        if (location != null) {
            location.setName(updatedLocation.getName());

        }
        entityManager.getTransaction().commit();
        return location;
    }
}
