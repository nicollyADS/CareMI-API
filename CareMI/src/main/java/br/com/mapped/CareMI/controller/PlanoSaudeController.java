package br.com.mapped.CareMI.controller;
import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.CadastroPacientePlanoSaudeDto;
import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.DetalhesPacientePlanoSaudeDto;
import br.com.mapped.CareMI.dto.PlanoSaudeDto.AtualizacaoPlanoSaudeDto;
import br.com.mapped.CareMI.dto.PlanoSaudeDto.CadastroPlanoSaudeDto;
import br.com.mapped.CareMI.dto.PlanoSaudeDto.DetalhesPlanoSaudeDto;
import br.com.mapped.CareMI.model.PacientePlanoSaude;
import br.com.mapped.CareMI.model.PlanoSaude;
import br.com.mapped.CareMI.repository.PacientePlanoSaudeRepository;
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
@RequestMapping("planos-saude")
public class PlanoSaudeController {
    @Autowired
    private PlanoSaudeRepository planoSaudeRepository;


    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesPlanoSaudeDto>> get(Pageable pageable){
        var planoSaude = planoSaudeRepository.findAll(pageable)
                .stream().map(DetalhesPlanoSaudeDto::new).toList();
        return ResponseEntity.ok(planoSaude);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesPlanoSaudeDto> get(@PathVariable("id")Long id){
        var planoSaude = planoSaudeRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesPlanoSaudeDto(planoSaude));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesPlanoSaudeDto> post(@RequestBody @Valid CadastroPlanoSaudeDto planoSaudeDto,
                                                  UriComponentsBuilder uriBuilder){
        var planoSaude = new PlanoSaude(planoSaudeDto);
        planoSaudeRepository.save(planoSaude);
        var uri = uriBuilder.path("planos-saude/{id}").buildAndExpand(planoSaude.getIdPlanoSaude()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPlanoSaudeDto(planoSaude));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        planoSaudeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesPlanoSaudeDto> put(@PathVariable("id")Long id,
                                                 @RequestBody @Valid AtualizacaoPlanoSaudeDto dto){
        var planoSaude = planoSaudeRepository.getReferenceById(id);
        planoSaude.atualizarInformacoesPlanoSaude(dto);
        return ResponseEntity.ok(new DetalhesPlanoSaudeDto(planoSaude));
    }


}
