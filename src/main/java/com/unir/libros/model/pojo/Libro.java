package com.unir.libros.model.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "libros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "autor")
    private String autor;

    @Column(name = "formato")
    private String formato;

    @Column(name = "anioPublicacion")
    private Integer anioPublicacion;

    @Column(name = "ISBN")
    private Long ISBN;

    @Column(name = "idioma")
    private String idioma;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "nPaginas")
    private Integer nPaginas;

    @Column(name = "encuadernacion")
    private String encuadernacion;

    @Column(name = "dimensiones")
    private String dimensiones;

    @Column(name = "precio")
    private String precio;

    @Column(name = "imgSrc")
    private String imgSrc;

    @Column(name = "sinopsis")
    private String sinopsis;

    @Column(name = "puntoVistaAutor")
    private String puntoVistaAutor;

    @Column(name = "opinionEditorial")
    private String opinionEditorial;

    public void update(LibroDto productDto) {
        this.titulo = productDto.getTitulo();
        this.autor = productDto.getAutor();
        this.formato = productDto.getFormato();
        this.anioPublicacion = productDto.getAnioPublicacion();
        this.ISBN = productDto.getISBN();
        this.idioma = productDto.getIdioma();
        this.categoria = productDto.getCategoria();
        this.nPaginas = productDto.getNPaginas();
        this.encuadernacion = productDto.getEncuadernacion();
        this.dimensiones = productDto.getDimensiones();
        this.precio = productDto.getPrecio();
        this.imgSrc = productDto.getImgSrc();
        this.sinopsis = productDto.getSinopsis();
        this.puntoVistaAutor = productDto.getPuntoVistaAutor();
        this.opinionEditorial = productDto.getOpinionEditorial();
    }
}
