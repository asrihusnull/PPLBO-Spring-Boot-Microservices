package com.techie.microservices.product.repository;

import com.techie.microservices.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository; //Mengimpor MongoRepository untuk menghubungkan Spring Boot dengan MongoDB

public interface ProductRepository extends MongoRepository<Product, String> { //Product → Entity yang dikelola, String → Tipe data ID-nya, Memiliki fungsi bawaan seperti save(), findById(), dan findAll()

}
