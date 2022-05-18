package com.example.URLChecker.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    private final String siteIsUp = "Site is up!";
    private final String siteIsDown = "Site is down! :(";
    private final String incorrectURL = "Incorrect URL";
    
    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){
        String returnMessage = "";
        try {
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode() / 100;
            if (responseCode >= 2 && responseCode <= 3){
                System.out.println(connection.getResponseCode());
                returnMessage = siteIsUp;
            }else{
                returnMessage = siteIsDown;
            }
            connection.disconnect();
        } catch (MalformedURLException e) {
            returnMessage = incorrectURL;
        } catch (IOException e) {
            returnMessage = siteIsDown;
        }

        return returnMessage;
    }

}
