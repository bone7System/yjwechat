package com.yj.messagequeue;

import com.yj.domain.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by tao.huang on 2017/6/27.
 */
@Service
public class UserReceiverService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

//    @RabbitListener(queues = "user.update")
//    public void receiveUserUpdateQueue(Object message) {
//
//    }



    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "(^(13\\d|15[^4,\\D]|17[13678]|18\\d)\\d{8}|170[^346,\\D]\\d{7})?$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
