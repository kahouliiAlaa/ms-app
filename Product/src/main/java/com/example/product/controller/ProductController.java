package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
       this.productService = productService;
   }

   @GetMapping
   public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return  new ResponseEntity<>(products, HttpStatus.OK);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        if ( product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   @PostMapping
   public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product createProduct = productService.createProduct(product);
        return new ResponseEntity<>(createProduct, HttpStatus.CREATED);

   }

   @PutMapping("/{id}")
   public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        Product updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct != null){
            return  new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   @DeleteMapping("{id}")
   public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}
