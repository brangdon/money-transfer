package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import json.JsonUtil;
import model.Account;
import model.Transfer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.MainServer;

import java.math.BigDecimal;
import java.util.List;

public class TransferControllerTest {
    private MainServer mainServer;
    private DefaultHttpClient httpClient;

    @Before
    public void setUpAll() throws Exception {
        mainServer = new MainServer();
        mainServer.run();
        httpClient = new DefaultHttpClient();
    }

    @After
    public void tearDownAll() {
        httpClient.getConnectionManager().shutdown();
    }

    @Test
    public void getAccountTest() throws Exception {
        HttpGet getRequest = new HttpGet("http://localhost:4567/transfer/1");
        HttpResponse response = httpClient.execute(getRequest);

        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(200, statusCode);
        HttpEntity httpEntity = response.getEntity();
        String apiOutput = EntityUtils.toString(httpEntity);
        Gson gson = new GsonBuilder().serializeNulls().create();

        Transfer transfer = gson.fromJson(apiOutput, Transfer.class);
        Assert.assertNotNull(transfer);

    }

    @Test
    public void getInvalidAccountTest() throws Exception {
        HttpGet getRequest = new HttpGet("http://localhost:4567/transfer/11");
        HttpResponse response = httpClient.execute(getRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(404, statusCode);
    }

    @Test
    public void getAllTransfersTest() throws Exception{
        HttpGet getRequest = new HttpGet("http://localhost:4567/transfer/all");
        HttpResponse response = httpClient.execute(getRequest);

        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(200, statusCode);
        HttpEntity httpEntity = response.getEntity();
        String apiOutput = EntityUtils.toString(httpEntity);
        Gson gson = new GsonBuilder().serializeNulls().create();
        System.out.println(apiOutput);

        List<Transfer> transfers = gson.fromJson(apiOutput, new TypeToken<List<Transfer>>() {
        }.getType());

        Assert.assertNotNull(transfers);
    }

    @Test
    public void createValidTransferTest() throws Exception {
        HttpPost postRequest = new HttpPost("http://localhost:4567/transfer");

        postRequest.addHeader("content-type", "application/json");
        Transfer transfer = new Transfer(1,2,new BigDecimal(100));
        StringEntity userEntity = new StringEntity(new JsonUtil().render(transfer));
        postRequest.setEntity(userEntity);

        HttpResponse response = httpClient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(200, statusCode);
    }

    @Test
    public void createInvalidTransferScenario1Test() throws Exception {
        HttpPost postRequest = new HttpPost("http://localhost:4567/transfer");

        postRequest.addHeader("content-type", "application/json");
        Transfer transfer = new Transfer(1,1,new BigDecimal(100));
        StringEntity userEntity = new StringEntity(new JsonUtil().render(transfer));
        postRequest.setEntity(userEntity);

        HttpResponse response = httpClient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(404, statusCode);
    }

    @Test
    public void createInvalidTransferScenario2Test() throws Exception {
        HttpPost postRequest = new HttpPost("http://localhost:4567/transfer");

        postRequest.addHeader("content-type", "application/json");
        Transfer transfer = new Transfer(1,2,new BigDecimal(-100));
        StringEntity userEntity = new StringEntity(new JsonUtil().render(transfer));
        postRequest.setEntity(userEntity);

        HttpResponse response = httpClient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(404, statusCode);
    }

    @Test
    public void createInvalidTransferScenario3Test() throws Exception {
        HttpPost postRequest = new HttpPost("http://localhost:4567/transfer");

        postRequest.addHeader("content-type", "application/json");
        Transfer transfer = new Transfer(1,2,new BigDecimal(10000));
        StringEntity userEntity = new StringEntity(new JsonUtil().render(transfer));
        postRequest.setEntity(userEntity);

        HttpResponse response = httpClient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(500, statusCode);
    }
}
