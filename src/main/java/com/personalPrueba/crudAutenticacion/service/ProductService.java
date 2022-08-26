package com.personalPrueba.crudAutenticacion.service;

import com.personalPrueba.crudAutenticacion.entity.Product;
import com.personalPrueba.crudAutenticacion.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product>getProduct(){
        return this.productRepository.findAll();
    }

    public Optional<Product>getProductById(int id){
        return this.productRepository.findById(id);
    }

    public Optional<Product>getProductByName(String name){
        return  this.productRepository.findByName(name);
    }

    public Product saveProduct(Product product){
        return this.productRepository.save(product);
    }

    public void deleteProduct(Integer id){
        this.productRepository.deleteById(id);
    }

    public boolean  existsById(Integer id){
        return  this.productRepository.existsById(id);
    }

    public boolean existsByName(String name){
        return this.productRepository.existsByName(name);
    }

    public Optional<Product> getOne(int id){
        return productRepository.findById(id);
    }
}
