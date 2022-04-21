package Juego;


//imports
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.Random;


public class Servidor extends javax.swing.JFrame {
    // ceracion de globales
    JPanel panel;
    JLabel matriz [][];
    int mat [][] = new int[4][5];
    int mat2 [][] = new int[4][5];
    Random ran;
    int contador,ban,ban1,annum,anposx,anposy,acnum,acposx,acposy;
    Timer espera, espera2,tiempo;
    int consegund,seg,min;
    int hora,minutos,segundos;
    
    
    
    
    public Servidor() {
        
        initComponents();
        //creacion de los elementos y el panel a utlizar
        panel = new JPanel();
        this.getContentPane().setBackground(Color.orange);
        panel.setBackground(Color.orange);
        this.setBounds(165, 40, 740, 730);
        panel.setLayout(null);
        panel.setBounds(165, 0, 740, 730);
        final var add = this.add(panel);
        panel.setVisible(true);
        
 
     
        
        
        //funcion para que aparescan las cartas aparescan volteadas
        ran = new Random();
        this.numaleatorios();
        
        //matriz de imagenes
        //este hara una matriz de 4filas por 5columnas donde mostrara imagenes 

        matriz = new JLabel[4][5];

        
        
        //ceracion de la matriz e colocaion de imagenes
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {

                matriz[i][j] = new JLabel();
                

                File file = new File("C:\\Users\\Personal\\Documents\\NetBeansProjects\\Proyecto1datos\\src\\main\\java\\Cartas\\0.jpg");

                BufferedImage bufferedImage = null;
                try {
                    bufferedImage = ImageIO.read(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ImageIcon imageIcon = new ImageIcon(bufferedImage);

                matriz[i][j].setIcon(imageIcon);
                panel.add(matriz[i][j]);
                matriz[i][j].setVisible(true);
                //matriz[i][j].setSize(740,730);

                matriz[i][j].setSize(matriz[i][j].getWidth(), matriz[i][j].getHeight());
                //aqui la demas espacios a la imagenes para que no salgan pegadas 
                matriz[i][j].setBounds(100+(j*125),30+(i*156), 125, 156);
                /*
                //declaramos la imagenes que tiene el los nombres de 1 a 10
                matriz[i][j].setIcon(new ImageIcon("C:\\Users\\Personal\\Documents\\NetBeansProjects\\Proyecto1datos\\src\\main\\java\\Cartas"+mat2[i][j]+".jpg"));
                //colocamos que la matriz se muestre en pantalla
                matriz[i][j].setVisible(true);
                */
                //aqui se añaden junto con la 0
                final var add1 = panel.add(matriz[i][j], 0);
            }
        }
        
        
        
        
        seg = 0;
        min = 0;        
        
        //este lo colocamos para podermo mostrar el tiempo que 
        //transcurre durante el juego
        tiempo = new Timer (1000, new ActionListener()
        {
          
            public void actionPerformed(ActionEvent e) 
            {
                
                seg++;
                if(seg == 60){
                    min++;
                    seg=0;
                }
                
            //declaramos en una variable cronometro el tiempo que transcurre
            
            }});
        tiempo.start();
        System.out.println(seg);
        //declaramos en la variable espera la cual es otro tiempo el cual lo utilizamos 
        //para colocar un tiempo a la hora de que las cartas se voltean 
        consegund = 0;
        espera = new Timer (1000, new ActionListener()
        {
          
            public void actionPerformed(ActionEvent e) 
            {
                consegund++;
            }});
        espera.start();
        espera.stop();
        consegund = 0;
        ban=0;
        ban1=0;
        
        //evento de clic en la cartas
        contador = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j].addMouseListener(new MouseAdapter(){
                    public void mousePressed(MouseEvent e){
                        for (int k = 0; k < 4; k++) {
                            for (int l = 0; l < 5; l++) {
                                if(e.getSource() == matriz[k][l]){
                        
                                   
                                   //cuando se da click a la carta esta se volteara
                                   if(mat2[k][l] == 0 && contador !=2){
                                       mat2[k][l] = mat[k][l];
                                       matriz[k][l].setIcon(new ImageIcon("C:\\Users\\Personal\\Documents\\NetBeansProjects\\Proyecto1datos\\src\\main\\java\\Cartas"+mat2[k][l]+".JPG"));
                                       contador++;
                                       acnum = mat[k][l];
                                       acposx = k;
                                       acposy = l;
                                       if(contador == 1){
                                            annum = mat[k][l];
                                            anposx = k;
                                            anposy = l;
                                       }
                                       
                                       //tiempo que se tarda en dar vuelta
                                       espera2 = new Timer (500, (ActionEvent e1) -> {
                                           if(contador == 2 && ban1 == 0){
                                               espera.restart();
                                               ban1=1;
                                           }
                                           if(contador == 2 && consegund == 2){
                                               espera.stop();
                                               consegund = 0;
                                               
                                               //Desaparecen las cartas que son iguales y deja las que aun no se encuentran
                                               if(mat2[acposx][acposy]==mat2[anposx][anposy]){
                                                   
                                                   mat2[acposx][acposy] = -1;
                                                   mat2[anposx][anposy] = -1;
                                                   matriz[acposx][acposy].setIcon(new ImageIcon("C:\\Users\\Personal\\Documents\\NetBeansProjects\\Proyecto1datos\\src\\main\\java\\Cartas"+mat2[acposx][acposy]+".JPG"));
                                                   matriz[anposx][anposy].setIcon(new ImageIcon("C:C:\\Users\\Personal\\Documents\\NetBeansProjects\\Proyecto1datos\\src\\main\\java\\Cartas"+mat2[anposx][anposy]+".JPG"));
                                                   contador=0;
                                                   //gano si toda la mat2 es -1
                                                   int acum = 0;
                                                   for (int m = 0; m < 4; m++) {
                                                       for (int n = 0; n < 5; n++) {
                                                           if (mat2[m][n] == -1)
                                                               acum++;
                                                       }
                                                   }
                                                   //cuando no se encuentre ninguna para entonces aparecera
                                                   //un mensaje diciendo que gano
                                                   if(acum == 20){
                                                       JOptionPane.showMessageDialog(panel, "Ganaste");
                                                       
                                                       Cliente ventana = new Cliente();
                                                       ventana.setVisible(true);
                                                       tiempo.stop();
                                                       
                                                       
                                                       
                                                   }
                                               }
                                               for (int m = 0; m < 4; m++) {
                                                   for (int n = 0; n < 5; n++) {
                                                       //se coloca el valor -1 a las cartas pares
                                                       if(mat2[m][n]!=0 && mat2[m][n]!=-1){
                                                           mat2[m][n] = 0;
                                                           matriz[m][n].setIcon(new ImageIcon("C:\\Users\\Personal\\Documents\\NetBeansProjects\\Proyecto1datos\\src\\main\\java\\Cartas"+mat2[m][n]+".JPG"));
                                                           contador=0;
                                                           
                                                       }
                                                       System.out.println("salio");
                                                       
                                                   }
                                                   
                                               }
                                               espera2.stop();
                                               ban1=0;
                                           }
                                       });
                                       if(ban == 0)
                                           espera2.start();
                                           ban = 1;
                                       if(contador == 2)
                                               espera2.restart();
                                   }    
  
                                }
                                
                            }
                            
                        }
                    }

                   
                });
                
            }
            
        }

    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        labnom1 = new javax.swing.JLabel();
        juga2 = new javax.swing.JLabel();
        temporio = new javax.swing.JLabel();
        nombreju1 = new javax.swing.JLabel();
        nombreju2 = new javax.swing.JLabel();
        cronometro = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 720));
        setSize(new java.awt.Dimension(900, 720));

        labnom1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        labnom1.setText("Jugador 1");

        juga2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        juga2.setText("Jugador 2");

        temporio.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        temporio.setText("Tiempo");

        nombreju1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                nombreju1ComponentAdded(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(temporio)
                        .addComponent(juga2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labnom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nombreju1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nombreju2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(814, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labnom1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nombreju1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(juga2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreju2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(temporio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cronometro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(497, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombreju1ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_nombreju1ComponentAdded
        // TODO add your handling code here:
        
    }//GEN-LAST:event_nombreju1ComponentAdded
    // aqui se obtiene los nombres de los jugadores
    public void obtenernom(String nombre,String nombre2){
        cronometro.setText(min+":"+seg); 
        nombreju1.setText(nombre);
        nombreju2.setText(nombre2);
    }
    
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
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Servidor().setVisible(true);
            }
        });
    }



    





   //este metodo se hace para obtener aleatoriamente las cartas
    //cada vez que se inicie el juego las cartas apareceran en 
    //diferentes lugares.
    //tambien para obtener dos veces la misma carta
    private void numaleatorios(){
        int acumulador = 0;
         for (int i = 0; i < 4; i++) 
            for (int j = 0; j < 5; j++){
                mat[i][j] = 0;
            
                
            }    
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                mat[i][j] = ran.nextInt(10)+1;
                
                do{
                    acumulador = 0;
                    for (int k = 0; k < 4; k++) {
                        for (int l = 0; l < 5; l++) {
                             if(mat[i][j]== mat[k][l]){
                                acumulador +=1;
                            }
                        }
                    }
                //esto se coloca para que solo se duplique dos veces la carta
                if(acumulador == 3){
                    mat[i][j] = ran.nextInt(10)+1;
                }
                }while(acumulador == 3); 
            }   
        }
    }


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cronometro;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel juga2;
    public javax.swing.JLabel labnom1;
    private javax.swing.JLabel nombreju1;
    private javax.swing.JLabel nombreju2;
    private javax.swing.JLabel temporio;
    // End of variables declaration//GEN-END:variables
}

