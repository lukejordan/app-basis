package com.fam.controller;

import java.net.URI;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.domain.CustomerRepository;
import com.fam.Example;
import com.lukeyj.example.data.Job;
import com.lukeyj.example.data.Person;
import com.lukeyj.example.data.persistence.CustomerEntity;

@RestController
@RequestMapping("/persist")
public class PersistenceController {

	private static Logger LOGGER = Logger.getLogger(Example.class.getName());
	
	@Autowired
	private CustomerRepository customerRepo;
	

	  @Transactional
	  @RequestMapping(value = "/persist", method = RequestMethod.POST)
	   public ResponseEntity testPersist(@RequestBody Person person) {
		  
	      LOGGER.info("test submiteed");
	      LOGGER.info("key: " + person.getFirstName());

	      Person person2 = new Person ();
	      
	      person2.setAge(21);
	      person2.setFirstName(person.getFirstName());
	      person2.setLastName("gyuguyguygy");

	      Job job = new Job();
	      job.setCompany("company2");
	      job.setRole("role1");
	      
	      person2.setJob(job);
	      
	      if (customerRepo != null) {
		      displayAllCustomers();
		      
		      CustomerEntity save2 = customerRepo.save(new CustomerEntity("rah", "bees"));
		      CustomerEntity save = customerRepo.save(new CustomerEntity("moo", "cheese"));
		      
		      CustomerEntity save3 = customerRepo.save(new CustomerEntity(person.getFirstName(), person.getLastName()));
		      LOGGER.info("Customers saving");
		      
		      displayAllCustomers();
		      
		      save.setFirstName("Edited");
		      
		      customerRepo.save(save);
		      save.setFirstName("saved change");
		      
		      displayAllCustomers();

		      LOGGER.info("Customer deleting");
		      customerRepo.delete(save);
		      
		      displayAllCustomers();
		      
		      LOGGER.info("Customer deleting");
		      customerRepo.delete(save2);
		      
		      displayAllCustomers(); 
		      
	      }
	      else {
		      LOGGER.info("Customer repo is null");
	      }

	      ResponseEntity response = new ResponseEntity<Person>(person2, HttpStatus.OK);
	      
	      return response;
	   }
	  
	  
	  @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	   public @ResponseBody ResponseEntity testRetrieveFromDb(ModelMap model) {
		  
	      LOGGER.info("In test service");
	      
	      List<CustomerEntity> displayAllCustomers = null;

	      if (customerRepo != null) {
		      displayAllCustomers();
		      
		      CustomerEntity save2 = customerRepo.save(new CustomerEntity("rah", "bees"));
		      CustomerEntity save = customerRepo.save(new CustomerEntity("moo", "cheese"));
		      
		      LOGGER.info("Customers saving");
		      
		      displayAllCustomers();
		      
		      save.setFirstName("Edited");
		      
		      customerRepo.save(save);
		      save.setFirstName("saved change");
		      
		      displayAllCustomers();

		      LOGGER.info("Customer deleting");
		      customerRepo.delete(save);
		      
		      displayAllCustomers();
		      
		      LOGGER.info("Customer deleting");
		      customerRepo.delete(save2);
		      
		      displayAllCustomers = displayAllCustomers(); 
		      
	      }
	      else {
		      LOGGER.info("Customer repo is null");
	      }

	      URI location = URI.create("http://localhost/moo");
		     
	      HttpHeaders responseHeaders = new HttpHeaders();
	      responseHeaders.setLocation(location);
	      responseHeaders.set("MyResponseHeader", "MyValue");
	      
	      ResponseEntity response = new ResponseEntity<List<CustomerEntity>>(displayAllCustomers, responseHeaders, HttpStatus.OK);
	      
	      return response;
	   }
	  
	  private List<CustomerEntity> displayAllCustomers () {
		  LOGGER.info("Displaying");
	      List<CustomerEntity> findAll = customerRepo.findAll();
	      if (findAll != null && findAll.size() > 0) {
	    	  for (CustomerEntity currentCustomer : findAll) {
	    	      LOGGER.info("this customer: " + currentCustomer.toString());
	    	  }
	      }
	      return findAll;
	  }
	  
}
