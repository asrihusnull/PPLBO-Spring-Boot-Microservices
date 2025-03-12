package com.techie.microservices.product.service;

import com.techie.microservices.product.dto.ProductRequest;
import com.techie.microservices.product.dto.ProductResponse;
import com.techie.microservices.product.model.Product;
import com.techie.microservices.product.repository.ProductRepository; //untuk pengolahan data
import lombok.RequiredArgsConstructor; //Otomatis membuat constructor untuk final fiel
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j //Menyediakan logging untuk debugging
public class ProductService {
    private final ProductRepository productRepository; //Menggunakan repository untuk mengakses database

    public ProductResponse createProduct(ProductRequest productRequest){ //Membuat Product baru dari ProductRequest menggunakan Builder pattern
        Product product = Product.builder()
            .name(productRequest.name())
            .description(productRequest.description())
            .skuCode(productRequest.skuCode())
            .price(productRequest.price())
            .build();
        productRepository.save(product); //Menyimpan produk ke database
        log.info("Product created successfully!");
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getSkuCode(), product.getPrice()); //mengembalikan ProductResponse
    }

    public List<ProductResponse> getAllProducts(){ //Mengambil semua produk dari database
        return productRepository.findAll() //mengubahnya menjadi ProductResponse menggunakan Stream API
            .stream()
            .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getSkuCode(), product.getPrice()))
            .toList();

    }
}
