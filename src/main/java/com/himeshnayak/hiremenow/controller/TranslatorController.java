package com.himeshnayak.hiremenow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.himeshnayak.hiremenow.model.Message;
import com.himeshnayak.hiremenow.service.TranslateService;

@Controller
public class TranslatorController {

    @GetMapping("/translate")
    public String showTranslatePage(Model model) {
        
        String translation = "";
        model.addAttribute("translation", translation);

        Message message = new Message();
        model.addAttribute("message", message);
        
        return "translate";
    }

    @PostMapping("/translate")
    public String translate(@ModelAttribute(name="message") Message message, Model model) {
        try {
            TranslateService translateRequest = new TranslateService();
            String text = "[{\"Text\":\"" + message.getText() + "\"}]";
            String response = translateRequest.Post(text);
            model.addAttribute("translation", response);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "translate";
    }
}
