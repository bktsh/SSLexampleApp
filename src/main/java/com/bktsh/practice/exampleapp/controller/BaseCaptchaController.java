package com.bktsh.practice.exampleapp.controller;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Created on 7/11/17.
 */
public class BaseCaptchaController {
    @Value(value = "${app.name}")
    protected String nameOfApp;

    // Replace this with your site secret.
    public static final String SITE_SECRET = "6LfeHx4UAAAAAFWXGh_xcL0B8vVcXnhn9q_SnQ1b";
    public static final String SECRET_PARAM = "secret";
    public static final String RESPONSE_PARAM = "response";
    public static final String G_RECAPTCHA_RESPONSE = "g-recaptcha-response";
    public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    protected JSONObject performRecaptchaSiteVerify(String recaptchaResponseToken)
            throws IOException {
        URL url = new URL(SITE_VERIFY_URL);
        StringBuilder postData = new StringBuilder();
        addParam(postData, SECRET_PARAM, SITE_SECRET);
        addParam(postData, RESPONSE_PARAM, recaptchaResponseToken);

        return postAndParseJSON(url, postData.toString());
    }

    protected JSONObject postAndParseJSON(URL url, String postData) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConnection.setRequestProperty("charset", StandardCharsets.UTF_8.displayName());
        urlConnection.setRequestProperty("Content-Length", Integer.toString(postData.length()));
        urlConnection.setUseCaches(false);
        urlConnection.getOutputStream().write(postData.getBytes(StandardCharsets.UTF_8));
        JSONTokener jsonTokener = new JSONTokener(urlConnection.getInputStream());
        return new JSONObject(jsonTokener);
    }

    protected StringBuilder addParam(
            StringBuilder postData, String param, String value)
            throws UnsupportedEncodingException {
        if (postData.length() != 0) {
            postData.append("&");
        }
        return postData.append(
                String.format("%s=%s",
                        URLEncoder.encode(param, StandardCharsets.UTF_8.displayName()),
                        URLEncoder.encode(value, StandardCharsets.UTF_8.displayName())));
    }
}
