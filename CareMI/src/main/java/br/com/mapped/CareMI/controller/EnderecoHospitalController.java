package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.EnderecoHospitalDto.AtualizacaoEnderecoHospitalDto;
import br.com.mapped.CareMI.dto.EnderecoHospitalDto.CadastroEnderecoHospitalDto;
import br.com.mapped.CareMI.dto.EnderecoHospitalDto.DetalhesEnderecoHospitalDto;
import br.com.mapped.CareMI.model.EnderecoHospital;
import br.com.mapped.CareMI.repository.EnderecoHospitalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("enderecos/hospitais")
public class EnderecoHospitalController {
    @Autowired
    private EnderecoHospitalRepository enderecoHospitalRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesEnderecoHospitalDto>> get(Pageable pageable){
        var enderecoHospital = enderecoHospitalRepository.findAll(pageable)
                .stream().map(DetalhesEnderecoHospitalDto::new).toList();
        return ResponseEntity.ok(enderecoHospital);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesEnderecoHospitalDto> get(@PathVariable("id")Long id){
        var enderecoHospital = enderecoHospitalRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesEnderecoHospitalDto(enderecoHospital));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesEnderecoHospitalDto> post(@RequestBody @Valid CadastroEnderecoHospitalDto enderecoHospitalDto,
                                                       UriComponentsBuilder uriBuilder){
        var enderecoHospital = new EnderecoHospital(enderecoHospitalDto);
        enderecoHospitalRepository.save(enderecoHospital);
        var uri = uriBuilder.path("enderecos/hospitais/{id}").buildAndExpand(enderecoHospital.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEnderecoHospitalDto(enderecoHospital));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        enderecoHospitalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesEnderecoHospitalDto> put(@PathVariable("id")Long id,
                                                      @RequestBody @Valid AtualizacaoEnderecoHospitalDto dto){
        var enderecoHospital = enderecoHospitalRepository.getReferenceById(id);
        enderecoHospital.atualizarInformacoesEnderecoHospital(dto);
        return ResponseEntity.ok(new DetalhesEnderecoHospitalDto(enderecoHospital));
    }
}
