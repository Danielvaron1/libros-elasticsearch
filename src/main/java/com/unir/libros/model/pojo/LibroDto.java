package com.unir.libros.model.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LibroDto {

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
