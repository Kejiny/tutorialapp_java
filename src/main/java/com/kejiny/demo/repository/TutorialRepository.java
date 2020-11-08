package com.kejiny.demo.repository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kejiny.demo.model.Tutorial;

@Repository
public interface TutorialRepository extends MongoRepository<Tutorial, String> {
	 List<Tutorial> findByTitleContaining(String title);
	    List<Tutorial> findByPublished(boolean published);

}
