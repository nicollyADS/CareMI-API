package br.com.mapped.CareMI.controller;
import br.com.mapped.CareMI.dto.UsuarioDto.AtualizacaoUsuarioDto;
import br.com.mapped.CareMI.dto.UsuarioDto.CadastroUsuarioDto;
import br.com.mapped.CareMI.dto.UsuarioDto.DetalhesUsuarioDto;
import br.com.mapped.CareMI.model.Usuario;
import br.com.mapped.CareMI.repository.CarteirinhaRepository;
import br.com.mapped.CareMI.repository.LoginRepository;
import br.com.mapped.CareMI.repository.LogradouroRepository;
import br.com.mapped.CareMI.repository.UsuarioRepository;
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
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LogradouroRepository logradouroRepository;

    @Autowired
    private LoginRepository loginRepository;


    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesUsuarioDto>> get(Pageable pageable){
        var usuario = usuarioRepository.findAll(pageable)
                .stream().map(DetalhesUsuarioDto::new).toList();
        return ResponseEntity.ok(usuario);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesUsuarioDto> get(@PathVariable("id")Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesUsuarioDto(usuario));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesUsuarioDto> cadastrar(@RequestBody @Valid CadastroUsuarioDto dto, UriComponentsBuilder builder) {
        var usuario = new Usuario(dto);
        var logradouro = logradouroRepository.getReferenceById(dto.idLogradouro());
        usuario.getEnderecoPaciente().setLogradouro(logradouro);
        usuario = usuarioRepository.save(usuario);
        var uri = builder.path("/usuarios/{id}").buildAndExpand(usuario.getIdUsuario()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesUsuarioDto(usuario));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesUsuarioDto> put(@PathVariable("id")Long id,
                                                 @RequestBody @Valid AtualizacaoUsuarioDto dto){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.atualizarInformacoesUsuario(dto);
        return ResponseEntity.ok(new DetalhesUsuarioDto(usuario));
    }


    //BUSCAR USUARIO POR CPF
    @GetMapping("por-cpf")
    public ResponseEntity<Page<DetalhesUsuarioDto>> getCPF(@RequestParam("cpf") String cpf, Pageable pageable){
        var page = usuarioRepository.findByCpf(cpf, pageable).map(DetalhesUsuarioDto::new);
        return ResponseEntity.ok(page);
    }

    //BUSCAR USUARIO POR RG
    @GetMapping("por-rg")
    public ResponseEntity<Page<DetalhesUsuarioDto>> getRG(@RequestParam("rg") String rg, Pageable pageable){
        var page = usuarioRepository.findByRG(rg, pageable).map(DetalhesUsuarioDto::new);
        return ResponseEntity.ok(page);
    }



}
