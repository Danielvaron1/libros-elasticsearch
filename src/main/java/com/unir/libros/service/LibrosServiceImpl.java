package com.unir.libros.service;

import com.unir.libros.data.LibrosRepository;
import com.unir.libros.model.pojo.Libro;
import com.unir.libros.model.pojo.LibroDto;
import com.unir.libros.model.request.CreateLibroRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class LibrosServiceImpl implements LibrosService{

    @Autowired
    private LibrosRepository repository;

    @Override
    public List<Libro> getLibros(String autor, String categoria, String idioma, String titulo, String formato, Integer anioPublicacion) {
        if(StringUtils.hasLength(autor)||StringUtils.hasLength(categoria)||StringUtils.hasLength(idioma)
        ||StringUtils.hasLength(titulo)||StringUtils.hasLength(formato)||anioPublicacion!=null){
            return repository.search(autor, categoria, titulo, formato, anioPublicacion);
        }

        List<Libro> libros = repository.getLibros();
        return libros.isEmpty() ? null : libros;
    }

    @Override
    public Libro getLibro(String libroId) {
        return repository.getById(Long.valueOf(libroId));
    }

    @Override
    public Libro createLibro(CreateLibroRequest request) {

        Libro libro = Libro.builder().titulo(request.getTitulo()).autor(request.getAutor()).formato(request.getFormato())
                    .anioPublicacion(request.getAnioPublicacion()).ISBN(request.getISBN()).idioma(request.getIdioma())
                    .categoria(request.getCategoria()).nPaginas(request.getNPaginas()).encuadernacion(request.getEncuadernacion())
                    .dimensiones(request.getDimensiones()).precio(request.getPrecio()).imgSrc(request.getImgSrc())
                    .sinopsis(request.getSinopsis()).puntoVistaAutor(request.getPuntoVistaAutor())
                    .opinionEditorial(request.getOpinionEditorial()).build();
        return repository.save(libro);
    }

    @Override
    public Libro updateLibro(String libroId, LibroDto body) {
        Libro libro = repository.getById(Long.valueOf(libroId));
        if (libro != null) {
            libro.update(body);
            repository.save(libro);
            return libro;
        } else {
            return null;
        }
    }

    @Override
    public Boolean removeProduct(String libroId) {
        Libro libro = repository.getById(Long.valueOf(libroId));

        if (libro != null){
            repository.delete(libro);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
