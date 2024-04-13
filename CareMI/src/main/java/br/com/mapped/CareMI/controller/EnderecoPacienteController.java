package br.com.mapped.CareMI.controller;
import br.com.mapped.CareMI.dto.EnderecoPacienteDto.AtualizacaoEnderecoPacienteDto;
import br.com.mapped.CareMI.dto.EnderecoPacienteDto.CadastroEnderecoPacienteDto;
import br.com.mapped.CareMI.dto.EnderecoPacienteDto.DetalhesEnderecoPacienteDto;
import br.com.mapped.CareMI.model.EnderecoPaciente;
import br.com.mapped.CareMI.repository.EnderecoPacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("enderecos/pacientes")
public class EnderecoPacienteController {
    @Autowired
    private EnderecoPacienteRepository enderecoPacienteRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesEnderecoPacienteDto>> get(Pageable pageable){
        var enderecoPaciente = enderecoPacienteRepository.findAll(pageable)
                .stream().map(DetalhesEnderecoPacienteDto::new).toList();
        return ResponseEntity.ok(enderecoPaciente);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesEnderecoPacienteDto> get(@PathVariable("id")Long id){
        var enderecoPaciente = enderecoPacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesEnderecoPacienteDto(enderecoPaciente));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesEnderecoPacienteDto> post(@RequestBody @Valid CadastroEnderecoPacienteDto enderecoPacienteDto,
                                                            UriComponentsBuilder uriBuilder){
        var enderecoPaciente = new EnderecoPaciente(enderecoPacienteDto);
        enderecoPacienteRepository.save(enderecoPaciente);
        var uri = uriBuilder.path("enderecos/pacientes/{id}").buildAndExpand(enderecoPaciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEnderecoPacienteDto(enderecoPaciente));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        enderecoPacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesEnderecoPacienteDto> put(@PathVariable("id")Long id,
                                                           @RequestBody @Valid AtualizacaoEnderecoPacienteDto dto){
        var enderecoPaciente = enderecoPacienteRepository.getReferenceById(id);
        enderecoPaciente.atualizarInformacoesEnderecoPaciente(dto);
        return ResponseEntity.ok(new DetalhesEnderecoPacienteDto(enderecoPaciente));
    }
}
