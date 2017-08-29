package controllers;

import api.user.UserAPI;
import api.user.UserFacade;
import api.user.UserRegistration;
import controllers.user.UserController;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import play.Application;
import play.mvc.Http;
import play.test.Helpers;
import play.test.WithApplication;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerUTest extends WithApplication {
    UserAPI mockedAPI = mock(UserFacade.class);
    UserController controller = new UserController(mockedAPI);

    public static Application app;
    private final Http.Request request = mock(Http.Request.class);

    @BeforeClass
    public static void startApp() {
        app = Helpers.fakeApplication();
        Helpers.start(app);
    }

    @Before
    public void setUp() throws Exception {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("username", "user");
        userData.put("password", "pass");
        Map<String, Object> argData = Collections.emptyMap();
        Long id = 2L;
        play.api.mvc.RequestHeader header = mock(play.api.mvc.RequestHeader.class);
        Http.Context context = new Http.Context(id, header, request, userData, userData, argData);
        Http.Context.current.set(context);
    }

    @Test
    public void on_successful_register_an_ok_result_is_returned () {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("username", "user1");
        userData.put("password", "Pass1000");

        Http.RequestBuilder mockRequest = new Http.RequestBuilder().method("POST")
                .bodyForm(userData);
        when(mockedAPI.register("user1", "Pass1000")).thenReturn(new UserRegistration(true));
        when(request.body()).
                thenReturn(mockRequest.body());

        assertEquals(controller.register().status(), 200);
    }

    @Test
    public void if_user_already_exists_a_not_found_result_is_returned () {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("username", "userExists");
        userData.put("password", "Pass1000");

        Http.RequestBuilder mockRequest = new Http.RequestBuilder().method("POST")
                .bodyForm(userData);
        when(mockedAPI.register("userExists", "Pass1000")).
                thenReturn(new UserRegistration(false, "User already exists"));
        when(request.body()).
                thenReturn(mockRequest.body());

        assertEquals(controller.register().status(), 404);
    }

    @Test
    public void if_username_or_password_is_not_valid_not_found_result_is_returned () {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("username", "user");
        userData.put("password", "pass");

        Http.RequestBuilder mockRequest = new Http.RequestBuilder().method("POST")
                .bodyForm(userData);
        when(request.body()).
                thenReturn(mockRequest.body());

        assertEquals(controller.register().status(), 404);
    }
}
