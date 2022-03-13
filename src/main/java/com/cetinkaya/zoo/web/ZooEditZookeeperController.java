package com.cetinkaya.zoo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cetinkaya.zoo.model.Zookeeper;
import com.cetinkaya.zoo.service.ZooService;

@Controller
public class ZooEditZookeeperController {

	@Autowired
	private ZooService zooService;

	@RequestMapping(value = "/zookeepers/update/{id}", method = RequestMethod.GET)
	public String loadZookeeper(@PathVariable Long id, ModelMap modelMap) {
		Zookeeper zookeeper = zooService.findZookeeper(id);
		modelMap.put("zookeeper", zookeeper);
		return "editZookeeper";
	}
	
	@RequestMapping(value = "/zookeepers/update/{id}", method = RequestMethod.POST)
	public String handleSubmitForm(@ModelAttribute @Valid Zookeeper zookeeper, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return "editZookeeper";
		}
		
		zooService.updateZookeeper(zookeeper);
		redirectAttributes.addFlashAttribute("message", "Zookeeper updated with id : " + zookeeper.getId());
		return "redirect:/zookeepers";
	}
}
