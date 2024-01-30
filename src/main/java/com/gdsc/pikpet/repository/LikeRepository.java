package com.gdsc.pikpet.repository;

import com.gdsc.pikpet.entity.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LikeRepository extends JpaRepository<UserLike, Long> {
}
