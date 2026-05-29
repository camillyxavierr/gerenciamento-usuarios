package com.api.gerenciamento.service;

import com.api.gerenciamento.dto.RespostaUsuarioDto;
import com.api.gerenciamento.dto.UsuarioDto;
import com.api.gerenciamento.entity.UsuarioEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private List<UsuarioEntity> listaUsuario = new ArrayList<>();

    public boolean adicionar(UsuarioDto usuarioDto){

        for (UsuarioEntity usuario : listaUsuario){
            //se ja existe, ele retorna para o controller como false
            if (usuario.getLogin().equals(usuarioDto.getLogin())){
                return false;
            }
        }

        UsuarioEntity usuario = new UsuarioEntity();

        usuario.setCpf(usuarioDto.getCpf());
        usuario.setNome(usuarioDto.getNome());
        usuario.setLogin(usuarioDto.getLogin());
        usuario.setSenha(usuarioDto.getSenha());

        listaUsuario.add(usuario);

        return true;

    }

    public List<RespostaUsuarioDto> obterUsuarios(){

        List<RespostaUsuarioDto> lista = new ArrayList<>();

        for (UsuarioEntity usuario : listaUsuario){
            RespostaUsuarioDto usuarios = new RespostaUsuarioDto();

            usuarios.setCpf(usuario.getCpf());
            usuarios.setNome(usuario.getNome());
            usuarios.setLogin(usuario.getLogin());

            lista.add(usuarios);
        }
        return lista;
    }

    public boolean atualizarUsuario(String cpf, UsuarioDto usuarioDto){

        for (UsuarioEntity usuario : listaUsuario){
            if (usuario.getLogin().equals(usuarioDto.getLogin())){
                if (!usuario.getCpf().equals(cpf)) {
                    return false;
                }
            }
        }

        for (UsuarioEntity usuario : listaUsuario){
            if (usuario.getCpf().equals(cpf)) {
                usuario.setCpf(usuarioDto.getCpf());
                usuario.setNome(usuarioDto.getNome());
                usuario.setLogin(usuarioDto.getLogin());
                usuario.setSenha(usuarioDto.getSenha());
                return true;
            }
        }

        return true;
    }

    public RespostaUsuarioDto buscarUsuarioPorCpf(String cpf){

        RespostaUsuarioDto usuarioDto = new RespostaUsuarioDto();
        for (UsuarioEntity usuario : listaUsuario){
            if (usuario.getCpf().equals(cpf)){
                usuarioDto.setNome(usuario.getNome());
                usuarioDto.setCpf(usuario.getCpf());
                usuarioDto.setLogin(usuario.getLogin());
                break;
            }
        }
        return usuarioDto;
    }

    public boolean removerUsuario(String cpf){
        for (UsuarioEntity usuario : listaUsuario){
            if (usuario.getCpf().equals(cpf)){
                listaUsuario.remove(usuario);
                return true;
            }
        }
        return false;
    }
}
