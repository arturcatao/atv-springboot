package biblioteca.biblioteca.Controller;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Service.LivroCrudService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/livros", produces = MediaType.APPLICATION_JSON_VALUE)
public class LivroV1Controller {

    @Autowired
    LivroCrudService livroCrudService;

    @PostMapping
    public ResponseEntity<?> criarLivro(@RequestBody @Valid LivroPostPutDto livroPostPutDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCrudService.criarLivro(livroPostPutDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarLivro(@PathVariable Long id, @RequestBody @Valid LivroPostPutDto livroPostPutDto) {
        return ResponseEntity.status(HttpStatus.OK).body(livroCrudService.atualizarLivro(id, livroPostPutDto));
    }

    @GetMapping
    public ResponseEntity<?> buscarTodosLivros() {
        return ResponseEntity.status(HttpStatus.OK).body(livroCrudService.buscarTodosLivros());
    }

    @GetMapping("/isbn/{ISBN}")
    public ResponseEntity<?> buscarLivro(@PathVariable String ISBN) {
        return ResponseEntity.status(HttpStatus.OK).body(livroCrudService.buscarLivro(ISBN));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<?> buscarLivros(@PathVariable String titulo) {
        return ResponseEntity.status(HttpStatus.OK).body(livroCrudService.buscarLivros(titulo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerLivro(@PathVariable Long id) {
        livroCrudService.removerLivro(id);
        return ResponseEntity.noContent().build();
    }
}
