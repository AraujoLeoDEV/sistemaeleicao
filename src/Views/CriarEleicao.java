/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.Eleicao;
import Services.Conexao;
import java.awt.HeadlessException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Altimar
 */
public class CriarEleicao extends javax.swing.JInternalFrame {

    private Integer campusid;
    public CriarEleicao() {
        initComponents();
        preencherDatas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        edtDescricaoNovaEleicao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnCriarEleicao = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        edtDataInicio = new javax.swing.JTextField();
        edtDataFim = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.highlight")));
        setClosable(true);
        setTitle("Criar Eleição - Admin");
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
        jPanel1.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));

        jLabel2.setFont(new java.awt.Font("Malgun Gothic", 0, 12)); // NOI18N
        jLabel2.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        jLabel2.setText("Descricao da Eleição");

        btnCriarEleicao.setBackground(new java.awt.Color(0, 102, 255));
        btnCriarEleicao.setFont(new java.awt.Font("Malgun Gothic", 0, 14)); // NOI18N
        btnCriarEleicao.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        btnCriarEleicao.setText("Criar");
        btnCriarEleicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarEleicaoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Malgun Gothic", 0, 12)); // NOI18N
        jLabel3.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        jLabel3.setText("Data inicio");

        jLabel4.setFont(new java.awt.Font("Malgun Gothic", 0, 12)); // NOI18N
        jLabel4.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        jLabel4.setText("Data fim");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtDescricaoNovaEleicao)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCriarEleicao)
                        .addGap(183, 183, 183))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(edtDataInicio)
                            .addComponent(edtDataFim, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                        .addGap(92, 366, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtDescricaoNovaEleicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCriarEleicao)
                .addContainerGap(12, Short.MAX_VALUE))
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

    private void btnCriarEleicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarEleicaoActionPerformed
        criarNovaEleicao();
    }//GEN-LAST:event_btnCriarEleicaoActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

    }//GEN-LAST:event_formInternalFrameOpened

    
    public void criarNovaEleicao(){
        Conexao con = new Conexao();
        if (edtDescricaoNovaEleicao.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "O campo de descrição da eleição não pode estar vazio !", "Atenção !", JOptionPane.WARNING_MESSAGE);
          return;
        }
        
       Date dataInicio = null;
       Date dataFim    = null; 
        try {
          
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("dd/MM/yyyy");
       
          dataInicio=new Date(edtDataInicio.getText());
          dataFim= new Date(edtDataFim.getText());
          
          
          if (dataFim.compareTo(dataInicio) < 0 ){
             JOptionPane.showMessageDialog(rootPane, "A data final não pode ser menor do que a data inicial!", "Atenção !", JOptionPane.WARNING_MESSAGE);
          return;
        }
       
        } catch (HeadlessException e) {
            
            JOptionPane.showMessageDialog(rootPane, "Uma das datas inseridas é inválida", "Erro", JOptionPane.ERROR_MESSAGE);
            
        }
         
        Eleicao eleicaoNova = new Eleicao(edtDataInicio.getText(), edtDescricaoNovaEleicao.getText(), edtDataFim.getText(),getCampusid());
      
         
        con.criarEleicao(eleicaoNova);
        
        this.dispose();
            
    
    }
    
    public void preencherDatas(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("dd/MM/yyyy");
        edtDataInicio.setText(LocalDate.now().format(formatter));
        edtDataFim.setText(LocalDate.now().format(formatter));
    
    }
    
    
    private MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCriarEleicao;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField edtDataFim;
    private javax.swing.JTextField edtDataInicio;
    private javax.swing.JTextField edtDescricaoNovaEleicao;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

   
    public Integer getCampusid() {
        return campusid;
    }

  void setCampusid(Integer campusid) {
        this.campusid = campusid;
    }
}
