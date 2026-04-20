package com.tapsilat;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
public class CheckType {
    public static void main(String[] args) {
        System.out.println("Is interface: " + CloseableHttpResponse.class.isInterface());
        System.out.println("Resource: " + CloseableHttpResponse.class.getResource("CloseableHttpResponse.class"));
    }
}
