package com.learning.springhibernateudemy.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.learning.springhibernateudemy.entity.Customer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private static final List<Customer> customers = new ArrayList<>();

    @Override
    public List<Customer> getCustomers() {
        Session session = this.sessionFactory.getCurrentSession();

        Query<Customer> theQuery = session.createQuery("from Customer order by lastName", Customer.class);

        return theQuery.getResultList();

        // if (customers.isEmpty()) {
        // customers.add(new Customer(1, "John", "Doe", "jd@gmail.com"));
        // customers.add(new Customer(2, "James", "Bond", "jBond@gmail.com"));
        // customers.add(new Customer(3, "Jack", "Rayn", "jRayn@gmail.com"));
        // customers.add(new Customer(4, "Steve", "Robert", "sRobert@gmail.com"));
        // }

        // customers.sort((obj1, obj2) ->
        // obj1.getLastName().compareTo(obj2.getLastName()));

        // return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();

        session.saveOrUpdate(customer);

        // if (customer.getId() == 0) {
        // customer.setId(customers.size() + 1);
        // customers.add(customer);
        // } else {
        // Customer existingCustomer = this.getCustomer(customer.getId());
        // existingCustomer.setFirstName(customer.getFirstName());
        // existingCustomer.setLastName(customer.getLastName());
        // existingCustomer.setEmail(customer.getEmail());
        // }
    }

    @Override
    public Customer getCustomer(int id) {
        Session session = this.sessionFactory.getCurrentSession();

        return session.get(Customer.class, id);

        // return customers.stream().filter(obj -> obj.getId() ==
        // id).findFirst().orElse(null);
    }

    @Override
    public void deleteCustomer(int id) {
        Session session = this.sessionFactory.getCurrentSession();

        Query theQuery = session.createQuery("delete from Customer where id=:customerId");
        theQuery.setParameter("customerId", id);

        theQuery.executeUpdate();

        // for (Customer customer : customers) {
        // if (customer.getId() == id) {
        // customers.remove(customer);
        // break;
        // }
        // }
    }

    @Override
    public List<Customer> searchCustomers(String searchText) {
        Session session = this.sessionFactory.getCurrentSession();

        Query<Customer> theQuery = session.createQuery(
                "from Customer where lower(firstName) like :searchText or lower(lastName) like :searchText");
        theQuery.setParameter("searchText", "%" + searchText.toLowerCase() + "%");

        return theQuery.getResultList();

        // return customers.stream()
        // .filter(obj ->
        // obj.getFirstName().toLowerCase().contains(searchText.toLowerCase())
        // || obj.getLastName().toLowerCase().contains(searchText.toLowerCase()))
        // .collect(Collectors.toList());
    }
}