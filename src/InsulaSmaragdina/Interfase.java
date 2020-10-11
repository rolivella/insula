package InsulaSmaragdina;

import java.applet.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;

import com.borland.jbcl.layout.*;
import javax.swing.UIManager;


public class Interfase extends Applet {

  ArrayList arrayNiteracio = new ArrayList();
  ArrayList arrayResultatLectura = new ArrayList();
  String strResultat = "";

  private boolean isStandalone = false;

  Panel objPanel = new Panel();
  XYLayout xYLayout1 = new XYLayout();
  Label label1 = new Label();
  Label label2 = new Label();
  Label label3 = new Label();
  Label label4 = new Label();
  Label label5 = new Label();
  Label label6 = new Label();
  Label label7 = new Label();
  Label label8 = new Label();
  Label label9 = new Label();
  TextField txtAxioma = new TextField();
  Choice cRwRule = new Choice();
  TextField txtEscala = new TextField();
  TextField txtIteracio = new TextField();
  TextField txtCentreX = new TextField();
  TextField txtCentreY = new TextField();
  TextField txtAmple = new TextField();
  TextField txtAltura = new TextField();
  TextArea txtaOutput = new TextArea();
  Button bDibuixa = new Button();
  Label label10 = new Label();
  Label label11 = new Label();
  Label label12 = new Label();
  TextField txtMidaLletra = new TextField();
  Choice cTipusLletra = new Choice();
  Choice cDir = new Choice();
  XYLayout xYLayout2 = new XYLayout();
  //Get a parameter value
  public String getParameter(String key, String def) {
    return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

  //Construct the applet
  public Interfase() {
  }

  //Initialize the applet
  public void init() {
    cRwRule.addItem("F+F-F-FF+F+F-F");
    cTipusLletra.addItem("Dialog");
    cTipusLletra.addItem("DialogInput");
    cTipusLletra.addItem("Monospaced");
    cTipusLletra.addItem("Dialog");
    cTipusLletra.addItem("Serif");
    cTipusLletra.addItem("SansSerif");
    cDir.addItem("/home/rolivella/tallerDir/prova.png");
    cDir.addItem("c:/tallerDir/prova.png");

    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception {
    label1.setText("Axioma");
    this.setLayout(xYLayout2);
    label2.setLocale(java.util.Locale.getDefault());
    label2.setText("Rewriting rule");
    label3.setLocale(java.util.Locale.getDefault());
    label3.setText("Escala");
    label4.setText("Iteracions");
    label5.setLocale(java.util.Locale.getDefault());
    label5.setText("Centre X");
    label6.setText("Centre Y");
    label7.setText("Ample imatge");
    label8.setText("Altura imatge");
    label8.setVisible(true);
    label9.setLocale(java.util.Locale.getDefault());
    label9.setText("Output");
    txtAxioma.setText("F+F+F+F");
    txtEscala.setText("50");
    txtIteracio.setText("2");
    txtCentreX.setText("500");
    txtCentreY.setText("500");
    txtAmple.setText("2000");
    txtAmple.setVisible(true);
    txtAltura.setSelectionEnd(3);
    txtAltura.setText("2000");
    bDibuixa.setLabel("Dibuixa!");
    bDibuixa.addActionListener(new Interfase_bDibuixa_actionAdapter(this));
    txtaOutput.setEditable(false);
    txtaOutput.setText("");
    this.setBackground(UIManager.getColor("OptionPane.questionDialog.titlePane.background"));
    xYLayout1.setWidth(476);
    xYLayout1.setHeight(441);
    label10.setText("Directòri Output");
    label11.setLocale(java.util.Locale.getDefault());
    label11.setText("Tipus lletra");
    label12.setText("Mida lletra");
    txtMidaLletra.setText("10");
    xYLayout2.setWidth(590);
    xYLayout2.setHeight(369);
    this.add(txtaOutput,    new XYConstraints(240, 112, 315, 116));
    this.add(label9, new XYConstraints(341, 84, -1, -1));
    this.add(label1, new XYConstraints(42, 23, -1, -1));
    this.add(label2, new XYConstraints(40, 52, -1, -1));
    this.add(label4, new XYConstraints(40, 84, -1, -1));
    this.add(label5, new XYConstraints(37, 139, -1, -1));
    this.add(label3, new XYConstraints(38, 112, -1, -1));
    this.add(label6, new XYConstraints(37, 169, -1, -1));
    this.add(label8, new XYConstraints(35, 207, -1, -1));
    this.add(label7, new XYConstraints(33, 231, -1, -1));
    this.add(label10, new XYConstraints(34, 262, -1, -1));
    this.add(label12, new XYConstraints(35, 292, -1, -1));
    this.add(label11, new XYConstraints(32, 319, -1, -1));
    this.add(txtAxioma, new XYConstraints(105, 18, -1, -1));
    this.add(cRwRule, new XYConstraints(124, 48, -1, -1));
    this.add(txtIteracio, new XYConstraints(106, 79, -1, -1));
    this.add(txtEscala, new XYConstraints(94, 110, -1, -1));
    this.add(txtCentreX, new XYConstraints(110, 136, -1, -1));
    this.add(txtCentreY, new XYConstraints(109, 163, -1, -1));
    this.add(txtAltura, new XYConstraints(116, 203, -1, -1));
    this.add(txtAmple, new XYConstraints(117, 229, -1, -1));
    this.add(cDir, new XYConstraints(134, 260, -1, -1));
    this.add(txtMidaLletra, new XYConstraints(107, 291, -1, -1));
    this.add(cTipusLletra, new XYConstraints(107, 318, -1, -1));
    this.add(bDibuixa, new XYConstraints(407, 236, 82, 44));
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
  //Get Applet information
  public String getAppletInfo() {
    return "Applet Information";
  }
  //Get parameter info
  public String[][] getParameterInfo() {
    return null;
  }

  void bDibuixa_actionPerformed(ActionEvent e) {

    double centreX=Double.parseDouble(txtCentreX.getText());
    double centreY=Double.parseDouble(txtCentreY.getText());
    double dEscala=Double.parseDouble(txtEscala.getText());
    int iAltura=Integer.parseInt(txtAltura.getText());
    int iAmple=Integer.parseInt(txtAmple.getText());
    int strMidaLletra=Integer.parseInt(txtMidaLletra.getText());
    boolean bFlagFileNotFound=false;
    String strOutputDir=cDir.getSelectedItem().toString();
    String strTipusLletra=cTipusLletra.getSelectedItem().toString();
    Kernel objKernel=new Kernel(centreX,centreY,iAltura,iAmple,strOutputDir,strTipusLletra,strMidaLletra);
    PostProcess popr=new PostProcess();

    arrayNiteracio.clear();//neteja array d'iteracions
    arrayNiteracio.add(txtAxioma.getText());//Axioma = arrayNiteracio(0), per definició.

    //SUBSTITUEIX
    for (int i = 0; i < Integer.parseInt(txtIteracio.getText()); i++) {
      strResultat = objKernel.substitueix(cRwRule.getSelectedItem().toString(),arrayNiteracio.get(i).toString(), "F");
      arrayNiteracio.add(i + 1, strResultat);
    }

    //LLEGEIX
    ArrayList arrayOrdresTortuga=objKernel.llegeix(strResultat, objPanel, dEscala);

    //DIBUIXA
    objKernel.gestionaDibuix(dEscala,objPanel);

    objPanel.setSize(new Dimension(iAmple,iAltura));//dimensiona el panell de sortida
    objPanel.paint(objKernel.getGraphics2D());//pinta la figura sobre el panell
    String strMissatgeRetorn=popr.FileOps(objKernel.getBufferedImage(),strOutputDir);//salva la imatge amb el dibuix
    txtaOutput.append(strMissatgeRetorn+"\n");
  }

}


class Interfase_bDibuixa_actionAdapter implements java.awt.event.ActionListener {
  Interfase adaptee;

  Interfase_bDibuixa_actionAdapter(Interfase adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.bDibuixa_actionPerformed(e);
  }
}
