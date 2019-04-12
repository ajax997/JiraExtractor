package com.dxc.JiraExtractor.JiraAPIInteractor;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nguyennghi on 2019-04-12 21:52
 */
public class SendRequestTest {

    @Test
    public void sendRequest() {
        String returns = SendRequest.sendRequest("https://jsonplaceholder.typicode.com/todos/1", RequestType.GET);
        boolean jsonSignal = returns.charAt(0)=='{';
        assertTrue(jsonSignal);
    }

    @Test
    public void sendPOSTRequest() {
        String returns = SendRequest.sendRequest("https://jsonplaceholder.typicode.com/posts/1", RequestType.GET);
        boolean jsonSignal = returns.charAt(0)=='{';
        assertTrue(jsonSignal);
    }
}