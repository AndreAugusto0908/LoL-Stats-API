package com.leagueStats.lol_api.controllers;

import com.leagueStats.lol_api.dtos.UserDTO;
import com.leagueStats.lol_api.dtos.requests.RequestUserDTO;
import com.leagueStats.lol_api.services.LolUserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/user")
public class LolUserController {

    private LolUserService lolUserService;

    public LolUserController(LolUserService lolUserService) {
        this.lolUserService = lolUserService;
    }

    @GetMapping("getUser")
    public Mono<UserDTO> buscarInvocador(@RequestBody RequestUserDTO data) {
        return lolUserService.buscaInvocadorPeloNome(data.nome(), data.tag());
    }

}
