package com.cetinkaya.zoo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cetinkaya.zoo.service.ZooService;

@Controller
public class ZooController {
	
	@Autowired
	private ZooService zooService;
	
	@RequestMapping("/login.html")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
//		Oncesinde application.yaml deki spring.mvc.view.prefix: /WEB-INF/jsp/ ve sonrasında suffix: .jsp gelir. 
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value = {"/","/index.html"})
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/zookeepers")
	public ModelAndView getZookeepers() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("zookeepers", zooService.findZookeepers());
		mav.setViewName("zookeepers");
		return mav;
	}
	
	@RequestMapping ("/zoo")
	@ResponseBody // Eğer bu annotation olmazsa daistpatcher servlet dönen string ifadeyi view olarak render etmeye çalışır.
	public String welcome() {
		return "Welcome to Zoo World !";
	}
}
