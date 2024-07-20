/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package RPSGame;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.lang.Math;
import Controller.RPSGameController;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
/**
 *
 * @author Derek
 */
public class RPSGameWindow extends javax.swing.JFrame {
    int object_amount = 50; //default=50
    private JLabel[] objects;
    private int[] object_type_counts;
    private String[] object_type_names;
    RPSGameController game_thread;
    private Random rand;
    private boolean startSim;
    private boolean resetSim;
    private int counter;
    /**
     * Creates new form RPSGameWindow
     */
    public RPSGameWindow() {
        initComponents();
        game_thread = new RPSGameController(this);
        game_thread.start();
        this.counter = 0;
        this.startSim = false;
        this.resetSim = true;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        importButton = new javax.swing.JButton();
        exportButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RockPaperScissorsSim");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 609, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );

        jLabel1.setText("RPS amount:");

        jTextField1.setText("50");

        jButton1.setText("Start Simulation");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        importButton.setText("Import");
        importButton.setToolTipText("");
        importButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtonActionPerformed(evt);
            }
        });

        exportButton.setText("Export");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(importButton)
                .addGap(18, 18, 18)
                .addComponent(exportButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(importButton)
                        .addComponent(exportButton))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public boolean check_uniform_class(){
        return (object_type_counts[0]==0 && object_type_counts[1]==0) ||
               (object_type_counts[0]==0 && object_type_counts[2]==0) ||
               (object_type_counts[1]==0 && object_type_counts[2]==0);
    }
    public void stepSimulation(){
        boolean salir = false;
        counter = counter+1;
        counter = counter%objects.length;
        for (int j=0; j<objects.length && !salir; j++){
            double distance = Math.sqrt((objects[j].getX()-objects[counter].getX())*(objects[j].getX()-objects[counter].getX())
            +(objects[j].getY()-objects[counter].getY())*(objects[j].getY()-objects[counter].getY()));
            if (distance <=60) {
                int first_obj_type = Integer.parseInt(objects[counter].getName());
                int second_obj_type = Integer.parseInt(objects[j].getName());
                switch(first_obj_type){
                    case 0:
                        if (second_obj_type==2){ //rock beats scissors
                            objects[j].setName("0");
                            ImageIcon imgrock = new ImageIcon(getClass().getResource("/img/rock.png"));
                            objects[j].setIcon(imgrock);
                            object_type_counts[0] += 1;
                            object_type_counts[2] -= 1;
                        }
                        break;
                    case 1: 
                        if (second_obj_type == 0){ //paper beats rock
                            objects[j].setName("1");
                            ImageIcon imgpaper = new ImageIcon(getClass().getResource("/img/paper.png"));
                            objects[j].setIcon(imgpaper);
                            object_type_counts[1] += 1;
                            object_type_counts[0] -= 1;
                        }
                        break;
                    case 2: 
                        if (second_obj_type == 1) { //scissors beats paper
                            objects[j].setName("2");
                            ImageIcon imgscissors = new ImageIcon(getClass().getResource("/img/scissors.png"));
                            objects[j].setIcon(imgscissors);
                            object_type_counts[2] += 1;
                            object_type_counts[1] -= 1;
                        }
                        break;
                }
            }
        }
        //Now move the object by a random amount
        Dimension size = objects[counter].getPreferredSize();
        int xpos = -1;
        int ypos = -1;
        while (xpos <0 || ypos <0 || xpos>jPanel1.getWidth()-20 || ypos>jPanel1.getHeight()-20) {
            xpos = objects[counter].getX()+rand.nextInt(20)-10;
            ypos = objects[counter].getY()+rand.nextInt(20)-10;
        }
        objects[counter].setBounds(xpos, ypos, size.width, size.height);
    }
    public void controlSim(){
        if (this.resetSim){
            this.generateElements();
            resetSim = false;
        }
        if (startSim){
            if (!check_uniform_class()){
                this.stepSimulation();
            }else{
                for (int i=0; i<this.object_type_counts.length; i++){
                    if (this.object_type_counts[i] > 0) {
                        JOptionPane.showMessageDialog(null, "" + this.object_type_names[i] + " wins!");
                        break;
                    }
                }
                this.startSim=false;
            }
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.startSim = true;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!this.startSim) {
            generateElements();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION){
            //File matrix_file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            BufferedReader reader;
            String[] obj_coordinates;
            try {
                reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile().getAbsolutePath()));
                String line = reader.readLine();
                int size_coordinates = Integer.parseInt(line);
                obj_coordinates = new String[size_coordinates];
                int i = 0;
                while (i<size_coordinates) {
                    line = reader.readLine();
                    obj_coordinates[i] = line;
                    i++;
                }
                generateElementsWithCoordinates(obj_coordinates, 0);
                reader.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error reading file!");
		e.printStackTrace();
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "File has an incorrect format!");
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_importButtonActionPerformed

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        int response = fileChooser.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION){
           try {
               FileWriter matrix_file_writer = new FileWriter(fileChooser.getSelectedFile().getAbsolutePath());
               matrix_file_writer.write(objects.length+"\n");
               for (int i=0; i<objects.length; i++) {
                   matrix_file_writer.write(objects[i].getX()+","+objects[i].getY()+","+objects[i].getName()+"\n");
               }
               matrix_file_writer.close();
           } catch (IOException e) {
               JOptionPane.showMessageDialog(null, "Error creating file");
               e.printStackTrace();
           }
       }
    }//GEN-LAST:event_exportButtonActionPerformed
    public void generateElements(){
        int parsed_amount = 50;
        try{
            parsed_amount = Integer.parseInt(jTextField1.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Enter a number of objects greater or equal to 1");
        }
        if (parsed_amount>0){
            object_amount = parsed_amount;
        }
        generateElementsWithCoordinates(null, object_amount);
    }
    
    public void generateElementsWithCoordinates(String[] coords, int object_amount){
        jPanel1.removeAll();
        if (coords!=null) {
            object_amount = coords.length;
        }
        objects = new JLabel[object_amount];
        object_type_counts = new int[3]; 
        object_type_names = new String[3]; //make it generic
        object_type_names[0] = "Rock";
        object_type_names[1] = "Paper";
        object_type_names[2] = "Scissors"; 
        rand = new Random(1);
        for (int i=0; i<object_amount; i++) {
            int x = rand.nextInt(jPanel1.getHeight()-50);
            int y = rand.nextInt(jPanel1.getWidth()-90);
            int type = rand.nextInt(3);
            if (coords!=null) {
                String line = coords[i];
                String[] line_parts = coords[i].split(",", 3); //x y fig_type
                x = Integer.parseInt(line_parts[0]);
                y = Integer.parseInt(line_parts[1]);
                type = Integer.parseInt(line_parts[2]);
            }
            String imagePath = "";
            switch (type) {
                case 0: {
                    imagePath = "/img/rock.png";
                    break;
                }
                case 1: {
                    imagePath = "/img/paper.png";
                    break;
                }
                case 2: {
                    imagePath = "/img/scissors.png";
                    break;
                }
            }
            ImageIcon img = new ImageIcon(getClass().getResource(imagePath));
            objects[i] = new JLabel("");
            objects[i].setIcon(img);
            objects[i].setName(""+type);
            Dimension size = objects[i].getPreferredSize();
            objects[i].setBounds(x, y, size.width, size.height);
            object_type_counts[type] += 1;
            jPanel1.add(objects[i]);
        }
        jPanel1.repaint();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RPSGameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RPSGameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RPSGameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RPSGameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RPSGameWindow().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exportButton;
    private javax.swing.JButton importButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
