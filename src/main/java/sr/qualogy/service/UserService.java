package sr.qualogy.service;

import sr.qualogy.configuration.JPAConfiguration;
import sr.qualogy.entity.User;
import sr.qualogy.repository.WerknemerRepository;

import java.util.List;

public class WerknemerService {

    private final WerknemerRepository werknemerRepository;

    public WerknemerService() {
        this.werknemerRepository = new WerknemerRepository(JPAConfiguration.getEntityManager());
    }

    public List<User> getWerknemers(){
        return werknemerRepository.getWerknemers();
    }

    public User saveWerknemer(User user) {
        return werknemerRepository.saveWerknemer(user);
    }
}
