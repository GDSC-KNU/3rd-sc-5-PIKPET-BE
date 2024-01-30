package com.gdsc.pikpet.repository;

import com.gdsc.pikpet.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ShelterRepository extends JpaRepository<Shelter, Integer> {
}
