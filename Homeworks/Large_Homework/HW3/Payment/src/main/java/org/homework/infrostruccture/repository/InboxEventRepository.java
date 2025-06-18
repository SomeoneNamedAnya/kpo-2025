package org.homework.infrostruccture.repository;

import org.homework.domain.InboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface InboxEventRepository extends JpaRepository<InboxEvent, Integer> {
    List<InboxEvent> findAllBySentFalseOrderByCreatedAtAsc();
}