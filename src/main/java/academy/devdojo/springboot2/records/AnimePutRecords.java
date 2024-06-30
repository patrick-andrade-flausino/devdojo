package academy.devdojo.springboot2.records;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class AnimePutRecords {
    @NotNull(message = "The id of this anime cannot be null")
    private long id;

    @NotNull(message = "The name of this anime cannot be null")
    @NotEmpty(message = "The name of this anime cannot be empty")
    private String name;

    @CPF(message = "CPF inválido")
    private String cpf;
}
