package com.himeshnayak.hiremenow.service;

import java.io.*;
import com.google.gson.*;
import com.squareup.okhttp.*;

public class TranslateService {
    private static String subscriptionKey = "39e93d911cac4aedaede447f9f4f93ef";

    // Add your location, also known as region. The default is global.
    // This is required if using a Cognitive Services resource.
    private static String location = "southeastasia";

    HttpUrl url = new HttpUrl.Builder()
        .scheme("https")
        .host("api.cognitive.microsofttranslator.com")
        .addPathSegment("/translate")
        .addQueryParameter("api-version", "3.0")
        .addQueryParameter("from", "en")
        .addQueryParameter("to", "hi")
        .build();

    // Instantiates the OkHttpClient.
    OkHttpClient client = new OkHttpClient();

    // This function performs a POST request.
    public String Post(String bodyJsonString) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, bodyJsonString);
        Request request = new Request.Builder().url(url).post(body)
                .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                .addHeader("Ocp-Apim-Subscription-Region", location)
                .addHeader("Content-type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        String returnString = response.body().string();
        response.body().close();
        return prettify(returnString);
    }

    // This function prettifies the json response.
    public static String prettify(String json_text) {
        JsonElement json = JsonParser.parseString(json_text).getAsJsonArray().get(0).getAsJsonObject();
        return json.getAsJsonObject().get("translations").getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString();
    }    
}
