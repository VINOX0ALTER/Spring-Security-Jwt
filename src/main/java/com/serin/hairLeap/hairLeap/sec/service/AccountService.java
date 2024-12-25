package com.serin.hairLeap.hairLeap.sec.service;

import com.serin.hairLeap.hairLeap.sec.entities.AppRole;
import com.serin.hairLeap.hairLeap.sec.entities.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> listUser();
}
