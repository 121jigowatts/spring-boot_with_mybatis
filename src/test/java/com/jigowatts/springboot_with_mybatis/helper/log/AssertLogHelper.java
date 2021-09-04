package com.jigowatts.springboot_with_mybatis.helper.log;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.groups.Tuple;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

public class AssertLogHelper {
    private Logger logger;
    private ListAppender<ILoggingEvent> listAppender;

    public AssertLogHelper(String className) {
        this.logger = (Logger) LoggerFactory.getLogger(className);
        this.listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
    }

    public void detachAppender() {
        this.logger.detachAppender(this.listAppender);
    }

    public void assertContains(String message) {
        assertThat(this.listAppender.list).extracting(ILoggingEvent::getFormattedMessage).contains(message);
    }

    public void assertContains(String message, Level level) {
        assertThat(this.listAppender.list).extracting(ILoggingEvent::getFormattedMessage, ILoggingEvent::getLevel)
                .contains(Tuple.tuple(message, level));
    }
}
