package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;


@Controller
@RequestMapping("/customer")
public class CutomerController {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@RequestMapping("list")
	public String listCustomers(Model theModel) {
		
		// get the customers from the DAO
		List<Customer> theCustomers = customerDAO.getCustomers();
		
		// add the customers to the model
		theModel.addAttribute("customers",theCustomers);
		
		return "list-customers";
	}

}