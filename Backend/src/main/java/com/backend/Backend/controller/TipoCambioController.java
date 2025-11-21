package com.backend.Backend.controller;

import com.backend.Backend.service.TipoCambioService;
import dto.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/tipo-cambio")
@CrossOrigin("*")
public class TipoCambioController {

    private final TipoCambioService service;

    public TipoCambioController(TipoCambioService service) {
        this.service = service;
    }

    @GetMapping("/consultar")
    public ResponseEntity<MessageDto> consultar() {
        return ResponseEntity.ok(service.consultarTipoCambio());
    }

    @GetMapping("/historial")
    public ResponseEntity<MessageDto> historial() {
        return ResponseEntity.ok(new MessageDto(true, "Historial cargado", service.listar()));
    }

}
