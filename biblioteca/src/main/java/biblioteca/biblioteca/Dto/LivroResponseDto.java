package biblioteca.biblioteca.Dto;

import biblioteca.biblioteca.Model.Autor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("nacionalidade")
    private String ISBN;

    @JsonProperty("ano")
    private String ano;

    @JsonProperty("edicao")
    private String edicao;

    @JsonProperty("autor")
    private AutorResponseDto autorResponseDto;
}