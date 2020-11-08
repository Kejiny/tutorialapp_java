package com.kejiny.demo.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kejiny.demo.model.Tutorial;
import com.kejiny.demo.repository.TutorialRepository;

@Service
public class TutorialService {

	@Autowired
  TutorialDao tutorialDao;
    
    public ResponseEntity<List<Tutorial>> getAllTutorials(String title) {
   	 try {
   	 	List<Tutorial> tutorials = new ArrayList<Tutorial>();
   	 
   	 	if (title == null) {
   	   	      tutorialDao.findAll().forEach(tutorials::add);
   	 	} else {
   	   	      tutorialDao.findByTitleContaining(title).forEach(tutorials::add);
   	 	}
   	 
   	 	if (tutorials.isEmpty()) {
   	   	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   	 	}
   	 
   	 	return new ResponseEntity<>(tutorials, HttpStatus.OK);
   	 } catch (Exception e) {
   	 	return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
   	 }
    }

    public ResponseEntity<Tutorial> createTutorial(Tutorial tutorial) {
   	 try {
   	 	Tutorial _tutorial = tutorialDao.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
   	 	return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
   	 } catch (Exception e) {
   	 	return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
   	 }
    }

    public ResponseEntity<Tutorial> getTutorialById(String id) {
   	 Optional<Tutorial> tutorialData = tutorialDao.findById(id);

   	 if (tutorialData.isPresent()) {
   		 return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
   	 } else {
   		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   	 }
    }

    public ResponseEntity<List<Tutorial>> findByPublished() {
   	 try {
   	 	List<Tutorial> tutorials = tutorialDao.findByPublished(true);

   	 	if (tutorials.isEmpty()) {
   	   	       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   	 	}
   	 	return new ResponseEntity<>(tutorials, HttpStatus.OK);
   	 } catch (Exception e) {
   	 	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   	 }
    }

    public ResponseEntity<Tutorial> updateTutorial(String id, Tutorial tutorial) {
   	 Optional<Tutorial> tutorialData = tutorialDao.findById(id);

   	 if (tutorialData.isPresent()) {
   		 Tutorial _tutorial = tutorialData.get();
   	 	_tutorial.setTitle(tutorial.getTitle());
   	 	_tutorial.setDescription(tutorial.getDescription());
   	 	_tutorial.setPublished(tutorial.isPublished());
   	 	return new ResponseEntity<>(tutorialDao.save(_tutorial), HttpStatus.OK);
   	 } else {
   	 	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   	 }
    }

    public ResponseEntity<HttpStatus> deleteTutorial(String id) {
   	 try {
   	 	tutorialDao.deleteById(id);
   	 	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   	 } catch (Exception e) {
   		 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   	 }
    }

    public ResponseEntity<HttpStatus> deleteAllTutorials() {
   	 try {
   	 	tutorialDao.deleteAll();
   	 	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   	 } catch (Exception e) {
   	 	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   	 }
    }
}
