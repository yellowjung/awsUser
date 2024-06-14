package com.example.awsuser.controller;

import com.example.awsuser.domain.UserAccessKeyInfo;
import com.example.awsuser.service.AwsIamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AwsIamController {

    private final AwsIamService awsIamService;

    //AWS IAM에서 생성한지 N시간을 초과하는 IAM User의 User ID와 Access Key ID를 반환
    @GetMapping("/old-create-keys")
    public List<UserAccessKeyInfo> getOldCreateKeys(@RequestParam int hours) {
        return awsIamService.getOldCreateKeys(hours);
    }
}
