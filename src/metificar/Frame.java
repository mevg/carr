package metificar;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Frame extends javax.swing.JFrame {

    Conexion conexion;
    public static int codigo;
    double total = 0.0D;
    LinkedList<Articulo> lista;

    public Frame() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        lista = new LinkedList();
        txtCodigoFoco.setText("");
        txtCodigoFoco.requestFocus();
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        String[] columnName = {"Codigo", "Descripcion", "Precio Unitario", "Peso", "Cantidad"};
        dtm.setColumnIdentifiers(columnName);
        Thread t = new Thread(new Escanear());
        t.start();

    }

    public void llenarTabla(String codigo) {
        try {
            conexion = new Conexion();
            Connection con = conexion.getConnection();
            PreparedStatement stm = con.prepareStatement("Select * from articulos where codigo_de_barras = ?");
            stm.setString(1, codigo);
            ResultSet rs = stm.executeQuery();
            //String[] row;
            //ResultSetMetaData metaData = rs.getMetaData();
            checar:
            while (rs.next()) {
                DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                for (Articulo a : lista) {
                    if (a.getCodigo_barras().equals(rs.getString("codigo_de_barras"))) {
                        a.setCantidad(a.getCantidad() + 1);
                        dtm.setValueAt(a.getCantidad(), lista.indexOf(a), 4);
                        total += Double.parseDouble(a.getPrecio());
                        lblTotal.setText("" + total);
                        break checar;
                    }
                }
                Articulo a = new Articulo(rs.getString("codigo_de_barras"), rs.getString("descripcion"), rs.getString("precio_unitario"), rs.getString("peso"), 1);
                lista.add(a);
                String[] row = {a.getCodigo_barras(), a.getDescripcion(), a.getPrecio(), a.getPeso(), "" + a.getCantidad()};
                dtm.addRow(row);
                total += Double.parseDouble(a.getPrecio());
                lblTotal.setText("" + total);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    class Escanear implements Runnable {

        String codigo_text;

        @Override
        public void run() {
            while (true) {
                try {
                    codigo_text = txtCodigoFoco.getText();
                    if (!codigo_text.isEmpty()) {
                        //codigo = Integer.parseInt(codigo_text);
                        //Frame.codigo = codigo;
                        llenarTabla(codigo_text);
                        txtCodigoFoco.setText("");
                        //codigo = 0;
                        System.out.println("codigo: " + codigo);
                    } else {
                        System.out.println("Campo vacio");
                    }
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnChecar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        txtCodigoFoco = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        btnChecar.setText("Checar");

        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        btnFinalizar.setText("Finalizar \nCompra");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnChecar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuitar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFinalizar)
                .addGap(18, 18, 18)
                .addComponent(txtCodigoFoco, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChecar)
                    .addComponent(btnQuitar)
                    .addComponent(btnFinalizar)
                    .addComponent(txtCodigoFoco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel1.setText("Total");

        lblTotal.setText("$ 0.0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addComponent(lblTotal)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 206, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTotal)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        int fila = table.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) table.getModel(); //TableProducto es el nombre de mi tabla ;) 
        double descontar = Double.parseDouble(lista.get(fila).getPrecio());
        int cantidad = lista.get(fila).getCantidad();
        if (cantidad > 1) {
            int remover = Integer.parseInt(JOptionPane.showInputDialog("Cuantos desea quitar?"));
            if (remover > cantidad) {
                JOptionPane.showMessageDialog(null, "Introduce un numero valido");
            } else if ((cantidad - remover) == 0) {
                lista.remove(fila);
                dtm.removeRow(fila);
                total -= (descontar * remover);
                lblTotal.setText("" + total);
            } else {
                lista.get(fila).setCantidad(cantidad - remover);
                dtm.setValueAt(lista.get(fila).getCantidad(), fila, 4);
                total -= (descontar * remover);
                lblTotal.setText("" + total);
            }
        } else {
            lista.remove(fila);
            dtm.removeRow(fila);
            total -= (descontar * cantidad);
            lblTotal.setText("" + total);
        }
    }//GEN-LAST:event_btnQuitarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChecar;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtCodigoFoco;
    // End of variables declaration//GEN-END:variables
}
