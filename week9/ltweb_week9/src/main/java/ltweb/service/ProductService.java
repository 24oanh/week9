package ltweb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ltweb.entity.ProductEntity;
import ltweb.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepository repo;
    
    /**
     * Lấy tất cả sản phẩm
     */
    public List<ProductEntity> listAll() {
        return repo.findAll();
    }
    
    /**
     * Lưu hoặc cập nhật sản phẩm
     */
    public ProductEntity save(ProductEntity product) {
        return repo.save(product);
    }
    
    /**
     * Lấy sản phẩm theo ID
     */
    public ProductEntity get(Long id) {
        return repo.findById(id).orElse(null);
    }
    
    /**
     * Xóa sản phẩm theo ID
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
    /**
     * Tìm kiếm sản phẩm theo tên
     */
    public List<ProductEntity> search(String keyword) {
        return repo.findByNameContaining(keyword);
    }
    
    /**
     * Tìm sản phẩm theo thương hiệu
     */
    public List<ProductEntity> findByBrand(String brand) {
        return repo.findByBrand(brand);
    }
    
    /**
     * Tìm sản phẩm theo khoảng giá
     */
    public List<ProductEntity> findByPriceRange(float minPrice, float maxPrice) {
        return repo.findByPriceBetween(minPrice, maxPrice);
    }
    
    /**
     * Kiểm tra sản phẩm có tồn tại không
     */
    public boolean exists(Long id) {
        return repo.existsById(id);
    }
}