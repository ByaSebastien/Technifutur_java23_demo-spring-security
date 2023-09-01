package be.bstorm.technifutur_java23_demospringsecurity.services;

import be.bstorm.technifutur_java23_demospringsecurity.models.entities.Gamer;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


public interface GamerService extends UserDetailsService {

    Gamer login(Gamer gamer);
    Page<Gamer> getMany(Integer page,Integer nbElem);
}
