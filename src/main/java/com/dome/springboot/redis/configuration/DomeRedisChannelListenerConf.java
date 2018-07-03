package com.dome.springboot.redis.configuration;


import com.dome.springboot.redis.listener.DomeRedisPubSubChanneListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

@Configuration
public class DomeRedisChannelListenerConf {
    @Bean
    MessageListenerAdapter listenerAdapter( ) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(new DomeRedisPubSubChanneListener());
        adapter.setSerializer(new JdkSerializationRedisSerializer());
        return adapter;

    }
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅所有news.* 频道内容
        container.addMessageListener(listenerAdapter, new PatternTopic("news*"));
        return container;
    }
}
