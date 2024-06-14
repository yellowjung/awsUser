package com.example.awsuser.controller;

import com.example.awsuser.service.AwsIamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.iam.model.AccessKeyMetadata;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AwsIamController {

    private final AwsIamService awsIamService;

    @GetMapping("/old-create-keys")
    public List<AccessKeyMetadata> getOldCreateKeys(@RequestParam int hours) {
        return awsIamService.getOldCreateKeys(hours);
    }
}
