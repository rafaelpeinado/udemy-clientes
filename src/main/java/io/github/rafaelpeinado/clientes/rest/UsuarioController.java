package io.github.rafaelpeinado.clientes.rest;

import io.github.rafaelpeinado.clientes.exception.UsuarioCadastradoException;
import io.github.rafaelpeinado.clientes.model.entity.Usuario;
import io.github.rafaelpeinado.clientes.model.repository.UsuarioRepository;
import io.github.rafaelpeinado.clientes.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario) {
        try {
            usuarioService.save(usuario);
        } catch (UsuarioCadastradoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
