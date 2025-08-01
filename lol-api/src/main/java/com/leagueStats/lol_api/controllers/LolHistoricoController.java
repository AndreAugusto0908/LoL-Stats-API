package com.leagueStats.lol_api.controllers;

import com.leagueStats.lol_api.services.LolHistoricoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("api/historico")
public class LolHistoricoController {

    LolHistoricoService lolHistoricoService;

    public LolHistoricoController(LolHistoricoService lolHistoricoService) {
        this.lolHistoricoService = lolHistoricoService;
    }

    @GetMapping("getHistorico")
    public Mono<List<String>> getHistorico(@RequestParam String puuid, @RequestParam int qtdPartidas) {
        return lolHistoricoService.buscarHistorico(puuid, qtdPartidas);
    }

}
