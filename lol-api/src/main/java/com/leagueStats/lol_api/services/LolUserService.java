package com.leagueStats.lol_api.services;


import com.leagueStats.lol_api.dtos.response.ResponseUserDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class LolUserService {

    private final WebClient riotWebClient;         // para account/puuid
    private final WebClient riotSummonerClient;    // para summoner profile

    public LolUserService(
            @Qualifier("riotWebClient") WebClient riotWebClient,
            @Qualifier("riotSummonerClient") WebClient riotSummonerClient) {
        this.riotWebClient = riotWebClient;
        this.riotSummonerClient = riotSummonerClient;
    }

    /**
     * Faz uma requisição GET para a Riot API e busca as informações de um jogador
     * com base no gameName (nome) e tagLine (tag).
     *
     * Endpoint utilizado: /riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}
     *
     * A base URL do WebClient deve ser: https://americas.api.riotgames.com
     *
     * Em caso de erro (status 4xx ou 5xx), a mensagem da resposta da Riot é lançada
     * como uma RuntimeException.
     *
     * @param nome o nome do jogador (ex: "Dede de Piranhas")
     * @param tag a tag do jogador (ex: "BR1")
     * @return um Mono com os dados do jogador em formato UserDTO
     */
    public Mono<ResponseUserDTO> buscandoPUUID(String nome, String tag) {
        return riotWebClient.get()
                .uri("/riot/account/v1/accounts/by-riot-id/{nome}/{tag}", nome, tag)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> {
                    return response.bodyToMono(String.class)
                            .flatMap(errorBody -> Mono.error(new RuntimeException("Erro da Riot API: " + errorBody)));
                })
                .bodyToMono(ResponseUserDTO.class);


    }

    public Mono<Map<String, Object>> buscandoPerfilInvocador(String puuid) {
        return riotSummonerClient.get()
                .uri("/lol/summoner/v4/summoners/by-puuid/{puuid}", puuid)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .flatMap(error -> Mono.error(new RuntimeException("Erro da Riot API: " + error)))
                )
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }

}
