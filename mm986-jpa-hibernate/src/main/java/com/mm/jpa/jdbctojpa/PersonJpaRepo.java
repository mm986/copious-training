package com.mm.jpa.jdbctojpa;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonJpaRepo {

    @PersistenceContext
    EntityManager em;

//    public List<Person> findAll() {
//        return em.find(Person.class);
//    }

    public Person findById(int id) {
        return em.find(Person.class, id);
    }

    public void deleteById(int id) {
        Person person = findById(id);
        em.remove(person);
    }

    public Person insert(Person person) {
        return em.merge(person);
    }

    public Person update(Person person) {
        return em.merge(person);
    }
}
