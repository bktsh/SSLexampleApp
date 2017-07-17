package com.bktsh.practice.exampleapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created on 7/14/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-app-context.xml"})
public class ThymeleafEmailSenderTest {
    @Autowired
    ThymeleafEmailSender subject;

    @org.junit.Test
    public void sendMail() throws Exception {
        boolean result = subject.sendMail("Hashem_Baktash@anfcorp.com", "Hashem_Baktash@anfcorp.com", "TEST", "This is a test");
        assertThat(result).isTrue();
    }


    @org.junit.Test
    public void sendTextMail() throws Exception {
        boolean result = subject.sendTextMail("Hashem_Baktash@anfcorp.com", "Hashem_Baktash@anfcorp.com", "TEST", "This is a test");
        assertThat(result).isTrue();
    }


    @org.junit.Test
    public void sendHtmlMail() throws Exception {
        boolean result = subject.sendHtmlMail("Hashem_Baktash@anfcorp.com", "Hashem_Baktash@anfcorp.com", "TEST", "This is a test");
        assertThat(result).isTrue();
    }


}