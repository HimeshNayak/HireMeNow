package com.himeshnayak.hiremenow;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexPage {

	@GetMapping("/")
	public String index() {
		return "Azure Connected";
	}

}

