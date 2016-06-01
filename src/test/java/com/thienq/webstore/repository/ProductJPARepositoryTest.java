package com.thienq.webstore.repository;

import com.thienq.webstore.WebstoreApplication;
import com.thienq.webstore.domain.Category;
import com.thienq.webstore.domain.Product;
import com.thienq.webstore.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import java.math.BigDecimal;

/**
 * Created by thien.quach on 5/31/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(WebstoreApplication.class)
public class ProductJPARepositoryTest {

    @Autowired
    private ProductJPARepository productRepository;

    @Autowired
    private CategoryJPARepository categoryRepository;

    @Test
    public void testSaveCategoryAndProduct(){
        Category category = new Category("Smartphone");

        assertNull(category.getId());
        categoryRepository.save(category);
        assertNotNull(category.getId());

        Product product = new Product("P1234", "Samsung Galaxy S5", category, new BigDecimal(400.0));
        product.setDescription("A new innovation");

        //save product, verify has ID value after save
        assertNull(product.getId());    //null before save
        productRepository.save(product);
        assertNotNull(product.getId()); //not null after save
    }
}
