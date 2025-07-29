package com.leagueStats.lol_api.controllers;

import com.leagueStats.lol_api.dtos.requests.RequestUserDTO;
import com.leagueStats.lol_api.dtos.response.UsuarioCompletoDTO;
import com.leagueStats.lol_api.services.LolUserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("api/user")
public class LolUserController {

    private LolUserService lolUserService;

    public LolUserController(LolUserService lolUserService) {
        this.lolUserService = lolUserService;
    }

    @GetMapping("getUser")
    public Mono<UsuarioCompletoDTO> buscarInvocador(@RequestBody RequestUserDTO data) {
        return lolUserService.buscandoPUUID(data.nome(), data.tag())
                .flatMap(user -> lolUserService.buscandoPerfilInvocador(user.puuid())
                        .map(perfil -> {
                            int profileIconId = (int) perfil.get("profileIconId");
                            long summonerLevel = ((Number) perfil.get("summonerLevel")).longValue();

                            return new UsuarioCompletoDTO(
                                    user.gameName(),
                                    user.tagLine(),
                                    user.puuid(),
                                    profileIconId,
                                    summonerLevel
                            );
                        }));
    }

}
