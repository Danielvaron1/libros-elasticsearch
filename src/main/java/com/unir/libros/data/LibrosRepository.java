package com.unir.libros.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.unir.libros.model.db.Libro;

public interface LibrosRepository extends ElasticsearchRepository<Libro, String>{

    List<Libro> findByTitulo(String titulo);

    List<Libro> findByAutor(String autor);

    Optional<Libro> findById(String id);

    Libro save(Libro libro);

    void delete(Libro libro);

    List<Libro> findAll();
}
