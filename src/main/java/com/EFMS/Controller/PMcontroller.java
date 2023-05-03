package com.EFMS.Controller;

import com.EFMS.entity.Bid;
import com.EFMS.entity.ProjectMessage;
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
public class PMcontroller{
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

    @PostMapping("postMessage")
    public ProjectMessage projectMessagePost(@RequestBody ProjectMessage projectMessage){
        Long seq = this.getNextSequence("PmSeq");
        projectMessage.setPm_id(seq);
        return mongoTemplate.save(projectMessage);
    }

    @GetMapping("getMessage/{id}")
    public List<ProjectMessage> projectMessageGet(@PathVariable Long id){
        Query query = new Query();
        query.addCriteria(Criteria.where("project_id").is(id));
        return  mongoTemplate.find(query, ProjectMessage.class);
    }


}
