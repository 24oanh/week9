package ltweb.controller;

import jakarta.validation.Valid;
import ltweb.entity.CategoryEntity;
import ltweb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryApiController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryApiController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Lấy tất cả danh mục
     * GET /api/category
     */
    @GetMapping("/category")
    public ResponseEntity<List<CategoryEntity>> listAll() {
        List<CategoryEntity> list = categoryService.listAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Lấy danh mục theo ID
     * GET /api/category/{id}
     */
    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryEntity> get(@PathVariable("id") Long id) {
        CategoryEntity category = categoryService.get(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    /**
     * Tạo danh mục mới
     * POST /api/category
     */
    @PostMapping("/category")
    public ResponseEntity<CategoryEntity> create(@Valid @RequestBody CategoryEntity category) {
        CategoryEntity saved = categoryService.save(category);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * Cập nhật danh mục
     * PUT /api/category/{id}
     */
    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryEntity> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody CategoryEntity categoryForm) {
        
        CategoryEntity category = categoryService.get(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        // Cập nhật dữ liệu
        categoryForm.setId(id);
        CategoryEntity updated = categoryService.save(categoryForm);
        return ResponseEntity.ok(updated);
    }

    /**
     * Xóa danh mục
     * DELETE /api/category/{id}
     */
    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        CategoryEntity category = categoryService.get(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}