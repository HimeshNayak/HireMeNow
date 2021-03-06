package com.himeshnayak.hiremenow.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.*;

@Controller
public class JobDetailsController {

	@GetMapping("/jobDetails")
	public String jobDetails(@RequestParam(name="id", required=true) String id, Model model) {
		
		try {
			String url = "https://www.themuse.com/api/public/jobs/" + id;
			HttpResponse <JsonNode> httpResponse = Unirest.get(url).asJson();
			
			JSONObject obj =  (JSONObject) httpResponse.getBody().getObject();
			model.addAttribute("title", obj.getString("name"));
			model.addAttribute("contents", obj.getString("contents"));
			model.addAttribute("company", obj.getJSONObject("company").getString("name"));
			model.addAttribute("link", obj.getJSONObject("refs").getString("landing_page"));
			

			return "jobDetails";
			
		} catch (UnirestException e) {
			return "error";
		}
	}

}