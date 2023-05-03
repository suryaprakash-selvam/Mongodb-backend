package com.EFMS.Controller;

import com.EFMS.entity.Bid;
import com.EFMS.entity.ProjectDescripition;
import com.EFMS.entity.ProjectSeq;
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
public class BidController {

    @Autowired
    MongoTemplate mongoTemplate;

    public Long getNextSequence(String seqName) {
        Query query = new Query(Criteria.where("_id").is(seqName));
        Update update = new Update().inc("seq", 1);
        FindAndModifyOptions options = new FindAndModifyOptions().upsert(true).returnNew(true);
        ProjectSeq seqId = mongoTemplate.findAndModify(query, update, options, ProjectSeq
                .class);
        assert seqId != null;
        return seqId.getSeq();
    }



    @PostMapping("BidPost")
    public Bid postBid(@RequestBody Bid bid){
        Long seq = this.getNextSequence("BidSeq");
        bid.setBid_Id(seq);
        return mongoTemplate.save(bid);
    }

    @GetMapping("getBid/{id}")
    public List<Bid> getbid(@PathVariable Long id){
        Query query = new Query();
        query.addCriteria(Criteria.where("Project_id").is(id));
        return mongoTemplate.find(query, Bid.class);
    }

    @GetMapping("getBidUser/{id}")
    public List<Bid> getbid(@PathVariable String id ){
        Query query = new Query();
        query.addCriteria(Criteria.where("UserName").is(id));
        query.addCriteria(Criteria.where("Bid_Status").is("Accepted"));
        return mongoTemplate.find(query, Bid.class);
    }

    @GetMapping("getBidUserNotRes/{id}")
    public List<Bid> getbidnotresponse(@PathVariable String id ){
        Query query = new Query();
        query.addCriteria(Criteria.where("UserName").is(id));
        query.addCriteria(Criteria.where("Bid_Status").ne("Accepted"));
        return mongoTemplate.find(query, Bid.class);
    }

    @GetMapping("getBidbyUser/{id}/{user}")
    public List<Bid> getbidbyUser(@PathVariable Long id, @PathVariable String user){
        Query query = new Query();
        query.addCriteria(Criteria.where("UserName").is(user));
      query.addCriteria(Criteria.where("Project_id").is(id));
        return mongoTemplate.find(query, Bid.class);
    }

    @PatchMapping("updateAcceptBid")
    public Bid updateAcceptBid(@RequestBody Bid bid){
        Query query = new Query();
        query.addCriteria(Criteria.where("Bid_Id").is(bid.getBid_Id()));
        Update update = new Update();
        update.set("Bid_Status", "Accepted");
        Bid updateResult = mongoTemplate.findAndModify(query,update,new FindAndModifyOptions().returnNew(true),Bid.class);

        assert updateResult != null;
        if(updateResult.getBid_Status().equalsIgnoreCase("Accepted")){
            Bid updateResult1 = UpdateRemaing(bid);
            return updateResult;
        }
        return updateResult;

    }

    public Bid UpdateRemaing(Bid bid){
        Query query = new Query();
        query.addCriteria(Criteria.where("Bid_Status").is("YTR"));
        query.addCriteria(Criteria.where("Project_id").is(bid.getProject_id()));
        Update update = new Update();
        update.set("Bid_Status", "Rejected");
        updateInProjectDescription(bid);
        return mongoTemplate.findAndModify(query,update,new FindAndModifyOptions().returnNew(true),Bid.class);
    }
    public void updateInProjectDescription(Bid bid){
        Query query = new Query();
        query.addCriteria(Criteria.where("Project_Id").is(bid.getProject_id()));
        Update update = new Update();
        update.set("Status", "Assigned");
        update.set("Assigner",bid.getUserName());

        mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), ProjectDescripition.class);

    }

    @PatchMapping("updateRejectBid")
    public Bid updateregBid(@RequestBody Bid bid){
        Query query = new Query();
        query.addCriteria(Criteria.where("Bid_Id").is(bid.getBid_Id()));
        Update update = new Update();
        update.set("Bid_Status", "Rejected");
        Bid updateResult = mongoTemplate.findAndModify(query,update,new FindAndModifyOptions().returnNew(true),Bid.class);
        assert updateResult != null;
        if(updateResult.getBid_Status().equalsIgnoreCase("Rejected")){
            return updateResult;
        }
        return updateResult;

    }

}
