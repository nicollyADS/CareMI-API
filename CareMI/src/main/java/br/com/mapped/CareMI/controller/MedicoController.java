package br.com.mapped.CareMI.controller;
import br.com.mapped.CareMI.dto.MedicoDto.AtualizacaoMedicoDto;
import br.com.mapped.CareMI.dto.MedicoDto.CadastroMedicoDto;
import br.com.mapped.CareMI.dto.MedicoDto.DetalhesMedicoDto;
import br.com.mapped.CareMI.model.Medico;
import br.com.mapped.CareMI.repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesMedicoDto>> get(Pageable pageable){
        var medico = medicoRepository.findAll(pageable)
                .stream().map(DetalhesMedicoDto::new).toList();
        return ResponseEntity.ok(medico);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesMedicoDto> get(@PathVariable("id")Long id){
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesMedicoDto(medico));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesMedicoDto> post(@RequestBody @Valid CadastroMedicoDto medicoDto,
                                                  UriComponentsBuilder uriBuilder){
        var medico = new Medico(medicoDto);
        medicoRepository.save(medico);
        var uri = uriBuilder.path("medicos/{id}").buildAndExpand(medico.getIdMedico()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesMedicoDto(medico));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        medicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesMedicoDto> put(@PathVariable("id")Long id,
                                                 @RequestBody @Valid AtualizacaoMedicoDto dto){
        var medico = medicoRepository.getReferenceById(id);
        medico.atualizarInformacoesMedico(dto);
        return ResponseEntity.ok(new DetalhesMedicoDto(medico));
    }


}
