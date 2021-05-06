package com.bsahu.chotaurl.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bsahu.chotaurl.model.UrlEntity;

/**
 * @author bsahu
 *
 */
@Repository
public interface UrlConverterRepository extends JpaRepository<UrlEntity, Long> {

    @Query("SELECT u FROM url u WHERE u.longUrl = ?1")
    List<UrlEntity> findUrlByLongUrl(String fullUrl);
}