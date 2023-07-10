package tw.idv.petradisespringboot.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.idv.petradisespringboot.mall.repo.ProductDAO;
import tw.idv.petradisespringboot.mall.vo.Product;
import tw.idv.petradisespringboot.mall.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product insert (Product newProduct) {
        return productDAO.save(newProduct);
    }

    public Product update (Product product) {
        return productDAO.save(product);
    }

    public Product getOneById(Integer productId) {
        return productDAO.findById(productId).orElse(null);
    }

    public List<Product> getAll() {
        return productDAO.findAll();
    }


}
