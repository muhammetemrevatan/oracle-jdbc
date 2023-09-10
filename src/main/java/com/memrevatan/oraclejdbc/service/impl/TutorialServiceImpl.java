package com.memrevatan.oraclejdbc.service.impl;

import com.memrevatan.oraclejdbc.dto.TutorialCreateDto;
import com.memrevatan.oraclejdbc.dto.TutorialDto;
import com.memrevatan.oraclejdbc.entity.Tutorial;
import com.memrevatan.oraclejdbc.repository.TutorialRepository;
import com.memrevatan.oraclejdbc.service.TutorialService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorialServiceImpl implements TutorialService {

    private final TutorialRepository tutorialRepository;
    private final ModelMapper modelMapper;

    @Override
    public TutorialDto createTutorial(TutorialCreateDto tutorialDto) {
        Tutorial tutorial = tutorialRepository.save(tutorialDto);
        return modelMapper.map(tutorial, TutorialDto.class);
    }

    @Override
    public TutorialDto getTutorial(Long id) {
        Tutorial tutorial = tutorialRepository.findById(id);
        return modelMapper.map(tutorial, TutorialDto.class);
    }
}
