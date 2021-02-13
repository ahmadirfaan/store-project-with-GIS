package com.irfaan.api.inventory.config;

import com.irfaan.api.inventory.entities.Unit;
import com.irfaan.api.inventory.models.UnitModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // berfungsi untuk menampung beans(Global" Variable Object pada kode)
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // DIGUNAKAN UNTUK BENAR-BENAR MEMBUAT NAMA FIELD OBJEKNYA HARUS SAMA

        modelMapper.typeMap(UnitModel.class, Unit.class).addMappings(mapper -> {
            mapper.skip(Unit::setId);
        });
        return modelMapper;
    }

}
