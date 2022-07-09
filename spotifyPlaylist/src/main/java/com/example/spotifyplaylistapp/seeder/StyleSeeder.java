package com.example.spotifyplaylistapp.seeder;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StyleSeeder implements CommandLineRunner {
    private final StyleRepository styleRepository;

    @Autowired
    public StyleSeeder(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(this.styleRepository.count() ==0){
            Arrays.stream(StyleEnum.values())
                    .map(Style::new)
                    .forEach(this.styleRepository::save);
        }
    }
}
