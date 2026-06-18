package com.projetoLoginIzabele.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoLoginIzabele.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	Login findByUsername(String username);

}
