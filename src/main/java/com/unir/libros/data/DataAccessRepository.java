package com.unir.libros.data;

import java.util.*;

import com.unir.libros.model.db.Libro;
import lombok.SneakyThrows;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class DataAccessRepository {

    // Esta clase (y bean) es la unica que usan directamente los servicios para
    // acceder a los datos.
    private final LibrosRepository libroRepository;
    private final ElasticsearchOperations elasticClient;

    private final String[] autorSearchFields = {"autor", "autor._2gram", "autor._3gram"};
    private final String[] tituloSearchFields = {"titulo", "titulo._2gram", "titulo._3gram"};

    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    public Boolean delete(Libro libro) {
        libroRepository.delete(libro);
        return Boolean.TRUE;
    }

    public Optional<Libro> findById(String id) {
        return libroRepository.findById(id);
    }

    @SneakyThrows
    public List<Libro> findLibros(String categoria,String idioma,String autor,String formato,String texto) {
        if(texto==null || texto.isEmpty()){
            return searchQuery(categoria,idioma,autor,formato, null, null);
        } else{
            List<Libro> tituloFilter = searchQuery(categoria,idioma,autor,formato, texto, tituloSearchFields);
            System.out.println(tituloFilter);
            List<Libro> autorFilter = searchQuery(categoria,idioma,autor,formato, texto, autorSearchFields);
            System.out.println(autorFilter);
            Set<Libro> setCombined = new HashSet<>(tituloFilter);
            setCombined.addAll(autorFilter);
            List<Libro> listCombined = new ArrayList<>(setCombined);
            return listCombined;
        }
    }

    private List<Libro> searchQuery(String categoria,String idioma,String autor,String formato,String texto, String[] textoFields){
        BoolQueryBuilder querySpec = QueryBuilders.boolQuery();

        // Si el usuario ha seleccionado algun valor relacionado con la categoria, lo añadimos a la query
        if (categoria != null && !categoria.isEmpty()) {
            querySpec.must(QueryBuilders.termQuery("categoria", categoria));
        }

        // Si el usuario ha seleccionado algun valor relacionado con el idioma, lo añadimos a la query
        if (idioma != null && !idioma.isEmpty()) {
            querySpec.must(QueryBuilders.termQuery("idioma", idioma));
        }

        // Si el usuario ha seleccionado algun valor relacionado con el formato, lo añadimos a la query
        if (formato != null && !formato.isEmpty()) {
            querySpec.must(QueryBuilders.termQuery("formato", formato));
        }

        // Si el usuario ha seleccionado algun valor relacionado con el autor, lo añadimos a la query
        if (autor != null && !autor.isEmpty()) {
            querySpec.must(QueryBuilders.multiMatchQuery(autor, autorSearchFields).type(MultiMatchQueryBuilder.Type.BOOL_PREFIX));
        }

        // Si el usuario ha seleccionado algun valor relacionado con el texto, lo añadimos a la query
        if (texto != null && !texto.isEmpty()) {
            querySpec.must(QueryBuilders.multiMatchQuery(texto, textoFields).type(MultiMatchQueryBuilder.Type.BOOL_PREFIX));
        }

        //Si no se ha seleccionado ningun filtro, se añade un filtro por defecto para que la query no sea vacia
        if(!querySpec.hasClauses() ) {
            querySpec.must(QueryBuilders.matchAllQuery());
        }

        //Construimos la query
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(querySpec);

        //Se establece un maximo de 500 resultados, va acorde con el tamaño de la pagina
        nativeSearchQueryBuilder.withMaxResults(500);

        //Se construye la query
        Query query = nativeSearchQueryBuilder.build();
        // Se realiza la busqueda
        SearchHits<Libro> result = elasticClient.search(query, Libro.class);
        return getResponseLibros(result);
    }

    /**
     * Metodo que convierte los resultados de la busqueda en una lista de empleados.
     * @param result Resultados de la busqueda.
     * @return Lista de empleados.
     */
    private List<Libro> getResponseLibros(SearchHits<Libro> result) {
        return result.getSearchHits().stream().map(SearchHit::getContent).toList();
    }
}
