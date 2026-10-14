package biblioteca.biblioteca.Dto;

import biblioteca.biblioteca.Model.Autor;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroPostPutDto {
    @JsonProperty("titulo")
    @NotBlank(message = "Título do livro não pode ser vazio")
    private String titulo;

    @JsonProperty("ISBN")
    @NotBlank(message = "ISBN do livro não pode ser vazia")
    private String ISBN;

    @JsonProperty("ano")
    @NotBlank(message = "Ano do livro não pode ser vazio")
    private String ano;

    @JsonProperty("edicao")
    @NotBlank(message = "Edição do livro não pode ser vazio")
    private String edicao;

    @JsonProperty("autor")
    @NotNull(message = "Autor do livro não pode ser vazio")
    private Autor autor;
}