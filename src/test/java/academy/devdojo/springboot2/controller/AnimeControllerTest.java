package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.records.AnimePostRecords;
import academy.devdojo.springboot2.records.AnimePutRecords;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.UUID;


@ExtendWith(SpringExtension.class)
class AnimeControllerTest {
    @InjectMocks
    private AnimeController animeController;

    @Mock
    private AnimeService animeServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(animePage);

        BDDMockito.when(animeServiceMock.listAllNoPageAble())
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeServiceMock.findByIdOrThrowRequestException(ArgumentMatchers.any()))
                .thenReturn(AnimeCreator.createValidAnime());

        BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeServiceMock.save(ArgumentMatchers.any(AnimePostRecords.class)))
                .thenReturn(AnimeCreator.createValidAnime());

        BDDMockito.doNothing().when(animeServiceMock)
                .replace(ArgumentMatchers.any(AnimePutRecords.class));

        BDDMockito.doNothing().when(animeServiceMock)
                .replace(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("List returns list of anime inside page object when successfull")
    void list_ReturnsListOfAnimeInsidePageObject_WhenSuccessfull(){
        String expectedName = AnimeCreator.createValidAnime().getName();

        Page<Anime> animePage = animeController.list(null).getBody();

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);


    }

    @Test
    @DisplayName("ListAll returns list of anime inside page object when successfull")
    void listAll_ReturnsListOfAllAnimesInsidePageObject_WhenSuccessfull(){
        String expectedName = AnimeCreator.createValidAnime().getName();

        List<Anime> animePage = animeController.listnopagleable().getBody();

        Assertions.assertThat(animePage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(animePage.get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("Find by Id returns a anime inside page object when successfull")
    void findById_ReturnsAAnimeInsidePageObject_WhenSuccessfull(){
        UUID expectedId = AnimeCreator.createValidAnime().getId();

        Anime animePage = animeController.findById(UUID.randomUUID()).getBody();

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.getId()).isEqualTo(expectedId);

    }

    @Test
    @DisplayName("Find by name returns a anime inside page object when successfull")
    void findByName_ReturnsAAnimeInsidePageObject_WhenSuccessfull(){
        String expectedName = AnimeCreator.createValidAnime().getName();

        List<Anime> animePage = animeController.findByName("anime").getBody();

        Assertions.assertThat(animePage).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(animePage.get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("Find by name empty returns a empty list when anime is not found")
    void findByName_ReturnsEmptyListOfAnime_WhenAnimeIsNotFound(){
        BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Anime> animePage = animeController.findByName("anime").getBody();

        Assertions.assertThat(animePage).isNotNull().isEmpty();

    }

    @Test
    @DisplayName("save returns a anime inside page object when successfull")
    void save_ReturnsAAnimeInsidePageObject_WhenSuccessfull(){
        UUID expectedId = AnimeCreator.createValidAnime().getId();

        Anime animePage = animeController.save(AnimePostRequestBodyCreator.createValidAnime()).getBody();

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.getId()).isEqualTo(expectedId);

    }

    @Test
    @DisplayName("replace a anime inside page object when successfull")
    void replace_ReplaceAAnimeInsidePageObject_WhenSuccessfull(){

        ResponseEntity<Anime> entity = animeController.replace(AnimePutRequestBodyCreator.createValidAnime());

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    @DisplayName("replace a anime inside page object when successfull")
    void delete_DeleteAAnimeInsidePageObject_WhenSuccessfull(){

        ResponseEntity<Void> delete = animeController.delete(AnimePutRequestBodyCreator.createValidAnime().getId());

        Assertions.assertThat(delete).isNotNull();
        Assertions.assertThat(delete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

}