package com.pahul.captureanything.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.BasicHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;

import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;

import javax.net.ssl.SSLContext;

@Configuration
public class SSLConfig {

    // Creating to trust all https connections
    // making secure connections where all certificates are trusted automatically, even if they shouldn't be.
    @Bean
    public RestTemplate restTemplate() throws Exception {

        //TrustStrategy is an interface that decides whether a certificate is trusted or not.
        //The code (cert, authType) -> true is a lambda function that always returns true, no matter what certificate it receives.

        final TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;

//        This creates a custom SSLContext, which is a framework in Java used to manage the secure connections (like HTTPS).
//         SSLContexts.custom() is a method from the Apache HttpClient library that helps to build a custom SSL context.
//        .loadTrustMaterial(null, acceptingTrustStrategy)
//
//        This line is loading the "trust material," which is essentially the rules for deciding whether a certificate is trusted.
//        The null parameter means it's not using any default trust material (like the default list of trusted Certificate Authorities).
//        The acceptingTrustStrategy (from your previous line) is the strategy that always trusts all certificates, regardless of their validity.

        final SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();
        final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);


        // to make a connection to https use sslf(defined above)
        //to make a connection to http just use plain socket connection factory
        final Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();

        final BasicHttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager(socketFactoryRegistry);

        //create a Http Client
        final CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();

//Factory encapsulate the creation of HTTPRequest ( not to wory about details)
        final HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(requestFactory);
    }



}
