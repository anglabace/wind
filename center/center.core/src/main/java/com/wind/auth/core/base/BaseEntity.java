package com.wind.auth.core.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {
    @Getter
    @Setter
    @JsonIgnore
    private ObjectId id;

    @Getter
    @Setter
    private Date createTime = new Date();

    public String getIdentifier() {
        return id.toHexString();
    }

    public void setIdentifier(String identifier) {
        this.id = new ObjectId(identifier);
    }
}
