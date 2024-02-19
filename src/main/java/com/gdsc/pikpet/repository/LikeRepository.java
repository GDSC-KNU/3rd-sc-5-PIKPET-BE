package com.gdsc.pikpet.repository;

import com.gdsc.pikpet.entity.UserAccount;
import com.gdsc.pikpet.entity.UserLike;
import com.gdsc.pikpet.entity.animal.Animal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface LikeRepository extends JpaRepository<UserLike, Long> {
    List<UserLike> findAllByUserAccount(UserAccount userAccount);

    Optional<UserLike> findByUserAccountAndAnimal(UserAccount userAccount, Animal animal);
}
