package sr.qualogy.repository;

import jakarta.persistence.EntityManager;
import sr.qualogy.entity.User;

import java.util.List;

public class UserRepository {

    private final EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User saveUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    public List<User> getUsers() {
        String sql = "SELECT u FROM User u";
        return entityManager.createQuery(sql, User.class).getResultList();
    }

    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    public void deleteUserById(int id) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
        entityManager.getTransaction().commit();
    }

    public User updateUser(User updatedUser) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, updatedUser.getId());
        if (user != null) {
            user.setNaam(updatedUser.getNaam());
            user.setEmail(updatedUser.getEmail());
            user.setPhoneNumber(updatedUser.getPhoneNumber());

        }
        entityManager.getTransaction().commit();
        return user;
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT u FROM User u WHERE u.email = :email";
        return entityManager.createQuery(sql, User.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }



}
