package com.luv2code.springdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("list")
	public String listCustomers(Model theModel) {
		
		// get the customers from the service
		List<Customer> theCustomers = customerService.getCustomers();
		
		// add the customers to the model
		theModel.addAttribute("customers",theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form the data
		Customer newCustomer = new Customer();
		
		theModel.addAttribute("customer",newCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer")Customer theCustomer, BindingResult theBindingResult) {
		if(theBindingResult.hasErrors()) {
			return "customer-form";
		} 
		// save the customer using service
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFromForUpdate(@RequestParam("customerId") int theId,
									Model theModel) {
		
		// get the customer from service
		Customer theCustomer = customerService.getCustomer(theId);
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer",theCustomer);
		
		// send over to from
		return "customer-form";

	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		// delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
}
