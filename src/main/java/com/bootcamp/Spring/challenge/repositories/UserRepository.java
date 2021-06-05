package com.bootcamp.Spring.challenge.repositories;


import com.bootcamp.Spring.challenge.dto.FollowedInfoDTO;
import com.bootcamp.Spring.challenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
