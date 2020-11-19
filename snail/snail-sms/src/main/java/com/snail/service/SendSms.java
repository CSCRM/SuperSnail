package com.snail.service;

import com.aliyuncs.exceptions.ClientException;

import java.util.Map;

public interface SendSms {

    boolean send(String phone, String templateCode, Map<String, Object> code);
}
