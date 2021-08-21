package com.jigowatts.springboot_with_mybatis.helper.yaml.constructor;

import com.jigowatts.springboot_with_mybatis.domain.model.complexdata.ComplexData;
import com.jigowatts.springboot_with_mybatis.domain.model.item.Bag;
import com.jigowatts.springboot_with_mybatis.domain.model.item.Glasses;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.nodes.Tag;

public class ComplexDataConstructor extends ExtensionConstructorBase {
    public ComplexDataConstructor() {
        super(ComplexData.class);
        this.addTypeDescription(new TypeDescription(Glasses.class, new Tag("!glasses")));
        this.addTypeDescription(new TypeDescription(Bag.class, new Tag("!bag")));
    }
}
