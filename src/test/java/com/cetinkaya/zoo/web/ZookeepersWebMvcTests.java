package com.cetinkaya.zoo.web;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles("dev")
@AutoConfigureMockMvc
@WithMockUser(username = "user", password = "secret", authorities = "USER")
public class ZookeepersWebMvcTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testZookeepers() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/zookeepers");
		
		ResultActions resultActions = mockMvc.perform(requestBuilder);
		
		MvcResult mvcResult = resultActions.andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		
		MatcherAssert.assertThat(mav.getViewName(), Matchers.equalTo("zookeepers"));
		MatcherAssert.assertThat(mav.getModel().containsKey("zookeepers"), Matchers.is(true));
		
	}
}
