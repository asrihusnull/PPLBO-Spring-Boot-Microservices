package com.techie.microservices.product.model;

import lombok.AllArgsConstructor; //Membuat constructor dengan semua parameter
import lombok.Builder; //Menggunakan pola Builder untuk membuat objek dengan banyak parameter tanpa harus menghafal urutan parameter dalam konstruktor
import lombok.Data; //Menghasilkan getter, setter, toString(), equals(), dan hashCode()
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "product") //class ini adalah dokumen MongoDB dengan koleksi "product"
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    @Id //sebagai Primary Key di MongoDB
    private String id;
    private String name;
    private String description;
    private String skuCode;
    private BigDecimal price;
}
