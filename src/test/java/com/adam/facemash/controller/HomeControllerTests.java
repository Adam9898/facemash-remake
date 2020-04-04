package com.adam.facemash.controller;

import com.adam.facemash.dao.Person;
import com.adam.facemash.enums.UserRole;
import com.adam.facemash.service.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {HomeController.class})
@AutoConfigureMockMvc
public class HomeControllerTests {

    @MockBean
    UserService userService;
    @MockBean
    PersonService personService;
    @MockBean
    VoteService voteService;
    @Autowired
    MockMvc mockMvc;

    private MockHttpServletResponse returnHtmlMockResponseBasedOnParam(String URI) throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.TEXT_HTML);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        return mvcResult.getResponse();
    }

    @Test
    @DisplayName("Test that main url returns correct https status code")
    public void mainRouteStatusCodeTest() throws Exception {
        MockHttpServletResponse response = returnHtmlMockResponseBasedOnParam("/");
        assertTrue(httpStatusMultiTester(response.getStatus()),
                "Main page route should respond with status code 200 or 302");
    }

    private boolean httpStatusMultiTester(int httpStatusCode) {
        System.out.println(httpStatusCode);
        if (httpStatusCode == HttpStatus.OK.value() || httpStatusCode == HttpStatus.FOUND.value()) {
            return true;
        }
        return false;
    }

    @Test
    @DisplayName("Test that main login url responds with an html page")
    public void mainRouteHtmlResponseTest() throws Exception {
        MockHttpServletResponse response = returnHtmlMockResponseBasedOnParam("/login");
        assertEquals(MediaType.TEXT_HTML_VALUE + ";charset=" + response.getCharacterEncoding(), response.getContentType());
    }

    @Test
    @DisplayName("Test that main page redirect to /app when there is a security context with the proper role")
    @WithMockUser(roles = {"REGULAR"})
    public void mainRouteShouldRedirectToApp() throws Exception {
        MockHttpServletResponse response = returnHtmlMockResponseBasedOnParam("/");
        assertEquals("/app", response.getRedirectedUrl());
    }

    @Test
    @DisplayName("Test that app route redirects to login page")
    public void appRouteRedirectTest() throws Exception {
        MockHttpServletResponse response = returnHtmlMockResponseBasedOnParam("/app");
        String redirectedUrl = response.getRedirectedUrl();
        String expectedUri = "/login";
        assertEquals(expectedUri, redirectedUrl.substring(redirectedUrl.length() - expectedUri.length()));
    }

    @Test
    @DisplayName("Test that app route returns with http status code 200")
    @WithMockUser(roles = {"REGULAR"})
    public void appRouteReturnsOk() throws Exception {
        when(personService.generatePeople(anyString())).thenReturn(new Person[2]);
        when(personService.noMorePersonLeft(any(Person[].class))).thenReturn(false);
        MockHttpServletResponse response = returnHtmlMockResponseBasedOnParam("/app");
        assertEquals(HttpStatus.OK.value() ,response.getStatus());
    }

    @Test
    @DisplayName("Test that rank list route redirects to login page when there is no security context")
    public void topRouteShouldRedirectToLoginPage() throws Exception {
        MockHttpServletResponse response = returnHtmlMockResponseBasedOnParam("/top");
        String redirectUrl = response.getRedirectedUrl();
        String expectedUri = "/login";
        assertEquals(expectedUri, response.getRedirectedUrl().substring(redirectUrl.length() - expectedUri.length()));
    }

    @Test
    @DisplayName("Test that rank list route responds with http status code 200")
    @WithMockUser(roles = {"REGULAR"})
    public void topRouteShouldRespondWithHttpStatusCode200() throws Exception {
        MockHttpServletResponse response = returnHtmlMockResponseBasedOnParam("/top");
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @DisplayName("Test that rank list route respinds with html page")
    @WithMockUser(roles = {"REGULAR"})
    public void topRouteShouldRespondWithAdnHtmlPage() throws Exception {
        MockHttpServletResponse response = returnHtmlMockResponseBasedOnParam("/top");
        assertEquals(MediaType.TEXT_HTML_VALUE + ";charset=" + response.getCharacterEncoding(), response.getContentType());
    }
}