package com.tests.api;

import com.tasks.kivork.api.JsonHelper;
import com.tasks.kivork.api.RequestDto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class BaseApi {
    private final static String URL = "http://api.ipstack.com/";
    private final static String JSON_FILE = "src/main/resources/com.testdata/requestdata.json";
    protected final static String IP = "195.136.96.180";
    protected RequestSpecification specification;
    protected Response response;
    private RequestDto requestDto;

    @BeforeMethod
    public void setUp() throws IOException, ParseException {
        requestDto = new RequestDto();
        specification = new RequestSpecBuilder()
                .setBaseUri(URL)
                .addPathParam("ip", IP)
                .addQueryParam("access_key", JsonHelper.getDataFromJson(JSON_FILE, "access_key"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}
