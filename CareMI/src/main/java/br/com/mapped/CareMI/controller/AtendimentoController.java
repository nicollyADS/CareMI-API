package br.com.mapped.CareMI.controller;
import br.com.mapped.CareMI.dto.AtendimentoDto.AtualizacaoAtendimentoDto;
import br.com.mapped.CareMI.dto.AtendimentoDto.CadastroAtendimentoDto;
import br.com.mapped.CareMI.dto.ExameDto.CadastroExameDto;
import br.com.mapped.CareMI.dto.ExameDto.DetalhesExameDto;
import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.CadastroPacientePlanoSaudeDto;
import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.DetalhesPacientePlanoSaudeDto;
import br.com.mapped.CareMI.model.Atendimento;
import br.com.mapped.CareMI.model.Exame;
import br.com.mapped.CareMI.model.PacientePlanoSaude;
import br.com.mapped.CareMI.repository.AtendimentoRepository;
import br.com.mapped.CareMI.dto.AtendimentoDto.DetalhesAtendimentoDto;
import br.com.mapped.CareMI.repository.ExameRepository;
import br.com.mapped.CareMI.repository.MedicoRepository;
import br.com.mapped.CareMI.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("atendimentos")
public class AtendimentoController {
    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;



    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesAtendimentoDto>> get(Pageable pageable){
        var atendimento = atendimentoRepository.findAll(pageable)
                .stream().map(DetalhesAtendimentoDto::new).toList();
        return ResponseEntity.ok(atendimento);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesAtendimentoDto> get(@PathVariable("id")Long id){
        var atendimento = atendimentoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesAtendimentoDto(atendimento));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesAtendimentoDto> cadastrar(@RequestBody @Valid CadastroAtendimentoDto dto, UriComponentsBuilder builder) {
        var atendimento = new Atendimento(dto);
        var medico = medicoRepository.getReferenceById(dto.idMedico());
        var paciente = pacienteRepository.getReferenceById(dto.idPaciente());

        atendimento.setMedico(medico);
        atendimento.setPaciente(paciente);


        atendimento = atendimentoRepository.save(atendimento);
        var uri = builder.path("atendimentos/{id}").buildAndExpand(atendimento.getIdAtendimento()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesAtendimentoDto(atendimento));
    }


    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        atendimentoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesAtendimentoDto> put(@PathVariable("id")Long id,
                                                   @RequestBody @Valid AtualizacaoAtendimentoDto dto){
        var atendimento = atendimentoRepository.getReferenceById(id);
        atendimento.atualizarInformacoesAtendimento(dto);
        return ResponseEntity.ok(new DetalhesAtendimentoDto(atendimento));
    }

    //relacionamentos

    //POST EXAME
    @PostMapping("{id}/exames")
    @Transactional
    public ResponseEntity<DetalhesExameDto> post(@PathVariable("id") Long id,
                                                 @RequestBody @Valid CadastroExameDto dto,
                                                 UriComponentsBuilder uriBuilder){
        var atendimento = atendimentoRepository.getReferenceById(id);
        var exame = new Exame(dto, atendimento);
        exameRepository.save(exame);
        var uri = uriBuilder.path("exames/{id}").buildAndExpand(exame.getIdExame()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesExameDto(exame));
    }

    //BUSCAR ATENDIMENTOS POR MÉDICO
    @GetMapping("por-medico")
    public ResponseEntity<Page<DetalhesAtendimentoDto>> getMedico(@RequestParam("id-medico") Long id, Pageable pageable){
        var page = atendimentoRepository.findByMedico(id, pageable).map(DetalhesAtendimentoDto::new);
        return ResponseEntity.ok(page);
    }

    //BUSCAR ATENDIMENTOS POR PACIENTE
    @GetMapping("por-paciente")
    public ResponseEntity<Page<DetalhesAtendimentoDto>> getPaciente(@RequestParam("id-paciente") Long id, Pageable pageable){
        var page = atendimentoRepository.findByNome(id, pageable).map(DetalhesAtendimentoDto::new);
        return ResponseEntity.ok(page);
    }

}
