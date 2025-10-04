package ltweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ltweb.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    // Có thể thêm custom query methods ở đây
    // Ví dụ: List<CategoryEntity> findByCategoryNameContaining(String keyword);
}