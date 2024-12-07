package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;

import java.util.List;

public class ClientDemo {
  public static void main(String[] args) {
    // Create SessionFactory
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    Session session = factory.openSession();
    Transaction tx = session.beginTransaction();

    // Insert Records
    Customer cust1 = new Customer();
    cust1.setName("Alice");
    cust1.setEmail("alice@example.com");
    cust1.setAge(25);
    cust1.setLocation("New York");

    Customer cust2 = new Customer();
    cust2.setName("Bob");
    cust2.setEmail("bob@example.com");
    cust2.setAge(30);
    cust2.setLocation("California");

    session.save(cust1);
    session.save(cust2);

    tx.commit();

    // Criteria Queries
    Criteria criteria = session.createCriteria(Customer.class);

    
    System.out.println("\n--- Equal Example ---");
    criteria.add(Restrictions.eq("location", "New York"));
    List<Customer> result1 = criteria.list();
    result1.forEach(c -> System.out.println(c.getName()));

    
    System.out.println("\n--- Greater Than Example ---");
    criteria = session.createCriteria(Customer.class);
    criteria.add(Restrictions.gt("age", 25));
    List<Customer> result2 = criteria.list();
    result2.forEach(c -> System.out.println(c.getName()));

   
    System.out.println("\n--- Between Example ---");
    criteria = session.createCriteria(Customer.class);
    criteria.add(Restrictions.between("age", 20, 30));
    List<Customer> result3 = criteria.list();
    result3.forEach(c -> System.out.println(c.getName()));

   
    System.out.println("\n--- Like Example ---");
    criteria = session.createCriteria(Customer.class);
    criteria.add(Restrictions.like("name", "A%"));
    List<Customer> result4 = criteria.list();
    result4.forEach(c -> System.out.println(c.getName()));

    // Cleanup
    session.close();
    factory.close();
  }
}
