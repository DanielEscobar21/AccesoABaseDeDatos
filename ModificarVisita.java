
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ModificarVisita extends JInternalFrame {

    private JTextField ApMa1;
    private JTextField ApPa;
    private JButton Buscar;
    private JTextField Busqueda;
    private JButton Guardar;
    private JTextField Motivo;
    private JTextField Nombre1;
    private JPanel Panel;
    private JComboBox<String> Por;
    private JTable Tabla;
    private JTextField TipoDoc;
    private JLabel Titulo;
    private JTextField VisitaA;
    private JLabel eApMa;
    private JLabel eApPa;
    private JLabel eBuscar;
    private JLabel eBuscar1;
    private JLabel eMotivo;
    private JLabel eNombre1;
    private JLabel eTipoDoc;
    private JLabel eVisitaA;
    private JLabel Nota;
    private JScrollPane jScrollPane1;
    DefaultTableModel modelo = new DefaultTableModel();
    String Select = "Nombre";
    String ID = "";

    public ModificarVisita() throws ClassNotFoundException {
        GridBagConstraints gridBagConstraints;

        Panel = new JPanel();
        jScrollPane1 = new JScrollPane();
        Tabla = new JTable();
        Titulo = new JLabel();
        eTipoDoc = new JLabel();
        eApPa = new JLabel();
        eApMa = new JLabel();
        ApPa = new JTextField();
        Nombre1 = new JTextField();
        ApMa1 = new JTextField();
        TipoDoc = new JTextField();
        eVisitaA = new JLabel();
        VisitaA = new JTextField();
        eMotivo = new JLabel();
        Motivo = new JTextField();
        Guardar = new JButton();
        eNombre1 = new JLabel();
        eBuscar = new JLabel();
        Por = new JComboBox<>();
        Busqueda = new JTextField();
        eBuscar1 = new JLabel();
        Buscar = new JButton();
        Nota = new JLabel();

        setBackground(new Color(255, 255, 255));
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Agregar");
        
        getContentPane().setLayout(new GridBagLayout());

        Panel.setBackground(new Color(255, 255, 255));
        Panel.setForeground(new Color(255, 255, 255));
        Panel.setLayout(new GridBagLayout());

        Tabla.setBackground(new Color(255, 255, 255));
        Tabla.setFont(new Font("Arial", 0, 14));
        Tabla.setForeground(new Color(0, 0, 0));
        Tabla.setModel(new DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null, null}
                },
                new String[]{
                    "ID", "Documento", "Nombre(s)", "Apellido Paterno", "Apellido Materno", "Fecha de Registro", "Visita a: ", "Motivo"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabla);
        if (Tabla.getColumnModel().getColumnCount() > 0) {
            Tabla.getColumnModel().getColumn(0).setResizable(false);
            Tabla.getColumnModel().getColumn(0).setPreferredWidth(1);
            Tabla.getColumnModel().getColumn(1).setResizable(false);
            Tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
            Tabla.getColumnModel().getColumn(2).setResizable(false);
            Tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
            Tabla.getColumnModel().getColumn(3).setResizable(false);
            Tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
            Tabla.getColumnModel().getColumn(4).setResizable(false);
            Tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
            Tabla.getColumnModel().getColumn(5).setResizable(false);
            Tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
            Tabla.getColumnModel().getColumn(6).setResizable(false);
            Tabla.getColumnModel().getColumn(6).setPreferredWidth(30);
            Tabla.getColumnModel().getColumn(7).setResizable(false);
            Tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        Tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
                    TablaMouseClicked(evt);
                } catch (ClassNotFoundException ex) {}
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 81;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 980;
        gridBagConstraints.ipady = 285;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(12, 0, 23, 18);
        Panel.add(jScrollPane1, gridBagConstraints);

        Titulo.setFont(new Font("Century Gothic", 1, 48));
        Titulo.setForeground(new Color(153, 0, 0));
        Titulo.setText("Modificar una Visita");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 27;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(13, 64, 0, 0);
        Panel.add(Titulo, gridBagConstraints);

        eTipoDoc.setFont(new Font("Arial", 1, 18));
        eTipoDoc.setForeground(new Color(153, 0, 0));
        eTipoDoc.setText("Tipo de Documento: ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(12, 44, 0, 0);
        Panel.add(eTipoDoc, gridBagConstraints);

        eApPa.setFont(new Font("Arial", 1, 18));
        eApPa.setForeground(new Color(153, 0, 0));
        eApPa.setText("Apellido Paterno: ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(12, 61, 0, 0);
        Panel.add(eApPa, gridBagConstraints);

        eApMa.setFont(new Font("Arial", 1, 18));
        eApMa.setForeground(new Color(153, 0, 0));
        eApMa.setText("Apellido Materno: ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(12, 58, 0, 0);
        Panel.add(eApMa, gridBagConstraints);

        ApPa.setBackground(new Color(204, 204, 204));
        ApPa.setFont(new Font("Arial", 0, 18));
        ApPa.setForeground(new Color(0, 0, 0));
        ApPa.setBorder(null);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 247;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(12, 12, 0, 0);
        Panel.add(ApPa, gridBagConstraints);

        Nombre1.setBackground(new Color(204, 204, 204));
        Nombre1.setFont(new Font("Arial", 0, 18)); // NOI18N
        Nombre1.setForeground(new Color(0, 0, 0));
        Nombre1.setBorder(null);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 247;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(12, 12, 0, 0);
        Panel.add(Nombre1, gridBagConstraints);

        ApMa1.setBackground(new Color(204, 204, 204));
        ApMa1.setFont(new Font("Arial", 0, 18));
        ApMa1.setForeground(new Color(0, 0, 0));
        ApMa1.setBorder(null);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 247;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(12, 12, 0, 0);
        Panel.add(ApMa1, gridBagConstraints);

        TipoDoc.setBackground(new Color(204, 204, 204));
        TipoDoc.setFont(new Font("Arial", 0, 18)); // NOI18N
        TipoDoc.setForeground(new java.awt.Color(0, 0, 0));
        TipoDoc.setBorder(null);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 29;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 51;
        gridBagConstraints.ipadx = 247;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(12, 6, 0, 0);
        Panel.add(TipoDoc, gridBagConstraints);

        eVisitaA.setFont(new Font("Arial", 1, 18));
        eVisitaA.setForeground(new Color(153, 0, 0));
        eVisitaA.setText("Visita a: ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(12, 44, 0, 0);
        Panel.add(eVisitaA, gridBagConstraints);

        VisitaA.setBackground(new Color(204, 204, 204));
        VisitaA.setFont(new Font("Arial", 0, 18)); // NOI18N
        VisitaA.setForeground(new Color(0, 0, 0));
        VisitaA.setBorder(null);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 60;
        gridBagConstraints.ipadx = 354;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(12, 6, 0, 0);
        Panel.add(VisitaA, gridBagConstraints);

        eMotivo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        eMotivo.setForeground(new java.awt.Color(153, 0, 0));
        eMotivo.setText("Motivo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 44, 0, 0);
        Panel.add(eMotivo, gridBagConstraints);

        Motivo.setBackground(new java.awt.Color(204, 204, 204));
        Motivo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Motivo.setForeground(new java.awt.Color(0, 0, 0));
        Motivo.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 64;
        gridBagConstraints.ipadx = 364;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 6, 0, 0);
        Panel.add(Motivo, gridBagConstraints);

        Guardar.setIcon(new ImageIcon("src/image/Guardar_OFF.png"));
        Guardar.setBorder(null);
        Guardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Guardar.setRolloverIcon(new ImageIcon("src/image/Guardar_ON.jpg"));
        Guardar.setRolloverSelectedIcon(new ImageIcon("src/image/Guardar_ON.jpg"));
        Guardar.setSelectedIcon(new ImageIcon("src/image/Guardar_ON.jpg"));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(28, 84, 0, 0);
        Panel.add(Guardar, gridBagConstraints);
        Guardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    GuardarActionPerformed(evt);
                } catch (ClassNotFoundException ex) {}
            }
        });

        eNombre1.setFont(new Font("Arial", 1, 18));
        eNombre1.setForeground(new Color(153, 0, 0));
        eNombre1.setText("Nombre(s): ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor =GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(12, 114, 0, 0);
        Panel.add(eNombre1, gridBagConstraints);

        eBuscar.setFont(new Font("Arial", 1, 18));
        eBuscar.setForeground(new Color(153, 0, 0));
        eBuscar.setText("Buscar por  ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(19, 86, 0, 0);
        Panel.add(eBuscar, gridBagConstraints);

        Por.setBackground(new Color(255, 255, 255));
        Por.setFont(new Font("Century Gothic", 1, 12));
        Por.setForeground(new Color(0, 0, 0));
        Por.setModel(new DefaultComboBoxModel<>(new String[]{"Nombre(s)", "ID", "Documento", "Apellido Paterno", "Apellido Materno", "Busca A:", " "}));
        Por.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                PorActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 6, 0, 0);
        Panel.add(Por, gridBagConstraints);

        Busqueda.setBackground(new Color(204, 204, 204));
        Busqueda.setFont(new Font("Arial", 0, 18));
        Busqueda.setForeground(new Color(0, 0, 0));
        Busqueda.setBorder(null);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 50;
        gridBagConstraints.ipadx = 462;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(18, 6, 0, 0);
        Panel.add(Busqueda, gridBagConstraints);

        eBuscar1.setFont(new Font("Arial", 1, 18));
        eBuscar1.setForeground(new Color(153, 0, 0));
        eBuscar1.setText(":");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(19, 6, 0, 0);
        Panel.add(eBuscar1, gridBagConstraints);

        Buscar.setIcon(new ImageIcon("src/image/Buscar_OFF.png"));
        Buscar.setBorder(null);
        Buscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Buscar.setRolloverIcon(new ImageIcon("src/image/Buscar_ON.jpg"));
        Buscar.setRolloverSelectedIcon(new ImageIcon("src/image/Buscar_ON.jpg"));
        Buscar.setSelectedIcon(new ImageIcon("src/image/Buscar_ON.jpg"));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 68;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 70;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(11, 6, 0, 0);
        Panel.add(Buscar, gridBagConstraints);
        Buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });

        Nota.setFont(new Font("Arial", 1, 11));
        Nota.setForeground(new Color(102, 102, 102));
        Nota.setText("Busque en la barra de busqueda el registro a modificar o seleccionela "
                + "directamente de la tabla, cambie los datos arriba y de clic en guardar.");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 69;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(6, 138, 0, 0);
        Panel.add(Nota, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.ipady = 26;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        getContentPane().add(Panel, gridBagConstraints);

        pack();

        try {
            Tabla.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();

            String sql = "SELECT IDVisita, TipoDocumento, Nombre, ApellidoPaterno, ApellidoMaterno, FechaRegistro, VisitaA, Motivo FROM Visita";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("ID");
            modelo.addColumn("Documento");
            modelo.addColumn("Nombre(s)");
            modelo.addColumn("Apellido Paterno");
            modelo.addColumn("Apellido Materno");
            modelo.addColumn("Fecha de Registro");
            modelo.addColumn("Visita a: ");
            modelo.addColumn("Motivo");

            int[] anchos = {1, 50, 50, 50, 50, 50, 30, 100};
            for (int i = 0; i < Tabla.getColumnCount(); i++) {
                Tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }

    public void TablaMouseClicked(MouseEvent evt) throws ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Conexion objCon = new Conexion();
            Connection conn = objCon.getConexion();

            int Fila = Tabla.getSelectedRow();
            ID = Tabla.getValueAt(Fila, 0).toString();

            ps = conn.prepareStatement("SELECT TipoDocumento, Nombre, ApellidoPaterno, ApellidoMaterno, FechaRegistro, VisitaA, Motivo FROM Visita WHERE IDVisita = ?");
            ps.setString(1, ID);
            rs = ps.executeQuery();

            while (rs.next()) {
                this.TipoDoc.setText(rs.getString("TipoDocumento"));
                this.Nombre1.setText(rs.getString("Nombre"));
                this.ApPa.setText(rs.getString("ApellidoPaterno"));
                this.ApMa1.setText(rs.getString("ApellidoMaterno"));
                this.VisitaA.setText(rs.getString("VisitaA"));
                this.Motivo.setText(rs.getString("Motivo"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void PorActionPerformed(ActionEvent evt) {
        String itemSeleecionado = (String) Por.getSelectedItem();
        switch (itemSeleecionado) {
            case "ID":
                Select = "IDVisita";
                break;
            case "Documento":
                Select = "TipoDocumento";
                break;
            case "Apellido Paterno":
                Select = "ApellidoPaterno";
                break;
            case "Apellido Materno":
                Select = "ApellidoMaterno";
                break;
            case "Visita a":
                Select = "VisitaA";
                break;
            default:break;
        }
    }

    public void BuscarActionPerformed(ActionEvent e) {
        String campo = Busqueda.getText();
        String where = "";
        if (!"".equals(campo)) {
            where = "WHERE " + Select + " = '" + campo + "'";
        }
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            Tabla.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            com.mysql.jdbc.Connection con = conn.getConexion();

            String sql = "SELECT IDVisita, TipoDocumento, Nombre, ApellidoPaterno, ApellidoMaterno, "
                    + "FechaRegistro, VisitaA, Motivo FROM Visita " + where;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            modelo.addColumn("ID");
            modelo.addColumn("Documento");
            modelo.addColumn("Nombre(s)");
            modelo.addColumn("Apellido Paterno");
            modelo.addColumn("Apellido Materno");
            modelo.addColumn("Fecha de Registro");
            modelo.addColumn("Visita a: ");
            modelo.addColumn("Motivo");
            
            int[] anchos = {1, 50, 50, 50, 50, 50, 30, 100};
            for (int i = 0; i < Tabla.getColumnCount(); i++) {
                Tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
                con.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "La Busqueda no arrojó resultados.");
        }

    }

    private void GuardarActionPerformed(ActionEvent evt) throws ClassNotFoundException {
        int opp = JOptionPane.showConfirmDialog(null, "¿Guardar los datos?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opp == 0) {
            PreparedStatement ps = null;
            try {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                Date date = new Date();
                Conexion objCon = new Conexion();
                Connection conn = objCon.getConexion();
                ps = conn.prepareStatement("UPDATE Visita SET TipoDocumento=?, Nombre=?, ApellidoPaterno=?, ApellidoMaterno=?"
                        + ", FechaRegistro=?, VisitaA=?, Motivo =? WHERE IDVisita= " + ID);

                ps.setString(1, this.TipoDoc.getText());
                ps.setString(2, this.Nombre1.getText());
                ps.setString(3, this.ApPa.getText());
                ps.setString(4, this.ApMa1.getText());
                ps.setString(5, dateFormat.format(date));
                ps.setString(6, this.VisitaA.getText());
                ps.setString(7, this.Motivo.getText());
                ps.execute();

                JOptionPane.showMessageDialog(null, "Registro Modificado");
                TipoDoc.setText("");
                Nombre1.setText("");
                ApPa.setText("");
                ApMa1.setText("");
                VisitaA.setText("");
                Motivo.setText("");
                conn.close();
                this.dispose();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al Modificar el Registro");
                System.out.println(ex);
            }
        }

    }

}
