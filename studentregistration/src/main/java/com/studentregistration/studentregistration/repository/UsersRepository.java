package com.studentregistration.studentregistration.repository;

import com.studentregistration.studentregistration.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

    boolean existsByUserName(String userName);

    Optional<Users> findByUserName(String userName);
}
