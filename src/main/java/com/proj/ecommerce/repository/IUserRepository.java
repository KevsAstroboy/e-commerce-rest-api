package com.proj.ecommerce.repository;

import com.proj.ecommerce.model.Role;
import com.proj.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

       @Modifying
       @Query("update User set role= :role where username= :username")
       void makeAdmin(@Param("username") String username , @Param("role")Role role);

       Optional<User> findByUsername(String username);

       @Query("delete User where username = :username")
       void deleteByUsername(@Param("username") String username);
}
