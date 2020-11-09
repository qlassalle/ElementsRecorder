package com.qlassalle.elementsrecorder.utils;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;
import java.sql.SQLException;

public class TruncateTablesExtension implements BeforeAllCallback, AfterEachCallback {

    private static final String SCHEMA_NAME = "elements_recorder_schema";

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        cleanupDatabase(context);
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        cleanupDatabase(context);
    }

    private static void cleanupDatabase(ExtensionContext context) throws SQLException {
        JdbcTemplate jdbcTemplate = SpringExtension.getApplicationContext(context).getBean(JdbcTemplate.class);

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {

            String dbName = connection.getMetaData().getDatabaseProductName();

            if ("PostgreSQL".equals(dbName)) {
                jdbcTemplate.execute("SET SESSION_REPLICATION_ROLE = REPLICA;");
                jdbcTemplate.queryForList("SELECT TABLENAME FROM PG_TABLES WHERE " + "SCHEMANAME = '" + SCHEMA_NAME + "';", String.class)
                            .forEach(tableName -> jdbcTemplate.execute("DELETE FROM " + SCHEMA_NAME + "." + tableName + ";"));
                jdbcTemplate.execute("SET SESSION_REPLICATION_ROLE = DEFAULT;");
            }
        }
    }
}
