package com.novibe.common.util;

import com.novibe.common.base_structures.DnsProfile;
import com.novibe.common.exception.CredentialsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProfileFailuresCollectorTest {

    @Test
    void staysEmptyWhenEveryProfileIsFine() {
        assertTrue(new ProfileFailuresCollector().isEmpty());
    }

    @Test
    void keepsProfileNumberAndProviderInSummary() {
        ProfileFailuresCollector profileFailuresCollector = new ProfileFailuresCollector();

        profileFailuresCollector.add(profile(1, "NEXTDNS"), new CredentialsException("Invalid api key"));
        profileFailuresCollector.add(profile(3, "CLOUDFLARE"), new CredentialsException("Invalid API Token!"));

        assertFalse(profileFailuresCollector.isEmpty());
        assertEquals("""
                Failed to set up 2 of 3 profiles:
                Profile 1 (NEXTDNS): Invalid api key
                Profile 3 (CLOUDFLARE): Invalid API Token!""", profileFailuresCollector.summary(3));
    }

    private DnsProfile profile(int number, String provider) {
        return DnsProfile.builder().number(number).dnsProvider(provider).build();
    }
}
