package com.ravilyahya.paydaytrade.repository;

import com.ravilyahya.paydaytrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String userName);

    @Transactional
    @Modifying
    @Query("UPDATE User user " +
            "SET user.isEnabled = TRUE WHERE user.username = ?1")
    int enableAppUser(String username);
}
