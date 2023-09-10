package com.memrevatan.oraclejdbc.service;

import com.memrevatan.oraclejdbc.dto.TutorialCreateDto;
import com.memrevatan.oraclejdbc.dto.TutorialDto;
import com.memrevatan.oraclejdbc.dto.TutorialUpdateDto;

import java.util.List;

public interface TutorialService {
    TutorialDto createTutorial(TutorialCreateDto tutorialDto);

    TutorialDto getTutorial(Long id);

    List<TutorialDto> getTutorials();

    List<TutorialDto> getTitleContaining(String title);

    TutorialDto updateTutorial(Long id, TutorialUpdateDto tutorialUpdateDto);

    TutorialDto deleteTutorial(Long id);

    List<TutorialDto> deleteAllTutorials();
}
