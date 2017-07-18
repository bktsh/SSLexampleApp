package com.bktsh.practice.exampleapp.service;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 7/11/17.
 */
@Service
public class FreemarkerEmailSender {

    private static final String LOGO_BACKGROUND_IMAGE = "images/logo-background.png";
    private static final String BACKGROUND_IMAGE = "images/background.png";
    private static final String THYMELEAF_BANNER_IMAGE = "images/thymeleaf-banner.png";
    private static final String THYMELEAF_LOGO_IMAGE = "images/thymeleaf-logo.png";
    private static final String PNG_MIME = "image/png";

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    Configuration freemarkerConfiguration;


    public boolean sendFreemarkerEmail(final String from, final String to, final String subject, final String content) {


        MimeMessagePreparator preparator = getMessagePreparator(from, to, subject, content);

        try {
            this.javaMailSender.send(preparator);
            System.out.println("Message has been sent.............................");
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private MimeMessagePreparator getMessagePreparator(final String from, final String to, final String subject, final String content){

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                helper.setSubject("Your order on Demoapp with Templates");
                helper.setFrom("customerserivces@yourshop.com");
                helper.setTo(to);

                Map<String, Object> model = new HashMap<String, Object>();
                model.put("order", content);
                model.put("link", "http://www.google.com");

                String text = geFreeMarkerTemplateContent(model);//Use Freemarker or Velocity
                System.out.println("Template content : "+text);

                // use the true flag to indicate you need a multipart message
                helper.setText(text, true);

                //Additionally, let's add a resource as an attachment as well.
                helper.addAttachment("cutie.png", new ClassPathResource("images/thymeleaf-logo.png"));

            }
        };
        return preparator;
    }
    public String geFreeMarkerTemplateContent(Map<String, Object> model){
        StringBuffer content = new StringBuffer();
        try{
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate("fm_mailTemplate.txt"),model));
            return content.toString();
        }catch(Exception e){
            System.out.println("Exception occured while processing fmtemplate:"+e.getMessage());
        }
        return "";
    }
}
