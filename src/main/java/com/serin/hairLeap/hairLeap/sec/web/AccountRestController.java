package com.serin.hairLeap.hairLeap.sec.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.serin.hairLeap.hairLeap.JwtUtils;
import com.serin.hairLeap.hairLeap.sec.entities.AppRole;
import com.serin.hairLeap.hairLeap.sec.entities.AppUser;
import com.serin.hairLeap.hairLeap.sec.repo.AppRoleRepository;
import com.serin.hairLeap.hairLeap.sec.repo.AppUserRepository;
import com.serin.hairLeap.hairLeap.sec.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AccountRestController {

    @Autowired
    AccountService accountService;

    @GetMapping(path = "/users")
    @PostAuthorize("hasAuthority('CUSTOMER_MANAGER')")
    public List<AppUser> appUsers(){

        return accountService.listUser();
    }

    @PostMapping(path = "/users")
    public AppUser saveUser(@RequestBody AppUser appUser){
        return accountService.addNewUser(appUser);
    }

    @PostMapping(path = "/role")
    public AppRole saveRole(@RequestBody AppRole appRole){
        return accountService.addNewRole(appRole);
    }

    @PostMapping(path = "/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
         accountService.addRoleToUser(roleUserForm.getUsername(),roleUserForm.getPassword());
    }


    @GetMapping(path = "/refreshToken")
    public void refreshToken(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String authToken = request.getHeader("Authorization");

        if(authToken != null && authToken.startsWith("bearer ") )
        {
            try {
                String Jwt=authToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256(JwtUtils.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(Jwt);
                String username = decodedJWT.getSubject();
                AppUser appUser = accountService.loadUserByUsername(username);
                String jwtAccessToken = JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtils.EXPIRE_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles" , appUser.getAppRole().stream().map(r -> r.getRoleName()).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> idToken = new HashMap<>();
                idToken.put("access-Token",jwtAccessToken);
                idToken.put("refresh-Token",Jwt);


            }
            catch (Exception e){

                throw e;

            }

        }
        else
        {
            throw new RuntimeException("Refresh token required");
        }

    }

    @GetMapping(path = "/profile")
    //@PostAuthorize("hasAuthority('CUSTOMER_MANAGER')")
    public AppUser profile(Principal principal){
        return accountService.loadUserByUsername(principal.getName());
    }


}



@Data
class RoleUserForm{
    private  String username;
    private String password;
}