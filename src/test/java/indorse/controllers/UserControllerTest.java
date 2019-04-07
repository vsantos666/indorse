package indorse.controllers;

import indorse.bean.UserDTO;
import indorse.configuration.*;
import indorse.service.UserService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import javax.servlet.ServletContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppInitializer.class,AppConfiguration.class,
DozerConfiguration.class,PersistConfiguration.class,WebMvcConfiguration.class})
@WebAppConfiguration
public class UserControllerTest extends TestCase {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserService controller;

/*
    private MockMvc mockMvc;
    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
*/

 /*   @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("UserController"));
    }*/

    @Test
    public void getUser() {
        //try {
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail("vsantos@gmail.com");
            userDTO.setName("jose");
            userDTO.setLastName("bonilla");
            userDTO.setLogin("boni");
            userDTO.setPassword("123456");
            String jsonResult = controller.saveUser(userDTO,"ff");
            assertEquals(null,jsonResult);

    }

}
