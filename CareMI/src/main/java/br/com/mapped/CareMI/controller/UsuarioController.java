package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.UsuarioDto.AtualizacaoUsuarioDto;
import br.com.mapped.CareMI.dto.UsuarioDto.CadastroUsuarioDto;
import br.com.mapped.CareMI.dto.UsuarioDto.DetalhesUsuarioDto;
import br.com.mapped.CareMI.model.Usuario;
import br.com.mapped.CareMI.repository.LogradouroRepository;
import br.com.mapped.CareMI.repository.UsuarioRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("usuarios")
@Tag(name = "Usuários", description = "Operações para gerenciar usuários")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LogradouroRepository logradouroRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //GET
    @GetMapping
    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista paginada de todos os usuários")
    public ResponseEntity<List<DetalhesUsuarioDto>> get(Pageable pageable){
        var usuario = usuarioRepository.findAll(pageable)
                .stream().map(DetalhesUsuarioDto::new).toList();
        return ResponseEntity.ok(usuario);
    }

    //GET BY ID
    @GetMapping("{id}")
    @Operation(summary = "Obtém detalhes do usuário pelo ID", description = "Retorna os detalhes de um usuário específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detalhes do usuário encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesUsuarioDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do usuário", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesUsuarioDto> get(@PathVariable("id") Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesUsuarioDto(usuario));
    }

    //POST
    @PostMapping("register")
    @Transactional
    @Operation(summary = "Registra um novo usuário", description = "Cadastra um novo usuário no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesUsuarioDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<DetalhesUsuarioDto> post(@RequestBody @Valid CadastroUsuarioDto dto,
                                                   UriComponentsBuilder uri){
        var usuario = new Usuario(dto);
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuarioRepository.save(usuario);
        var uriBuilder = uri.path("usuarios/{id}").buildAndExpand(usuario.getIdUsuario()).toUri();
        return ResponseEntity.created(uriBuilder).body(new DetalhesUsuarioDto(usuario));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    @Operation(summary = "Remove um usuário pelo ID", description = "Remove um usuário específico pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do usuário", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Atualiza as informações de um usuário", description = "Atualiza as informações de um usuário específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesUsuarioDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID do usuário", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<DetalhesUsuarioDto> put(@PathVariable("id") Long id,
                                                  @RequestBody @Valid AtualizacaoUsuarioDto dto){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.atualizarInformacoesUsuario(dto);
        return ResponseEntity.ok(new DetalhesUsuarioDto(usuario));
    }

    //BUSCAR USUARIO POR RG
    @GetMapping("por-rg")
    @Operation(summary = "Busca usuário pelo RG", description = "Busca um usuário com base no RG fornecido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesUsuarioDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @Parameters({
            @Parameter(name = "rg", description = "RG do usuário", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "pageable", description = "Parâmetros de paginação", in = ParameterIn.QUERY)
    })
    public ResponseEntity<Page<DetalhesUsuarioDto>> getRG(@RequestParam("rg") String rg, Pageable pageable){
        var page = usuarioRepository.findByRG(rg, pageable).map(DetalhesUsuarioDto::new);
        return ResponseEntity.ok(page);
    }
}
