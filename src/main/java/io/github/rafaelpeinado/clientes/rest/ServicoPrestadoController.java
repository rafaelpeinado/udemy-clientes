package io.github.rafaelpeinado.clientes.rest;

import io.github.rafaelpeinado.clientes.model.entity.Cliente;
import io.github.rafaelpeinado.clientes.model.entity.ServicoPrestado;
import io.github.rafaelpeinado.clientes.model.repository.ClienteRepository;
import io.github.rafaelpeinado.clientes.model.repository.ServicoPrestadoRepository;
import io.github.rafaelpeinado.clientes.rest.dto.ServicoPrestadoDTO;
import io.github.rafaelpeinado.clientes.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
@CrossOrigin/*("*")*/
public class ServicoPrestadoController {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository servicoPrestadoRepository;
    private final BigDecimalConverter bigDecimalConverter;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado save(@RequestBody @Valid ServicoPrestadoDTO dto) {
        Cliente cliente =
                clienteRepository
                        .findById(dto.getIdCliente())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus
                                .BAD_REQUEST, "Cliente inexistente"));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));

        return servicoPrestadoRepository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ) {
        return servicoPrestadoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
    }
}
