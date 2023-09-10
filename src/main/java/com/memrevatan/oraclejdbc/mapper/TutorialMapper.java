package com.memrevatan.oraclejdbc.mapper;

import com.memrevatan.oraclejdbc.dto.TutorialUpdateDto;
import com.memrevatan.oraclejdbc.entity.Tutorial;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class TutorialMapper {
    public static void mapNonNullValues(TutorialUpdateDto tutorialUpdateDto, Tutorial tutorial) {
        Optional.ofNullable(tutorialUpdateDto.getTitle()).ifPresent(tutorial::setTitle);
        Optional.ofNullable(tutorialUpdateDto.getDescription()).ifPresent(tutorial::setDescription);
        Optional.ofNullable(tutorialUpdateDto.getContent()).ifPresent(tutorial::setContent);
        Optional.ofNullable(tutorialUpdateDto.getStatus()).ifPresent(tutorial::setStatus);
    }
}
