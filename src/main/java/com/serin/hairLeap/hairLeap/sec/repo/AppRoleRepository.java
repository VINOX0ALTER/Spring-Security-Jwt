package com.serin.hairLeap.hairLeap.sec.repo;

import com.serin.hairLeap.hairLeap.sec.entities.AppRole;
import com.serin.hairLeap.hairLeap.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String username);


}
