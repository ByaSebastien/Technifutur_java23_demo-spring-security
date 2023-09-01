package be.bstorm.technifutur_java23_demospringsecurity.repositories;

import be.bstorm.technifutur_java23_demospringsecurity.models.entities.Gamer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GamerRepository extends JpaRepository<Gamer,Long> {
    Optional<Gamer> getGamerByPseudo(String pseudo);
}
