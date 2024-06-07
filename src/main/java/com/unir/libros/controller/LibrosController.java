package com.unir.libros.controller;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.unir.libros.model.pojo.Libro;
import com.unir.libros.model.pojo.LibroDto;
import com.unir.libros.model.request.CreateLibroRequest;
import com.unir.libros.service.LibrosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Libros Controller", description = "Microservicio encargado de exponer operaciones CRUD sobre libros alojados en una base de datos en memoria.")
public class LibrosController {

    private final LibrosService service;

    @GetMapping
    @Operation(
            operationId = "Obtener libros",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos los libros almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class)))
    public ResponseEntity<List<Libro>> getLibros(
            @Parameter(name = "autor", description = "Autor del libro.", example = "J.K. Rowling", required = false)
            @RequestParam(required= false) String autor,
            @Parameter(name = "categoria", description = "Categoria del libro.", example = "Literatura juvenil, Fantasía", required = false)
            @RequestParam(required= false) String categoria,
            @Parameter(name = "idioma", description = "Idioma del libro.", example = "Español", required = false)
            @RequestParam(required= false) String idioma,
            @Parameter(name = "titulo", description = "Titulo del libro.", example = "Harry Potter y la Piedra Filosofal", required = false)
            @RequestParam(required= false) String titulo,
            @Parameter(name = "formato", description = "Formato del libro.", example = "Libro impreso", required = false)
            @RequestParam(required= false) String formato,
            @Parameter(name = "anioPublicacion", description = "Año de publicacion del libro.", example = "1997", required = false)
            @RequestParam(required= false) Integer anioPublicacion
    ) {
        List<Libro> libros = service.getLibros(autor,categoria,idioma,titulo,formato,anioPublicacion);

        return ResponseEntity.ok(Objects.requireNonNullElse(libros, Collections.emptyList()));
    }

    @GetMapping("/{libroId}")
    @Operation(
            operationId = "Obtener un libro",
            description = "Operacion de lectura",
            summary = "Se devuelve un libro a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el libro con el identificador indicado.")
    public ResponseEntity<Libro> getLibro(@PathVariable String libroId){
        Libro libro = service.getLibro(libroId);

        if (libro != null) {
            return ResponseEntity.ok(libro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
            operationId = "Insertar un libro",
            description = "Operacion de escritura",
            summary = "Se crea un libro a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del libro a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateLibroRequest.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    public ResponseEntity<Libro> addProduct(@RequestBody @Valid CreateLibroRequest request) {

        Libro createdLibro = service.createLibro(request);

        if (createdLibro != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLibro);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{libroId}")
    @Operation(
            operationId = "Modificar totalmente un libro",
            description = "Operacion de escritura",
            summary = "Se modifica totalmente un libro.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del libro a actualizar.",
                    required = true,
                    content = @Content(mediaType = "application/merge-put+json", schema = @Schema(implementation = LibroDto.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Libro.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Libro no encontrado.")
    public ResponseEntity<Libro> updateProduct(@PathVariable String libroId, @RequestBody LibroDto body) {

        Libro updated = service.updateLibro(libroId, body);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{libroId}")
    @Operation(
            operationId = "Eliminar un libro",
            description = "Operacion de escritura",
            summary = "Se elimina un libro a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Se ha eliminado correctamente el libro con el identificador indicado.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el libro con el identificador indicado.")
    public ResponseEntity<Void> deleteLibro(@PathVariable String libroId) {

        Boolean removed = service.removeProduct(libroId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
