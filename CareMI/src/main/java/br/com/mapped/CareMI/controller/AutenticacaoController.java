package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.AutenticacaoDto.DetalhesAutenticacaoDto;
import br.com.mapped.CareMI.dto.AutenticacaoDto.DetalhesTokenDto;
import br.com.mapped.CareMI.model.Usuario;
import br.com.mapped.CareMI.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@Tag(name = "Autenticacao", description = "Operações para gerenciar autenticações")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Operation(summary = "Autentica um usuário e gera um token", description = "Recebe as credenciais de autenticação e retorna um token JWT para o usuário autenticado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Token gerado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public ResponseEntity<DetalhesTokenDto> login(
            @RequestBody @Valid @Parameter(description = "Dados de autenticação do usuário", required = true) DetalhesAutenticacaoDto dto){
        var usuario = new UsernamePasswordAuthenticationToken(dto.cpf(), dto.senha());
        var authenticate = authenticationManager.authenticate(usuario);
        var token = tokenService.gerarToken((Usuario) authenticate.getPrincipal());
        return ResponseEntity.ok(new DetalhesTokenDto(token));
    }
}
