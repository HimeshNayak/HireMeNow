package com.himeshnayak.hiremenow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(@RequestParam(name="search", required=false, defaultValue="Software%20Engineer") String search, ArrayList<String> name, ArrayList<Integer> id, ArrayList<String> company, Model model) {

		try {
			String url = "https://www.themuse.com/api/public/jobs?category=" + search + "&level=Internship&page=1";
			HttpResponse <JsonNode> httpResponse = Unirest.get(url).asJson();
			
			JSONArray jobResults = httpResponse.getBody().getObject().getJSONArray("results");
			for (int i = 0; i < 3; i++) {
				JSONObject jsonObject = (JSONObject) jobResults.get(i);
				name.add(jsonObject.getString("name"));
				id.add(jsonObject.getInt("id"));
				company.add((String)jsonObject.getJSONObject("company").getString("name"));
			}

			model.addAttribute("name", name);
			model.addAttribute("company", company);
			model.addAttribute("id", id);

		} catch (UnirestException e) {
			System.out.println("Error Occured");
		}

		return "index";
	}

}

