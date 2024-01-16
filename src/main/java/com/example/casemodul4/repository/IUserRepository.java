package com.example.casemodul4.repository;

import com.example.casemodul4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
