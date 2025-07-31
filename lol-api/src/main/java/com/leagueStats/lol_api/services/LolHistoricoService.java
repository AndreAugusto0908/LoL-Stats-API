package com.leagueStats.lol_api.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;

public class LolHistoricoService {

    private final WebClient riotBrClient;

    public LolHistoricoService(
            @Qualifier("riotBrClient") WebClient riotBrClient) {
        this.riotBrClient = riotBrClient;
    }




}
