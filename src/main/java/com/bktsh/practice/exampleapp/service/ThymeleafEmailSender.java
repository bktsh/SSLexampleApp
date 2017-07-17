package com.bktsh.practice.exampleapp.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * Created on 7/14/17.
 */
@Service
public class ThymeleafEmailSender {
    private static final String LOGO_BACKGROUND_IMAGE = "images/logo-background.png";
    private static final String BACKGROUND_IMAGE = "images/background.png";
    private static final String THYMELEAF_BANNER_IMAGE = "images/thymeleaf-banner.png";
    private static final String THYMELEAF_LOGO_IMAGE = "images/thymeleaf-logo.png";
    private static final String PNG_MIME = "image/png";
    @Autowired
    @Qualifier(value = "htmlTemplateEngine")
    private TemplateEngine htmlTemplateEngine;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    @Qualifier(value = "textTemplateEngine")
    private TemplateEngine textTemplateEngine;

    public boolean sendMail(final String from, final String to, final String subject, final String content) {
        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content.toString());
            message.setSentDate(new Date());
            this.javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean sendTextMail(final String from, final String to, final String subject, final String content) {
        // Prepare the evaluation context
        final Context ctx = new Context(Locale.US);
        ctx.setVariable("name", "Hashem Baktash");
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("link", "www.google.com");
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            message.setSubject("Example plain TEXT email");
            message.setFrom(from);
            message.setTo(to);

            // Create the plain TEXT body using Thymeleaf
            final String textContent = this.textTemplateEngine.process("text_email", ctx);
            message.setText(textContent);

            this.javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean sendHtmlMail(final String from, final String to, final String subject, final String content) throws MessagingException {
        // Prepare the evaluation context
        final Context ctx = new Context(Locale.US);
        ctx.setVariable("name", "Hashem Baktash");
        ctx.setVariable("link", "www.google.com");
        ctx.setVariable("greeting", "Hello");
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
        try {
            message.setSubject("Example plain TEXT email");
            message.setFrom(from);
            message.setTo(to);

            // Create the plain TEXT body using Thymeleaf
            final String htmlContent = this.htmlTemplateEngine.process("html_email", ctx);
            message.setText(htmlContent, true /* isHtml */);
            this.javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
