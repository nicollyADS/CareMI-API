package br.com.mapped.CareMI.controller;
import br.com.mapped.CareMI.dto.CidadeDto.CadastroCidadeDto;
import br.com.mapped.CareMI.dto.CidadeDto.DetalhesCidadeDto;
import br.com.mapped.CareMI.dto.EstadoDto.AtualizacaoEstadoDto;
import br.com.mapped.CareMI.dto.EstadoDto.CadastroEstadoDto;
import br.com.mapped.CareMI.dto.EstadoDto.DetalhesEstadoDto;
import br.com.mapped.CareMI.model.Cidade;
import br.com.mapped.CareMI.model.Estado;
import br.com.mapped.CareMI.repository.CidadeRepository;
import br.com.mapped.CareMI.repository.EstadoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@RestController
@RequestMapping("estados")
public class EstadoController {
    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesEstadoDto>> get(Pageable pageable){
        var estado = estadoRepository.findAll(pageable)
                .stream().map(DetalhesEstadoDto::new).toList();
        return ResponseEntity.ok(estado);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesEstadoDto> get(@PathVariable("id")Long id){
        var estado = estadoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesEstadoDto(estado));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesEstadoDto> post(@RequestBody @Valid CadastroEstadoDto estadoDto,
                                                  UriComponentsBuilder uriBuilder){
        var estado = new Estado(estadoDto);
        estadoRepository.save(estado);
        var uri = uriBuilder.path("estados/{id}").buildAndExpand(estado.getIdEstado()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEstadoDto(estado));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        estadoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesEstadoDto> put(@PathVariable("id")Long id,
                                                 @RequestBody @Valid AtualizacaoEstadoDto dto){
        var estado = estadoRepository.getReferenceById(id);
        estado.atualizarInformacoesEstado(dto);
        return ResponseEntity.ok(new DetalhesEstadoDto(estado));
    }

    //relacionamentos

    //POST CIDADE
    @PostMapping("{id}/cidades")
    @Transactional
    public ResponseEntity<DetalhesCidadeDto> post(@PathVariable("id") Long id,
                                                  @RequestBody @Valid CadastroCidadeDto dto,
                                                  UriComponentsBuilder uriBuilder){
        var estado = estadoRepository.getReferenceById(id);
        var cidade = new Cidade(dto, estado);
        cidadeRepository.save(cidade);
        var uri = uriBuilder.path("cidades/{id}").buildAndExpand(cidade.getIdCidade()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesCidadeDto(cidade));
    }
}
