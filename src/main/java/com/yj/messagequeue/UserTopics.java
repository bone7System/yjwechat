package com.yj.messagequeue;

/**
 * Created by tao.huang on 2017/6/27.
 */
public class UserTopics {
    public final static String WECHAT_USER_LIST = "wechat-user.list";
    public final static String QUERY_USER = "query-user";

    public final static String EXCHANGE_PATTERN = "wechat.sync";
    public final static String TOPIC_PATTERN = "wechat.sync.%s";

    public final static String[] ALL = {WECHAT_USER_LIST, QUERY_USER};

    public static String getExchangeName() {
        return EXCHANGE_PATTERN;
    }
    public static String getTopicName(String topic) {
        return String.format(TOPIC_PATTERN, topic);
    }
}
