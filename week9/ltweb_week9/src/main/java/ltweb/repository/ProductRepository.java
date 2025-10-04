package ltweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ltweb.entity.ProductEntity;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    // Custom query methods
    List<ProductEntity> findByNameContaining(String keyword);
    List<ProductEntity> findByBrand(String brand);
    List<ProductEntity> findByPriceBetween(float minPrice, float maxPrice);
}