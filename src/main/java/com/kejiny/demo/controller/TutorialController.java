package com.kejiny.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kejiny.demo.model.Tutorial;
import com.kejiny.demo.service.TutorialService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController{
	
  @Autowired
  TutorialService tutorialService;
	
	@GetMapping("/tutorials")
		public  List<Tutorial> getAllTutorials() {
			return tutorialService.getAllTutorials();
		}
	
		
	@PostMapping("/tutorials")
	public Tutorial createTutorial(@RequestBody Tutorial tutorial) {
		return tutorialService.createTutorial(tutorial);
	}
	
	@GetMapping("/tutorial/{id}")
	public Optional<Tutorial> getTutorialById(@PathVariable String id) {
		return tutorialService.getTutorialById(id);
	}
		
	@PutMapping ("tutorials/{id}")
	public Tutorial updateTutorial(@RequestBody Tutorial tutorial, @PathVariable String id) {
		return tutorialService.updateTutorial(id, tutorial);
	}
	  @DeleteMapping("/tutorials/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
	      return tutorialService.deleteTutorial(id);
	  }

	  @DeleteMapping("/tutorials")
	  public ResponseEntity<HttpStatus> deleteAllTutorials() {
	      return tutorialService.deleteAllTutorials();
	  }

	  @GetMapping("/tutorials/published")
	  public ResponseEntity<List<Tutorial>> findByPublished() {
	      return tutorialService.findByPublished();

	}
