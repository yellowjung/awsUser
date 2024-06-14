package com.example.awsuser.service;

import com.example.awsuser.domain.UserAccessKeyInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AwsIamService {

    private final IamClient iamClient;

    public List<UserAccessKeyInfo> getOldCreateKeys(int hours) {
        List<UserAccessKeyInfo> oldCreateKeys = new ArrayList<>();
        ListUsersResponse listUsersResponse = iamClient.listUsers();
        Instant cutoffTime = Instant.now().minus(hours, ChronoUnit.HOURS);

        for(User user : listUsersResponse.users()){
            ListAccessKeysResponse listAccessKeysResponse = iamClient.listAccessKeys(
                    ListAccessKeysRequest.builder().userName(user.userName()).build());

            for (AccessKeyMetadata accessKey : listAccessKeysResponse.accessKeyMetadata()){
                if (accessKey.createDate().isBefore(cutoffTime)) {
                    oldCreateKeys.add(new UserAccessKeyInfo(user.userName(), accessKey.accessKeyId(), accessKey.createDate()));
                }
            }
        }

        Collections.sort(oldCreateKeys);

        return oldCreateKeys;
    }
}
