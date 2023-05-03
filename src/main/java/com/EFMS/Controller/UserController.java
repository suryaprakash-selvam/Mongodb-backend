package com.EFMS.Controller;

import com.EFMS.Entity2.User;
import com.EFMS.entity.ProjectSeq;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private MongoTemplate mongoTemplate;


    public Long getNextSequence(String seqName) {
        Query query = new Query(Criteria.where("_id").is(seqName));
        Update update = new Update().inc("seq", 1);
        FindAndModifyOptions options = new FindAndModifyOptions().upsert(true).returnNew(true);
        ProjectSeq seqId = mongoTemplate.findAndModify(query, update, options, ProjectSeq
                .class);
        assert seqId != null;
        return seqId.getSeq();
    }

    @GetMapping("getUser")
    private List<User> allUser( ){
        return mongoTemplate.findAll(User.class);
    }


    @GetMapping("/login/{us}/{pwd}")
    private User userlogin(@PathVariable String us, @PathVariable String pwd){
        Query query = new Query();
        query.addCriteria(Criteria.where("eMail").is(us).and("password").is(pwd));
        return mongoTemplate.findOne(query, User.class);
    }

    @GetMapping("/delete/{us}")
    private DeleteResult userlogin(@PathVariable Long us){
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(us));
        return mongoTemplate.remove(query, User.class);
    }

    @PostMapping("postUser")
    private User saveUser(@RequestBody User user){
        Long seq = this.getNextSequence("UserSeq");
        Query query = new Query();

// Create the OR condition
        Criteria emailCriteria = Criteria.where("eMail").is(user.getEmail());
        Criteria mobileCriteria = Criteria.where("mobileNumber").is(user.getMobileNumber());
        Criteria orCriteria = new Criteria().orOperator(emailCriteria, mobileCriteria);

// Add the OR condition to the query
        query.addCriteria(orCriteria);
        List<User> users = mongoTemplate.find(query, User.class);
        if(users.isEmpty()){
            user.setUserId(seq);

            return mongoTemplate.save(user);
        }
        else{
            user.setName("NO");
            return user;
        }

    }
}
