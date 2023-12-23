package com.tailorit.util;

import com.tailorit.api.http.resources.request.ColaboradorRequest;
import com.tailorit.api.http.resources.response.ColaboradorResponse;
import com.tailorit.domain.model.Colaborador;

import java.util.Arrays;
import java.util.List;

public class ColaboradorCreator {

    public static Colaborador novoColaborador() {
        return Colaborador.builder()
                .id(1L)
                .nome("nomeTeste")
                .build();
    }

    public static ColaboradorRequest colaboradorRequest() {
        return ColaboradorRequest.builder()
                .nome(novoColaborador().getNome())
                .build();
    }

    public static ColaboradorResponse colaboradorResponse() {
        return ColaboradorResponse.builder()
                .id(novoColaborador().getId())
                .nome(novoColaborador().getNome())
                .build();
    }

    public static List<Colaborador> colaboradorList() {
        Colaborador colaborador1 = novoColaborador();
        Colaborador colaborador2 = novoColaborador();
        colaborador2.setId(2L);

        return Arrays.asList(colaborador1, colaborador2);
    }
}