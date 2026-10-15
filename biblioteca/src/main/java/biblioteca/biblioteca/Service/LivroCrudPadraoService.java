package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.AutorResponseDto;
import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Autor;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.AutorRepository;
import biblioteca.biblioteca.Repository.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivroCrudPadraoService implements LivroCrudService{

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto dto) {
        Livro livro = new Livro();

        livro.setTitulo(dto.getTitulo());
        livro.setISBN(dto.getISBN());
        livro.setAno(dto.getAno());
        livro.setEdicao(dto.getEdicao());

        Autor autor = obterAutorExistente(dto.getAutorId());
        livro.setAutor(autor);

        Livro livroSalvo = livroRepository.save(livro);


        return  modelMapper.map(livroSalvo, LivroResponseDto.class);
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivro(String ISBN) {
        return modelMapper.map(obterLivroExistente(ISBN), LivroResponseDto.class);
    }

    @Override
    public List<LivroResponseDto> buscarLivros(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    @Override
    public LivroResponseDto atualizarLivro(Long id, LivroPostPutDto livroPostPutDto) {
        Livro livro = obterLivroExistente(id);
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setAno(livroPostPutDto.getAno());
        livro.setAutor(obterAutorExistente(livroPostPutDto.getAutorId()));
        livro.setEdicao(livroPostPutDto.getEdicao());
        livro.setISBN(livroPostPutDto.getISBN());

        livroRepository.save(livro);
        return modelMapper.map(livro, LivroResponseDto.class);
    }

    @Override
    public void removerLivro(Long id) {
        obterLivroExistente(id);
        livroRepository.deleteById(id);
    }

    private Livro obterLivroExistente(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    private Livro obterLivroExistente(String ISBN) {
        return livroRepository.getByISBN(ISBN)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    private Autor obterAutorExistente(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));
    }
}
