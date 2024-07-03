package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.records.AnimePostRecords;
import academy.devdojo.springboot2.records.AnimePutRecords;
import academy.devdojo.springboot2.service.AnimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/anime")
@Log4j2
//@AllArgsConstructor utilizado para injetar dependencia em todos os atribuitos da classe, bem como criação dos metodos getters e setters
@RequiredArgsConstructor //utilizado qdo somente necessita de injeção de dependência nos atributos finais, bem como criação de getters e setters para os mesmos
public class AnimeController {

    private final AnimeService animeService;

    @GetMapping("/listpageable")
    public ResponseEntity<Page<Anime>> list(Pageable pageable){
        return ResponseEntity.ok(animeService.listAll(pageable)) ;
    }

    @GetMapping("/list/all")
    public ResponseEntity<List<Anime>> listnopagleable(){
        return ResponseEntity.ok(animeService.listAllNoPageAble()) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> findById(@PathVariable UUID id){
        return ResponseEntity.ok(animeService.findByIdOrThrowRequestException(id)) ;
    }

    @GetMapping("/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name){
        return ResponseEntity.ok(animeService.findByName(name)) ;
    }

    @PostMapping
    public ResponseEntity<Anime> save (@RequestBody @Valid AnimePostRecords anime){
        return new ResponseEntity<>(animeService.save(anime), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        animeService.delete(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity<Anime> replace (@RequestBody @Valid AnimePutRecords anime){
        animeService.replace(anime);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
