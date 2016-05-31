package com.thienq.webstore.repository;

import com.thienq.webstore.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryJPARepository extends CrudRepository<Category, Long>{

	
}
