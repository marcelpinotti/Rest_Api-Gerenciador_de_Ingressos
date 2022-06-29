package br.com.marcelpinotti.gerenciadordeingressos.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMaperConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
