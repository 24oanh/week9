package ltweb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ltweb.entity.CategoryEntity;
import ltweb.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {
    
    @Autowired
    private CategoryRepository repo;
    
    /**
     * Lấy tất cả danh mục
     */
    public List<CategoryEntity> listAll() {
        return repo.findAll();
    }
    
    /**
     * Lưu hoặc cập nhật danh mục
     */
    public CategoryEntity save(CategoryEntity category) {
        return repo.save(category);
    }
    
    /**
     * Lấy danh mục theo ID
     */
    public CategoryEntity get(Long id) {
        return repo.findById(id).orElse(null);
    }
    
    /**
     * Xóa danh mục theo ID
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
    /**
     * Kiểm tra danh mục có tồn tại không
     */
    public boolean exists(Long id) {
        return repo.existsById(id);
    }
}