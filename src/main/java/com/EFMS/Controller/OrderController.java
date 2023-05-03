package com.EFMS.Controller;

import com.EFMS.Entity2.Order;
import com.EFMS.Entity2.Payment;
import com.EFMS.entity.ProjectSeq;
import com.mongodb.client.result.UpdateResult;
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
public class OrderController {

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

    @PostMapping("postOrder")
    private Order postOrder(@RequestBody Order order){
        Long seq = this.getNextSequence("orderSeq");
        order.setOrderId(seq);
        return mongoTemplate.save(order);
    }

    @GetMapping("getOrder/{id}")
    private List<Order> getOrderId(@PathVariable Long id){
        Query query = new Query(Criteria.where("userId").is(id));
        return mongoTemplate.find(query , Order.class);
    }
    @GetMapping("getOrder")
    private List<Order> getOrderIdAll(){
        return mongoTemplate.findAll(Order.class);
    }

    @PostMapping("UpdateStatus")
    private UpdateResult update(@RequestBody Order order){

        Query query = new Query(Criteria.where("OrderId").is(order.getOrderId()));

        // Create an Update object to specify the fields to update
        Update update = new Update();
        update.set("updateOn",order.getUpdateOn());
        update.set("managerID", order.getManagerID());
        update.set("status",order.getStatus());

        // Call mongoTemplate.updateFirst() method to update the order in the database
        ///  mongoTemplate.updateFirst(query, update, Order.class);
        return  mongoTemplate.updateFirst(query, update, Order.class);
    }
}
