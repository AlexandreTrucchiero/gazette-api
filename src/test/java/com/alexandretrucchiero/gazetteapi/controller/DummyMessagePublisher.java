package com.alexandretrucchiero.gazetteapi.controller;

import com.alexandretrucchiero.gazetteapi.domain.message.model.Message;
import com.alexandretrucchiero.gazetteapi.domain.message.spi.MessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Sera injectée grace à primary dans MessageServiceDouble
 */
@Slf4j
@Service
@Primary
public class DummyMessagePublisher implements MessagePublisher {

    @Override
    public void publier(Message message) {
        log.info("******************** {}", message);
    }
}
