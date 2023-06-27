package Models;
import java.time.LocalDate;

public class Voto {
    public Integer id;
    public Eleitor eleitor;
    public Candidatura candidatura;
    public LocalDate datahora;

    public Voto(Eleitor eleitor, Candidatura candidatura) {
        this.eleitor = eleitor;
        this.candidatura = candidatura;
        this.datahora = LocalDate.now();
        
    }   

    public Object getCandidatura() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
