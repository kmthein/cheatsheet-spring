package com.spring.repository;

import com.spring.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserRepository implements UserInterface {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public int saveUser(User user) {
        int result = 0;
        try {
            em.persist(user);
            result = 1;
        } catch (Exception e) {
            result = 0;
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public User findByEmail(String email) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        User user = null;
        try {
            user = (User) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            user = null;
        }
        return user;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try {
            user = em.find(User.class, id);
        } catch (Exception e) {
            user = null;
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Transactional
    @Override
    public int updateUser(User user) {
        System.out.println(user);
        int result = 0;
        try {
            em.merge(user);
            result = 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = 0;
        }
        return result;
    }
}
