package com.jigowatts.springboot_with_mybatis.helper.yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.jigowatts.springboot_with_mybatis.helper.yaml.constructor.ExtensionConstructorBase;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class YamlLoader<T> {
    private Constructor constructor;
    private static final String BASE_PATH = "src/test/resources/";

    public YamlLoader() {
        this.constructor = new ExtensionConstructorBase();
    }

    public YamlLoader(Constructor constructor) {
        this.constructor = constructor;
    }

    public T load(String yml) throws IOException {
        var path = Paths.get(BASE_PATH + yml);
        try (InputStream io = Files.newInputStream(path)) {
            return new Yaml(this.constructor).load(io);
        }
    }
}
