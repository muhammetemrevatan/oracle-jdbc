package com.memrevatan.oraclejdbc.repository.impl;

import com.memrevatan.oraclejdbc.constant.ErrorConstants;
import com.memrevatan.oraclejdbc.dto.TutorialCreateDto;
import com.memrevatan.oraclejdbc.entity.Tutorial;
import com.memrevatan.oraclejdbc.exception.TutorialNotSavedException;
import com.memrevatan.oraclejdbc.repository.TutorialRepository;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
public class JdbcTutorialRepository implements TutorialRepository {
    private final JdbcTemplate jdbcTemplate;

    private final ModelMapper modelMapper;
    private static final Pattern SQL_PATTERN = Pattern.compile("-- (\\w+)\\s*(.*?)\\s*;");
    private final Map<String, String> sqlQueries = new HashMap<>();

    public JdbcTutorialRepository(JdbcTemplate jdbcTemplate, ModelMapper modelMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() throws IOException {
        Resource resource = new ClassPathResource("sql/queries.sql");
        String queriesContent = Files.readString(resource.getFile().toPath());

        Matcher matcher = SQL_PATTERN.matcher(queriesContent);
        while (matcher.find()) {
            sqlQueries.put(matcher.group(1), matcher.group(2));
        }
    }

    @Override
    @Transactional
    public Tutorial save(TutorialCreateDto tutorialDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlQueries.get("SAVE_TUTORIAL"), new String[]{"ID"});
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
    public Tutorial findById(Long id) {
        var a = "edfg";
        RowMapper<Tutorial> rowMapper = (rs, rowNum) -> {
            Tutorial tutorial = new Tutorial();
            tutorial.setId(rs.getLong("ID"));
            tutorial.setTitle(rs.getString("TITLE"));
            tutorial.setDescription(rs.getString("DESCRIPTION"));
            tutorial.setContent(rs.getString("CONTENT"));
            tutorial.setStatus(rs.getString("STATUS"));
            tutorial.setCreatedAt(rs.getTimestamp("CREATEDAT").toLocalDateTime());
            tutorial.setUpdatedAt(rs.getTimestamp("UPDATEDAT").toLocalDateTime());
            tutorial.setCreatedBy(rs.getString("CREATEDBY"));
            tutorial.setUpdatedBy(rs.getString("UPDATEDBY"));
            return tutorial;
        };

        return jdbcTemplate.queryForObject(sqlQueries.get("GET_TUTORIAL"), rowMapper, id);
    }

    @Override
    public List<Tutorial> findAll() {
        return null;
    }

    @Override
    public List<Tutorial> findByTitleContaining(String title) {
        return null;
    }

    @Override
    public Tutorial update(Tutorial book) {
        return null;
    }

    @Override
    public Tutorial deleteById(Long id) {
        return null;
    }

    @Override
    public List<Tutorial> deleteAll() {
        return null;
    }
}
