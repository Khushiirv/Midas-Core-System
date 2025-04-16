package com.jpmc.midascore.service.impl;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Incentive postTransaction(Transaction transaction) {
        String url = "http://localhost:8080/incentive";
        return restTemplate.postForObject(url, transaction, Incentive.class);
    }
}