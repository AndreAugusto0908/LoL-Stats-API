package com.leagueStats.lol_api.dtos.response;

public record UsuarioCompletoDTO(
        String nome,
        String tag,
        String puuid,
        int profileIconId,
        long summonerLevel
) {}