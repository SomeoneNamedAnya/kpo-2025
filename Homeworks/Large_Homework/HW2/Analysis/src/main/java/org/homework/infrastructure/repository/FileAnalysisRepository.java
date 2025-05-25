package org.homework.infrastructure.repository;

import org.homework.domain.FileAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileAnalysisRepository extends JpaRepository<FileAnalysis, Integer> {
    Optional<FileAnalysis> findByIdFile(int id);
}
