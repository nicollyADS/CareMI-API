package br.com.mapped.CareMI.controller;
import br.com.mapped.CareMI.dto.PacienteDto.AtualizacaoPacienteDto;
import br.com.mapped.CareMI.dto.PacienteDto.CadastroPacienteDto;
import br.com.mapped.CareMI.dto.PacienteDto.DetalhesPacienteDto;
import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.CadastroPacientePlanoSaudeDto;
import br.com.mapped.CareMI.dto.PacientePlanoSaudeDto.DetalhesPacientePlanoSaudeDto;
import br.com.mapped.CareMI.dto.PlanoSaudeDto.CadastroPlanoSaudeDto;
import br.com.mapped.CareMI.dto.UsuarioDto.CadastroUsuarioDto;
import br.com.mapped.CareMI.dto.UsuarioDto.DetalhesUsuarioDto;
import br.com.mapped.CareMI.model.Paciente;
import br.com.mapped.CareMI.model.PacientePlanoSaude;
import br.com.mapped.CareMI.model.PlanoSaude;
import br.com.mapped.CareMI.model.Usuario;
import br.com.mapped.CareMI.repository.PacientePlanoSaudeRepository;
import br.com.mapped.CareMI.repository.PacienteRepository;
import br.com.mapped.CareMI.repository.PlanoSaudeRepository;
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
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PacientePlanoSaudeRepository pacientePlanoSaudeRepository;

    @Autowired
    private PlanoSaudeRepository planoSaudeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //GET
    @GetMapping
    public ResponseEntity<List<DetalhesPacienteDto>> get(Pageable pageable){
        var paciente =  pacienteRepository.findAll(pageable)
                .stream().map(DetalhesPacienteDto::new).toList();
        return ResponseEntity.ok(paciente);
    }

    //GET BY ID
    @GetMapping("{id}")
    public ResponseEntity<DetalhesPacienteDto> get(@PathVariable("id")Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesPacienteDto(paciente));
    }

    //POST
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesPacienteDto> cadastrar(@RequestBody @Valid CadastroPacienteDto dto, UriComponentsBuilder builder) {
        var paciente = new Paciente(dto);
        var usuario = usuarioRepository.getReferenceById(dto.idUsuario());
        paciente.setUsuario(usuario);
        paciente = pacienteRepository.save(paciente);
        var uri = builder.path("/pacientes/{id}").buildAndExpand(paciente.getIdPaciente()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPacienteDto(paciente));
    }

    //DELETE
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        pacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //PUT
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesPacienteDto> put(@PathVariable("id")Long id,
                                                 @RequestBody @Valid AtualizacaoPacienteDto dto){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.atualizarInformacoesPaciente(dto);
        return ResponseEntity.ok(new DetalhesPacienteDto(paciente));
    }

    //BUSCAR PACIENTE POR NOME
    @GetMapping("por-nome")
    public ResponseEntity<Page<DetalhesPacienteDto>> getNome(@RequestParam("nome") String nome, Pageable pageable){
        var page = pacienteRepository.findByNome(nome, pageable).map(DetalhesPacienteDto::new);
        return ResponseEntity.ok(page);
    }



}
