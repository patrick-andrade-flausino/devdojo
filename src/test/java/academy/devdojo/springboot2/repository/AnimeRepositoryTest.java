package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.util.AnimeCreator;
import jakarta.validation.*;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.hibernate.validator.constraints.time.DurationMax;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Teste para o repositorio de animações")
@Log4j2
class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save persist anime when sucessfull")
    void save_PersistAnime_WhenSucessfull(){
        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());
        Assertions.assertThat(animeSaved.getId()).isNotNull();
    }

    @Test
    @DisplayName("Save update anime when sucessfull")
    void save_UpdateAnime_WhenSucessfull(){
        Anime animeToBeUpdate = AnimeCreator.createAnimeToBeSaved();
        animeToBeUpdate.setName("NATURO O BRABO DE GOIÁS");
        Anime animeSaved = this.animeRepository.save(animeToBeUpdate);
        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeUpdate.getName());
        Assertions.assertThat(animeSaved.getId()).isNotNull().isEqualTo(animeToBeUpdate.getId());

    }

    @Test
    @DisplayName("delete anime when sucessfull")
    void delete_RemovesAnime_WhenSucessfull(){
        Anime animeToBeUpdate = AnimeCreator.createAnimeToBeSaved();
        animeToBeUpdate.setId(UUID.fromString("94debad2-0265-4600-a2e6-93c5594447da"));
        this.animeRepository.delete(animeToBeUpdate);

        Optional<Anime> byId = this.animeRepository.findById(animeToBeUpdate.getId());
        Assertions.assertThat(byId).isEmpty();



    }

    @Test
    @DisplayName("find anime by id when sucessfull")
    void find_GetAnimeById_WhenSucessfull(){
        Anime animeToBeUpdate = AnimeCreator.createAnimeToBeSaved();

        Anime savedAnime = this.animeRepository.save(animeToBeUpdate);

        String name= savedAnime.getName();

        List<Anime> byName = this.animeRepository.findByName(name);

        Assertions.assertThat(byName).isNotEmpty().contains(savedAnime);

    }
    @Test
    @DisplayName("empty list when name is not found")
    void empty_GetEmptyListWhenNameIsNotFound_WhenSucessfull(){
        List<Anime> byName = this.animeRepository.findByName("emptyList");

        Assertions.assertThat(byName).isEmpty();
    }

    @Test
    @DisplayName("trhow exception when name is empty")
    void empty_trhowExceptionWhenNameIsNotFound_WhenSucessfull(){

            Anime byName = Anime.builder().build();

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Anime>> violations = validator.validate(byName);

            log.info(violations);
            Assertions.assertThat(violations).isNotEmpty();
    }


}