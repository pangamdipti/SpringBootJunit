package com.springboot.task.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.any;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.task.Response;
import com.springboot.task.model.User;
import com.springboot.task.controller.ServiceController;
import com.springboot.task.service.UserService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeAll
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void createUser() throws Exception {
		User user = new User();
		user.setFname("Nupur");
		user.setLname("Deshmukh");
		user.setContact("9753417839");
		user.setEmail("nupur.deshmukh@gmail.com");
		user.setCity("Mumbai");
		user.setCountry("India");
		user.setPincode("409819");

		String JsonRequest = objectMapper.writeValueAsString(user);
		MvcResult result = mockMvc
				.perform(post("/users").content(JsonRequest).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(print()).andExpect(status().isOk()).andReturn();
		String resultContext = result.getResponse().getContentAsString();
		Response response = objectMapper.readValue(resultContext, Response.class);
		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success", response.getProgressMessage());
	}

	@Test
	public void getAllUsers() throws Exception {

		MvcResult result = mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andReturn();

		String resultContext = result.getResponse().getContentAsString();

		Response response = objectMapper.readValue(resultContext, Response.class);

		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success", response.getProgressMessage());

	}

	@Test
	public void getUserById() throws Exception {
		int id=1;
		MvcResult result = mockMvc.perform(get("/users/"+id).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		String resultContext = result.getResponse().getContentAsString();

		Response response = objectMapper.readValue(resultContext, Response.class);

		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success", response.getProgressMessage());
	}

	@Test
	public void getUserByName() throws Exception {
		String name="Dipti";
		MvcResult result = mockMvc.perform(get("/users/"+name).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		String resultContext = result.getResponse().getContentAsString();

		Response response = objectMapper.readValue(resultContext, Response.class);

		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success", response.getProgressMessage());
	}

	@Test
	public void getUserByPinCode() throws Exception {
		String pincode="400091";
		MvcResult result = mockMvc.perform(get("/users/"+pincode).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		String resultContext = result.getResponse().getContentAsString();

		Response response = objectMapper.readValue(resultContext, Response.class);

		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success", response.getProgressMessage());
	}

	@Test
	public void updateUserByEmployeeId() throws Exception {
		int id=10;
		User update = new User();
		update.setFname("Nupur");
		update.setLname("Sharma");
		update.setContact("7652810271");
		update.setEmail("nupur.deshmukh@gmail.com");
		update.setCity("Nallasopara");
		update.setCountry("India");
		update.setPincode("409819");
		String JsonRequest = objectMapper.writeValueAsString(update);
		MvcResult result = mockMvc
				.perform(put("/users/"+id).content(JsonRequest)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		String resultContext = result.getResponse().getContentAsString();

		Response response = objectMapper.readValue(resultContext, Response.class);

		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success", response.getProgressMessage());
	}

	@Test
	public void deleteUserById() throws Exception {
		int id=12;
		MvcResult result = mockMvc
				.perform(delete("/users/"+id).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		String resultContext = result.getResponse().getContentAsString();
		Response response = objectMapper.readValue(resultContext, Response.class);
		Assertions.assertTrue(response.isStatus() == Boolean.TRUE);
		Assertions.assertEquals("Success", response.getProgressMessage());
	}

}
