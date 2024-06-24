package com.unir.libros.controller;


import com.unir.libros.model.db.Libro;
import com.unir.libros.model.request.CreateLibroRequest;
import com.unir.libros.service.LibrosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LibrosController {

    private final LibrosService service;

    @GetMapping
    public ResponseEntity<List<Libro>> getLibros(
            @RequestParam(required= false) String categoria,
            @RequestParam(required= false) String idioma,
            @RequestParam(required= false) String autor,
            @RequestParam(required= false) String formato,
            @RequestParam(required= false) String texto
    ) {
        List<Libro> libros = service.getLibros(categoria, idioma, autor, formato, texto);
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{libroId}")
    public ResponseEntity<Libro> getLibro(@PathVariable String libroId){
        Libro libro = service.getLibro(libroId);
        if (libro != null) {
            return ResponseEntity.ok(libro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Libro> addProduct(@RequestBody CreateLibroRequest request) {

        Libro createdLibro = service.createLibro(request);

        if (createdLibro != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLibro);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{libroId}")
    public ResponseEntity<Libro> updateProduct(@PathVariable String libroId, @RequestBody CreateLibroRequest body) {

        Libro updated = service.updateLibro(libroId, body);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{libroId}")
    public ResponseEntity<Void> deleteLibro(@PathVariable String libroId) {

        Boolean removed = service.removeLibro(libroId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
