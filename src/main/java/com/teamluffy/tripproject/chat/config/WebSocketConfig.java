package com.teamluffy.tripproject.chat.config;

import com.teamluffy.tripproject.chat.config.func.ChatErrorHandler;
import com.teamluffy.tripproject.chat.config.func.HttpHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  private final ChatErrorHandler chatErrorHandler;
  private final HttpHandshakeInterceptor httpHandshakeInterceptor;

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws/chat").setAllowedOriginPatterns("*")
        .addInterceptors(httpHandshakeInterceptor).withSockJS();
    registry.setErrorHandler(chatErrorHandler);
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.enableSimpleBroker("/queue", "/sub");

    registry.setApplicationDestinationPrefixes("/pub");
  }
}
