package br.com.mapped.CareMI.controller;
import br.com.mapped.CareMI.dto.PacienteDto.CadastroPacienteDto;
import br.com.mapped.CareMI.dto.PacienteDto.DetalhesPacienteDto;
import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.AtualizacaoPacientePlanoSaudeDto;
import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.CadastroPacientePlanoSaudeDto;
import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.DetalhesPacientePlanoSaudeDto;
import br.com.mapped.CareMI.model.Paciente;
import br.com.mapped.CareMI.model.PacientePlanoSaude;
import br.com.mapped.CareMI.repository.PacientePlanoSaudeRepository;
import br.com.mapped.CareMI.repository.PacienteRepository;
import br.com.mapped.CareMI.repository.PlanoSaudeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("pacientes-planos-saude")
public class PacientePlanoSaudeController {
    @Autowired
    private PacientePlanoSaudeRepository pacientePlanoSaudeRepository;

    @Autowired
    private PlanoSaudeRepository planoSaudeRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesPacientePlanoSaudeDto>> get(Pageable pageable){
        var pacientePlanoSaude = pacientePlanoSaudeRepository.findAll(pageable)
                .stream().map(DetalhesPacientePlanoSaudeDto::new).toList();
        return ResponseEntity.ok(pacientePlanoSaude);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesPacientePlanoSaudeDto> get(@PathVariable("id")Long id){
        var pacientePlanoSaude = pacientePlanoSaudeRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesPacientePlanoSaudeDto(pacientePlanoSaude));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesPacientePlanoSaudeDto> cadastrar(@RequestBody @Valid CadastroPacientePlanoSaudeDto dto, UriComponentsBuilder builder) {
        var pacientePlanoSaude = new PacientePlanoSaude(dto);
        var planoSaude = planoSaudeRepository.getReferenceById(dto.idPlanoSaude());
        var paciente = pacienteRepository.getReferenceById(dto.idPaciente());

        pacientePlanoSaude.setPlanoSaude(planoSaude);
        pacientePlanoSaude.setPaciente(paciente);


        pacientePlanoSaude = pacientePlanoSaudeRepository.save(pacientePlanoSaude);
        var uri = builder.path("pacientes-planos-saude/{id}").buildAndExpand(pacientePlanoSaude.getIdPacientePlanoSaude()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPacientePlanoSaudeDto(pacientePlanoSaude));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        pacientePlanoSaudeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesPacientePlanoSaudeDto> put(@PathVariable("id")Long id,
                                                 @RequestBody @Valid AtualizacaoPacientePlanoSaudeDto dto){
        var pacientePlanoSaude = pacientePlanoSaudeRepository.getReferenceById(id);
        pacientePlanoSaude.atualizarInformacoesPacientePlanoSaude(dto);
        return ResponseEntity.ok(new DetalhesPacientePlanoSaudeDto(pacientePlanoSaude));
    }

}
