package com.csfive.hanium.iseeyou.domain.parent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    Optional<Parent> findByEmailAndPassword(String email, String password);

    Parent findByNameAndEmail(String name, String email);

    Parent findByEmail(String email);
}
