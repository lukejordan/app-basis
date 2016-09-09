package com.fam;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lukeyj.example.data.Job;
import com.lukeyj.example.data.Person;

//@RequestMapping("")
@Controller
//@RestController
public class Example {

	private static Logger LOGGER = Logger.getLogger(Example.class.getName());
	

	  @RequestMapping(value = "/hello", method = RequestMethod.GET)
	   public String printHello(ModelMap model) {
		  
	      LOGGER.info("We are in the method");
	      
	      model.addAttribute("message", "Hello Spring MVC Framework!");

	      return "home";
	   }
	  
	  

	  
	  
	  
	  //@RequestMapping("*")
	  //@ResponseBody
	  @RequestMapping("/{viewName}")
	  public String fallbackMethod(@PathVariable("viewName") String viewName){
		  String errorViewName = "error404";
		  return viewName;
	  }

}
