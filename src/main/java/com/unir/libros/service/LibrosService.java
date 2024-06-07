package com.unir.libros.service;

import com.unir.libros.model.pojo.Libro;
import com.unir.libros.model.pojo.LibroDto;
import com.unir.libros.model.request.CreateLibroRequest;

import java.util.List;

public interface LibrosService {

    List<Libro> getLibros(String autor, String categoria, String idioma, String titulo, String formato, Integer anioPublicacion);

    Libro getLibro(String libroId);

    Libro createLibro(CreateLibroRequest request);

    Libro updateLibro(String libroId, LibroDto body);

    Boolean removeProduct(String libroId);

}
