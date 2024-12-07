package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        // Configuring Hibernate
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        // Setting up the SessionFactory and starting a session
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();

        // Beginning transaction
        Transaction tx = session.beginTransaction();

        // Adding client records
        addClient(session, "Alice", "Female", 30, "New York", "alice@example.com", "1234567890");
        addClient(session, "Bob", "Male", 25, "California", "bob@example.com", "0987654321");

        // Fetching and displaying all client records
        fetchAndDisplayClients(session);

        // Finalizing transaction and closing resources
        tx.commit();
        session.close();
        factory.close();
    }

    private static void addClient(Session session, String name, String gender, int age, String city, String email, String phone) {
        // Creating a new Client object
        Client client = new Client();
        client.setName(name);
        client.setGender(gender);
        client.setAge(age);
        client.setLocation(city);
        client.setEmail(email);
        client.setMobile(phone);

        // Persisting the client object
        session.save(client);
        System.out.println("Added client: " + name);
    }

    private static void fetchAndDisplayClients(Session session) {
        // Using HQL to retrieve all client records
        List<Client> clients = session.createQuery("from Client", Client.class).getResultList();

        // Displaying each client record
        for (Client client : clients) {
            System.out.println("Client Details - ID: " + client.getId() +
                    ", Name: " + client.getName() +
                    ", Gender: " + client.getGender() +
                    ", Age: " + client.getAge() +
                    ", City: " + client.getLocation() +
                    ", Email: " + client.getEmail() +
                    ", Phone: " + client.getMobile());
        }
    }
}
