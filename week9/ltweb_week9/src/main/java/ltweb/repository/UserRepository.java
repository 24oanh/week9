package ltweb.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ltweb.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    UserEntity getUserByUsername(@Param("username") String username);
    
    Optional<UserEntity> findByEmail(String email);
    
    Optional<UserEntity> findByUsernameOrEmail(String username, String email);
    
    Optional<UserEntity> findByUsername(String username);
    
    Boolean existsByUsername(String username);
    
    Boolean existsByEmail(String email);
}