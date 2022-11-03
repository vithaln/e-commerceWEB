package com.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
