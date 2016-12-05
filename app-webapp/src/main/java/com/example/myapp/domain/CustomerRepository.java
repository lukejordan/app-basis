package com.example.myapp.domain;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.data.repository.*;

import com.lukeyj.example.data.persistence.CustomerEntity;

public interface CustomerRepository extends Repository<CustomerEntity, Long> {

    Page<CustomerEntity> findAll(Pageable pageable);

    List<CustomerEntity> findAll();

    //CustomerEntity findByNameAndCountryAllIgnoringCase(String name, String country);

    void delete(CustomerEntity customer);
    
    CustomerEntity save (CustomerEntity customer);
    
}