package br.com.mapped.CareMI.controller;
import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.AtualizacaoPacientePlanoSaudeDto;
import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.CadastroPacientePlanoSaudeDto;
import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.DetalhesPacientePlanoSaudeDto;
import br.com.mapped.CareMI.model.PacientePlanoSaude;
import br.com.mapped.CareMI.repository.PacientePlanoSaudeRepository;
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
    public ResponseEntity<DetalhesPacientePlanoSaudeDto> post(@RequestBody @Valid CadastroPacientePlanoSaudeDto pacientePlanoSaudeDto,
                                                  UriComponentsBuilder uriBuilder){
        var pacientePlanoSaude = new PacientePlanoSaude(pacientePlanoSaudeDto);
        pacientePlanoSaudeRepository.save(pacientePlanoSaude);
        var uri = uriBuilder.path("pacientes-plano-saude/{id}").buildAndExpand(pacientePlanoSaude.getId()).toUri();
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
