package com.memrevatan.oraclejdbc.service.impl;

import com.memrevatan.oraclejdbc.dto.TutorialCreateDto;
import com.memrevatan.oraclejdbc.dto.TutorialDto;
import com.memrevatan.oraclejdbc.dto.TutorialUpdateDto;
import com.memrevatan.oraclejdbc.entity.Tutorial;
import com.memrevatan.oraclejdbc.repository.TutorialRepository;
import com.memrevatan.oraclejdbc.service.TutorialService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<TutorialDto> getTutorials() {
        List<Tutorial> tutorials = tutorialRepository.findAll();
        return tutorials.stream().map(tutorial -> modelMapper.map(tutorial, TutorialDto.class)).toList();
    }

    @Override
    public List<TutorialDto> getTitleContaining(String title) {
        List<Tutorial> tutorials = tutorialRepository.findByTitleContaining(title);
        return tutorials.stream().map(tutorial -> modelMapper.map(tutorial, TutorialDto.class)).toList();
    }

    @Override
    public TutorialDto updateTutorial(Long id, TutorialUpdateDto tutorialUpdateDto) {
        Tutorial tutorial = tutorialRepository.update(id, tutorialUpdateDto);
        return modelMapper.map(tutorial, TutorialDto.class);
    }

    @Override
    public TutorialDto deleteTutorial(Long id) {
        Tutorial tutorial = tutorialRepository.deleteById(id);
        return modelMapper.map(tutorial, TutorialDto.class);
    }

    @Override
    public List<TutorialDto> deleteAllTutorials() {
        List<Tutorial> tutorials = tutorialRepository.deleteAll();
        return tutorials.stream().map(tutorial -> modelMapper.map(tutorial, TutorialDto.class)).toList();
    }


}
