package com.techie.microservices.product.controller;

import java.util.List; //untuk mengelola daftar produk

import org.springframework.http.HttpStatus; //untuk mengatur kode status HTTP
import org.springframework.web.bind.annotation.*; //untuk menangani request HTTP
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestController;
import com.techie.microservices.product.dto.ProductRequest; 
import com.techie.microservices.product.dto.ProductResponse;
// import com.techie.microservices.product.model.Product;
import com.techie.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor; //membuat constructor untuk field final

@RestController //class ini adalah controller berbasis REST
@RequestMapping("/api/product") //semua endpoint dalam controller ini akan memiliki prefix /api/product
@RequiredArgsConstructor 
public class ProductController {

    private final ProductService productService;

    @PostMapping //Menangani HTTP POST request untuk membuat produk
    @ResponseStatus(HttpStatus.CREATED) //Menandakan bahwa respons yang dikembalikan memiliki status 201 Created
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){ //Data yang dikirim dari client akan dikonversi menjadi objek ProductRequest
        return productService.createProduct(productRequest);
    }

    @GetMapping //Menangani HTTP GET request untuk mendapatkan semua produk
    @ResponseStatus(HttpStatus.OK) //Mengembalikan status 200 OK
    public List<ProductResponse> getAllProducts(){ //Mengembalikan daftar produk
        return productService.getAllProducts();
    }

}
