package com.bktsh.practice.exampleapp.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created on 7/14/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-app-context.xml"})
public class FreemarkerEmailSenderTest {

    @Autowired
    FreemarkerEmailSender subject;


    @org.junit.Test
    public void sendFreemarkerMail() throws Exception {
        boolean result = subject.sendFreemarkerEmail("Hashem_Baktash@mycompany.com", "Hashem_Baktash@mycompany.com", "TEST", "This is a test");
        assertThat(result).isTrue();
    }

}