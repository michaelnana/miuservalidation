package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.user.UserController;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import play.Application;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static play.test.Helpers.route;

public class UserControllerUTest extends WithApplication {
    UserController controller = new UserController();

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

    @Ignore
    public void on_successful_register_an_ok_result_is_returned () {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("username", "user");
        userData.put("password", "pass");

        FormFactory mockFormFactory = mock(FormFactory.class);
        Form mockForm = mock(Form.class);
        Form mockDataForm = mock(Form.class);
        when(mockFormFactory.form(any(Class.class)))
                .thenReturn(mockDataForm);
        when(mockDataForm.bindFromRequest(userData)).thenReturn(mockForm);

        assertEquals(controller.register().status(), 200);
    }

    @Ignore
    public void test_1 () {
        try {
            JsonNode jsonNode = (new ObjectMapper()).readTree("{ \"someName\": \"sameValue\" }");
            Http.RequestBuilder request = new Http.RequestBuilder().method("POST")
                    .bodyJson(jsonNode)
                    .uri("/register");
            Result result = route(request);

            assertEquals(result.status(), 200);
        } catch (Exception e) {

        }
    }
}
