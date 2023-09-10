package com.memrevatan.oraclejdbc.service;

import com.memrevatan.oraclejdbc.dto.TutorialCreateDto;
import com.memrevatan.oraclejdbc.dto.TutorialDto;

public interface TutorialService {
    TutorialDto createTutorial(TutorialCreateDto tutorialDto);

    TutorialDto getTutorial(Long id);
}
