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

        // 정보를 받아온 IAM 유저들의 UserID와 Access Key를 확인합니다.
        for(User user : listUsersResponse.users()){
            ListAccessKeysResponse listAccessKeysResponse = iamClient.listAccessKeys(
                    ListAccessKeysRequest.builder().userName(user.userName()).build());

            //각 AccessKey의 생성 날짜를 확인하고, 요청된 시간보다 오래된 경우 결과에 추가합니다.
            for (AccessKeyMetadata accessKey : listAccessKeysResponse.accessKeyMetadata()){
                if (accessKey.createDate().isBefore(cutoffTime)) {
                    oldCreateKeys.add(new UserAccessKeyInfo(user.userName(), accessKey.accessKeyId(), accessKey.createDate()));
                }
            }
        }
        //createDate 기준으로 정렬합니다.
        Collections.sort(oldCreateKeys);

        return oldCreateKeys;
    }
}
