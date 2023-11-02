package webapp.dao;

import webapp.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User ", User.class).getResultList();
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void updateUser(int id, User user) {
        User updatedUser = entityManager.find(User.class, id);

        updatedUser.setName(user.getName());
        updatedUser.setLastName(user.getLastName());

        entityManager.flush();
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(entityManager.find(User.class, id));
        entityManager.flush();
    }
}
