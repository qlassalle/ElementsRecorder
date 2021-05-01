package com.qlassalle.elementsrecorder.adapters.entities;

import com.qlassalle.elementsrecorder.domain.model.UUIDProvider;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RandomUUIDProvider implements UUIDProvider {

    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }
}
