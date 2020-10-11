package Fractus;

import java.util.*;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Kernel {


  //Definició de variables
  private ArrayList arrayBibliotecaOrdresTortuga=new ArrayList();
  private ArrayList arrayBibliotecaOp=new ArrayList();
  private ArrayList arrayCadenaLlegida=new ArrayList();
  //Var. de carregaBibliotecaOrdresTortuga
  private int iNumCaractMaxOrdres=0;
  //Var. de llegeix
  private int p,q,r,l=0;
  //Coordenades del centre recalculades
  double dNouCentreX=0;
  double dNouCentreY=0;
  //Coordenades del centre antigues
  //double dCentreX=450;
  //double dCentreY=100;

  Graphics2D g2d;

  //Angles
  double dAngle=0;

  double dCentreX;
  double dCentreY;

  public Kernel(double OX, double OY){
   //Coordenades del centre antigues
   this.dCentreX=OX;
   this.dCentreY=OY;
 }

 public Kernel(){
 }



//Mètode SUBSTITUEIX
//Agafa la "rewriting rule" i la inserta a la n-èssima iteració segons el caràcter que es
//vol que trobi
public String substitueix(String strRwRule,String strNiteracio,String strCaracterBuscat){

  //Var. internes del mètode
  //Cadena que conté un troç o "chunk" de la cadena resultant de la n-èssima iteració
  String strChunkNiteracio = "";
  //Buffer de Strings d'output
  StringBuffer buffResultat = new StringBuffer();
  //Es carreguen els operadors
  carregaBibliotecaOp();
  for (int i = 0; i < strNiteracio.length(); i++) {
    strChunkNiteracio=strNiteracio.substring(i, i + 1);
    if (strCaracterBuscat.equals(strChunkNiteracio)) {
      buffResultat.append(strRwRule);
    }
    else {
      for (int j = 0; j < arrayBibliotecaOp.size(); j++) {
        String strComponentArrayBiblioOp=arrayBibliotecaOp.get(j).toString();
        if (strChunkNiteracio.equals(strComponentArrayBiblioOp)){
          buffResultat.append(strComponentArrayBiblioOp);
        }
      }
    }
  }
  String strAux=buffResultat.toString();
  return strAux;
}

  //Mètode LLEGEIX
  public void llegeix(String strRwRule, JPanel objPanel,double dEscala){

    //Es carrega l'array d'ordres de tortuga
    carregaBibliotecaOrdresTortuga();
    for(p=0;p<strRwRule.length();p++){
      for(q=0;q<iNumCaractMaxOrdres;q++){
        String strChunkRwRule=strRwRule.substring(p,p+q+1);
          for(r=0;r<arrayBibliotecaOrdresTortuga.size();r++){
            if(strChunkRwRule.equals(arrayBibliotecaOrdresTortuga.get(r).toString())){
              dibuixa(strChunkRwRule,dEscala,dCentreX,dCentreY,objPanel);
              //Es calcula el salt que s'ha de fer quan s'ha acabat de llegir una ordre
              p=p+arrayBibliotecaOrdresTortuga.get(r).toString().length()-1;
              //Var. que posa les ordres llegides a l'arrayCadenaLlegida
              l=l+1;
            }
          }
      }
    }
  }

  //Mètode GESTIONA DIBUIX
  public void dibuixa(String strOrdreTortuga,double dEscala,double dOX,double dOY,JPanel objPanel){

    dCentreX=dOX;
    dCentreY=dOY;
    //Valor d'escala del fractal
    double dLongitud=dEscala;
    //Escull l'angle amb el que haurà de dibuixar la tortuga per una
    //certa ordre
    if(strOrdreTortuga.equals("+F")){
      dAngle=dAngle+(Math.PI)/2;
    } else if(strOrdreTortuga.equals("-F")){
      dAngle=dAngle-(Math.PI)/2;
    } else if(strOrdreTortuga.equals("F")){
      dAngle=dAngle+0;
    }

    //Calcula les coordenades de destinació
    dNouCentreX=dLongitud*Math.cos(dAngle)+dCentreX;
    dNouCentreY=dLongitud*Math.sin(dAngle)+dCentreY;

    //Dibuixa
    g2d=(Graphics2D) objPanel.getGraphics();
    g2d.draw(new Line2D.Double(dCentreX,dCentreY,dNouCentreX,dNouCentreY));

    //Canvi del punt de partida de la tortuga
    dCentreX=dNouCentreX;
    dCentreY=dNouCentreY;
  }

  //Carrega la biblioteca d'ordres possibles que pot entendre la tortuga (privat)
  private void carregaBibliotecaOrdresTortuga(){
    arrayBibliotecaOrdresTortuga.clear();
    //Definició de l'array que conté TOTES les ordres.
    arrayBibliotecaOrdresTortuga.add("F");
    arrayBibliotecaOrdresTortuga.add("+F");
    arrayBibliotecaOrdresTortuga.add("-F");
    //Nombre de caracters màxims que pot contenir una ordre.
    iNumCaractMaxOrdres=2;
  }

  //Carrega la biblioteca d'operadors ("+","-", etc.)
  private void carregaBibliotecaOp(){
    arrayBibliotecaOp.clear();
    //Definició de l'array que conté TOTES les ordres.
    arrayBibliotecaOp.add("+");
    arrayBibliotecaOp.add("-");
  }
}
