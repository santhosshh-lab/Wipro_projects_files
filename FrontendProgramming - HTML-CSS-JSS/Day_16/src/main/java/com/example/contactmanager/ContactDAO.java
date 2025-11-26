package com.example.contactmanager;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ContactDAO {
    private Map<Integer, Contact> contacts = new LinkedHashMap<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    private static ContactDAO instance = new ContactDAO();

    private ContactDAO() {
        // sample data
        add(new Contact(0, "Alice", "alice@example.com", "9990011111"));
        add(new Contact(0, "Bob", "bob@example.com", "9990022222"));
    }

    public static ContactDAO getInstance() {
        return instance;
    }

    public List<Contact> list() {
        return new ArrayList<>(contacts.values());
    }

    public Contact find(int id) {
        return contacts.get(id);
    }

    public synchronized Contact add(Contact c) {
        int id = idCounter.getAndIncrement();
        c.setId(id);
        contacts.put(id, c);
        return c;
    }

    public synchronized boolean update(Contact c) {
        if (contacts.containsKey(c.getId())) {
            contacts.put(c.getId(), c);
            return true;
        }
        return false;
    }

    public synchronized boolean delete(int id) {
        return contacts.remove(id) != null;
    }
}
