package ltweb.controller;

import jakarta.validation.Valid;
import ltweb.entity.ProductEntity;
import ltweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductApiController {

    private final ProductService productService;

    @Autowired
    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Lấy tất cả sản phẩm
     * GET /api/product
     */
    @GetMapping("/product")
    public ResponseEntity<List<ProductEntity>> listAll() {
        List<ProductEntity> list = productService.listAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Lấy sản phẩm theo ID
     * GET /api/product/{id}
     */
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductEntity> find(@PathVariable("id") Long id) {
        ProductEntity product = productService.get(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    /**
     * Tạo sản phẩm mới
     * POST /api/product
     */
    @PostMapping("/product")
    public ResponseEntity<ProductEntity> create(@Valid @RequestBody ProductEntity product) {
        ProductEntity saved = productService.save(product);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * Cập nhật sản phẩm
     * PUT /api/product/{id}
     */
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductEntity> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody ProductEntity productForm) {
        
        ProductEntity product = productService.get(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        // Đảm bảo ID đúng khi update
        productForm.setId(id);
        ProductEntity updated = productService.save(productForm);
        return ResponseEntity.ok(updated);
    }

    /**
     * Xóa sản phẩm
     * DELETE /api/product/{id}
     */
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        ProductEntity product = productService.get(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}