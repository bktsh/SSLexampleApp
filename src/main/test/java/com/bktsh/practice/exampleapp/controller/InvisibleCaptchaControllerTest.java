package com.bktsh.practice.exampleapp.controller;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created on 7/18/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-app-context.xml"})
@WebAppConfiguration
public class InvisibleCaptchaControllerTest {

    protected InvisibleCaptchaController subjectSpy;

    @Autowired
    protected InvisibleCaptchaController subject;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        subjectSpy = Mockito.spy(subject);
        doReturn(new JSONObject()).when(subjectSpy).performRecaptchaSiteVerify(anyString());
    }

    @Test
    public void postCommentGivenRuntimeException() throws Exception {
        MvcResult result = mockMvc.perform(post("/invisible")
                .param("message", "ERROR")
                .param("g-recaptcha-response", "SUCCESS")
                .sessionAttr("user", "ERROR")).andReturn();
        assertThat(result.getModelAndView().getViewName()).isEqualToIgnoringCase("generic_error");
        assertThat(((RuntimeException)result.getModelAndView().getModelMap().get("exception")).getMessage()).isEqualToIgnoringCase("TEST RuntimeException");
    }

    @Test
    public void postCommentGivenException() throws Exception {
        MvcResult result = mockMvc.perform(post("/invisible")
                .param("message", "exception")
                .param("g-recaptcha-response", "SUCCESS")
                .sessionAttr("user", "EXCEPTION")).andReturn();
        assertThat(result.getModelAndView().getViewName()).isEqualToIgnoringCase("generic_error");
        assertThat(((Exception)result.getModelAndView().getModelMap().get("exception")).getMessage()).isEqualToIgnoringCase("TEST Exception");
    }

    @Test
    public void postCommentGivenIOException() throws Exception {
        MvcResult result = mockMvc.perform(post("/invisible")
                .param("message", "IO")
                .param("g-recaptcha-response", "SUCCESS")
                .sessionAttr("user", "IO")).andReturn();
        assertThat(result.getResolvedException()).isNotNull();
        assertThat(result.getResolvedException().getMessage()).containsIgnoringCase("IO");
    }

}