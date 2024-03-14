package sr.qualogy.service;

import sr.qualogy.configuration.JPAConfiguration;
import sr.qualogy.entity.Location;
import sr.qualogy.repository.LocationRepository;

import java.util.List;

public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService() {
        this.locationRepository = new LocationRepository(JPAConfiguration.getEntityManager());
    }

    public List<Location> getAllLocations() {
        return locationRepository.getAllLocations();
    }

    public Location getLocationById(Integer id) {
        return locationRepository.getLocationById(id);
    }

    public Location saveLocation(Location location) {
        return locationRepository.saveLocation(location);
    }

    public void deleteLocationById(Integer id) {
        locationRepository.deleteLocationById(id);
    }

    public Location updateLocation(Location location) {
        return locationRepository.updateLocation(location);
    }
}
