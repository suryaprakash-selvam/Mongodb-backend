package com.EFMS.Entity2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "UserSeq")
public class UserSeq {
    private String _id;
    private Long seq;
}
