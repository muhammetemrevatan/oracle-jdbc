package com.memrevatan.oraclejdbc.controller;

import com.memrevatan.oraclejdbc.dto.TutorialCreateDto;
import com.memrevatan.oraclejdbc.dto.TutorialDto;
import com.memrevatan.oraclejdbc.service.TutorialService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
