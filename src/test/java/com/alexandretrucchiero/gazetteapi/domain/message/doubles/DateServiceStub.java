package com.alexandretrucchiero.gazetteapi.domain.message.doubles;


import com.alexandretrucchiero.gazetteapi.domain.message.DateService;

import java.time.OffsetDateTime;

// Stubs : provide canned answers to calls made during the test
public class DateServiceStub extends DateService {

    public static final OffsetDateTime NOW = OffsetDateTime.now();

    @Override
    public OffsetDateTime now() {
        return NOW;
    }
}
