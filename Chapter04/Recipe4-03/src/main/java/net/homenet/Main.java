package net.homenet;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //# 1.
        //final String uri = "http://localhost:8080/members.xml";
        //RestTemplate restTemplate = new RestTemplate();
        //String result = restTemplate.getForObject(uri, String.class);
        //System.out.println(result);

        //# 2.
        //final String uri = "http://localhost:8080/member/{memberId}";
        //Map<String, String> params = new HashMap<>();
        //params.put("memberId", "1");
        //
        //RestTemplate restTemplate = new RestTemplate();
        //String result = restTemplate.getForObject(uri, String.class, params);
        //System.out.println(result);

        //# 3.
        final String uri = "http://localhost:8080/members.xml";
        RestTemplate restTemplate = new RestTemplate();
        Members members = restTemplate.getForObject(uri, Members.class);
        System.out.println(members);
    }
}
