package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class App {
  public static void main(String[] args) {
    // Initialize SessionFactory
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    Session session = factory.openSession();

    // Start transaction
    Transaction transaction = session.beginTransaction();

    // Create Customer objects
    Customer customer1 = new Customer();
    customer1.setName("Alice");
    customer1.setEmail("alice@example.com");
    customer1.setAge(25);
    customer1.setLocation("New York");

    Customer customer2 = new Customer();
    customer2.setName("Bob");
    customer2.setEmail("bob@example.com");
    customer2.setAge(30);
    customer2.setLocation("California");

    Customer customer3 = new Customer();
    customer3.setName("Charlie");
    customer3.setEmail("charlie@example.com");
    customer3.setAge(28);
    customer3.setLocation("Texas");

    // Save the Customer objects to the database
    session.save(customer1);
    session.save(customer2);
    session.save(customer3);

    // Commit transaction
    transaction.commit();

    System.out.println("Data inserted successfully!");

    // Close session and factory
    session.close();
    factory.close();
  }
}
