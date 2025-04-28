package com.example.faq_backend_api.api.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.faq_backend_api.api.model.FAQ;



public interface FAQRepository extends JpaRepository<FAQ, Long> {

    @Query(value = """
        SELECT DISTINCT f.*
        FROM faq f
        LEFT JOIN faq_tags ft ON f.id = ft.faq_id
        LEFT JOIN tag t ON ft.tag_id = t.id
        WHERE 
            (f.search_vector @@ plainto_tsquery('german', :query))
            OR
            (LOWER(t.name) LIKE LOWER(CONCAT('%', :query, '%')))
        """, nativeQuery = true)
    List<FAQ> fullTextSearchIncludingTags(@Param("query") String query);
}