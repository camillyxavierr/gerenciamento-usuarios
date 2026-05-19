package com.api.gerenciamento.controller;

import com.api.gerenciamento.dto.UsuarioDto;
import com.api.gerenciamento.dto.UsuarioRespostaDto;
import com.api.gerenciamento.entity.UsuarioEntity;
import com.api.gerenciamento.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api")
public class UsuarioController {

    final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/usuario")
    public ResponseEntity<String> adicionarUsuario(@RequestBody UsuarioDto usuarioDto) {
        boolean retorno = service.adicionar(usuarioDto);
        if (retorno) {
            return ResponseEntity.badRequest().body("Cadastrado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("login já existende!");
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioRespostaDto>> listarUsuarios(){
        return ResponseEntity.ok().body(service.obterUsuarios());
    }



}