package sr.qualogy.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import sr.qualogy.entity.User;

import java.util.List;

public class WerknemerRepository {

    private EntityManager entityManager;

    public WerknemerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User saveWerknemer(User user){
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        return user;
    }

    public List<User> getWerknemers() {
        String sql = "select w from User w";
        TypedQuery<User> typedQuery = entityManager.createQuery(sql, User.class);
        List<User> users = typedQuery.getResultList();

        return users;
    }
}
