package be.bstorm.technifutur_java23_demospringsecurity.services;

import be.bstorm.technifutur_java23_demospringsecurity.models.entities.Gamer;
import be.bstorm.technifutur_java23_demospringsecurity.repositories.GamerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GamerServiceImpl implements GamerService, UserDetailsService {

    private final GamerRepository gamerRepository;

    public GamerServiceImpl(GamerRepository gamerRepository) {
        this.gamerRepository = gamerRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return gamerRepository.getGamerByPseudo(username).orElseThrow();
    }

    @Override
    public Gamer login(Gamer gamer) {
        Gamer existingGamer = gamerRepository.getGamerByPseudo(gamer.getPseudo()).orElseThrow();
        if(!existingGamer.getPassword().equals(gamer.getPassword())){
            throw new RuntimeException();
        }
        return existingGamer;
    }

    @Override
    public Page<Gamer> getMany(Integer page, Integer nbElem) {
        return gamerRepository.findAll(PageRequest.of(page,nbElem));
    }
}
