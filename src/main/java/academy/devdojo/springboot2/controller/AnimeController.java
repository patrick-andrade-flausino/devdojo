package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/anime")
@Log4j2
//@AllArgsConstructor utilizado para injetar dependencia em todos os atribuitos da classe, bem como criação dos metodos getters e setters
@RequiredArgsConstructor //utilizado qdo somente necessita de injeção de dependência nos atributos finais, bem como criação de getters e setters para os mesmos
public class AnimeController {

    private final DateUtil dateUtil;


    @GetMapping("/list")
    public List<Anime> list(){
        log.info(dateUtil.formatLocalDateTimeDataBaseStyle(LocalDateTime.now()));
        return List.of(new Anime("DBZ"), new Anime("Berserk"));
    }

}
