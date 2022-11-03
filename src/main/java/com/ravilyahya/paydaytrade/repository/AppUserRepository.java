package com.ravilyahya.paydaytrade.repository;

import com.ravilyahya.paydaytrade.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    @Override
    Optional<AppUser> findById(Long aLong);

    Optional<AppUser> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser user " +
            "SET user.isEnabled = TRUE WHERE user.username = ?1")
    int enableAppUser(String username);
}
