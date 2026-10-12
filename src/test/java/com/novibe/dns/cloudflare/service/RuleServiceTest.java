package com.novibe.dns.cloudflare.service;

import com.novibe.dns.cloudflare.http.CloudflareRuleClient;
import com.novibe.dns.cloudflare.http.dto.response.rule.GatewayRuleDto;
import com.novibe.dns.cloudflare.http.dto.response.rule.MultiRuleApiResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RuleServiceTest {

    @Test
    void returnsEmptyListWhenApiAnswersWithoutResult() {
        RuleService ruleService = ruleServiceAnswering(null);

        assertTrue(ruleService.obtainExistingRules().isEmpty());
    }

    @Test
    void keepsRulesMutableSoOldOnesCanBeRemoved() {
        GatewayRuleDto rule = new GatewayRuleDto();
        RuleService ruleService = ruleServiceAnswering(List.of(rule));

        List<GatewayRuleDto> rules = ruleService.obtainExistingRules();
        rules.remove(rule);

        assertEquals(List.of(), rules);
    }

    private RuleService ruleServiceAnswering(List<GatewayRuleDto> result) {
        MultiRuleApiResponse response = new MultiRuleApiResponse();
        response.setResult(result);
        return new RuleService(new StubRuleClient(response), "session-id");
    }

    private static class StubRuleClient extends CloudflareRuleClient {

        private final MultiRuleApiResponse response;

        private StubRuleClient(MultiRuleApiResponse response) {
            super(null);
            this.response = response;
        }

        @Override
        public MultiRuleApiResponse getRules() {
            return response;
        }
    }
}
