package com.reviewboard.user.service.dozer;

import org.dozer.classmap.MappingFileData;
import org.dozer.loader.DozerBuilder;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeDefinition;
import org.dozer.loader.api.TypeMappingBuilder;
import org.dozer.loader.api.TypeMappingOption;

/**
 * Extends the BeanMappingBuilder to expose the DozerBuilder
 */
public abstract class ExtendedBeanMappingBuilder extends BeanMappingBuilder {

    private DozerBuilder builder;

    @Override
    public MappingFileData build() {
        builder = new DozerBuilder();
        configure();
        return builder.build();
    }

    @Override
    public TypeMappingBuilder mapping(TypeDefinition typeA, TypeDefinition typeB,
                                      TypeMappingOption... typeMappingOption) {
        DozerBuilder.MappingBuilder mappingBuilder = builder.mapping();
        DozerBuilder.ClassDefinitionBuilder typeBuilderA = mappingBuilder.classA(typeA.getName());
        DozerBuilder.ClassDefinitionBuilder typeBuilderB = mappingBuilder.classB(typeB.getName());

        typeA.build(typeBuilderA);
        typeB.build(typeBuilderB);

        for (TypeMappingOption option : typeMappingOption) {
            option.apply(mappingBuilder);
        }

        return new TypeMappingBuilder(mappingBuilder);
    }

    @Override
    protected final void configure() {
        configure(builder);
    }

    protected abstract void configure(DozerBuilder builder);

}
