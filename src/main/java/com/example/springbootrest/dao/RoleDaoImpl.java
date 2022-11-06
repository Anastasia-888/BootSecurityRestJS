package com.example.springbootrest.dao;

import com.example.springbootrest.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("FROM Role ", Role.class).getResultList();
    }

    @Override
    public Role getByName(String name) {
        Query query = entityManager.createQuery("from Role where role=:name")
                .setParameter("name", name);
        try {
            return (Role) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}

