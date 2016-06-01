package com.thienq.webstore.bootstrap;

import com.thienq.webstore.domain.Category;
import com.thienq.webstore.domain.Product;
import com.thienq.webstore.repository.CategoryJPARepository;
import com.thienq.webstore.repository.ProductJPARepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by thien.quach on 6/1/2016.
 */
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private Logger log = Logger.getLogger(DataLoader.class);

    @Autowired
    private ProductJPARepository productRepository;

    @Autowired
    private CategoryJPARepository categoryRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //List of Category to be added as startup
        List<Category> categoriesTobeAdded = new ArrayList<Category>(){{
            add(new Category("SmartPhone"));
            add(new Category("Laptop"));
            add(new Category("Digital Camera"));
            add(new Category("Table"));
        }};

        categoryRepository.save(categoriesTobeAdded);
        //loop the list
        log.info("Categories to be add");
        categoriesTobeAdded.forEach((c)-> log.info(c.toString()));

        //List of Product to be added as startup
        List<Product> productsToBeAdded = new ArrayList<Product>(){{
            add(new Product("P1234", "Apple Iphone 6", categoriesTobeAdded.get(0), new BigDecimal(700.0)));
            add(new Product("P1235", "Samsung Galaxy S6", categoriesTobeAdded.get(0), new BigDecimal(600.0)));
            add(new Product("P1236", "HTC One", categoriesTobeAdded.get(0), new BigDecimal(500.0)));
            add(new Product("P1237", "Blackberry Priv", categoriesTobeAdded.get(0), new BigDecimal(500.0)));

            add(new Product("P1238", "Sony Vaio", categoriesTobeAdded.get(1), new BigDecimal(500.0)));
            add(new Product("P1239", "Dell XPS", categoriesTobeAdded.get(1), new BigDecimal(600.0)));
            add(new Product("P1240", "Lenovo E400", categoriesTobeAdded.get(1), new BigDecimal(700.0)));
            add(new Product("P1241", "Macbook Air 11\"", categoriesTobeAdded.get(1), new BigDecimal(800.0)));
        }};

        productRepository.save(productsToBeAdded);
        //loop the list
        log.info("Products to be added");
        productsToBeAdded.forEach((p) -> log.info(p.toString()));

    }
}
