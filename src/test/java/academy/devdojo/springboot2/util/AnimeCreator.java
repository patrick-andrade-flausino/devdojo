package academy.devdojo.springboot2.util;

import academy.devdojo.springboot2.domain.Anime;

import java.util.UUID;

public class AnimeCreator {
    public static Anime createAnimeToBeSaved(){
        return Anime.builder().name("Naruto Brabo").build();
    }

    public static Anime createValidAnime(){
        return Anime.builder().name("Naruto Brabo").id(UUID.fromString("25764169-fe04-49d4-aa8f-5c63345787c9")).build();
    }

    public static Anime createValidUpdatedAnime(){
        return Anime.builder().name("Naruto Brabo demais").id(UUID.fromString("25764169-fe04-49d4-aa8f-5c63345787c9")).build();

    }
}
