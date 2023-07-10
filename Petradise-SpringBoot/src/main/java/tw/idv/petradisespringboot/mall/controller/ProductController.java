package tw.idv.petradisespringboot.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.idv.petradisespringboot.mall.vo.Product;
import tw.idv.petradisespringboot.mall.service.ProductService;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product/add")
    public Product insert(@RequestBody Product product) {
        return productService.insert(product);
    }

    @PutMapping("/product/update/{pdId}")
    public Product update(@PathVariable("pdId") Integer pdId, @RequestBody Product product) {
        Product existingProduct = productService.getOneById(pdId);
        if (existingProduct != null) {
            product.setPdId(pdId);
            productService.update(product);
        }
        return product;
    }

    @GetMapping("/product/get/{pdId}")
    public Product getPdById(@PathVariable("pdId") Integer pdId) {
        return productService.getOneById(pdId);
    }

    @GetMapping("/product/get/all")
    public List<Product> getAll() {
        return productService.getAll();
    }


}
