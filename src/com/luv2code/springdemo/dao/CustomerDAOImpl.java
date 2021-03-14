package com.luv2code.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

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
	@Transactional
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		
		Session session = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Customer> theQuery = session.createQuery("from customer",Customer.class);
		
		// execute query and get a list of customers
		List<Customer> customerList = theQuery.getResultList();
		
		// return the result
		return customerList;	
	}

}
