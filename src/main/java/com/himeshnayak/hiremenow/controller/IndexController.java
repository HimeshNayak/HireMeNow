package com.himeshnayak.hiremenow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.himeshnayak.hiremenow.service.UserService;
import com.himeshnayak.hiremenow.model.JobHeader;
import com.himeshnayak.hiremenow.model.User;

@Controller
public class IndexController {

	private final UserService userService;

	@Autowired
	public IndexController(UserService userService) {
		this.userService = userService;
	}

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

		User user = new User();
		model.addAttribute("user", user);

		return "index";
	}

	@PostMapping("/user") 
	public String registerSuccess(@ModelAttribute("user") User user, HttpServletResponse response) {
		user.setUserId();
		userService.addUser(user);
		
		// add id to cookie and then fetch details from cosmos db in profile
		String userId = user.getUUID().toString();
		String name = user.getName().replace(' ', '_');
		String type = user.getType();
		response.addCookie(new Cookie("userId", userId));
		response.addCookie(new Cookie("name", name));
		response.addCookie(new Cookie("type", type));
		
		return "user";
	}

}

