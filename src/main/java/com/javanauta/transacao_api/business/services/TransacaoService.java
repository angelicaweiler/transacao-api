package com.javanauta.transacao_api.business.services;

import com.javanauta.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.javanauta.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacoes(TransacaoRequestDTO dto){

        log.info("Iniciado o processamento de gravar transações " + dto);

        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data e hora maiores que a data atual");
            throw new UnprocessableEntity("Data e hora maiores que a data e hora atuais");
        }
        if(dto.valor() < 0){
            log.error("Valor não pode ser menor que 0");
            throw new UnprocessableEntity("Valor não pode ser menor que 0");
        }

        listaTransacoes.add(dto);
        log.info("Transacoes adicionadas com sucesso");
    }

    public void limparTransacoes(){
        log.info("Iniciado processamento para deletar transações");
        listaTransacoes.clear();
        log.info("Transações deletadas com sucesso");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca){
        log.info("Inicadas buscas de transações por tempo " + intervaloBusca);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("Retorno de transações com sucesso");
        return listaTransacoes.stream()
                .filter(transacao -> transacao.dataHora()
                        .isAfter(dataHoraIntervalo)).toList();

    }

}
