package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.AutorResponseDto;
import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.LivroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroCrudPadraoService implements LivroCrudService{

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LivroRepository livroRepository;

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto) {
        Livro livro = modelMapper.map(livroPostPutDto, Livro.class);

        livroRepository.save(livro);
        return modelMapper.map(livro, LivroResponseDto.class);
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
        return modelMapper.map(livroRepository.getByISBN((ISBN)), LivroResponseDto.class);
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
        Livro livro = livroRepository.getById(id);
        livro.setTitulo(livroPostPutDto.getTitulo());
        livro.setAno(livroPostPutDto.getAno());
        livro.setAutor(livroPostPutDto.getAutor());
        livro.setEdicao(livroPostPutDto.getEdicao());
        livro.setISBN(livroPostPutDto.getISBN());
    }

    @Override
    public void removerLivro(Long id) {
        livroRepository.deleteById(id);
    }
}
