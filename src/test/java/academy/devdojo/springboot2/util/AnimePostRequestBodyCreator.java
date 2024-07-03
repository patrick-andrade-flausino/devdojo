package academy.devdojo.springboot2.util;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.records.AnimePostRecords;

import java.util.UUID;

public class AnimePostRequestBodyCreator {
    public static AnimePostRecords createValidAnime(){
        return AnimePostRecords.builder().name(AnimeCreator.createAnimeToBeSaved().getName()).build();
    }
}
