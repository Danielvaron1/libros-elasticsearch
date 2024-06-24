package com.unir.libros.service;

import com.unir.libros.model.db.Libro;
import com.unir.libros.model.request.CreateLibroRequest;

import java.util.List;

public interface LibrosService {

    List<Libro> getLibros(String categoria, String idioma, String autor, String formato, String texto);

    Libro getLibro(String libroId);

    Libro createLibro(CreateLibroRequest request);

    Libro updateLibro(String libroId, CreateLibroRequest body);

    Boolean removeLibro(String libroId);

}
