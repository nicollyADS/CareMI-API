package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.AtendimentoDto.AtualizacaoAtendimentoDto;
import br.com.mapped.CareMI.dto.AtendimentoDto.CadastroAtendimentoDto;
import br.com.mapped.CareMI.dto.AtendimentoDto.DetalhesAtendimentoDto;
import br.com.mapped.CareMI.dto.ExameDto.CadastroExameDto;
import br.com.mapped.CareMI.dto.ExameDto.DetalhesExameDto;
import br.com.mapped.CareMI.model.Atendimento;
import br.com.mapped.CareMI.model.Exame;
import br.com.mapped.CareMI.repository.AtendimentoRepository;
import br.com.mapped.CareMI.repository.ExameRepository;
import br.com.mapped.CareMI.repository.MedicoRepository;
import br.com.mapped.CareMI.repository.PacienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("atendimentos")
@Tag(name = "Atendimentos", description = "Operações para gerenciar atendimentos")
public class AtendimentoController {
    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    @Operation(summary = "Lista todos os atendimentos", description = "Retorna uma lista paginada de todos os atendimentos registrados.")
    public ResponseEntity<List<DetalhesAtendimentoDto>> get(Pageable pageable){
        var atendimento = atendimentoRepository.findAll(pageable)
                .stream().map(DetalhesAtendimentoDto::new).toList();
        return ResponseEntity.ok(atendimento);
    }

    @GetMapping("{id}")
    @Operation(summary = "Obtém detalhes do atendimento", description = "Retorna os detalhes de um atendimento específico pelo ID.")
    public ResponseEntity<DetalhesAtendimentoDto> get(@PathVariable("id") @Parameter(description = "ID do atendimento", required = true) Long id){
        var atendimento = atendimentoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesAtendimentoDto(atendimento));
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastra um novo atendimento", description = "Cria um novo atendimento com os dados fornecidos.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Atendimento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<DetalhesAtendimentoDto> cadastrar(@RequestBody @Valid @Parameter(description = "Dados do novo atendimento", required = true) CadastroAtendimentoDto dto, UriComponentsBuilder builder) {
        var atendimento = new Atendimento(dto);
        var medico = medicoRepository.getReferenceById(dto.idMedico());
        var paciente = pacienteRepository.getReferenceById(dto.idPaciente());

        atendimento.setMedico(medico);
        atendimento.setPaciente(paciente);

        atendimento = atendimentoRepository.save(atendimento);
        var uri = builder.path("atendimentos/{id}").buildAndExpand(atendimento.getIdAtendimento()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesAtendimentoDto(atendimento));
    }

    @DeleteMapping("{id}")
    @Transactional
    @Operation(summary = "Remove um atendimento", description = "Deleta um atendimento existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Atendimento deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Atendimento não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable("id") @Parameter(description = "ID do atendimento", required = true) Long id){
        atendimentoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Atualiza um atendimento", description = "Atualiza as informações de um atendimento existente pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atendimento atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Atendimento não encontrado")
    })
    public ResponseEntity<DetalhesAtendimentoDto> put(@PathVariable("id") @Parameter(description = "ID do atendimento", required = true) Long id,
                                                      @RequestBody @Valid @Parameter(description = "Dados para atualização do atendimento", required = true) AtualizacaoAtendimentoDto dto){
        var atendimento = atendimentoRepository.getReferenceById(id);
        atendimento.atualizarInformacoesAtendimento(dto);
        return ResponseEntity.ok(new DetalhesAtendimentoDto(atendimento));
    }

    @PostMapping("{id}/exames")
    @Transactional
    @Operation(summary = "Adiciona um exame ao atendimento", description = "Cadastra um exame associado a um atendimento existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Exame criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Atendimento não encontrado")
    })
    public ResponseEntity<DetalhesExameDto> post(@PathVariable("id") @Parameter(description = "ID do atendimento", required = true) Long id,
                                                 @RequestBody @Valid @Parameter(description = "Dados do exame a ser adicionado", required = true) CadastroExameDto dto,
                                                 UriComponentsBuilder uriBuilder){
        var atendimento = atendimentoRepository.getReferenceById(id);
        var exame = new Exame(dto, atendimento);
        exameRepository.save(exame);
        var uri = uriBuilder.path("exames/{id}").buildAndExpand(exame.getIdExame()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesExameDto(exame));
    }

    @GetMapping("por-medico")
    @Operation(summary = "Busca atendimentos por médico", description = "Retorna uma lista paginada de atendimentos filtrados por médico.")
    public ResponseEntity<Page<DetalhesAtendimentoDto>> getMedico(@RequestParam("id-medico") @Parameter(description = "ID do médico", required = true) Long id, Pageable pageable){
        var page = atendimentoRepository.findByMedico(id, pageable).map(DetalhesAtendimentoDto::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("por-paciente")
    @Operation(summary = "Busca atendimentos por paciente", description = "Retorna uma lista paginada de atendimentos filtrados por paciente.")
    public ResponseEntity<Page<DetalhesAtendimentoDto>> getPaciente(@RequestParam("id-paciente") @Parameter(description = "ID do paciente", required = true) Long id, Pageable pageable){
        var page = atendimentoRepository.findByNome(id, pageable).map(DetalhesAtendimentoDto::new);
        return ResponseEntity.ok(page);
    }
}
