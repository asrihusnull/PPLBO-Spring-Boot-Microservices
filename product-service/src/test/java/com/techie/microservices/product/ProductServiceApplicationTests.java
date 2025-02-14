package com.techie.microservices.product;

import org.hamcrest.Matchers; //untuk memverifikasi hasil respons API (seperti memeriksa apakah ID tidak null, atau apakah nilai yang dikembalikan sesuai)
import org.junit.jupiter.api.BeforeEach; //Untuk menentukan metode yang dieksekusi sebelum setiap pengujian
import org.junit.jupiter.api.Test; //mendefinisikan unit test
import org.springframework.boot.test.context.SpringBootTest; //Menjalankan konteks Spring Boot selama pengujian
import org.springframework.boot.test.web.server.LocalServerPort; //Mengambil port yang digunakan oleh server Spring Boot yang dijalankan secara acak saat pengujian
import org.springframework.boot.testcontainers.service.connection.ServiceConnection; //untuk menghubungkan MongoDB Testcontainers ke konteks Spring Boot
import org.springframework.context.annotation.Import; //Mengimpor konfigurasi tambahan untuk Testcontainers
import org.testcontainers.containers.MongoDBContainer; //membuat MongoDB sementara untuk pengujian

import io.restassured.RestAssured; //untuk mengirim HTTP request ke API dalam pengujian otomatis

@Import(TestcontainersConfiguration.class) //Mengimpor konfigurasi tambahan untuk Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //Menjalankan aplikasi Spring Boot dengan port acak, agar tidak bentrok dengan aplikasi lain yang berjalan di sistem
class ProductServiceApplicationTests {

	@ServiceConnection //Memastikan bahwa MongoDBContainer digunakan sebagai basis data untuk Spring Boot
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5"); //Membuat container MongoDB versi 7.0.5 untuk pengujian

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost"; //Mengatur base URL dari API yang akan diuji
		RestAssured.port = port; //Mengatur port API sesuai dengan port acak
	}

	static {
		mongoDBContainer.start(); //Menjalankan container MongoDB sebelum pengujian dimulai
	}

	@Test
	void shouldCreateProduct() { //pengujian dilakukan untuk memastikan produk bisa dibuat
		String requestBody = """
					{
						"name": "iPhone 15",
						"description": "iPhone 15 is a smartphone from Apple",
						"price": 1000
					}
				"""; //untuk mendefinisikan JSON request body yang akan dikirim ke API

		RestAssured.given()
			.contentType("application/json") //Menentukan bahwa request yang dikirim berupa JSON
			.body(requestBody)
			.when()
			.post("/api/product") //Mengirimkan HTTP POST request ke endpoint /api/product
			.then() //memverifikasi data yang dikembalikan oleh API
			.statusCode(201)
			.body("id", Matchers.notNullValue())
			.body("name", Matchers.equalTo("iPhone 15"))
			.body("description", Matchers.equalTo("iPhone 15 is a smartphone from Apple"))
			.body("price", Matchers.equalTo(1000));
	}

}
