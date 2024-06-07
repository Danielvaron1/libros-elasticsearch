package com.unir.libros.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLibroRequest {

    @NotEmpty
    @NotNull
    private String titulo;

    @NotEmpty
    @NotNull
    private String autor;

    @NotEmpty
    @NotNull
    private String formato;

    @PositiveOrZero
    @NotNull
    private Integer anioPublicacion;

    @PositiveOrZero
    @NotNull
    private Long ISBN;

    @NotEmpty
    @NotNull
    private String idioma;

    @NotEmpty
    @NotNull
    private String categoria;

    @PositiveOrZero
    @NotNull
    private Integer nPaginas;

    @NotEmpty
    @NotNull
    private String encuadernacion;

    @NotEmpty
    @NotNull
    private String dimensiones;

    @NotEmpty
    @NotNull
    private String precio;

    @NotEmpty
    @NotNull
    private String imgSrc;

    @NotEmpty
    @NotNull
    private String sinopsis;

    @NotEmpty
    @NotNull
    private String puntoVistaAutor;

    @NotEmpty
    @NotNull
    private String opinionEditorial;

}
