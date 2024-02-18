package com.gdsc.pikpet.repository;

import com.gdsc.pikpet.entity.Animal;
import com.gdsc.pikpet.entity.Application;
import com.gdsc.pikpet.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByUserAccountAndAnimal(UserAccount userAccount, Animal animal);

    List<Application> findAllByUserAccount(UserAccount userAccount);
}
