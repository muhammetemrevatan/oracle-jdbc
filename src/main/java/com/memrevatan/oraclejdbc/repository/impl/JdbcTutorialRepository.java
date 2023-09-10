package com.memrevatan.oraclejdbc.repository.impl;

import com.memrevatan.oraclejdbc.constant.ErrorConstants;
import com.memrevatan.oraclejdbc.constant.SqlConstants;
import com.memrevatan.oraclejdbc.dto.TutorialCreateDto;
import com.memrevatan.oraclejdbc.dto.TutorialUpdateDto;
import com.memrevatan.oraclejdbc.entity.Tutorial;
import com.memrevatan.oraclejdbc.exception.TutorialNotDeletedException;
import com.memrevatan.oraclejdbc.exception.TutorialNotSavedException;
import com.memrevatan.oraclejdbc.exception.TutorialNotUpdatedException;
import com.memrevatan.oraclejdbc.mapper.TutorialMapper;
import com.memrevatan.oraclejdbc.repository.TutorialRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
@Slf4j
public class JdbcTutorialRepository implements TutorialRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ModelMapper modelMapper;
    private static final Pattern SQL_PATTERN = Pattern.compile("-- (\\w+)\\s*(.*?)\\s*;");
    private final Map<String, String> sqlQueries = new HashMap<>();
    private RowMapper<Tutorial> tutorialRowMapper;

    public JdbcTutorialRepository(JdbcTemplate jdbcTemplate, ModelMapper modelMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() throws IOException {
        Resource resource = new ClassPathResource(SqlConstants.SQL_QUERIES);
        String queriesContent = Files.readString(resource.getFile().toPath());

        Matcher matcher = SQL_PATTERN.matcher(queriesContent);
        while (matcher.find()) {
            sqlQueries.put(matcher.group(1), matcher.group(2));
        }

        tutorialRowMapper = createRowMapper();
    }

    @Override
    @Transactional
    public Tutorial save(TutorialCreateDto tutorialDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlQueries.get(SqlConstants.SAVE_TUTORIAL), new String[]{SqlConstants.ID});
            ps.setString(1, tutorialDto.getTitle());
            ps.setString(2, tutorialDto.getDescription());
            ps.setString(3, tutorialDto.getContent());
            ps.setString(4, tutorialDto.getStatus());
            ps.setTimestamp(5, Timestamp.valueOf(tutorialDto.getCreatedAt()));
            ps.setTimestamp(6, Timestamp.valueOf(tutorialDto.getUpdatedAt()));
            ps.setString(7, tutorialDto.getCreatedBy());
            ps.setString(8, tutorialDto.getUpdatedBy());
            return ps;
        }, keyHolder);

        if (rowsAffected != 1)
            throw new TutorialNotSavedException(ErrorConstants.TUTORIAL_NOT_SAVED_ERROR);

        Tutorial tutorial = modelMapper.map(tutorialDto, Tutorial.class);

        Number key = keyHolder.getKey();
        if (key != null) {
            tutorial.setId(key.longValue());
        }

        return tutorial;
    }

    @Override
    @Transactional
    public Tutorial findById(Long id) {
        List<Tutorial> results = jdbcTemplate.query(sqlQueries.get(SqlConstants.GET_TUTORIAL), this.tutorialRowMapper, id);

        Assert.notEmpty(results, ErrorConstants.TUTORIAL_NOT_FOUND_WITH_ID_ERROR + id);

        return results.get(0);
    }

    @Override
    @Transactional
    public List<Tutorial> findAll() {
        return jdbcTemplate.query(sqlQueries.get(SqlConstants.GET_TUTORIALS), this.tutorialRowMapper);
    }

    @Override
    @Transactional
    public List<Tutorial> findByTitleContaining(String title) {
        return jdbcTemplate.query(sqlQueries.get(SqlConstants.GET_TITLE_TUTORIAL), this.tutorialRowMapper, title);
    }

    @Override
    @Transactional
    public Tutorial update(Long id, TutorialUpdateDto tutorialUpdateDto) {
        Tutorial tutorial = findById(id);
        TutorialMapper.mapNonNullValues(tutorialUpdateDto, tutorial);

        int response = jdbcTemplate.update(sqlQueries.get(SqlConstants.UPDATE_TUTORIAL),
                tutorial.getTitle(), tutorial.getDescription(), tutorial.getContent(), tutorial.getStatus(), id);

        if (response != 1)
            throw new TutorialNotUpdatedException(ErrorConstants.TUTORIAL_NOT_UPDATED_ERROR + id);

        return tutorial;
    }

    @Override
    @Transactional
    public Tutorial deleteById(Long id) {
        Tutorial tutorial = findById(id);

        int rowsAffected = jdbcTemplate.update(sqlQueries.get(SqlConstants.DELETE_TUTORIAL), id);

        if (rowsAffected != 1)
            throw new TutorialNotDeletedException(ErrorConstants.TUTORIAL_NOT_DELETED_WITH_ID_ERROR + id);

        return tutorial;
    }

    @Override
    @Transactional
    public List<Tutorial> deleteAll() {
        List<Tutorial> tutorials = findAll();

        int rowsAffected = jdbcTemplate.update(sqlQueries.get(SqlConstants.DELETE_TUTORIALS));
        if (rowsAffected < 1) {
            throw new TutorialNotDeletedException(ErrorConstants.TUTORIALS_NOT_DELETED_ERROR);
        }

        return tutorials;
    }

    private RowMapper<Tutorial> createRowMapper() {
        return (rs, rowNum) -> {
            Tutorial tutorial = new Tutorial();
            tutorial.setId(rs.getLong(SqlConstants.ID));
            tutorial.setTitle(rs.getString(SqlConstants.TITLE));
            tutorial.setDescription(rs.getString(SqlConstants.DESCRIPTION));
            tutorial.setContent(rs.getString(SqlConstants.CONTENT));
            tutorial.setStatus(rs.getString(SqlConstants.STATUS));
            tutorial.setCreatedAt(rs.getTimestamp(SqlConstants.CREATED_AT).toLocalDateTime());
            tutorial.setUpdatedAt(rs.getTimestamp(SqlConstants.UPDATED_AT).toLocalDateTime());
            tutorial.setCreatedBy(rs.getString(SqlConstants.CREATED_BY));
            tutorial.setUpdatedBy(rs.getString(SqlConstants.UPDATED_BY));
            return tutorial;
        };
    }
}
