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
import org.junit.*;
import server.MainServer;
import java.math.BigDecimal;
import java.util.List;

public class AccountControllerTest {
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
        HttpGet getRequest = new HttpGet("http://localhost:4567/account/1");
        HttpResponse response = httpClient.execute(getRequest);

        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(200, statusCode);
        HttpEntity httpEntity = response.getEntity();
        String apiOutput = EntityUtils.toString(httpEntity);
        Gson gson = new GsonBuilder().serializeNulls().create();

        Account account = gson.fromJson(apiOutput, Account.class);
        Assert.assertNotNull(account);

    }

    @Test
    public void getInvalidAccountTest() throws Exception {
        HttpGet getRequest = new HttpGet("http://localhost:4567/account/11");
        HttpResponse response = httpClient.execute(getRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(404, statusCode);
    }

    @Test
    public void getAccountsTest() throws Exception {
        HttpGet getRequest = new HttpGet("http://localhost:4567/account/all");
        HttpResponse response = httpClient.execute(getRequest);

        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(200, statusCode);
        HttpEntity httpEntity = response.getEntity();
        String apiOutput = EntityUtils.toString(httpEntity);
        Gson gson = new GsonBuilder().serializeNulls().create();
        System.out.println(apiOutput);

        List<Account> accounts = gson.fromJson(apiOutput, new TypeToken<List<Account>>() {
        }.getType());

        Assert.assertNotNull(accounts);

    }

    @Test
    public void getAccountTransfersTest() throws Exception {
        HttpGet getRequest = new HttpGet("http://localhost:4567/account/transfer/1");
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
    public void getAccountTransfersInvalidIdTest() throws Exception {
        HttpGet getRequest = new HttpGet("http://localhost:4567/account/transfer/11");
        HttpResponse response = httpClient.execute(getRequest);

        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(404, statusCode);

    }

    @Test
    public void createValidAccount() throws Exception {
        HttpPost postRequest = new HttpPost("http://localhost:4567/account/add");

        postRequest.addHeader("content-type", "application/json");
        Account account = new Account(new BigDecimal(100), "email@a");
        StringEntity userEntity = new StringEntity(new JsonUtil().render(account));
        postRequest.setEntity(userEntity);

        HttpResponse response = httpClient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(200, statusCode);
    }

    @Test
    public void createInvalidAccount() throws Exception {
        HttpPost postRequest = new HttpPost("http://localhost:4567/account/add");

        postRequest.addHeader("content-type", "application/json");
        Account account = new Account(new BigDecimal(-100), "email@a");
        StringEntity userEntity = new StringEntity(new JsonUtil().render(account));
        postRequest.setEntity(userEntity);

        HttpResponse response = httpClient.execute(postRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(500, statusCode);
    }
}
