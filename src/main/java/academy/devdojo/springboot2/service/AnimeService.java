package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.exception.BadRequestException;
import academy.devdojo.springboot2.records.AnimePostRecords;
import academy.devdojo.springboot2.records.AnimePutRecords;
import academy.devdojo.springboot2.repository.AnimeRepository;
import academy.devdojo.springboot2.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AnimeService {
    private final DateUtil dateUtil;

    private final AnimeRepository animeList;
    public Page<Anime> listAll(Pageable pagleable){
        return animeList.findAll(pagleable);
    }

    public List<Anime> listAllNoPageAble(){
        return animeList.findAll();
    }
    public List<Anime> findByName(String name){
        return animeList.findByName(name);
    }

    public Anime findByIdOrThrowRequestException(UUID id){
        return animeList.findById(id).orElseThrow(() -> new BadRequestException("ID do anime não encontrado!"));
    }

//    @Transactional(rollbackOn = Exception.class )
    @Transactional
    public Anime save(AnimePostRecords anime){
        var animesave = Anime.builder().build();
        BeanUtils.copyProperties(anime, animesave);
        animeList.save(animesave);
        return animesave;
    }

    @Transactional
    public void delete(UUID id){
        animeList.deleteById(id);
    }

    @Transactional
    public void replace(AnimePutRecords anime) {
        var animePut = Anime.builder().build();
        animePut =  findByIdOrThrowRequestException(anime.getId());
        animePut.setName(anime.getName());
        animeList.save(animePut);
    }
}
