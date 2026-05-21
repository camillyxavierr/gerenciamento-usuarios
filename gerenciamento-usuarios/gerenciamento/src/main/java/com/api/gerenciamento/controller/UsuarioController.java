package com.api.gerenciamento.controller;

import com.api.gerenciamento.dto.UsuarioDto;
import com.api.gerenciamento.dto.UsuarioRespostaDto;
import com.api.gerenciamento.entity.UsuarioEntity;
import com.api.gerenciamento.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/usuario/{cpf}")
    public ResponseEntity<UsuarioRespostaDto> buscarUsuarioPorCpf(@PathVariable String cpf){
        UsuarioRespostaDto resposta = service.buscarUsuarioPorCpf(cpf);

        if (resposta.getCpf().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }else {
            return ResponseEntity.status(HttpStatus.FOUND).body(resposta);
        }
    }



}