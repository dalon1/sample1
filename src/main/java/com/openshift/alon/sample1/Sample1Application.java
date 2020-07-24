package com.openshift.alon.sample1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

@SpringBootApplication
@RestController
public class Sample1Application {

	private String appName = "Sample 1";

	public static void main(String[] args) {
		SpringApplication.run(Sample1Application.class, args);
	}

	@GetMapping("/")
	public String indexAPI() {
		String webContent = String.format("<h1>Welcome to %s.</h1>", appName);
		webContent += String.format("<p>This is just a demo</p>");
		return webContent;
	}

	@GetMapping("/family")
	public String getFamilyAPI() {
		List<String> brothersList = new ArrayList<String>(Arrays.asList("Dannel", "Joel", "Eitan"));
		HashMap<String, List<String>> familyMap = new HashMap<>();
		familyMap.put("brothers", brothersList);
		List<String> puppyList = new ArrayList<>(Arrays.asList("Rocky", "Cloe", "Coco"));
		familyMap.put("puppies", puppyList);
		return new Gson().toJson(familyMap);
	}
}
