package com.memrevatan.oraclejdbc.controller;

import com.memrevatan.oraclejdbc.dto.TutorialCreateDto;
import com.memrevatan.oraclejdbc.dto.TutorialDto;
import com.memrevatan.oraclejdbc.dto.TutorialUpdateDto;
import com.memrevatan.oraclejdbc.service.TutorialService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tutorial")
@AllArgsConstructor
public class TutorialController {
    private final TutorialService tutorialService;

    @PostMapping
    public TutorialDto createTutorial(@RequestBody TutorialCreateDto tutorialCreateDto) {
        return tutorialService.createTutorial(tutorialCreateDto);
    }

    @GetMapping("/{id}")
    public TutorialDto getTutorial(@PathVariable("id") Long id) {
        return tutorialService.getTutorial(id);
    }

    @GetMapping
    public List<TutorialDto> getTutorials() {
        return tutorialService.getTutorials();
    }

    @GetMapping("/title")
    public List<TutorialDto> getTitleContaining(@RequestParam("title") String title) {
        return tutorialService.getTitleContaining(title);
    }

    @PutMapping("/{id}")
    public TutorialDto updateTutorial(@PathVariable("id") Long id, @RequestBody TutorialUpdateDto tutorialUpdateDto) {
        return tutorialService.updateTutorial(id, tutorialUpdateDto);
    }

    @DeleteMapping("/{id}")
    public TutorialDto deleteTutorial(@PathVariable("id") Long id) {
        return tutorialService.deleteTutorial(id);
    }

    @DeleteMapping
    public List<TutorialDto> deleteAllTutorials() {
        return tutorialService.deleteAllTutorials();
    }
}
