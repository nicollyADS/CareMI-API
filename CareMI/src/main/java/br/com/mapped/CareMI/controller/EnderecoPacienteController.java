package br.com.mapped.CareMI.controller;

import br.com.mapped.CareMI.dto.AtendimentoDto.AtualizacaoAtendimentoDto;
import br.com.mapped.CareMI.dto.AtendimentoDto.CadastroAtendimentoDto;
import br.com.mapped.CareMI.dto.AtendimentoDto.DetalhesAtendimentoDto;
import br.com.mapped.CareMI.dto.EnderecoPacienteDto.AtualizacaoEnderecoPacienteDto;
import br.com.mapped.CareMI.dto.EnderecoPacienteDto.CadastroEnderecoPacienteDto;
import br.com.mapped.CareMI.dto.EnderecoPacienteDto.DetalhesEnderecoPacienteDto;
import br.com.mapped.CareMI.model.Atendimento;
import br.com.mapped.CareMI.model.EnderecoPaciente;
import br.com.mapped.CareMI.model.Usuario;
import br.com.mapped.CareMI.repository.EnderecoPacienteRepository;
import br.com.mapped.CareMI.repository.LogradouroRepository;
import br.com.mapped.CareMI.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("enderecos-pacientes")
public class EnderecoPacienteController {

    @Autowired
    private EnderecoPacienteRepository enderecoPacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LogradouroRepository logradouroRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesEnderecoPacienteDto>> get(Pageable pageable){
        var enderecoPac = enderecoPacienteRepository.findAll(pageable)
                .stream().map(DetalhesEnderecoPacienteDto::new).toList();
        return ResponseEntity.ok(enderecoPac);
    }

    //GEY BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesEnderecoPacienteDto> get(@PathVariable("id")Long id){
        var enderecoPac = enderecoPacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesEnderecoPacienteDto(enderecoPac));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesEnderecoPacienteDto> cadastrar(@RequestBody @Valid CadastroEnderecoPacienteDto dto, UriComponentsBuilder builder) {
        var enderecoPac = new EnderecoPaciente(dto);
        var logradouro = logradouroRepository.getReferenceById(dto.idLogradouro());
        var usuario = usuarioRepository.getReferenceById(dto.idUsuario());

        enderecoPac.setLogradouro(logradouro);
        enderecoPac.setUsuario(usuario);

        enderecoPac = enderecoPacienteRepository.save(enderecoPac);
        var uri = builder.path("enderecos-pacientes/{id}").buildAndExpand(enderecoPac.getIdEnderecoPaciente()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEnderecoPacienteDto(enderecoPac));
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesEnderecoPacienteDto> put(@PathVariable("id")Long id,
                                                      @RequestBody @Valid AtualizacaoEnderecoPacienteDto dto){
        var enderecoPac = enderecoPacienteRepository.getReferenceById(id);
        enderecoPac.atualizarInformacoesEnderecoPaciente(dto);
        return ResponseEntity.ok(new DetalhesEnderecoPacienteDto(enderecoPac));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        enderecoPacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
