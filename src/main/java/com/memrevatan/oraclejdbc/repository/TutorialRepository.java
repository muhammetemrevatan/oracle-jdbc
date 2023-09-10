package com.memrevatan.oraclejdbc.repository;

import com.memrevatan.oraclejdbc.dto.TutorialCreateDto;
import com.memrevatan.oraclejdbc.dto.TutorialUpdateDto;
import com.memrevatan.oraclejdbc.entity.Tutorial;

import java.util.List;

public interface TutorialRepository {
    Tutorial save(TutorialCreateDto tutorialDto);

    Tutorial findById(Long id);

    List<Tutorial> findAll();

    List<Tutorial> findByTitleContaining(String title);

    Tutorial update(Long id, TutorialUpdateDto tutorialUpdateDto);

    Tutorial deleteById(Long id);

    List<Tutorial> deleteAll();
}
