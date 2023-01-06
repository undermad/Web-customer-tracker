package com.example.webcustomertracker.dao;

import com.example.webcustomertracker.SortUtils;
import com.example.webcustomertracker.pojo.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveCustomer(Customer customer) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public List<Customer> getCustomers(int sort) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Customer> query;
        switch (sort) {
            case SortUtils.FIRST_NAME:
                query = currentSession.createQuery("from Customer order by firstName", Customer.class);
                break;
            case SortUtils.LAST_NAME:
                query = currentSession.createQuery("from Customer order by lastName", Customer.class);
                break;
            case SortUtils.EMAIL:
                query = currentSession.createQuery("from Customer order by email", Customer.class);
                break;
            default:
                query = currentSession.createQuery("from Customer order by email", Customer.class);
        }

        return query.getResultList();
    }

    @Override
    public Customer getCustomer(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Customer.class, id);
    }

    @Override
    public void deleteCustomer(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.createQuery("delete from Customer c where c.id=" + id).executeUpdate();
    }

    @Override
    public List<Customer> searchCustomer(String customerName) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = null;

        if (customerName == null || customerName.trim().length() == 0) {
            query = currentSession.createQuery("from Customer", Customer.class);
        } else {
            query = currentSession.createQuery("from Customer where lower(firstName) like :theName" +
                    " or lower(lastName) like :theName", Customer.class);
            query.setParameter("theName", "%" + customerName.toLowerCase() + "%");
        }
        return query.getResultList();
    }
}
