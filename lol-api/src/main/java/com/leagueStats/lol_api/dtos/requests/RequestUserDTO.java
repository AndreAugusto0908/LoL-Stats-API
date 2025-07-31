package com.leagueStats.lol_api.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para requisição de dados de um jogador de League of Legends.
 *
 * @param nome Nome de invocador (nick) do jogador, ex: "Faker"
 * @param tag Tag do jogador, incluindo a '#', ex: "#BR1"
 */
public record RequestUserDTO(

        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        @NotBlank(message = "A tag não pode estar em branco")
        @Size(max = 6, min = 1, message = "A TAG deve ter de 1 a 6 caracteres")
        String tag

) {}