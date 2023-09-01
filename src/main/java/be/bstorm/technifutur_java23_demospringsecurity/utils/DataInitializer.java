package be.bstorm.technifutur_java23_demospringsecurity.utils;

import be.bstorm.technifutur_java23_demospringsecurity.models.entities.Gamer;
import be.bstorm.technifutur_java23_demospringsecurity.models.enums.Role;
import be.bstorm.technifutur_java23_demospringsecurity.repositories.GamerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private GamerRepository gamerRepository;

    public DataInitializer(GamerRepository gamerRepository) {
        this.gamerRepository = gamerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Gamer gamer = new Gamer();
        gamer.setPseudo("Bambino");
        gamer.setPassword("test1234=");
        gamer.setEmail("seb@test.be");
        gamer.setActive(true);
        gamer.setRole(Role.ADMIN);
        gamer.setBirthdate(LocalDate.of(1991,3,27));
        gamerRepository.save(gamer);
        Gamer gamer1 = new Gamer();
        gamer1.setPseudo("Rudolf");
        gamer1.setPassword("test1234=");
        gamer1.setEmail("rudolf@test.be");
        gamer1.setActive(true);
        gamer1.setRole(Role.USER);
        gamer1.setBirthdate(LocalDate.of(1991,3,27));
        gamerRepository.save(gamer1);
    }
}
