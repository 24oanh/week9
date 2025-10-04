package ltweb.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ltweb.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    
    @Query("SELECT r FROM RoleEntity r WHERE r.name = :name")
    RoleEntity getUserByName(@Param("name") String name);
    
    Optional<RoleEntity> findByName(String name);
    
    boolean existsByName(String name);
}