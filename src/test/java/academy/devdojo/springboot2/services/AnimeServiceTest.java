package academy.devdojo.springboot2.services;

import academy.devdojo.springboot2.controller.AnimeController;
import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.exception.BadRequestException;
import academy.devdojo.springboot2.records.AnimePostRecords;
import academy.devdojo.springboot2.records.AnimePutRecords;
import academy.devdojo.springboot2.repository.AnimeRepository;
import academy.devdojo.springboot2.service.AnimeService;
import academy.devdojo.springboot2.util.AnimeCreator;
import academy.devdojo.springboot2.util.AnimePostRequestBodyCreator;
import academy.devdojo.springboot2.util.AnimePutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ExtendWith(SpringExtension.class)
class AnimeServiceTest {
    @InjectMocks
    private AnimeService animeService;

    @Mock
    private AnimeRepository animeRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(animePage);

        BDDMockito.when(animeRepositoryMock.findAll())
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMock.save(ArgumentMatchers.any(Anime.class)))
                .thenReturn(AnimeCreator.createValidAnime());

        BDDMockito.doNothing().when(animeRepositoryMock)
                .delete(ArgumentMatchers.any(Anime.class));
    }

    @Test
    @DisplayName("List returns list of anime inside page object when successfull")
    void list_ReturnsListOfAnimeInsidePageObject_WhenSuccessfull(){
        String expectedName = AnimeCreator.createValidAnime().getName();

        Page<Anime> animePage = animeService.listAll(PageRequest.of(1,15));

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);


    }

    @Test
    @DisplayName("listAllNoPageAble returns list of anime inside page object when successfull")
    void listAllNoPageAble_ReturnsListOfAllAnimesInsidePageObject_WhenSuccessfull(){
        String expectedName = AnimeCreator.createValidAnime().getName();

        List<Anime> animePage = animeService.listAllNoPageAble();

        Assertions.assertThat(animePage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(animePage.get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("findByIdOrThrowRequestException returns a anime inside page object when successfull")
    void findByIdOrThrowRequestException_ReturnsAAnimeInsidePageObject_WhenSuccessfull(){
        UUID expectedId = AnimeCreator.createValidAnime().getId();

        Anime animePage = animeService.findByIdOrThrowRequestException(UUID.randomUUID());

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.getId()).isEqualTo(expectedId);

    }

    @Test
    @DisplayName("findByIdOrThrowRequestException returns a anime inside page object when successfull")
    void findByIdOrThrowRequestException_ThrowsBadRequestException_WhenAnimeIsNotFound(){
        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> animeService.findByIdOrThrowRequestException(UUID.randomUUID()));
    }

    @Test
    @DisplayName("Find by name returns a anime inside page object when successfull")
    void findByName_ReturnsAAnimeInsidePageObject_WhenSuccessfull(){
        String expectedName = AnimeCreator.createValidAnime().getName();

        List<Anime> animePage = animeService.findByName("anime");

        Assertions.assertThat(animePage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(animePage.get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("Find by name empty returns a empty list when anime is not found")
    void findByName_ReturnsEmptyListOfAnime_WhenAnimeIsNotFound(){
        BDDMockito.when(animeRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Anime> animePage = animeService.findByName("anime");

        Assertions.assertThat(animePage).isNotNull().isEmpty();

    }

    @Test
    @DisplayName("save returns a anime inside page object when successfull")
    void save_ReturnsAAnimeInsidePageObject_WhenSuccessfull(){

        Anime animePage = animeService.save(AnimePostRequestBodyCreator.createValidAnime());

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.getName()).isEqualTo(AnimePostRequestBodyCreator.createValidAnime().getName());

    }

    @Test
    @DisplayName("replace a anime inside page object when successfull")
    void replace_ReplaceAAnimeInsidePageObject_WhenSuccessfull(){

        Assertions.assertThatCode(() -> animeService.replace(AnimePutRequestBodyCreator.createValidAnime()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("replace a anime inside page object when successfull")
    void delete_DeleteAAnimeInsidePageObject_WhenSuccessfull(){

        Assertions.assertThatCode(() -> animeService.delete(AnimePutRequestBodyCreator.createValidAnime().getId()))
                .doesNotThrowAnyException();

    }

}