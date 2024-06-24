package com.unir.libros.service;

import com.unir.libros.data.DataAccessRepository;
import com.unir.libros.model.db.Libro;
import com.unir.libros.model.request.CreateLibroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibrosServiceImpl implements LibrosService{

    private final DataAccessRepository repository;

    @Override
    public List<Libro> getLibros(String categoria, String idioma, String autor, String formato, String texto) {
        return repository.findLibros(categoria, idioma, autor, formato, texto);
    }

    @Override
    public Libro getLibro(String libroId) {
        return repository.findById(libroId).orElse(null);
    }

    @Override
    public Libro createLibro(CreateLibroRequest request) {
        Libro libro = newLibro(request);
        if(libro!=null){
            return repository.save(libro);
        } else{
            return null;
        }
    }

    @Override
    public Libro updateLibro(String libroId, CreateLibroRequest request) {
        Libro libro = newLibro(request);
        if(libro!=null){
            libro.setId(libroId);
            return repository.save(libro);
        } else{
            return null;
        }
    }

    private Libro newLibro(CreateLibroRequest request){
        if (request != null && StringUtils.hasLength(request.getTitulo().trim())
                && StringUtils.hasLength(request.getAutor().trim())
                && StringUtils.hasLength(request.getCategoria().trim())) {
            Libro libro = Libro.builder().titulo(request.getTitulo()).autor(request.getAutor()).formato(request.getFormato())
                    .anioPublicacion(request.getAnioPublicacion()).ISBN(request.getISBN()).idioma(request.getIdioma())
                    .categoria(request.getCategoria()).nPaginas(request.getNPaginas()).encuadernacion(request.getEncuadernacion())
                    .dimensiones(request.getDimensiones()).precio(request.getPrecio()).imgSrc(request.getImgSrc())
                    .sinopsis(request.getSinopsis()).puntoVistaAutor(request.getPuntoVistaAutor())
                    .opinionEditorial(request.getOpinionEditorial()).build();
            return libro;
        } else{
            return null;
        }
    }

    @Override
    public Boolean removeLibro(String libroId) {
        Libro libro = repository.findById(libroId).orElse(null);

        if (libro != null){
            repository.delete(libro);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
