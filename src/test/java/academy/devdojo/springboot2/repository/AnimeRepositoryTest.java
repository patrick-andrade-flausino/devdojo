package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Anime;
import org.assertj.core.api.Assertions;
import org.hibernate.validator.constraints.time.DurationMax;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Teste para o repositorio de Anime")
class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save creates anime when sucessfull")
    void save_PersistAnime_WhenSucessfull(){
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());
        Assertions.assertThat(animeSaved.getId()).isNotNegative();
        Assertions.assertThat(animeSaved.getId()).isNotZero();
    }

    private Anime createAnime(){
        Anime anime = new Anime();
        anime.setName("Naruto Brabo");
        return anime;

    }
}