package com.api.gerenciamento.controller;

import com.api.gerenciamento.dto.LoginDto;
import com.api.gerenciamento.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    final UsuarioService service;

    public LoginController(UsuarioService service) {
        this.service = service;
    }


    @PostMapping("/login")
    public ResponseEntity<String> logar(@RequestBody LoginDto loginDto){
        if (loginDto.getLogin().isEmpty() && loginDto.getSenha().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login/senha obrigatório.");
        }

        boolean retorno = service.logar(loginDto);

        if (retorno){
            return ResponseEntity.status(HttpStatus.OK).body("Login realizado com sucesso.");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao realizar login: credenciais inválidas.");
        }
    }


}
