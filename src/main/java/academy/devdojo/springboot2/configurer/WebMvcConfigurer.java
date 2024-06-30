package academy.devdojo.springboot2.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.List;

@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

    //é possível sobrescrever esses parâmetros passando size(quantidade de elementos por pagina) e page(numero da pagina) na chamada do método paginado
    //trazendo dados paginados: basta passar o parametro sort na URL no seguinte formato: sort=name_desc ou sort=name_asc onde name é o nome do campo que vc deseja realizar a ordenação
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){
        PageableHandlerMethodArgumentResolver pageHandler = new PageableHandlerMethodArgumentResolver();
        pageHandler.setFallbackPageable(PageRequest.of(0,10));
        resolvers.add(pageHandler);
    }

}
