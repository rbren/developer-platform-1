package com.kaltura.code.example;
import java.util.ArrayList;
import java.util.List;
import com.kaltura.client.enums.*;
import com.kaltura.client.types.*;
import com.kaltura.client.services.*;
import com.kaltura.client.APIOkRequestsExecutor;
import com.kaltura.client.Client;
import com.kaltura.client.Configuration;
import com.kaltura.client.services.MediaService;
import com.kaltura.client.services.MediaService.ListMediaBuilder;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.OnCompletion;
import com.kaltura.client.utils.response.base.Response;

class CodeExample {
    public static void main(String[] args) {
        Client client = CodeExample.generateKalturaClient();
        MediaEntryFilter filter = new MediaEntryFilter();
        FilterPager pager = new FilterPager();

        ListMediaBuilder requestBuilder = MediaService.list(filter, pager)
            .setCompletion(new OnCompletion<Response<ListResponse<MediaEntry>>>() {
                @Override
                public void onComplete(Response<ListResponse<MediaEntry>> result) {
                    System.out.println(result);
                }
            });
        APIOkRequestsExecutor.getExecutor().queue(requestBuilder.build(client));
    }

    public static Client generateKalturaClient() {
        Configuration config = new Configuration();
        config.setEndpoint("https://www.kaltura.com/");
        Client client = new Client(config);
        try {
            String session = client.generateSessionV2(
                  "YOUR_KALTURA_SECRET",
                  "YOUR_USER_ID",
                  SessionType.ADMIN,
                  0,
                  86400, "");
            client.setSessionId(session);
        } catch (Exception e) {
            System.out.println("Failed to start Kaltura session");
            System.exit(1);
        }
        return client;
    }
}