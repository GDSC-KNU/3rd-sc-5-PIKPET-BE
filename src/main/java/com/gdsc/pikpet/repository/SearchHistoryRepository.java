package com.gdsc.pikpet.repository;

import com.gdsc.pikpet.entity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
}
