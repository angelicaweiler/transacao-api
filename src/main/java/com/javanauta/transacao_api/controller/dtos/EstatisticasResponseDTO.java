package com.javanauta.transacao_api.controller.dtos;

public record EstatisticasResponseDTO(Long count,
                                      Double sum,
                                      Double avg,
                                      Double min,
                                      Double max) {

}
