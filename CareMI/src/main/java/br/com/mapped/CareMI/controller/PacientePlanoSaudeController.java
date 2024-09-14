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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pacientes-planos-saude")
@Tag(name = "Pacientes e Planos de Saúde", description = "Operações para gerenciar a relação entre pacientes e planos de saúde")
public class PacientePlanoSaudeController {
    @Autowired
    private PacientePlanoSaudeRepository pacientePlanoSaudeRepository;

    @Autowired
    private PlanoSaudeRepository planoSaudeRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    //GET
    @GetMapping
    @Operation(summary = "Lista todos os relacionamentos entre pacientes e planos de saúde", description = "Retorna uma lista paginada de todos os relacionamentos entre pacientes e planos de saúde")
    public ResponseEntity<List<DetalhesPacientePlanoSaudeDto>> get(Pageable pageable){
        var pacientePlanoSaude = pacientePlanoSaudeRepository.findAll(pageable)
                .stream().map(DetalhesPacientePlanoSaudeDto::new).toList();
        return ResponseEntity.ok(pacientePlanoSaude);
    }

    //GET BY ID
    @GetMapping("{id}")
    @Operation(summary = "Obtém detalhes do relacionamento entre paciente e plano de saúde pelo ID", description = "Retorna os detalhes de um relacionamento específico entre paciente e plano de saúde pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalhes do relacionamento encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesPacientePlanoSaudeDto.class))),
            @ApiResponse(responseCode = "404", description = "Relacionamento não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do relacionamento entre paciente e plano de saúde", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesPacientePlanoSaudeDto> get(@PathVariable("id") Long id){
        var pacientePlanoSaude = pacientePlanoSaudeRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesPacientePlanoSaudeDto(pacientePlanoSaude));
    }

    //POST
    @PostMapping
    @Transactional
    @Operation(summary = "Cadastra um novo relacionamento entre paciente e plano de saúde", description = "Registra um novo relacionamento entre paciente e plano de saúde")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Relacionamento cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesPacientePlanoSaudeDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
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
    @Operation(summary = "Remove um relacionamento entre paciente e plano de saúde pelo ID", description = "Remove um relacionamento específico entre paciente e plano de saúde pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Relacionamento removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Relacionamento não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do relacionamento entre paciente e plano de saúde", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        pacientePlanoSaudeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Atualiza as informações do relacionamento entre paciente e plano de saúde", description = "Atualiza as informações de um relacionamento específico entre paciente e plano de saúde")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Relacionamento atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesPacientePlanoSaudeDto.class))),
            @ApiResponse(responseCode = "404", description = "Relacionamento não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do relacionamento entre paciente e plano de saúde", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesPacientePlanoSaudeDto> put(@PathVariable("id") Long id,
                                                             @RequestBody @Valid AtualizacaoPacientePlanoSaudeDto dto){
        var pacientePlanoSaude = pacientePlanoSaudeRepository.getReferenceById(id);
        pacientePlanoSaude.atualizarInformacoesPacientePlanoSaude(dto);
        return ResponseEntity.ok(new DetalhesPacientePlanoSaudeDto(pacientePlanoSaude));
    }
}
