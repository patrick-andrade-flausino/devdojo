package academy.devdojo.springboot2.util;

import academy.devdojo.springboot2.records.AnimePutRecords;

public class AnimePutRequestBodyCreator {
    public static AnimePutRecords createValidAnime(){
        return AnimePutRecords.builder()
                .name(AnimeCreator.createValidUpdatedAnime().getName())
                .id(AnimeCreator.createValidUpdatedAnime().getId())
                .build();
    }
}
