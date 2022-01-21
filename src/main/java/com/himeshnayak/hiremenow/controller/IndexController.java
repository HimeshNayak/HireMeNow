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

import com.himeshnayak.hiremenow.model.JobHeader;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(@RequestParam(name="search", required=false, defaultValue="Software%20Engineer") String search, Model model) {

		ArrayList<JobHeader> jobs = new ArrayList<>();
		// ArrayList<String> categories = new ArrayList<>();

		// categories.add("Software Engineer");
		// categories.add("Data Science");
		// categories.add("Design");
		// categories.add("IT");
		// categories.add("Project Management");
		// categories.add("UX");

		try {
			String url = "https://www.themuse.com/api/public/jobs?category=" + search + "&level=Internship&page=1";
			HttpResponse <JsonNode> httpResponse = Unirest.get(url).asJson();
			
			JSONArray jobResults = httpResponse.getBody().getObject().getJSONArray("results");
			for (int i = 0; i < 3; i++) {
				JSONObject jsonObject = (JSONObject) jobResults.get(i);
				int id = jsonObject.getInt("id");
				String title = jsonObject.getString("name");
				String company = (String)jsonObject.getJSONObject("company").getString("name");
				jobs.add(new JobHeader(id, title, company));
			}

			model.addAttribute("jobs", jobs);
			// model.addAttribute("categories", categories);

		} catch (UnirestException e) {
			System.out.println("Error Occured");
		}

		return "index";
	}

}

