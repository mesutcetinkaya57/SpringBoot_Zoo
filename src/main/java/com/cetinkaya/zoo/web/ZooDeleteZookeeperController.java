package com.cetinkaya.zoo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cetinkaya.zoo.model.Zookeeper;
import com.cetinkaya.zoo.service.ZooService;

@Controller
public class ZooDeleteZookeeperController {

	@Autowired
	private ZooService zooService;
	
	@RequestMapping(value = "/zookeepers/delete/{id}", method = RequestMethod.GET)
	public String loadZookeeper(@PathVariable Long id, ModelMap modelMap) {
		Zookeeper zookeeper = zooService.findZookeeper(id);
		modelMap.put("zookeeper", zookeeper);
		return "deleteZookeeper";
		
	}
	
	@RequestMapping(value = "/zookeepers/delete/{id}", method = RequestMethod.POST)
	public String handleSubmitForm(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		zooService.deleteZookeeper(id);
		redirectAttributes.addFlashAttribute("message", "Zookeeper deleted with id : " + id);
		return "redirect:/zookeepers";
	}
}
