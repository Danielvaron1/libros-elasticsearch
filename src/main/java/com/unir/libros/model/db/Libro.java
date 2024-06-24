package com.unir.libros.model.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(indexName = "libros", createIndex = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Libro {
    @Id
    private String id;

    @Field(type = FieldType.Search_As_You_Type,name = "titulo")
    private String titulo;

    @Field(type = FieldType.Search_As_You_Type,name = "autor")
    private String autor;

    @Field(type = FieldType.Keyword,name = "formato")
    private String formato;

    @Field(type = FieldType.Integer,name = "anioPublicacion")
    private Integer anioPublicacion;

    @Field(type = FieldType.Long,name = "ISBN")
    private Long ISBN;

    @Field(type = FieldType.Keyword,name = "idioma")
    private String idioma;

    @Field(type = FieldType.Keyword,name = "categoria")
    private String categoria;

    @Field(type = FieldType.Integer,name = "nPaginas")
    private Integer nPaginas;

    @Field(type = FieldType.Keyword,name = "encuadernacion")
    private String encuadernacion;

    @Field(type = FieldType.Text,name = "dimensiones")
    private String dimensiones;

    @Field(type = FieldType.Text,name = "precio")
    private String precio;

    @Field(type = FieldType.Text,name = "imgSrc")
    private String imgSrc;

    @Field(type = FieldType.Text,name = "sinopsis")
    private String sinopsis;

    @Field(type = FieldType.Text,name = "puntoVistaAutor")
    private String puntoVistaAutor;

    @Field(type = FieldType.Text,name = "opinionEditorial")
    private String opinionEditorial;
}
