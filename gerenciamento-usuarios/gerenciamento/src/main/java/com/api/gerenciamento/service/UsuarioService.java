package com.api.gerenciamento.service;

import com.api.gerenciamento.dto.UsuarioDto;
import com.api.gerenciamento.dto.UsuarioRespostaDto;
import com.api.gerenciamento.entity.UsuarioEntity;
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

    public List<UsuarioRespostaDto> obterUsuarios(){

        List<UsuarioRespostaDto> lista = new ArrayList<>();

        for (UsuarioEntity usuario : listaUsuario){
            UsuarioRespostaDto listaDeUsuario = new UsuarioRespostaDto();

            listaDeUsuario.setCpf(usuario.getCpf());
            listaDeUsuario.setNome(usuario.getNome());
            listaDeUsuario.setLogin(usuario.getLogin());

            lista.add(listaDeUsuario);
        }
        return lista;
    }
}
