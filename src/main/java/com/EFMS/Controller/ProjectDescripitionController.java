package com.EFMS.Controller;

import com.EFMS.entity.ProjectDescripition;
import com.EFMS.entity.ProjectSeq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class ProjectDescripitionController {

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

    @PostMapping("postProject")
    public ProjectDescripition postDescription(@RequestBody ProjectDescripition projectDescripition){
     Long seq = this.getNextSequence("ProjectSeq");

     projectDescripition.setProject_Id(seq);
     return mongoTemplate.save(projectDescripition);
    }

    @GetMapping("duplicated/{collectionName}")
    public List<ProjectDescripition> findDuplicateFields(@PathVariable String collectionName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("Project_Title").is(collectionName));
        return mongoTemplate.find(query, ProjectDescripition.class);
    }

    @GetMapping("allProjects")
    private List<ProjectDescripition> FindAll(){
        return mongoTemplate.findAll(ProjectDescripition.class);
    }


}
