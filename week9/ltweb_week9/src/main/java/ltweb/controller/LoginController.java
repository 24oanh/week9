package ltweb.controller;

import ltweb.entity.ProductEntity;
import ltweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private ProductService service;

    /**
     * Xử lý khi đăng nhập thành công
     * POST /login_success_handler
     */
    @PostMapping("/login_success_handler")
    public String loginSuccessHandler() {
        System.out.println("Logging user login success...");
        return "index";
    }

    /**
     * Xử lý khi đăng nhập thất bại
     * POST /login_failure_handler
     */
    @PostMapping("/login_failure_handler")
    public String loginFailureHandler() {
        System.out.println("Login failure handler...");
        return "login";
    }

    /**
     * Trang chủ (danh sách sản phẩm)
     * GET /
     */
    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<ProductEntity> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }

    /**
     * Hiển thị form tạo sản phẩm mới
     * GET /new
     */
    @GetMapping("/new")
    public String showNewProductForm(Model model) {
        ProductEntity product = new ProductEntity();
        model.addAttribute("product", product);
        return "new_product";
    }

    /**
     * Lưu sản phẩm (tạo mới hoặc cập nhật)
     * POST /save
     */
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") ProductEntity product) {
        service.save(product);
        return "redirect:/";
    }

    /**
     * Hiển thị form chỉnh sửa sản phẩm
     * GET /edit/{id}
     */
    @GetMapping("/edit/{id}")
    public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_product");
        ProductEntity product = service.get(id);
        mav.addObject("product", product);
        return mav;
    }

    /**
     * Xóa sản phẩm
     * GET /delete/{id}
     */
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/";
    }
}