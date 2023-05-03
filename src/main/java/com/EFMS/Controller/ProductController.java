package com.EFMS.Controller;


import com.EFMS.Entity2.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
    public MongoTemplate mongoTemplate;


    @PostMapping("postProduct")
    public ProductDetails post(@RequestBody ProductDetails pro){
        return mongoTemplate.save(pro);
    }
    @PostMapping("postProductList")
    public List<ProductDetails> postList(@RequestBody List<ProductDetails> pro){
        pro.forEach(a->{
            mongoTemplate.save(a);
        });
        return pro;
    }

    @GetMapping("getAllProductDetails")
    public List<ProductDetails> GetALlProduct(){
        return mongoTemplate.findAll(ProductDetails.class);
    }


}
