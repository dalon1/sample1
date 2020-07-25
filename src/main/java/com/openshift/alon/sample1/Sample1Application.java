package com.openshift.alon.sample1;

import com.openshift.alon.sample1.model.Puppy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

@SpringBootApplication
@RestController
public class Sample1Application {

	private String appName = "Sample 1";
	public static List<Puppy> puppyGlobalList = new ArrayList<Puppy>();

	public static void main(String[] args) {
		SpringApplication.run(Sample1Application.class, args);
		puppyGlobalList.add(new Puppy("Rocky", 10));
		puppyGlobalList.add(new Puppy("Cloe", 10));
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

	@GetMapping("/puppy")
	public String getPuppiesAPI() {
		return new Gson().toJson(puppyGlobalList);
	}

	@GetMapping("/puppy/{puppyName}")
	public String getPuppyByNameAPI(@PathVariable String puppyName) {
		Puppy selectedPuppy = null;
		for (int i = 0; i < puppyGlobalList.size(); i++) {
			if (puppyGlobalList.get(i).getName().equalsIgnoreCase(puppyName)) {
				selectedPuppy = puppyGlobalList.get(i);
				break;
			}
		}
		return new Gson().toJson(selectedPuppy);
	}

	@PostMapping("/puppy")
	public String addPuppyAPI(@RequestBody Puppy puppy) {
		puppyGlobalList.add(puppy);
		return new Gson().toJson(puppy);
	}

	@PutMapping("/puppy/{puppyName}")
	public String updatePuppyAPI(@RequestBody Puppy puppy, @PathVariable String puppyName ) {
		for (int i = 0; i < puppyGlobalList.size(); i++) {
			if (puppyGlobalList.get(i).getName().equalsIgnoreCase(puppyName)) {
				puppyGlobalList.get(i).setName(puppy.getName());
				puppyGlobalList.get(i).setAge(puppy.getAge());
				puppyGlobalList.get(i).setBreed(puppy.getBreed());
				break;
			}
		}
		return new Gson().toJson(puppy);
	}

	@DeleteMapping("/puppy/{puppyName}")
	public void deletePuppyAPI(@PathVariable String puppyName) {
		for (int i = 0; i < puppyGlobalList.size(); i++) {
			if (puppyGlobalList.get(i).getName().equalsIgnoreCase(puppyName)) {
				puppyGlobalList.remove(i);
				break;
			}
		}
	}
}
