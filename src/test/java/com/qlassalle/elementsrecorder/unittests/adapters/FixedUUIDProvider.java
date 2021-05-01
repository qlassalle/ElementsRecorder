package com.qlassalle.elementsrecorder.unittests.adapters;

import com.qlassalle.elementsrecorder.domain.model.UUIDProvider;

import java.util.UUID;

public class FixedUUIDProvider implements UUIDProvider {
    @Override
    public UUID generate() {
        return UUID.fromString("00000000-0000-0000-0000-000000000001");
    }
}
