package com.github.piomin.springboot.istio.config;

import com.github.piomin.springboot.istio.processor.ApplicationStartupListener;
import com.github.piomin.springboot.istio.processor.EnableIstioAnnotationProcessor;
import com.github.piomin.springboot.istio.service.IstioService;
import me.snowdrop.istio.client.DefaultIstioClient;
import me.snowdrop.istio.client.IstioClient;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootIstioAutoConfiguration {

    @Bean
    IstioClient istioClient() {
        return new DefaultIstioClient();
    }

    @Bean
    IstioService istioService() {
        return new IstioService();
    }

    @Bean
    ApplicationStartupListener listener(ApplicationContext context) {
        return new ApplicationStartupListener(context, istioAnnotationProcessor());
    }

    @Bean
    EnableIstioAnnotationProcessor istioAnnotationProcessor() {
        return new EnableIstioAnnotationProcessor(istioClient(), istioService());
    }

}