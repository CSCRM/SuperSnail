package com.snail.controller;

import com.aliyuncs.utils.StringUtils;
import com.snail.service.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin  //跨域支持
public class SmsApiController {

    @Autowired
    private SendSms sendSms;

    @Autowired
    private RedisTemplate<String, String> template;

    @GetMapping("/send/{phone}")
    public String send(@PathVariable("phone") String phone){
        String code = template.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return phone +":"+ code +"已存在，还未过期！";
        }

        //生成随机验证码并保存到redis
        code = UUID.randomUUID().toString().substring(0, 4);
        HashMap<String, Object> param = new HashMap<>();
        param.put("code", code);

        boolean bool = sendSms.send(phone, "SMS_176536334", param);
        if (bool){
            template.opsForValue().set(phone, code, 60, TimeUnit.SECONDS);
            return phone +":"+ code +"发送成功";
        }else {
            return "发送失败！";
        }
    }
}
