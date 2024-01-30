package com.gdsc.pikpet.repository;

import com.gdsc.pikpet.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
