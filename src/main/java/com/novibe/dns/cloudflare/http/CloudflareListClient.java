package com.novibe.dns.cloudflare.http;

import com.novibe.dns.cloudflare.http.dto.request.CreateListRequest;
import com.novibe.dns.cloudflare.http.dto.response.list.GatewayListDto;
import com.novibe.dns.cloudflare.http.dto.response.list.MultiListApiResponse;
import com.novibe.dns.cloudflare.http.dto.response.list.SingleListApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Log
@Service
@RequiredArgsConstructor
public class CloudflareListClient {

    private static final String path = "/lists";

    private final AbstractCloudflareApiClient abstractCloudflareApiClient;

    public List<GatewayListDto> getLists() {
        List<GatewayListDto> lists = abstractCloudflareApiClient.get(path, MultiListApiResponse.class).getResult();
        return Objects.requireNonNullElse(lists, List.of());
    }

    public SingleListApiResponse postList(CreateListRequest createListRequest) {
        return abstractCloudflareApiClient.post(path, createListRequest, SingleListApiResponse.class);
    }

    public SingleListApiResponse deleteListById(UUID listId) {
        return abstractCloudflareApiClient.delete(path + "/" + listId, SingleListApiResponse.class);
    }

}
