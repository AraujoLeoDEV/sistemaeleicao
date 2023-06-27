package Models;

import java.util.Date;

public class Eleicao {
    private Integer id;
    private String dataInicio;
    private String dataFim;
    private String descricao;
    private Integer campusid;
    private Integer tipoEleicao;
    
    public Eleicao(String dataHoraInicio, String descricao, String dataHoraFim, Integer campusid) {
        this.dataInicio = dataHoraInicio;
        this.dataFim = dataHoraFim;
        this.descricao = descricao;
        this.campusid = campusid;

    }

    public Eleicao(String toString, String nome, String descricao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Eleicao(String nome, String descricao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getId() {
        return id;
    }

   
    public String getDataInicio() {
        return dataInicio;
    }

 
    public String getDataFim() {
        return dataFim;
    }

  
    public String getDescricao() {
        return descricao;
    }

 
    public Integer getCampusid() {
        return campusid;
    }

   
    public void setId(Integer id) {
        this.id = id;
    }

    public Object getNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
