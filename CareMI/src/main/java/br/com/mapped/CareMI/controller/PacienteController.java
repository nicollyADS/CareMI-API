package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.PacienteDto.AtualizacaoPacienteDto;
import br.com.mapped.CareMI.dto.PacienteDto.CadastroPacienteDto;
import br.com.mapped.CareMI.dto.PacienteDto.DetalhesPacienteDto;
import br.com.mapped.CareMI.model.Paciente;
import br.com.mapped.CareMI.repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesPacienteDto>> get(Pageable pageable){
        var paciente =  pacienteRepository.findAll(pageable)
                .stream().map(DetalhesPacienteDto::new).toList();
        return ResponseEntity.ok(paciente);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesPacienteDto> get(@PathVariable("id")Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesPacienteDto(paciente));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesPacienteDto> post(@RequestBody @Valid CadastroPacienteDto pacienteDto,
                                                  UriComponentsBuilder uriBuilder){
        var paciente = new Paciente(pacienteDto);
        pacienteRepository.save(paciente);
        var uri = uriBuilder.path("pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPacienteDto(paciente));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        pacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesPacienteDto> put(@PathVariable("id")Long id,
                                                 @RequestBody @Valid AtualizacaoPacienteDto dto){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.atualizarInformacoesPaciente(dto);
        return ResponseEntity.ok(new DetalhesPacienteDto(paciente));
    }
}
