package com.novibe.common.util;

import com.novibe.common.base_structures.DnsProfile;
import com.novibe.common.exception.ProfileSetupException;

import java.util.ArrayList;
import java.util.List;

public class ProfileFailuresCollector {

    private final List<String> failures = new ArrayList<>();

    public void add(DnsProfile dnsProfile, ProfileSetupException exception) {
        failures.add("Profile %s (%s): %s".formatted(dnsProfile.number(), dnsProfile.dnsProvider(), exception.getMessage()));
    }

    public boolean isEmpty() {
        return failures.isEmpty();
    }

    public String summary(int profilesAmount) {
        return "Failed to set up %s of %s profiles:\n%s"
                .formatted(failures.size(), profilesAmount, String.join("\n", failures));
    }

}
