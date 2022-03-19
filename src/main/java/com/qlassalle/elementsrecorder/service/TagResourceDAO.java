package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.domain.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TagResourceDAO {

    private final JdbcTemplate jdbcTemplate;

    public void save(UUID resourceId, Set<UUID> tagsId) {
        var query = "insert into elements_recorder_schema.tag_resource values('%s', '%s')";
        tagsId.forEach(tag -> jdbcTemplate.update(String.format(query, tag, resourceId)));
    }

    public Set<Tag> findByResourceId(UUID resourceId) {
        var query = "select id, name " +
                "from elements_recorder_schema.tag " +
                "where id in ( " +
                "select tag_id " +
                "from elements_recorder_schema.tag_resource " +
                "where resource_id = '%s')";
        var tags = jdbcTemplate.query(String.format(query, resourceId), new TagResourceRowMapper());
        return new HashSet<>(tags);
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
