package com.example.awsuser.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class UserAccessKeyInfo implements Comparable<UserAccessKeyInfo> {

    private String userId;
    private String accessKey;
    private Instant createDate;

    @Override
    public int compareTo(UserAccessKeyInfo other){
        return this.createDate.compareTo(other.createDate);
    }
}
