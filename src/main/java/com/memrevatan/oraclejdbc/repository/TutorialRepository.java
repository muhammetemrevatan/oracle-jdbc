package com.memrevatan.oraclejdbc.repository;

import com.memrevatan.oraclejdbc.dto.TutorialCreateDto;
import com.memrevatan.oraclejdbc.entity.Tutorial;

import java.util.List;

public interface TutorialRepository {
    String SAVE_SQL = "INSERT INTO tutorials (title, description, content, status, createdAt, updatedAt, createdBy, updatedBy) VALUES(?,?,?,?,?,?,?,?)";

    Tutorial save(TutorialCreateDto tutorialDto);

    Tutorial findById(Long id);

    List<Tutorial> findAll();

    List<Tutorial> findByTitleContaining(String title);

    Tutorial update(Tutorial book);

    Tutorial deleteById(Long id);

    List<Tutorial> deleteAll();
}
