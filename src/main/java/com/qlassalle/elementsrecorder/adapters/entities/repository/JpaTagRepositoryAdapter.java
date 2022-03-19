package com.qlassalle.elementsrecorder.adapters.entities.repository;

import com.qlassalle.elementsrecorder.adapters.entities.mappers.TagMapper;
import com.qlassalle.elementsrecorder.domain.model.Tag;
import com.qlassalle.elementsrecorder.domain.model.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class JpaTagRepositoryAdapter implements TagRepository {

    private final JpaTagRepository jpaTagRepository;
    private final TagMapper tagMapper = new TagMapper();

    @Override
    public Tag save(Tag tag) {
        var tagEntity = tagMapper.map(tag);
        var savedEntity = jpaTagRepository.save(tagEntity);
        return tagMapper.map(savedEntity);
    }

    @Override
    public Set<Tag> findAll() {
        throw new NotImplementedException("Coming soon!");
    }

    @Override
    public Optional<Tag> findByNameAndUser(String name, UUID userId) {
        return jpaTagRepository.findByNameAndUserId(name, userId)
                               .map(tagMapper::map);
    }
}
