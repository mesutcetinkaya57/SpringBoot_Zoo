package com.cetinkaya.zoo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cetinkaya.zoo.model.Zookeeper;
import com.cetinkaya.zoo.service.ZooService;

@Controller
public class ZooNewZookeeperController {

	@Autowired
	private ZooService zooService;
	
	@RequestMapping(value = "/zookeepers/new", method = RequestMethod.GET)
	public String newZookeeper() {
		return "newZookeeper";
	}
	
	@ModelAttribute
	public Zookeeper initModel() {
		return new Zookeeper();
	}
	
	@RequestMapping(value = "zookeepers/new", method = RequestMethod.POST)
	public String handleFormSubmit(@Valid @ModelAttribute  Zookeeper zookeeper, BindingResult bindingResult, 
			RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return "newZookeeper";
		}
		
		zooService.createZookeeper(zookeeper);
		redirectAttributes.addFlashAttribute("message", "Zookeeper created with id : " + zookeeper.getId());
		return "redirect:/zookeepers";
	}
}
