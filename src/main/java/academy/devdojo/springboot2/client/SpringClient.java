package academy.devdojo.springboot2.client;

import academy.devdojo.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Anime> entity =  new RestTemplate().getForEntity("http://localhost:8080/anime/2", Anime.class);
        log.info(entity);

        Anime Object = new RestTemplate().getForObject("http://localhost:8080/anime/2", Anime.class,2,5,7) ;
        log.info(Object);

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/anime/list/all", Anime[].class) ;
        log.info(Arrays.toString(animes));


        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/anime/list/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<Anime>>() {
        });
        log.info(exchange.getBody());

        Anime kingdom = new Anime();
        kingdom.setName("kingdom");
//        Anime anime = new RestTemplate().postForObject("http://localhost:8080/anime", kingdom, Anime.class);
//        log.info(anime);

        ResponseEntity<Anime> postAnime = new RestTemplate().exchange("http://localhost:8080/anime", HttpMethod.POST, new HttpEntity<>(kingdom, createJsonHeader()), Anime.class);
        log.info(postAnime);

        Anime animePut = postAnime.getBody();
        animePut.setName("kingdom 2");
        ResponseEntity<Void> putVariable = new RestTemplate().exchange("http://localhost:8080/anime", HttpMethod.PUT, new HttpEntity<>(animePut, createJsonHeader()), Void.class);
        log.info(putVariable);

        ResponseEntity<Void> deleteVariable = new RestTemplate().exchange("http://localhost:8080/anime/{id}", HttpMethod.DELETE, null, Void.class,animePut.getId());
        log.info(deleteVariable);

    }
    private static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
