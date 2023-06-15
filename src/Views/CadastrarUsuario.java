/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.Administrador;
import Models.Aluno;
import Models.Usuario;
import Models.Visitante;
import Services.Conexao;
import javax.swing.JOptionPane;

/**
 *
 * @author Altimar
 */
public class CadastrarUsuario extends javax.swing.JInternalFrame {

    private Integer campusid;
    private Integer tipousuario;
    public boolean edicao = false;
    private Usuario usuarioEdicao;
    public CadastrarUsuario() {
        initComponents();
    }
    
    public void setUsuarioEdicao(Usuario usuarioEdicao){
        edicao = true;
        edtNomeUsuario.setText(usuarioEdicao.getNome());
        spnIdade.setValue(usuarioEdicao.getIdade());
        edtCPFUsuario.setText(usuarioEdicao.getCPF());
        
        this.usuarioEdicao = usuarioEdicao;
        
        btnCriarUsuario.setText("Salvar Edição");
        lblCriandoNovoUsuario.setVisible(false);
        this.title = "Editar usuario";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        brgPrincipal = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblCriandoNovoUsuario = new javax.swing.JLabel();
        btnCriarUsuario = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        edtNomeUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        edtCPFUsuario = new javax.swing.JTextField();
        edtSenhaUsuario = new javax.swing.JTextField();
        lblSenha = new javax.swing.JLabel();
        spnIdade = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        lblSenha1 = new javax.swing.JLabel();
        rdbAluno = new javax.swing.JRadioButton();
        rdbExterno = new javax.swing.JRadioButton();
        rdbAdministrador = new javax.swing.JRadioButton();
        lblUsuarioExtra = new javax.swing.JLabel();
        edtCampoExtraUsuario = new javax.swing.JTextField();

        setClosable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 51, 204));
        jPanel1.setToolTipText("");

        lblCriandoNovoUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCriandoNovoUsuario.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));

        btnCriarUsuario.setText("Criar usuario");
        btnCriarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCriarUsuarioMouseClicked(evt);
            }
        });
        btnCriarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarUsuarioActionPerformed(evt);
            }
        });
        btnCriarUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCriarUsuarioKeyPressed(evt);
            }
        });

        jLabel1.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        jLabel1.setText("Nome:");

        jLabel2.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        jLabel2.setText("CPF:");

        lblSenha.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        lblSenha.setText("Senha:");

        spnIdade.setModel(new javax.swing.SpinnerNumberModel(1, 1, 110, 1));

        jLabel4.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        jLabel4.setText("Idade:");

        lblSenha1.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        lblSenha1.setText("Selecione o tipo de usuario desejado:");

        rdbAluno.setBackground(new java.awt.Color(0, 51, 204));
        brgPrincipal.add(rdbAluno);
        rdbAluno.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        rdbAluno.setSelected(true);
        rdbAluno.setText("Aluno");
        rdbAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbAlunoActionPerformed(evt);
            }
        });

        rdbExterno.setBackground(new java.awt.Color(0, 51, 204));
        brgPrincipal.add(rdbExterno);
        rdbExterno.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        rdbExterno.setText("Externo");
        rdbExterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbExternoActionPerformed(evt);
            }
        });

        rdbAdministrador.setBackground(new java.awt.Color(0, 51, 204));
        brgPrincipal.add(rdbAdministrador);
        rdbAdministrador.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        rdbAdministrador.setText("Administrador");
        rdbAdministrador.setAutoscrolls(true);
        rdbAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbAdministradorActionPerformed(evt);
            }
        });

        lblUsuarioExtra.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        lblUsuarioExtra.setText("Matricula");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblCriandoNovoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edtNomeUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addComponent(lblSenha)
                        .addComponent(edtSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(spnIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(edtCPFUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblSenha1)
                    .addComponent(lblUsuarioExtra)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdbAluno)
                        .addGap(18, 18, 18)
                        .addComponent(rdbAdministrador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdbExterno))
                    .addComponent(edtCampoExtraUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCriarUsuario)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCriandoNovoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(edtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(4, 4, 4)
                        .addComponent(spnIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(4, 4, 4)
                        .addComponent(edtCPFUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(lblSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSenha1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbAluno)
                    .addComponent(rdbExterno)
                    .addComponent(rdbAdministrador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuarioExtra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtCampoExtraUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(btnCriarUsuario)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
       getDesricaoCampus();
       spnIdade.setValue(18);
       setTipousuario(1);
    }//GEN-LAST:event_formInternalFrameOpened

    private void rdbAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbAlunoActionPerformed
        lblUsuarioExtra.setText("Matricula");
        setTipousuario(1);
    }//GEN-LAST:event_rdbAlunoActionPerformed

    private void rdbAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbAdministradorActionPerformed
        lblUsuarioExtra.setText("Código do Administrador"); 
        setTipousuario(0);
    }//GEN-LAST:event_rdbAdministradorActionPerformed

    private void rdbExternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbExternoActionPerformed
        lblUsuarioExtra.setText("[Campo desconsiderado para usuario externo]");
        setTipousuario(3);
    }//GEN-LAST:event_rdbExternoActionPerformed

    private void btnCriarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarUsuarioActionPerformed
        if(edicao){
            editarUsuario();
        }
        else{
            criarUsuario();
        }
        
    }//GEN-LAST:event_btnCriarUsuarioActionPerformed

    private void btnCriarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCriarUsuarioMouseClicked
      
    }//GEN-LAST:event_btnCriarUsuarioMouseClicked

    private void btnCriarUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCriarUsuarioKeyPressed
        
    }//GEN-LAST:event_btnCriarUsuarioKeyPressed
    public Integer getCampusid() {
        return campusid;
    }

    public void criarUsuario(){
        Boolean result= false;
        
        Conexao con = new Conexao();
       
        switch (getTipousuario()){
            case 0: result = con.criarUsuario(new Administrador(0, edtNomeUsuario.getText(), Integer.valueOf(spnIdade.getValue().toString()),edtCPFUsuario.getText(),
                    edtSenhaUsuario.getText(), edtCampoExtraUsuario.getText(), getCampusid()), getTipousuario());break;

            case 1: result = con.criarUsuario(new Aluno(1, edtNomeUsuario.getText(), Integer.valueOf(spnIdade.getValue().toString()),edtCPFUsuario.getText(),
                    edtSenhaUsuario.getText(), getCampusid(), edtCampoExtraUsuario.getText()), getTipousuario());break;

            case 3: result = con.criarUsuario(new Visitante(3, edtNomeUsuario.getText(), Integer.valueOf(spnIdade.getValue().toString()),edtCPFUsuario.getText(),
                    edtSenhaUsuario.getText(), getCampusid()), getTipousuario());break;
        }

        if (result){
            JOptionPane.showMessageDialog(rootPane, "Usuario criado com sucesso !!!", "Informação", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
  
    }
    
    public void editarUsuario(){
        Boolean result= false;
        
        Conexao con = new Conexao();
        this.usuarioEdicao.setNome(edtNomeUsuario.getText());
        this.usuarioEdicao.setCPF(edtCPFUsuario.getText());
        this.usuarioEdicao.setIdade((int) spnIdade.getValue());
        
        result = con.editarUsuario(this.usuarioEdicao);
        
        if (result){
            JOptionPane.showMessageDialog(rootPane, "Usuario atualizado com sucesso !!!", "Informação", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
  
    }
    public void setCampusid(Integer campusid) {
        this.campusid = campusid;
    }
    
    public void getDesricaoCampus(){ 
        Conexao con = new Conexao();

        String descricaocampus = con.getDescricaoCampus(campusid);

        if (!descricaocampus.isEmpty()){
            lblCriandoNovoUsuario.setText("Criando um usuario no Campus "+descricaocampus);
            return;
        }

        JOptionPane.showConfirmDialog(null,"Algo deu errado com a seleção do campus, tente novamente", "Ok", JOptionPane.DEFAULT_OPTION);
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup brgPrincipal;
    private javax.swing.JButton btnCriarUsuario;
    private javax.swing.JTextField edtCPFUsuario;
    private javax.swing.JTextField edtCampoExtraUsuario;
    private javax.swing.JTextField edtNomeUsuario;
    private javax.swing.JTextField edtSenhaUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCriandoNovoUsuario;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblSenha1;
    private javax.swing.JLabel lblUsuarioExtra;
    private javax.swing.JRadioButton rdbAdministrador;
    private javax.swing.JRadioButton rdbAluno;
    private javax.swing.JRadioButton rdbExterno;
    private javax.swing.JSpinner spnIdade;
    // End of variables declaration//GEN-END:variables

    
    public Integer getTipousuario() {
        return tipousuario;
    }


    public void setTipousuario(Integer tipousuario) {
        this.tipousuario = tipousuario;
    }
}
