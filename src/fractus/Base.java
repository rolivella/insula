package Fractus;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Base extends JApplet {

  //Variables Fractals

  ArrayList arrayNiteracio=new ArrayList();
  ArrayList arrayResultatLectura=new ArrayList();
  String strResultat="";
  String selectedFractal="F+F-F-FF+F+F-F";
  //Fi var. fractals

  private boolean isStandalone = false;
  JPanel jPbase=new JPanel();
  JPanel superior = new JPanel();
  JButton dibuixa = new JButton();
  JTextField txtIteracio = new JTextField();
  JButton bReset = new JButton();
  JLabel label4 = new JLabel();
  JLabel label5 = new JLabel();
  JLabel label6 = new JLabel();
  JTextField txtEscala = new JTextField();
  JTextField txtOX = new JTextField();
  JTextField txtOY = new JTextField();
  BorderLayout borderLayout1 = new BorderLayout();
  GridLayout gridLayout1 = new GridLayout();
  JPanel jP1 = new JPanel();
  JPanel jP3 = new JPanel();
  JPanel jP2 = new JPanel();
  GridLayout gridLayout2 = new GridLayout();
  JPanel jPaxioma = new JPanel();
  JPanel jPrwRule = new JPanel();
  JTextField txtAxioma = new JTextField();
  JLabel jLrwRule = new JLabel();
  JLabel label2 = new JLabel();
  JComboBox cbRwRule= new JComboBox();
  JLabel jLaxioma = new JLabel();
  JPanel jPiteracions = new JPanel();
  GridLayout gridLayout3 = new GridLayout();
  JPanel jPescala = new JPanel();
  JPanel jPox = new JPanel();
  JPanel jPoy = new JPanel();
  GridLayout gridLayout4 = new GridLayout();
  JPanel jPdibuixa = new JPanel();
  JPanel jPreset = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel inferior = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPcredits = new JPanel();
  JLabel jLcredits = new JLabel();
  //Obtener el valor de un parámetro
  public String getParameter(String key, String def) {
    return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

  //Construir el applet
  public Base() {
  }
  //Inicializar el applet
  public void init() {
      cbRwRule.addItem("Illa de Kock");
      cbRwRule.addItem("Corba 1");
      cbRwRule.addItem("Lily");
      cbRwRule.addItem("Quadrat de Sierpinsky");
      cbRwRule.addItem("Piràmide de Kock");

      jLaxioma.setVisible(false);
      txtAxioma.setVisible(false);

   try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Inicialización de componentes
  private void jbInit() throws Exception {
    jPbase.setLayout(borderLayout3);
    dibuixa.setText("Dibuixa");
    bReset.setText("Borra");
    inferior.setBackground(Color.white);
    inferior.setBorder(BorderFactory.createLineBorder(Color.black));
    inferior.setLayout(borderLayout2);
    jLcredits.setBackground(Color.white);
    jLcredits.setBorder(null);
    jLcredits.setText("Fractus - Desenvolupat per Roger Olivella Pujol");
    jPcredits.setBackground(Color.orange);
    jPcredits.setAlignmentY((float) 0.5);
    txtIteracio.setPreferredSize(new Dimension(25, 20));
    txtEscala.setMinimumSize(new Dimension(11, 20));
    txtEscala.setPreferredSize(new Dimension(35, 20));
    txtOX.setPreferredSize(new Dimension(35, 20));
    txtOY.setMinimumSize(new Dimension(11, 20));
    txtOY.setPreferredSize(new Dimension(35, 20));
    txtAxioma.setEnabled(false);
    txtAxioma.setDisabledTextColor(Color.black);
    cbRwRule.addActionListener(new Base_cbRwRule_actionAdapter(this));
    this.getContentPane().add(jPbase,BorderLayout.CENTER);
    jPbase.add(superior, BorderLayout.NORTH);
    superior.setBackground(Color.orange);
    superior.setLocale(java.util.Locale.getDefault());
    superior.setLayout(gridLayout1);
    dibuixa.setPreferredSize(new Dimension(90, 33));
    dibuixa.addActionListener(new dibuixaRectangle_dibuixa_actionAdapter(this));
    txtIteracio.setText("1");
    txtIteracio.setSelectionEnd(1);
    txtIteracio.setText("3");
    bReset.setActionCommand("button1");
    bReset.addActionListener(new dibuixaRectangle_bReset_actionAdapter(this));
    label4.setText("Escala");
    label5.setText("OX");
    label6.setText("OY");
    txtEscala.setText("4");
    txtOX.setText("330");
    txtOY.setText("105");
    gridLayout1.setColumns(3);
    gridLayout1.setRows(1);
    jP1.setLayout(gridLayout2);
    gridLayout2.setColumns(1);
    gridLayout2.setRows(3);
    txtAxioma.setColumns(10);
    txtAxioma.setText("F+F+F+F");
    jLrwRule.setText("Fractal");
    label2.setText("Iteracions");
    jLaxioma.setText("Axioma");
    jP2.setBackground(Color.orange);
    jP2.setLayout(gridLayout3);
    jP3.setBackground(Color.orange);
    jP3.setLayout(gridLayout4);
    gridLayout3.setColumns(1);
    gridLayout3.setRows(3);
    gridLayout4.setColumns(1);
    gridLayout4.setRows(2);
    jP1.setBackground(Color.orange);
    jPaxioma.setBackground(Color.orange);
    jPiteracions.setBackground(Color.orange);
    jPrwRule.setBackground(Color.orange);
    jPescala.setBackground(Color.orange);
    jPox.setBackground(Color.orange);
    jPoy.setBackground(Color.orange);
    jPdibuixa.setBackground(Color.orange);
    jPreset.setBackground(Color.orange);
    jPrwRule.add(jLaxioma, null);
    jPrwRule.add(txtAxioma, null);
    superior.add(jP1, null);
    jP1.add(jPaxioma, null);
    jPaxioma.add(jLrwRule, null);
    jPaxioma.add(cbRwRule, null);
    jP1.add(jPiteracions, null);
    jPiteracions.add(label2, null);
    jPiteracions.add(txtIteracio, null);
    jPiteracions.add(txtIteracio, null);
    jP1.add(jPrwRule, null);
    superior.add(jP2, null);
    jP2.add(jPescala, null);
    jPescala.add(label4, null);
    jPescala.add(txtEscala, null);
    jP2.add(jPox, null);
    jPox.add(label5, null);
    jPox.add(txtOX, null);
    jP2.add(jPoy, null);
    jPoy.add(label6, null);
    jPoy.add(txtOY, null);
    superior.add(jP3, null);
    jP3.add(jPdibuixa, null);
    jPdibuixa.add(dibuixa, null);
    jP3.add(jPreset, null);
    jPreset.add(bReset, null);
    jPbase.add(inferior, BorderLayout.CENTER);
    jPbase.add(jPcredits,  BorderLayout.SOUTH);
    jPcredits.add(jLcredits, null);
  }
  //Iniciar el applet
  public void start() {
  }
  //Detener el applet
  public void stop() {
  }
  //Destruir el applet
  public void destroy() {
  }
  //Obtener información del applet
  public String getAppletInfo() {
    return "Información del applet";
  }
  //Obtener información del parámetro
  public String[][] getParameterInfo() {
    return null;
  }

  void dibuixa_actionPerformed(ActionEvent e) {
    Kernel objKernel=new Kernel(Double.parseDouble(txtOX.getText()),Double.parseDouble(txtOY.getText()));
    Graphics2D g2d=(Graphics2D) inferior.getGraphics();
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0,0,inferior.getWidth(),inferior.getHeight());
    g2d.setColor(Color.BLACK);
   //Axioma = arrayNiteracio(0), per definició.
   arrayNiteracio.clear();
   arrayNiteracio.add(txtAxioma.getText());
   for(int i=0;i<Integer.parseInt(txtIteracio.getText());i++){
    strResultat=objKernel.substitueix(selectedFractal,arrayNiteracio.get(i).toString(),"F");
    arrayNiteracio.add(i+1,strResultat);
   }
   objKernel.llegeix(strResultat,inferior,Double.parseDouble(txtEscala.getText()));
 }

  void bReset_actionPerformed(ActionEvent e) {
    inferior.repaint();
  }

  void cbRwRule_actionPerformed(ActionEvent e) {
    if(cbRwRule.getSelectedItem().toString().equals("Illa de Kock")){
      selectedFractal="F+F-F-FF+F+F-F";
    } else if(cbRwRule.getSelectedItem().toString().equals("Corba 1")){
      selectedFractal="FF+F+F+F+F+F-F";
    } else if(cbRwRule.getSelectedItem().toString().equals("Lily")){
      selectedFractal="FF+F+F+F+FF";
    } else if(cbRwRule.getSelectedItem().toString().equals("Quadrat de Sierpinsky")){
      selectedFractal="F+F-F+F+F";
    } else if(cbRwRule.getSelectedItem().toString().equals("Piràmide de Kock")){
      selectedFractal="F-F+F+F-F";
    }
  }
}

class dibuixaRectangle_dibuixa_actionAdapter implements java.awt.event.ActionListener {
  Base adaptee;

  dibuixaRectangle_dibuixa_actionAdapter(Base adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.dibuixa_actionPerformed(e);
  }
}

class dibuixaRectangle_bReset_actionAdapter implements java.awt.event.ActionListener {
  Base adaptee;

  dibuixaRectangle_bReset_actionAdapter(Base adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.bReset_actionPerformed(e);
  }
}

class Base_cbRwRule_actionAdapter implements java.awt.event.ActionListener {
  Base adaptee;

  Base_cbRwRule_actionAdapter(Base adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.cbRwRule_actionPerformed(e);
  }
}


