package com.api.gerenciamento.controller;

import com.api.gerenciamento.dto.RespostaUsuarioDto;
import com.api.gerenciamento.dto.UsuarioDto;
import com.api.gerenciamento.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
            return ResponseEntity.ok().body("Cadastrado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("login já existende!");
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<RespostaUsuarioDto>> listarUsuarios(){
        return ResponseEntity.ok().body(service.obterUsuarios());
    }

    //Não esta funcionando!
    @GetMapping("/usuarios/{cpf}")
    public ResponseEntity<RespostaUsuarioDto> buscarUsuarioPorCpf(@PathVariable String cpf){
        RespostaUsuarioDto resposta = service.buscarUsuarioPorCpf(cpf);

        if (resposta.getCpf().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }else {
            return ResponseEntity.status(HttpStatus.FOUND).body(resposta);
        }
    }

    @PutMapping("/usuario/{cpf}")
    public ResponseEntity<String> atualizarUsuario(@RequestBody UsuarioDto usuarioDto, @PathVariable String cpf){
        boolean retorno = service.atualizarUsuario(cpf, usuarioDto);

        if (retorno) {
            return ResponseEntity.ok().body("Atualizado com sucesso");
        } else {
            return ResponseEntity.badRequest().body("Já existe este login cadastrado!");
        }

    }

    @DeleteMapping("/usuario/{cpf}")
    public ResponseEntity<String> removerUsuario(@PathVariable String cpf){
        boolean retorno = service.removerUsuario(cpf);
        if (retorno){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Exclusão com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
    }
}