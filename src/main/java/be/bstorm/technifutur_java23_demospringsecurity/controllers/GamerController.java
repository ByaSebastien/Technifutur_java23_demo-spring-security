package be.bstorm.technifutur_java23_demospringsecurity.controllers;

import be.bstorm.technifutur_java23_demospringsecurity.models.dto.GamerTokenDTO;
import be.bstorm.technifutur_java23_demospringsecurity.models.entities.Gamer;
import be.bstorm.technifutur_java23_demospringsecurity.models.form.GamerLoginForm;
import be.bstorm.technifutur_java23_demospringsecurity.services.GamerService;
import be.bstorm.technifutur_java23_demospringsecurity.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class GamerController {

    private GamerService gamerService;
    private JwtUtil jwtUtil;

    public GamerController(GamerService gamerService,JwtUtil jwtUtil) {
        this.gamerService = gamerService;
        this.jwtUtil = jwtUtil;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @PostMapping("/login")
    public ResponseEntity<GamerTokenDTO> login(
            @RequestBody GamerLoginForm gamerLoginForm
            ){
        Gamer gamer = gamerService.login(gamerLoginForm.toEntity());
        GamerTokenDTO dto = GamerTokenDTO.fromEntity(gamer);
        dto.setToken(jwtUtil.generateToken(gamer));
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/gamer")
    public ResponseEntity<List<Gamer>> getMany(
            @RequestParam(defaultValue = "0",required = false) Integer page,
            @RequestParam(defaultValue = "10",required = false) Integer nbElem
    ){
        List<Gamer> gamers = gamerService.getMany(page,nbElem).toList();
        return ResponseEntity.ok(gamers);
    }
}
