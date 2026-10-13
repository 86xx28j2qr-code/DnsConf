package com.novibe.dns.cloudflare.http;

import com.novibe.dns.cloudflare.http.dto.request.CreateRuleRequest;
import com.novibe.dns.cloudflare.http.dto.response.rule.MultiRuleApiResponse;
import com.novibe.dns.cloudflare.http.dto.response.rule.SingleRuleApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CloudflareRuleClient {

    private static final String path = "/rules";

    private final AbstractCloudflareApiClient abstractCloudflareApiClient;

    public SingleRuleApiResponse createBlockingRule(CreateRuleRequest rule) {
        return abstractCloudflareApiClient.post(path, rule, SingleRuleApiResponse.class);
    }

    public SingleRuleApiResponse removeRuleById(String id) {
        return abstractCloudflareApiClient.delete(path + "/" + id, SingleRuleApiResponse.class);
    }

    public MultiRuleApiResponse getRules() {
        return abstractCloudflareApiClient.get(path, MultiRuleApiResponse.class);
    }

}
