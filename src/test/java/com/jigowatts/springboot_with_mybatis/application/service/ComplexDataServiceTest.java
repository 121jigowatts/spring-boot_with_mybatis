package com.jigowatts.springboot_with_mybatis.application.service;

import static com.jigowatts.springboot_with_mybatis.application.service.helper.AddressHelper.address_id_1;
import static com.jigowatts.springboot_with_mybatis.application.service.helper.CustomersHelper.customers_id_1;
import static com.jigowatts.springboot_with_mybatis.application.service.helper.ItemsHelper.items_id_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.jigowatts.springboot_with_mybatis.application.component.AddressComponent;
import com.jigowatts.springboot_with_mybatis.application.component.CustomerComponent;
import com.jigowatts.springboot_with_mybatis.application.component.ItemComponent;
import com.jigowatts.springboot_with_mybatis.domain.model.complexdata.ComplexData;
import com.jigowatts.springboot_with_mybatis.domain.model.item.Bag;
import com.jigowatts.springboot_with_mybatis.domain.model.item.Glasses;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

/**
 * MessagesServiceTest
 */
@SpringBootTest
public class ComplexDataServiceTest {
    @InjectMocks
    ComplexDataService target;
    @Mock
    private AddressComponent addressComponent;
    @Mock
    private CustomerComponent customerComponent;
    @Mock
    private ItemComponent itemComponent;

    @Test
    public void findByIdTest() throws IOException {
        String id = "1";
        when(addressComponent.getBy(id)).thenReturn(address_id_1());
        when(customerComponent.getBy(id)).thenReturn(customers_id_1());
        when(itemComponent.getBy(id)).thenReturn(items_id_1());

        var path = Paths.get("src/test/resources/" + "fixture/complexdata/findByIdTest.yml");
        try (InputStream io = Files.newInputStream(path)) {
            Constructor constructor = new Constructor(ComplexData.class);
            constructor.addTypeDescription(new TypeDescription(Glasses.class, new Tag("!glasses")));
            constructor.addTypeDescription(new TypeDescription(Bag.class, new Tag("!bag")));
            Yaml yaml = new Yaml(constructor);
            ComplexData expected = yaml.load(io);
            var actual = target.findById(id);

            assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        }
    }
}