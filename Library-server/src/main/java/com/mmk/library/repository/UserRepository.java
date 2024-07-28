package com.mmk.library.repository;


import com.mmk.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);


    @Query("SELECT u FROM User u WHERE lower(u.firstName) LIKE lower(concat('%',:keywords,'%')) OR lower(u.lastName) LIKE lower(concat('%',:keywords,'%')) OR lower(u.email) LIKE lower(concat('%',:keywords,'%'))")
    List<User> findBySearchKeyword(@Param("keywords") String keywords);
}
