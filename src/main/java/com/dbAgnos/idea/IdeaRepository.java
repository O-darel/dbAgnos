package com.dbAgnos.idea;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IdeaRepository extends JpaRepository<ProjectIdea, Long> {
    List<ProjectIdea> findByUserId(Long userId);
}
