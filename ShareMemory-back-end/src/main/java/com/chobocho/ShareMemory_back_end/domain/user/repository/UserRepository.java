package com.chobocho.ShareMemory_back_end.domain.user.repository;

import com.chobocho.ShareMemory_back_end.domain.user.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface UserRepository extends JpaRepository<User, String> {


}
