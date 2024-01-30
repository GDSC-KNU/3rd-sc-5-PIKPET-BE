package com.gdsc.pikpet.repository;

import com.gdsc.pikpet.entity.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DescriptionRepository extends JpaRepository<Description, Long> {
}
