package com.memrevatan.oraclejdbc.constant;


public class SqlConstants {

    // Compare this snippet from src\main\java\com\memrevatan\oraclejdbc\repository\impl\JdbcTutorialRepository.java:
    private SqlConstants() {
        throw new AssertionError("Utility class is not instantiable");
    }

    // Query Classpath resource
    public static final String SQL_QUERIES = "sql/queries.sql";

    // Query names
    public static final String SAVE_TUTORIAL = "SAVE_TUTORIAL";
    public static final String GET_TUTORIAL = "GET_TUTORIAL";
    public static final String GET_TUTORIALS = "GET_TUTORIALS";
    public static final String GET_TITLE_TUTORIAL = "GET_TITLE_TUTORIAL";
    public static final String DELETE_TUTORIAL = "DELETE_TUTORIAL";
    public static final String DELETE_TUTORIALS = "DELETE_TUTORIALS";
    public static final String UPDATE_TUTORIAL = "UPDATE_TUTORIAL";

    // Query parameters
    public static final String ID = "ID";
    public static final String TITLE = "TITLE";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String CONTENT = "CONTENT";
    public static final String STATUS = "STATUS";
    public static final String CREATED_AT = "CREATED_AT";
    public static final String UPDATED_AT = "UPDATED_AT";
    public static final String CREATED_BY = "CREATED_BY";
    public static final String UPDATED_BY = "UPDATED_BY";
}
