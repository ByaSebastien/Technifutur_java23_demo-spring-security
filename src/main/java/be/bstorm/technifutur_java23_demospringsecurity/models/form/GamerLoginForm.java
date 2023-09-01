package be.bstorm.technifutur_java23_demospringsecurity.models.form;

import be.bstorm.technifutur_java23_demospringsecurity.models.entities.Gamer;
import lombok.Data;

@Data
public class GamerLoginForm {

    private String pseudo;
    private String password;

    public Gamer toEntity(){
        Gamer gamer = new Gamer();
        gamer.setPseudo(this.pseudo);
        gamer.setPassword(this.password);
        return gamer;
    }
}
