package com.example.springbootlearning.Database.Repository;

//import jakarta.transaction.Transactional;
import com.example.springbootlearning.Database.Entity.RefreshToken;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;


@Hidden
//在swagger上隱藏api
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    RefreshToken findByUsername(String username);
    RefreshToken findByToken(String token);
}
