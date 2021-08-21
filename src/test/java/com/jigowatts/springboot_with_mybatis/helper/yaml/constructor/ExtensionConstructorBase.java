package com.jigowatts.springboot_with_mybatis.helper.yaml.constructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;

public class ExtensionConstructorBase extends Constructor {
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public ExtensionConstructorBase() {
        this(Object.class);
    }

    public ExtensionConstructorBase(Class<? extends Object> theRoot) {
        super(theRoot);
        setup();
    }

    private void setup() {
        yamlConstructors.put(new Tag("!localdate"), new LocalDateConstructor());
        yamlConstructors.put(new Tag("!localdatetime"), new LocalDateTimeConstructor());
    }

    protected class LocalDateConstructor extends Constructor.ConstructScalar {
        @Override
        public Object construct(Node node) {
            return LocalDate.parse(((ScalarNode) node).getValue());
        }
    }

    protected class LocalDateTimeConstructor extends Constructor.ConstructScalar {
        @Override
        public Object construct(Node node) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
            return LocalDateTime.parse(((ScalarNode) node).getValue(), dtf);
        }
    }
}
