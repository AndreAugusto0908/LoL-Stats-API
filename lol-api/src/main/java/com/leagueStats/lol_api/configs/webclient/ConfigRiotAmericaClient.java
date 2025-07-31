package com.leagueStats.lol_api.configs.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Configuration
public class ConfigRiotAmericaClient {

    @Value("${riot.api.key}")
    private String apiKey;

    /**
     * Cria e configura um bean do {@link WebClient} para comunicação com a API da Riot Games.
     *
     * @return uma instância configurada de {@link WebClient}
     */
    @Bean
    public WebClient riotAmericaClient() {
        return WebClient
                .builder()
                .baseUrl("https://americas.api.riotgames.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(addApiKeyHeader())
                .build();
    }

    /**
     * Cria um {@link ExchangeFilterFunction} que adiciona automaticamente o header
     * {@code X-Riot-Token} com a chave da API da Riot em todas as requisições HTTP.
     *
     * @return um filtro que insere o token da Riot em cada requisição
     */
    private ExchangeFilterFunction addApiKeyHeader() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            ClientRequest newRequest = ClientRequest.from(clientRequest)
                    .header("X-Riot-Token", apiKey)
                    .build();
            return Mono.just(newRequest);
        });
    }

}
