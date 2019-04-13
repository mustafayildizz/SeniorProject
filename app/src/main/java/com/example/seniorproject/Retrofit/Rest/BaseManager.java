package com.example.seniorproject.Retrofit.Rest;

public class BaseManager {


    protected RestApi getRestApi() {
        RestApiClient restApiClient = new RestApiClient(BaseUrl.base_url);
        return restApiClient.getRestApi();
    }

    protected RestApi getDarkSky() {
        RestApiClient restApiClient = new RestApiClient(BaseUrl.base_url_dark_sky);
        return  restApiClient.getRestApi();
    }
}
