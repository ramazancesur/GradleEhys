package tr.com.prolms.base.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tr.com.prolms.base.TestConfig;
import tr.com.prolms.base.entity.Role;
import tr.com.prolms.base.entity.RoleBuilder;
import tr.com.prolms.base.service.RoleManagementService;

import java.util.Arrays;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class RoleControllerTest {
  private static final Long ID = 1L;
  private MockMvc mockMvc;
  @Autowired
  private RoleManagementService roleManagementService;
  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void listOfRolesTest() throws Exception {
    Role adminRole = new RoleBuilder()
        .id(ID)
        .name("ADMIN").build();

    Role userRole = new RoleBuilder()
        .id(2L)
        .name("USER").build();

    when(roleManagementService.findAll()).thenReturn(Arrays.asList(adminRole, userRole));

    mockMvc.perform(get("/roles"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name(RoleController.VIEW_ROLE_LIST))
        .andExpect(forwardedUrl("/roles/list"))
        .andExpect(model().attribute(RoleController.MODEL_ATTRIBUTE_ROLES_LIST, hasSize(2)))
        .andExpect(model().attribute(RoleController.MODEL_ATTRIBUTE_ROLES_LIST, hasItem(
            allOf(
                hasProperty("roleId", is(1L)),
                hasProperty("name", is("ADMIN"))
            )
        )));

    verify(roleManagementService, times(1)).findAll();
    verifyNoMoreInteractions(roleManagementService);
  }

}
