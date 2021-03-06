package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;



@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		
		Session session = sessionFactory.getCurrentSession();
		
		// create a query ... sort by the last name
		Query<Customer> theQuery = session.createQuery("from Customer order by lastName"
														,Customer.class);
		
		// execute query and get a list of customers
		List<Customer> customerList = theQuery.getResultList();
		
		// return the result
		return customerList;	
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the customer to the DB
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Customer theCustomer = session.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery =
				session.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
	}
	


}
