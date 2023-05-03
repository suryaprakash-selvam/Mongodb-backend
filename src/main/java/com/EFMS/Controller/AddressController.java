package com.EFMS.Controller;

import com.EFMS.Entity2.Address;
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

@RestController
@CrossOrigin("*")
public class AddressController {
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

    @PostMapping("postAddress")
    private UpdateResult postOrder(@RequestBody Address order){
        Long seq = this.getNextSequence("orderSeq");
        order.setAddressID(seq);
         Address address= mongoTemplate.save(order);
        Query query = new Query(Criteria.where("OrderId").is(order.getOrderId()));

        // Create an Update object to specify the fields to update
        Update update = new Update();
        update.set("addressId", address.getAddressID());
        update.set("status", "YTR");

        // Call mongoTemplate.updateFirst() method to update the order in the database
      ///  mongoTemplate.updateFirst(query, update, Order.class);
        return  mongoTemplate.updateFirst(query, update, Order.class);
    }
    @PostMapping("postPayment")
    private UpdateResult postOrderpay(@RequestBody Payment order){
        Long seq = this.getNextSequence("paySeq");
        order.setId(seq);
        Payment pay= mongoTemplate.save(order);
        Query query = new Query(Criteria.where("OrderId").is(order.getOrderId()));

        // Create an Update object to specify the fields to update
        Update update = new Update();
        update.set("paymentId", pay.getId());
        update.set("status", "Ordered");

        // Call mongoTemplate.updateFirst() method to update the order in the database
        ///  mongoTemplate.updateFirst(query, update, Order.class);
        return  mongoTemplate.updateFirst(query, update, Order.class);
    }

    @GetMapping("getAddress/{id}")
    public Address getAddress(@PathVariable Long id){
        Query query = new Query(Criteria.where("addressID").is(id));
        return mongoTemplate.findOne(query,Address.class);
    }
}

