package com.adminservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.adminservice.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
}
