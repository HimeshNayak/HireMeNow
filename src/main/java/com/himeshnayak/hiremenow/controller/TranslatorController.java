package com.himeshnayak.hiremenow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.*;
import com.himeshnayak.hiremenow.service.TranslateService;

@Controller
public class TranslatorController {

    @PostMapping("/translate")
    public String translate(@RequestBody(required = true) String text) {
        try {
            TranslateService translateRequest = new TranslateService();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String postString = gson.toJson(text);
            postString = '[' + postString.substring(1, postString.length() - 1) + ']';
            System.out.println(postString);
            String response = translateRequest.Post(postString);
            System.out.println(TranslateService.prettify(response));
        } catch (Exception e) {
            System.out.println(e);
        }
        return "translate";
    }
}
