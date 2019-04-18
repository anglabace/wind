package com.wind.gateway.entry.entity;

import lombok.Data;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    private ObjectId id;
    private Date createTime = new Date();
}
