package com.unir.libros.data;

import com.unir.libros.data.utils.SearchCriteria;
import com.unir.libros.data.utils.SearchOperation;
import com.unir.libros.data.utils.SearchStatement;
import com.unir.libros.model.pojo.Libro;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LibrosRepository {
    private final LibrosJpaRepository repository;

    public List<Libro> getLibros() {
        return repository.findAll();
    }

    public Libro getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Libro save(Libro libro) {
        return repository.save(libro);
    }

    public void delete(Libro libro) {
        repository.delete(libro);
    }

    public List<Libro> search(String autor, String categoria, String titulo, String formato, Integer anioPublicacion) {
        SearchCriteria<Libro> spec = new SearchCriteria<>();
        if (StringUtils.isNotBlank(autor)) {
            spec.add(new SearchStatement("autor", autor, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(categoria)) {
            spec.add(new SearchStatement("categoria", categoria, SearchOperation.EQUAL));
        }

        if (StringUtils.isNotBlank(titulo)) {
            spec.add(new SearchStatement("titulo", titulo, SearchOperation.MATCH));
        }

        if (StringUtils.isNotBlank(formato)) {
            spec.add(new SearchStatement("formato", formato, SearchOperation.MATCH));
        }

        if (anioPublicacion!=null) {
            spec.add(new SearchStatement("anioPublicacion", anioPublicacion, SearchOperation.EQUAL));
        }
        return repository.findAll(spec);
    }
}
