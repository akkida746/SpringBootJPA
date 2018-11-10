package com.akash.SpringBootJPA;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class PersonDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public long getTotalConnectionsCount(){
        return getSession().getSessionFactory().getStatistics().getConnectCount();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public String savePerson(Person person) {
        Long isSuccess = (Long)getSession().save(person);
        if(isSuccess >= 1){
            return "Success";
        }else{
            return "Error while Saving Person";
        }

    }

    public boolean delete(Person person) {
        getSession().delete(person);
        return true;
    }

    @SuppressWarnings("unchecked")
    public List getAllPersons() {
        return getSession().createQuery("from Person").list();
    }
}
