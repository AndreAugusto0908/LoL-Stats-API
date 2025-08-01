package com.leagueStats.lol_api.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class LolHistoricoService {

    private final WebClient riotBrClient;
    private final WebClient riotAmericaClient;

    public LolHistoricoService(
            @Qualifier("riotBrClient") WebClient riotBrClient,
            @Qualifier("riotAmericaClient") WebClient riotAmericaClient) {
        this.riotBrClient = riotBrClient;
        this.riotAmericaClient = riotAmericaClient;
    }

    public Mono<List<String>> buscarHistorico(String puuid, int qtdJogos) {
        return riotAmericaClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/lol/match/v5/matches/by-puuid/{puuid}/ids")
                        .queryParam("start", 0)
                        .queryParam("count", qtdJogos)
                        .build(puuid))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException("Erro da Riot API: " + errorBody)))
                )
                .bodyToMono(new ParameterizedTypeReference<List<String>>() {});
    }

    public Mono<Map<String, Object>> buscarDetalhesPartida(String matchId) {
        return riotAmericaClient.get()
                .uri("/lol/match/v5/matches/{matchId}", matchId)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException("Erro da Riot API: " + errorBody)))
                )
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
    }




}
