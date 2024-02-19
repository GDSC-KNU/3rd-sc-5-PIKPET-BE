package com.gdsc.pikpet.repository;

import com.gdsc.pikpet.entity.animal.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("select animal from Animal animal order by (ST_Distance_Sphere(ST_MakePoint(animal.shelter.latitude,animal.shelter.location), ST_MakePoint(?1, ?2)))")
    Page<Animal> findByLocation(double lat, double lon, Pageable pageable);
}
