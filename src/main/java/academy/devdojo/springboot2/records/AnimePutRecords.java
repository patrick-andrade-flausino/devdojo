package academy.devdojo.springboot2.records;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

@Data
@Builder
public class AnimePutRecords {
    @NotNull(message = "The id of this anime cannot be null")
    private UUID id;

    @NotNull(message = "The name of this anime cannot be null")
    @NotEmpty(message = "The name of this anime cannot be empty")
    private String name;

    @CPF(message = "CPF inválido")
    private String cpf;
}
