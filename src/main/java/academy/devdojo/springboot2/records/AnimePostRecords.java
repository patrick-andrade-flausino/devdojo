package academy.devdojo.springboot2.records;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class AnimePostRecords {

    @NotNull(message = "The name of this anime cannot be null")
    @NotEmpty(message = "The name of this anime cannot be empty")
    private String name;

}
