package com.example.springbootrest.dao;

import com.example.springbootrest.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User newUser, long id) {
        User userForUpdate = entityManager.find(User.class, id);
        userForUpdate = newUser;
        userForUpdate.setId(id);
        entityManager.merge(userForUpdate);
    }

    @Override
    public User getUserByEmail(String email) {
        Query query = entityManager.createQuery("from User where email=:login")
                .setParameter("login", email);
        try {
            return (User) query.getSingleResult();
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public void delete(long id) {
        User user = getUserById(id);
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }
}
