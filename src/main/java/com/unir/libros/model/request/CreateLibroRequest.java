package com.unir.libros.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLibroRequest {

    private String titulo;
    private String autor;
    private String formato;
    private Integer anioPublicacion;
    private Long ISBN;
    private String idioma;
    private String categoria;
    private Integer nPaginas;
    private String encuadernacion;
    private String dimensiones;
    private String precio;
    private String imgSrc;
    private String sinopsis;
    private String puntoVistaAutor;
    private String opinionEditorial;

}
