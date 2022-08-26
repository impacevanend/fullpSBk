package com.personalPrueba.crudAutenticacion.controller;

import com.personalPrueba.crudAutenticacion.dto.Message;
import com.personalPrueba.crudAutenticacion.dto.ProductDto;
import com.personalPrueba.crudAutenticacion.entity.Product;
import com.personalPrueba.crudAutenticacion.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public ResponseEntity<List<Product>>getProducts(){
        return new ResponseEntity(this.productService.getProduct(), HttpStatus.OK);

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id){
        if(!productService.existsById(id))
            return new ResponseEntity(new Message("The product doesn't exist."), HttpStatus.NOT_FOUND);
        Product product = productService.getOne(id).get();
        return new ResponseEntity(product, HttpStatus.OK);
    }

    @GetMapping("/products/{name}")
    public ResponseEntity<Product>getProdcutByName(@PathVariable("name")String name){
        if(!this.productService.existsByName(name))
            return new ResponseEntity(new Message("The product does'nt exist."), HttpStatus.NOT_FOUND);
        return new ResponseEntity(this.productService.existsByName(name),HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product>createProduct(@RequestBody ProductDto productDto){

        //Validations:
        if(StringUtils.isBlank(productDto.getName()))
            return new ResponseEntity(new Message("Name is required"),HttpStatus.BAD_REQUEST);
        if(productDto.getPrice() == null || productDto.getPrice() < 0 )
            return new ResponseEntity(new Message("The price must be greater than zero."),HttpStatus.BAD_REQUEST);
        if(this.productService.existsByName(productDto.getName()))
            return new ResponseEntity(new Message("The registered name already exists."),HttpStatus.BAD_REQUEST);

        Product product = new Product(productDto.getName(), productDto.getPrice());
        this.productService.saveProduct(product);

        return new ResponseEntity(new Message("Product created successfully."), HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product>updateProduct(@PathVariable("id")int id, @RequestBody ProductDto productDto){

        //Validations:
        if(!this.productService.existsById(id))
            return new ResponseEntity(new Message("The product does'nt exist."), HttpStatus.NOT_FOUND);

        if(StringUtils.isBlank(productDto.getName()))
            return new ResponseEntity(new Message("Name is required"),HttpStatus.BAD_REQUEST);

        if(this.productService.existsByName(productDto.getName()) &&
                this.productService.getProductByName(productDto.getName()).get().getId()
                                                                                    != id)
            return new ResponseEntity(new Message("The registered name already exists."),HttpStatus.BAD_REQUEST);

        if(productDto.getPrice() < 0 )
            return new ResponseEntity(new Message("The price must be greater than zero."),HttpStatus.BAD_REQUEST);

        Product product = this.productService.getOne(id).get();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());  ;
        this.productService.saveProduct(product);

        return new ResponseEntity(new Message("Updated product."), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void>deleteProductById(@PathVariable("id")Integer id){

        if(!this.productService.existsById(id))
            return new ResponseEntity(new Message("The product doesn't exist."), HttpStatus.NOT_FOUND);

        this.productService.deleteProduct(id);

        return new ResponseEntity(new Message("Product removed."), HttpStatus.OK);

    }

}
