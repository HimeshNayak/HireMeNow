package com.himeshnayak.hiremenow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.*;

import org.json.JSONObject;

import java.util.ArrayList;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(@RequestParam(name="search", required=false, defaultValue="blockchain") ArrayList<String> title, ArrayList<String> author, Model model) {

		try {
			String url = "http://openlibrary.org/search.json?q=" + title + "&limit=3";
			HttpResponse <JsonNode> httpResponse = Unirest.get(url).asJson();
			
			httpResponse.getBody().getObject().getJSONArray("docs").forEach(book -> {
				JSONObject jsonObject = (JSONObject) book;
				title.add(jsonObject.getString("title"));
				author.add((String)jsonObject.getJSONArray("author_name").get(0));
			});

			model.addAttribute("title", title);
			model.addAttribute("author", author);
			
			// System.out.println("Book we found! " + title + " by " + author);

		} catch (UnirestException e) {
			System.out.println("Error Occured");
		}

		return "index";
	}

}

