package Services;
import Models.Administrador;
import Models.Aluno;
import Models.Candidatura;
import Models.Eleicao;
import Models.Usuario;
import Models.Visitante;
import Models.Voto;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import org.bson.Document;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoMongo {
    private final MongoClient client;
    private final MongoDatabase database;
    private final MongoCollection<Document> usuariosCollection;
    private final MongoCollection<Document> campusCollection;
    private final MongoCollection<Document> candidaturaCollection;
    private final MongoCollection<Document> eleicaoCollection;
    private final MongoCollection<Document> votoCollection;
  
    public ConexaoMongo() {
        String connectionString = "mongodb://localhost:27017";
        MongoClientURI uri = new MongoClientURI(connectionString);
        client = new MongoClient(uri);
        database = client.getDatabase("Eleicao");
        usuariosCollection = database.getCollection("usuarios");
        campusCollection = database.getCollection("campus");
        candidaturaCollection = database.getCollection("candidatura");
        eleicaoCollection = database.getCollection("eleicao");
        votoCollection = database.getCollection("voto");

    }

    private Document findOne(String login, String senha) {
    Document query = new Document("$or", Arrays.asList(new Document("cpf", login), new Document("senha", senha)));
    return usuariosCollection.find(query).first();
    }
   
   public Usuario fazerLogin(String login, String senha) {
    Document result = findOne(login, senha);
    if (result != null) {
        String storedSenha = result.getString("senha");
        if (storedSenha.equals(senha)) {
            int tipoUsuario = result.getInteger("tipo");
            switch (tipoUsuario) {
                case 0:
                    return new Administrador(result.getInteger("id"), result.getString("nome"), result.getInteger("idade"), result.getString("cpf"), storedSenha, result.getString("codigoadmin"), result.getInteger("campusid"));
                case 1:
                    return new Aluno(result.getInteger("id"), result.getString("nome"), result.getInteger("idade"), result.getString("cpf"), storedSenha, result.getInteger("campusid"), result.getString("matricula"));
                case 3:
                    return new Visitante(result.getInteger("id"), result.getString("nome"), result.getInteger("idade"), result.getString("cpf"), storedSenha, result.getInteger("campusid"));
                default:
                    return null;
            }
        }
    }
    return null;
    }
   
   public boolean criarUsuario(Usuario usuarioNovo, Integer tipoUsuario){
     
        String campoUsuario = "";

        switch(tipoUsuario) {
            case 0:
                campoUsuario = "codigoadmin";
                break;
            case 1:
            case 3:
                campoUsuario = "matricula";
                break;
        }

        Document novoUsuario = new Document()
                .append("nome", usuarioNovo.getNome())
                .append("idade", usuarioNovo.getIdade())
                .append("senha", usuarioNovo.getSenha())
                .append("cpf", usuarioNovo.getCPF())
                .append("campusid", usuarioNovo.getCampusId())
                .append("tipo", tipoUsuario);

        if (tipoUsuario == 1 || tipoUsuario == 3) {
            novoUsuario.append(campoUsuario, usuarioNovo.getLogin());
        }

        usuariosCollection.insertOne(novoUsuario);
        return true;
    }

    public boolean removerUsuario(String cpf) {
        usuariosCollection.deleteOne(Filters.eq("cpf", cpf));
        return true;
    }

   public Boolean editarUsuario(Usuario usuarioEdicao) {
    try {
        Document query = new Document("id", usuarioEdicao.getId());
        Document update = new Document("$set", new Document("nome", usuarioEdicao.getNome())
                                                        .append("idade", usuarioEdicao.getIdade())
                                                        .append("cpf", usuarioEdicao.getCPF()));
        usuariosCollection.updateOne(query, update);
        return true;
    } catch (Exception ex) {
        ex.printStackTrace();
        return false;
    }
}

    public void inserirEleitor(String nome, Integer idade, String cpf, String senha, Integer campusid) {
        int id = getLastId("id", "usuarios") + 1;
        Document doc = new Document("id", id)
                .append("nome", nome)
                .append("idade", idade)
                .append("cpf", cpf)
                .append("senha", senha)
                .append("campusid", campusid)
                .append("tipo", 3);
        usuariosCollection.insertOne(doc);
    }

    public void inserirVoto(int eleitorid, int candidaturaId) {
        Document doc = new Document("eleitorid", eleitorid)
                .append("candidaturaid", candidaturaId);
        votoCollection.insertOne(doc);
    }

    public void atualizarVoto(int eleitorid, int candidaturaId) {
        votoCollection.updateOne(Filters.eq("eleitorid", eleitorid),
                new Document("$set", new Document("candidaturaid", candidaturaId)));
    }

    public int getLastId(String field, String collectionName) {
        MongoCollection<Document> coll = database.getCollection(collectionName);
        FindIterable<Document> iterable = coll.find().sort(Sorts.descending(field)).limit(1);
        if (iterable.iterator().hasNext()) {
            Document result = iterable.iterator().next();
            return result.getInteger(field, 0);
        }
        return 0;
    }

    public void exibirRelatorio() {
        Map<Integer, Map<String, Integer>> relatorio = new TreeMap<>();

        FindIterable<Document> iterable = campusCollection.find();
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            int campusId = doc.getInteger("campusid");
            String nome = doc.getString("nome");

            if (!relatorio.containsKey(campusId)) {
                relatorio.put(campusId, new HashMap<>());
            }

            Map<String, Integer> campusData = relatorio.get(campusId);
            campusData.put(nome, campusData.getOrDefault(nome, 0) + 1);
        }

        for (Map.Entry<Integer, Map<String, Integer>> campusEntry : relatorio.entrySet()) {
            int campusId = campusEntry.getKey();
            System.out.println("Campus ID: " + campusId);

            Map<String, Integer> campusData = campusEntry.getValue();
            for (Map.Entry<String, Integer> dataEntry : campusData.entrySet()) {
                String nome = dataEntry.getKey();
                int quantidade = dataEntry.getValue();
                System.out.println("Nome: " + nome + ", Quantidade de votos: " + quantidade);
            }

            System.out.println("-------------------------");
        }
    }

    public DefaultTableModel getTableModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Idade");
        model.addColumn("CPF");
        model.addColumn("CampusID");
        model.addColumn("Tipo");
        model.addColumn("Código");

        FindIterable<Document> iterable = usuariosCollection.find();
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            Document obj = cursor.next();
            Vector row = new Vector();
            row.add(obj.get("id"));
            row.add(obj.get("nome"));
            row.add(obj.get("idade"));
            row.add(obj.get("cpf"));
            row.add(obj.get("campusid"));
            row.add(obj.get("tipo"));
            if (obj.get("tipo").equals(1) || obj.get("tipo").equals(3)) {
                row.add(obj.get("matricula"));
            } else {
                row.add(obj.get("codigoadmin"));
            }
            model.addRow(row);
        }
        return model;
    }
      
    public String getDescricaoCampus(Integer campusid) {
    try {
        Document query = new Document("campusid", campusid);
        Document result = campusCollection.find(query).first();
        if (result != null) {
            return result.getString("descricao");
        } else {
            return "";
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        return "";
    }
}
    
    public void excluirEleicao(Integer eleicaoid) {
    try {
        // Excluir eleição
        eleicaoCollection.deleteOne(Filters.eq("id", eleicaoid));

        // Excluir votos das candidaturas da eleição
        List<Integer> candidaturaIds = new ArrayList<>();
        FindIterable<Document> candidaturas = candidaturaCollection.find(Filters.eq("eleicaoid", eleicaoid));
        for (Document candidatura : candidaturas) {
            candidaturaIds.add(candidatura.getInteger("id"));
        }
        votoCollection.deleteMany(Filters.in("candidaturaid", candidaturaIds));

        // Excluir candidaturas da eleição
        candidaturaCollection.deleteMany(Filters.eq("eleicaoid", eleicaoid));
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
   
    public void excluirUsuario(Integer usuarioid) {
    try {
        // Excluir usuário
        usuariosCollection.deleteOne(Filters.eq("id", usuarioid));

        // Excluir candidaturas do usuário
        candidaturaCollection.deleteMany(Filters.eq("candidatoid", usuarioid));

        // Excluir votos do usuário
        votoCollection.deleteMany(Filters.eq("eleitorid", usuarioid));
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

    public TreeMap<String, Integer> getComboBoxCampus() {
    try {
        Map<String, Integer> mapCampus = new HashMap<>();

        FindIterable<Document> iterable = campusCollection.find();
        MongoCursor<Document> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();
            String descricao = doc.getString("descricao");
            Integer id = doc.getInteger("id");
            mapCampus.put(descricao, id);
        }

        TreeMap<String, Integer> Campus = new TreeMap<>(mapCampus);
        return Campus;
    } catch (Exception ex) {
        ex.printStackTrace();
        return new TreeMap<>();
    }
}
    
    public DefaultTableModel getTableEleicaoComCampus(Integer campusid) {
    try {
        Document query = new Document("campusid", campusid);

        FindIterable<Document> iterable = eleicaoCollection.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        return buildTableModel(cursor);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return null;
}

public DefaultTableModel getTableUsuariosExcetoUsuarioid(Integer campusId, Integer adminId) {
    try {
        Document query = new Document("campusid", campusId)
                .append("id", new Document("$ne", adminId));

        FindIterable<Document> iterable = usuariosCollection.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        return buildTableModel(cursor);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return null;
}

public DefaultTableModel getTableUsuariosCandidatos(Integer campusid) {
    try {
        Document query = new Document("usuarios.campusid", campusid)
                .append("tipousuario", new Document("$ne", 0))
                .append("usuarios.id", new Document("$exists", true));

        String aggregateQuery = "{$lookup: {from: 'candidatura', localField: 'usuarios.id', foreignField: 'candidatoid', as: 'candidatura'}}";
        List<Document> pipeline = new ArrayList<>();
        pipeline.add(Document.parse(aggregateQuery));

        AggregateIterable<Document> iterable = candidaturaCollection.aggregate(pipeline);
        MongoCursor<Document> cursor = iterable.iterator();

        return buildTableModel(cursor);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return null;
}

public DefaultTableModel getTableCandidatosEleicao(Integer eleicaoid) {
    try {
        Document query = new Document("usuarios.campusid", eleicaoid)
                .append("tipousuario", new Document("$ne", 0))
                .append("usuarios.id", new Document("$exists", true));

        String aggregateQuery = "{$lookup: {from: 'candidatura', localField: 'usuarios.id', foreignField: 'candidatoid', as: 'candidatura'}}";
        List<Document> pipeline = new ArrayList<>();
        pipeline.add(Document.parse(aggregateQuery));

        AggregateIterable<Document> iterable = usuariosCollection.aggregate(pipeline);
        MongoCursor<Document> cursor = iterable.iterator();

        return buildTableModel(cursor);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return null;
}

public DefaultTableModel getTableVotosEleicao(Integer eleicaoid) {
    try {
        Document query = new Document("eleicao.id", eleicaoid);

        String aggregateQuery = "{$lookup: {from: 'candidatura', localField: 'candidatura.CANDIDATOID', foreignField: 'usuarios.id', as: 'candidatura'}}"
                + "{$lookup: {from: 'eleicao', localField: 'candidatura.ELEICAOID', foreignField: 'eleicao.ID', as: 'eleicao'}}"
                + "{$group: {_id: {id: '$usuarios.id', nome: '$usuarios.nome', idade: '$usuarios.idade', codigocandidato: '$candidatura.CODIGOCANDIDATO'}, QTD_VOTOS: {$sum: 1}}}"
                + "{$project: {_id: 0, id: '$_id.id', nome: '$_id.nome', idade: '$_id.idade', codigocandidato: '$_id.codigocandidato', QTD_VOTOS: 1}}";

        List<Document> pipeline = new ArrayList<>();
        pipeline.add(Document.parse(aggregateQuery));

        AggregateIterable<Document> iterable = usuariosCollection.aggregate(pipeline);
        MongoCursor<Document> cursor = iterable.iterator();

        return buildTableModel(cursor);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return null;
}

public DefaultTableModel getTableRegistroVotos(Integer eleicaoid) {
    try {
        Document query = new Document("candidatura.eleicaoid", eleicaoid);

        String aggregateQuery = "{$lookup: {from: 'usuarios', localField: 'eleitorid', foreignField: 'usuarios.id', as: 'eleitor'}}"
                + "{$lookup: {from: 'candidatura', localField: 'candidaturaid', foreignField: 'candidatura.id', as: 'candidatura'}}"
                + "{$lookup: {from: 'usuarios', localField: 'candidatura.candidatoid', foreignField: 'usuarios.id', as: 'candidato'}}"
                + "{$project: {eleitor: '$eleitor.nome', codigovotado: '$candidatura.codigovotado', candidato: '$candidato.nome'}}";

        List<Document> pipeline = new ArrayList<>();
        pipeline.add(Document.parse(aggregateQuery));

        AggregateIterable<Document> iterable = votoCollection.aggregate(pipeline);
        MongoCursor<Document> cursor = iterable.iterator();

        return buildTableModel(cursor);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return null;
}

public TreeMap<String, Integer> getComboBoxUsuariosCandidatos(Integer campusid) {
    try {
        Document query = new Document("usuarios.campusid", campusid)
                .append("tipousuario", new Document("$ne", 0))
                .append("candidatura.candidatoid", new Document("$exists", false));

        FindIterable<Document> iterable = candidaturaCollection.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        Map<String, Integer> mapcandidatos = new HashMap<>();

        while (cursor.hasNext()) {
            Document doc = cursor.next();
            String nome = doc.getString("usuarios.nome");
            Integer id = doc.getInteger("usuarios.id");
            mapcandidatos.put(nome, id);
        }

        TreeMap<String, Integer> Campus = new TreeMap<>(mapcandidatos);
        return Campus;
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return new TreeMap<>();
}

public TreeMap<String, Integer> getComboBoxEleicao(Integer campusid) {
    try {
        Document query = new Document("campusid", campusid);

        FindIterable<Document> iterable = eleicaoCollection.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        Map<String, Integer> mapEleicoes = new HashMap<>();

        while (cursor.hasNext()) {
            Document doc = cursor.next();
            String descricao = doc.getString("descricao");
            Integer id = doc.getInteger("id");
            mapEleicoes.put(descricao, id);
        }

        TreeMap<String, Integer> Eleicoes = new TreeMap<>(mapEleicoes);
        return Eleicoes;
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return new TreeMap<>();
}

public Usuario getUsuario(int usuarioid) {
    try {
        Document query = new Document("id", usuarioid);

        FindIterable<Document> iterable = usuariosCollection.find(query);
        MongoCursor<Document> cursor = iterable.iterator();

        if (cursor.hasNext()) {
            Document doc = cursor.next();
            int id = doc.getInteger("id");
            String nome = doc.getString("nome");
            int idade = doc.getInteger("idade");
            String cpf = doc.getString("cpf");
            return new Aluno(id, nome, idade, cpf, "", 1, "");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return null;
}

public static DefaultTableModel buildTableModel(MongoCursor<Document> cursor) {
    List<Document> documents = new ArrayList<>();
    while (cursor.hasNext()) {
        documents.add(cursor.next());
    }

    if (documents.isEmpty()) {
        return new DefaultTableModel();
    }

    Set<String> columnNames = documents.get(0).keySet();

    // Nome das colunas
    Vector<String> columnNamesVector = new Vector<>(columnNames);

    // Conteúdo do campo
    Vector<Vector<Object>> data = new Vector<>();
    for (Document document : documents) {
        Vector<Object> row = new Vector<>();
        for (String columnName : columnNames) {
            row.add(document.get(columnName));
        }
        data.add(row);
    }

    return new DefaultTableModel(data, columnNamesVector);
}
    public void criarEleicao(Eleicao eleicaoNova) {
        Document eleicaoDocument = new Document()
            .append("descricao", eleicaoNova.getDescricao())
            .append("datainicio", eleicaoNova.getDataInicio())
            .append("datafim", eleicaoNova.getDataFim())
            .append("campusid", eleicaoNova.getCampusid());
        
        eleicaoCollection.insertOne(eleicaoDocument);
    }
    
    public boolean existeCodigoCandidato(Integer codigoCandidato) {
        Document query = new Document("codigocandidato", codigoCandidato);
        Document result = candidaturaCollection.find(query).first();
        
        return result != null;
    }
    
     public void criarCandidatura(Candidatura cUsuario, Integer candidatoid) {
        Document candidatura = new Document();
        candidatura.append("slogan", cUsuario.getSlogan())
                  .append("candidatoid", candidatoid)
                  .append("codigocandidato", cUsuario.getCodigoCandidato())
                  .append("eleicaoid", cUsuario.getEleicao().getId());
        
        candidaturaCollection.insertOne(candidatura);
    }
      public boolean usuarioVotou(Integer eleitorid, Integer eleicaoid) {
        Document query = new Document("eleitorid", eleitorid)
                            .append("eleicaoid", eleicaoid);
        
        Document result = votoCollection.find(query).first();
        
        return result != null;
    }
      
     public Integer getCandidaturaid(Integer codigoCandidato) {
        Document query = new Document("codigocandidato", codigoCandidato);
        
        Document result = candidaturaCollection.find(query).first();
        if (result != null) {
            return result.getInteger("id");
        } else {
            return null;
        }
    }
     
      public DefaultTableModel getTableCampus() {
        List<String> columnNames = new ArrayList<>();
        columnNames.add("ID");
        columnNames.add("Nome");
        columnNames.add("Endereço");
        
        List<Object[]> data = new ArrayList<>();
        
        for (Document document : campusCollection.find()) {
            Integer id = document.getInteger("id");
            String nome = document.getString("nome");
            String endereco = document.getString("endereco");
            
            Object[] row = {id, nome, endereco};
            data.add(row);
        }
        
        return new DefaultTableModel(data.toArray(new Object[0][0]), columnNames.toArray());
    }
      
       public void criarVoto(Voto votousuario) {
        try {
            Document votoDocument = new Document()
                    .append("codigovotado", votousuario.candidatura.getCodigoCandidato())
                    .append("eleitorid", votousuario.eleitor.getId())
                    .append("datavoto", votousuario.datahora)
                    .append("candidaturaid", votousuario.candidatura.getId());
            
            votoCollection.insertOne(votoDocument);
        }catch (MongoException ex) {
        Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}