package com.akash.SpringBootJPA;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonHibernateDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        SessionFactory factory = this.sessionFactory;
        Session session = factory.openSession();
        return session;
    }

    public void savePerson(Person person){
        Session session  = getSession();
        Transaction tx = session.beginTransaction();

        Long id = (Long) session.save(person);
        tx.commit();
    }
}
