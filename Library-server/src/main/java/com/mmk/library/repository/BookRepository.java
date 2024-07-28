package com.mmk.library.repository;

import com.mmk.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("SELECT b FROM Book b WHERE lower(b.title) LIKE lower(concat('%',:keywords,'%')) OR lower(b.authorName) LIKE lower(concat('%',:keywords,'%'))")
    List<Book> findBySearchKeyword(@Param("keywords") String keywords);

}
