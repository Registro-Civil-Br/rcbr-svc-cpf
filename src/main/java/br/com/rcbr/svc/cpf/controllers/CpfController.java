package br.com.rcbr.svc.cpf.controllers;

import br.com.rcbr.svc.cpf.models.enums.EstadoEnum;
import br.com.rcbr.svc.cpf.models.responses.CpfResponse;
import br.com.rcbr.svc.cpf.services.CpfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/cpf")
public class CpfController {

    @Autowired
    CpfService cpfService;

    @PostMapping
    public ResponseEntity<CpfResponse> cadastraNascimentoPessoa(@RequestBody EstadoEnum estadoEnum) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cpfService.realizaCriacaoDeCpf(estadoEnum));
    }

}
