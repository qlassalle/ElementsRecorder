package com.qlassalle.elementsrecorder.unittests.adapters;

import com.qlassalle.elementsrecorder.domain.model.UUIDProvider;

import java.util.UUID;

public class FixedUUIDProvider implements UUIDProvider {

    public static final UUID DEFAULT_UUID = UUID.fromString("00000000-0000-0000-0000-000000000001");
    public static final String DEFAULT_UUID_AS_STRING = "00000000-0000-0000-0000-000000000001";

    @Override
    public UUID generate() {
        return UUID.fromString(DEFAULT_UUID_AS_STRING);
    }
}
