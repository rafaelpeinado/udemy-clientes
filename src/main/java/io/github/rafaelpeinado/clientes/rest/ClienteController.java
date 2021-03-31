package io.github.rafaelpeinado.clientes.rest;

import io.github.rafaelpeinado.clientes.model.entity.Cliente;
import io.github.rafaelpeinado.clientes.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {

    private final ClienteRepository repository;

    // injetando via construtor, pois é mais apropriado
    // já que quando Repository não estiver funcionando,
    // não há porquê instanciar ClienteController
    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Cliente> getAllClientes() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente) {
        return repository.save(cliente);
    }

    @GetMapping("/{id}")
    public Cliente getById(@PathVariable Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    @DeleteMapping("/{id}")
    // não tem nenhum objeto de retorno - resultado de sucesso
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        // é uma opção de delete
        repository.deleteById(id);

        // desta forma dá para saber se ele estava na base de dados
//        repository
//                .findById(id)
//                .map( cliente -> {
//                    repository.delete(cliente);
//                    return Void.TYPE; // não pode retornar null, pois ele cai no Throw
//                })
//                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado) {
        repository
                .findById(id)
                .map( cliente -> {
//                    cliente.setNome(clienteAtualizado.getNome());
//                    cliente.setCpf(clienteAtualizado.getCpf());
                    clienteAtualizado.setId(cliente.getId());
                    return repository.save(clienteAtualizado);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
