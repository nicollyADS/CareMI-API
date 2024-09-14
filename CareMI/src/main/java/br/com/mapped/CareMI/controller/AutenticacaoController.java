package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.AutenticacaoDto.DetalhesAutenticacaoDto;
import br.com.mapped.CareMI.dto.AutenticacaoDto.DetalhesTokenDto;
import br.com.mapped.CareMI.model.Usuario;
import br.com.mapped.CareMI.service.TokenService;
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
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DetalhesTokenDto> login(
            @RequestBody @Valid DetalhesAutenticacaoDto dto){
        var usuario = new UsernamePasswordAuthenticationToken(dto.cpf(), dto.senha());
        var authenticate = authenticationManager.authenticate(usuario);
        var token = tokenService.gerarToken((Usuario) authenticate.getPrincipal());
        return ResponseEntity.ok(new DetalhesTokenDto(token));
    }

}
