package com.thienq.webstore.repository;

import com.thienq.webstore.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJPARepository extends CrudRepository<Customer, Long> {

}
