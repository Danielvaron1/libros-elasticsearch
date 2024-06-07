package com.unir.libros.data;

import com.unir.libros.model.pojo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LibrosJpaRepository extends JpaRepository<Libro, Long>, JpaSpecificationExecutor<Libro> {
    List<Libro> findByAutor(String autor);

    List<Libro> findByCategoria(String categoria);

    List<Libro> findByIdioma(String idioma);

    List<Libro> findByTitulo(String titulo);

    List<Libro> findByFormato(String formato);

    List<Libro> findByAnioPublicacion(Integer anioPublicacion);

    List<Libro> findByCategoriaAndIdioma(String categoria, String idioma);

    List<Libro> findByCategoriaAndAutor(String categoria, String autor);

    List<Libro> findByCategoriaAndFormato(String categoria, String formato);

    List<Libro> findByIdiomaAndAutor(String idioma, String autor);

    List<Libro> findByIdiomaAndFormato(String idioma, String formato);

    List<Libro> findByAutorAndFormato(String autor, String formato);
}
