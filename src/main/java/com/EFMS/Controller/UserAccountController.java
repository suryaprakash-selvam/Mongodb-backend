package com.EFMS.Controller;

import com.EFMS.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserAccountController {
    @Autowired
    public MongoTemplate mongoTemplate;




    @PostMapping("postuseraccount")
    public UserAccount postuser(@RequestBody UserAccount us){
        List<UserAccount> ls = mongoTemplate.findAll(UserAccount.class);
        List<String> email = new ArrayList<>();
        List<String> number = new ArrayList<>();
        UserAccount lo =new UserAccount();
        ls.forEach(ad->{
            email.add(ad.getEmail());
            number.add(ad.getMobile_Number());
        });
        if(!email.contains(us.getEmail()) && !number.contains(us.getMobile_Number())){
            return mongoTemplate.save(us);
        }else {
            lo.setUserName("falses");
            lo.setEmail("Duplicate entry Please check Email and Mobile number ");
            return lo;
        }
    }


    @GetMapping("UserAccount/{id}/{pwd}")
    public UserAccount getUserByIdAndPassword(@PathVariable String id, @PathVariable String pwd) {
        UserAccount lo =new UserAccount();
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(id));
        UserAccount UserAccount = mongoTemplate.findOne(query,UserAccount.class);
        if( UserAccount != null && pwd.equalsIgnoreCase(UserAccount.getPassword())){
            lo=UserAccount;
        }else {
            lo.setUserName("falses");
            lo.setEmail("Incorrect User name and Password");
        }
        return lo;
    }
}
