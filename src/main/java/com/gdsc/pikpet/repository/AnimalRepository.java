package com.gdsc.pikpet.repository;

import com.gdsc.pikpet.entity.Gender;
import com.gdsc.pikpet.entity.animal.Animal;
import com.gdsc.pikpet.entity.animal.AnimalSize;
import com.gdsc.pikpet.entity.animal.Color;
import com.gdsc.pikpet.entity.animal.Species;

import com.gdsc.pikpet.entity.animal.Breed;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query("SELECT a FROM Animal a WHERE " +
            "(:species is null or a.species in :species) " +
            "and (:breeds is null or a.breed in :breeds) " +
            "and (:minAge is null or a.age >= :minAge) " +
            "and (:maxAge is null or a.age <= :maxAge) " +
            "and (:gender is null or a.gender in :gender) " +
            "and (:animalSize is null or a.size in :animalSize) " +
            "and (:neutralized is null or a.isNeutralized = :neutralized)" +
            "and a.id IN ( SELECT DISTINCT a.id " +
                "FROM Animal a JOIN a.animalColors ac " +
                "WHERE (:colors is null or ac.color IN :colors))")
    Page<Animal> findAnimalsWithFilter(
            @Param("species") List<Species> species,
            @Param("breeds") List<Breed> breeds,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("gender") List<Gender> gender,
            @Param("animalSize") List<AnimalSize> animalSize,
            @Param("neutralized") Boolean neutralized,
            @Param("colors") List<Color> colors,
            Pageable pageable
    );
}
