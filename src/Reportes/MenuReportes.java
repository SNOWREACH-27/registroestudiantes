/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Reportes;

/**
 *
 * @author franc
 */
public class MenuReportes extends javax.swing.JDialog {

    Reporte reporte = new Reporte();

    /**
     * Creates new form MenuReportes
     */
    public MenuReportes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.getContentPane().setBackground(new java.awt.Color(153, 153, 153));
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        estudiante = new javax.swing.JButton();
        cedula = new javax.swing.JButton();
        estado = new javax.swing.JButton();
        Salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        estudiante.setBackground(new java.awt.Color(153, 153, 255));
        estudiante.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        estudiante.setText("Reporte general de estudiantes");
        estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estudianteActionPerformed(evt);
            }
        });

        cedula.setBackground(new java.awt.Color(153, 153, 255));
        cedula.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cedula.setText("Reporte por cedula");
        cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedulaActionPerformed(evt);
            }
        });

        estado.setBackground(new java.awt.Color(153, 153, 255));
        estado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        estado.setText("Reporte por estado");
        estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoActionPerformed(evt);
            }
        });

        Salir.setBackground(new java.awt.Color(102, 102, 255));
        Salir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Salir.setText("Volver");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(estado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(estudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(estudiante)
                .addGap(18, 18, 18)
                .addComponent(cedula)
                .addGap(18, 18, 18)
                .addComponent(estado)
                .addGap(35, 35, 35)
                .addComponent(Salir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        dispose();// Cerrar el diálogo actual
        
    }//GEN-LAST:event_SalirActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estudianteActionPerformed
        reporte.generarReporte("SELECT * FROM vista_estudiantes", "Reporte de Estudiantes General", "ReporteGeneral.pdf");
        dispose();// Cerrar el diálogo actual
        
    }//GEN-LAST:event_estudianteActionPerformed

    private void estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoActionPerformed
        MenuReportesEstados Dialog = new MenuReportesEstados(this, true); 
        Dialog.setLocationRelativeTo(this);
        Dialog.setVisible(true);
    }//GEN-LAST:event_estadoActionPerformed

    private void cedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulaActionPerformed
        MenuReportesCedula Dialog = new MenuReportesCedula(this, true);
        Dialog.setLocationRelativeTo(this);
        Dialog.setVisible(true);
    }//GEN-LAST:event_cedulaActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Salir;
    private javax.swing.JButton cedula;
    private javax.swing.JButton estado;
    private javax.swing.JButton estudiante;
    // End of variables declaration//GEN-END:variables
}
