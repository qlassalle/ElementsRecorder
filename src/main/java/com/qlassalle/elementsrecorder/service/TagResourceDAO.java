package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.domain.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TagResourceDAO {

    private static final String TAG_TABLE = "elements_recorder_schema.tag";
    private static final String TAG_RESOURCE_TABLE = "elements_recorder_schema.tag_resource";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void save(UUID resourceId, Set<UUID> tagsId) {
        var query = "insert into " + TAG_RESOURCE_TABLE + " values(:tagId, :resourceId)";
        tagsId.forEach(tag -> jdbcTemplate.update(query, new MapSqlParameterSource().addValue("tagId", tag)
                                                                                    .addValue("resourceId",
                                                                                              resourceId)));
    }

    public Set<Tag> findByResourceId(UUID resourceId) {
        var query = """
                select id, name  
                from %s
                where id in (
                select tag_id  
                from %s
                where resource_id = :resourceId)
                """.formatted(TAG_TABLE, TAG_RESOURCE_TABLE);
        var tags = jdbcTemplate.query(query, new MapSqlParameterSource("resourceId", resourceId),
                                      new TagResourceRowMapper());
        return new HashSet<>(tags);
    }

    public void delete(UUID resourceId) {
        var query = """
                delete from %s where resource_id = :resourceId
                """.formatted(TAG_RESOURCE_TABLE);
        jdbcTemplate.update(query, new MapSqlParameterSource("resourceId", resourceId));
    }

    private static class TagResourceRowMapper implements RowMapper<Tag> {

        @Override
        public Tag mapRow(ResultSet resultSet, int i) throws SQLException {
            return Tag.builder()
                    .id(UUID.fromString(resultSet.getString(1)))
                    .name(resultSet.getString(2))
                    .build();
        }
    }
}
