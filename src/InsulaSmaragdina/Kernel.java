package InsulaSmaragdina;

import java.util.*;

import java.awt.*;
import java.awt.image.*;

public class Kernel {

  double dCentreX,dCentreY;//Coordenades del centre antigues
  double dNouCentreX = 0;//Coordenada X del centre recalculada
  double dNouCentreY = 0;//Coordenada Y del centre recalculada
  double dAngle = 0;//Angle de dibuix de la tortuga
  float fXactual,fYactual=0;
  float fMinAbsolutX,fMaxAbsolutX,fMinAbsolutY,fMaxAbsolutY=0;
  float fAlturaLletra;
  int iAmple,iAltura;//Dimensions de la imatge final
  int iFontSize;//Mida de la lletra
  int iNumCaractMaxOrdres = 0;//Var. de carregaBibliotecaOrdresTortuga
  int p, q, r, l,m = 0;//Var. de llegeix
  int iConrRectDib = 0; //Contador de rectes dibuixades
  /*Es fixa que el nombre de divisions (allí on s'escriurán les lletres)
  sigui 7. Per mantenir la multiplicitat de 5,
  es farà que per 7 divisions dins un mateix segment de recta, s'omplin només 5 espais
  amb lletres. El primer i últim espai es deixen en blanc. Això únicament es fa per millorar
  substancialment la llegibilitat del text*/
  int iNombreDivisions = 7;
  int iNombreEspaisBuits = 2;
  int iFinalText = iNombreDivisions - 2;
  int iIniciText = 0;//Variable de control de la lletra en strbTextTabulaSmaragdina
  boolean bFlagDuesIgualsInici, bFlagDuesIgualsFinal = false;
  //boolean bFlagFileNotFound=false;
  String strFontType;//Tipus de la font de la lletra
  String strDirOutputImg;//Directòri d'output de la imatge
  String strMissatgeRetorn;
  //Definició del text a escriure:
  //StringBuffer strbTextTabulaSmaragdina = new StringBuffer("quodestinferiusestsicutquodestsuperiusetquodestsuperiusestsicutquodestinferiuset");
  StringBuffer strbTextTabulaSmaragdina=new StringBuffer("QUODESTINFERIUSESTSICUTQUODESTSUPERIUSETQUODESTSUPERIUSESTSICUTQUODESTINFERIUSET");
  //StringBuffer strbTextTabulaSmaragdina=new StringBuffer("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
  BufferedImage bi;
  Graphics2D g2d;
  FontMetrics fm;
  ArrayList arrayBibliotecaOrdresTortuga = new ArrayList();
  ArrayList arrayBibliotecaOp = new ArrayList();
  ArrayList arrayCadenaLlegida = new ArrayList();
  ArrayList arrayOrdresTortuga = new ArrayList();


  //Constructor per defecte
  Kernel (){
  }

  //Constructor
  Kernel (double dCX,double dCY,int iAlt,int iAmp,String strDir, String strFontype, int iFontsiz) {
    this.dCentreX=dCX;
    this.dCentreY=dCY;
    this.iAltura=iAlt;
    this.iAmple=iAmp;
    this.strDirOutputImg=strDir;
    this.strFontType=strFontype;
    this.iFontSize=iFontsiz;
    bi = new BufferedImage(iAmple,iAltura,BufferedImage.TYPE_BYTE_BINARY);
    g2d = bi.createGraphics();
    g2d.setColor(Color.WHITE);//Fixa el color del background de la imatge. En aquest cas, el BLANC.
    g2d.fillRect(0,0,iAmple,iAltura);
    //Es fixa el tipus i mida de la lletra.
    Font f;
    f = new Font(strFontType, Font.PLAIN, iFontSize);
    g2d.setFont(f);
    g2d.setColor(Color.BLACK);//color lletra. En aquest cas, negre.
    fm=g2d.getFontMetrics(f);
  }

  /*Mètode SUBSTITUEIX: Agafa la "rewriting rule" i la inserta a la n-èssima iteració segons el caràcter
   que es vol que trobi*/
  public String substitueix(String strRwRule,String strNiteracio,String strCaracterBuscat){
    String strChunkNiteracio = "";//Cadena que conté un troç o "chunk" de la cadena resultant de la n-èssima iteració
    StringBuffer buffResultat = new StringBuffer();//Buffer de Strings d'output
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
    return buffResultat.toString();
  }

  //Mètode LLEGEIX
  public ArrayList llegeix(String strRwRule, Panel objPanel,double dEscala){
    carregaBibliotecaOrdresTortuga();//Es carrega l'array d'ordres de tortuga
    /*Loop que examina cada ordre del RwRule, i quan en troba una reconeixible (segons la biblioteca
     d'operadors), la separa i guarda en una certa ubicació al arrayOrdresTortuga*/
    for(p=0;p<strRwRule.length();p++){
      for(q=0;q<iNumCaractMaxOrdres;q++){
          String strChunkRwRule=strRwRule.substring(p,p+q+1);
          for(r=0;r<arrayBibliotecaOrdresTortuga.size();r++){
            if(strChunkRwRule.equals(arrayBibliotecaOrdresTortuga.get(r).toString())){
              arrayOrdresTortuga.add(strChunkRwRule);
              p=p+arrayBibliotecaOrdresTortuga.get(r).toString().length()-1;//Es calcula el salt que s'ha de fer quan s'ha acabat de llegir una ordre
              l=l+1;//Var. que posa les ordres llegides a l'arrayCadenaLlegida
            }
          }
      }
    }
    return arrayOrdresTortuga;
  }

  /*Loop que, una vegada llegit l'String de RwRule i guardat a l'array, crida al mètode dibuixa
  per cada una de les ordres emmagatzemades. Té una particularitat: els parèmtres que se li envien
  a dibuixa() depenen de l'index de l'array amb les ordres tortuga. P.ex., si es la primera ordre,
  obviament no té sentit enviar a dibuixa() l'ordre anterior; per això se li envia String buit o "" */
  public void gestionaDibuix(double dEscala, Panel objPanel){
    for (int m=0;m<arrayOrdresTortuga.size();m++){
      if(m==arrayOrdresTortuga.size()-1){
        dibuixa(arrayOrdresTortuga.get(m).toString(),"",arrayOrdresTortuga.get(m-1).toString(),dEscala,dCentreX,dCentreY,objPanel);
      } else if (m==0){
          dibuixa(arrayOrdresTortuga.get(m).toString(),arrayOrdresTortuga.get(m+1).toString(),"",dEscala,dCentreX,dCentreY,objPanel);
      } else {
          dibuixa(arrayOrdresTortuga.get(m).toString(),arrayOrdresTortuga.get(m+1).toString(),arrayOrdresTortuga.get(m-1).toString(),dEscala,dCentreX,dCentreY,objPanel);
      }
    }

  }

  //Mètode DIBUIXA
  public void dibuixa(String strOrdreTortuga,String strOrdreTortugaMesU,String strOrdreTortugaMenysU,double dEscala,double dOX,double dOY,Panel objPanel){

    bFlagDuesIgualsInici=false;//Inicialitza el flag inici
    bFlagDuesIgualsFinal=false;//Inicialitza el flag final
    dCentreX=dOX;
    dCentreY=dOY;
    double dLongitud=dEscala;//Valor d'escala del fractal

    //Escull l'angle amb el que haurà de dibuixar la tortuga per una certa ordre
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

    //Discerneix si hi han dues rectes consecutives amb igual direcció i sentit
    if((strOrdreTortugaMenysU.equals("-F") && strOrdreTortuga.equals("-F") && strOrdreTortugaMesU.equals("F"))) bFlagDuesIgualsInici=true;
    if((strOrdreTortugaMenysU.equals("-F") && strOrdreTortuga.equals("F") && strOrdreTortugaMesU.equals("+F"))) bFlagDuesIgualsFinal=true;
    if((strOrdreTortugaMenysU.equals("+F") && strOrdreTortuga.equals("-F") && strOrdreTortugaMesU.equals("F"))) bFlagDuesIgualsInici=true;

    //Dibuixa les linies que composen el fractal (no es necessària)
    //g2d.draw(new Line2D.Double(dCentreX,dCentreY,dNouCentreX,dNouCentreY));

    //Escriu el text corresponent
    escriuRecta(dCentreX,dCentreY,dNouCentreX,dNouCentreY,strbTextTabulaSmaragdina.substring(iIniciText,iFinalText),bFlagDuesIgualsInici,bFlagDuesIgualsFinal);
    iIniciText=iFinalText;
    iFinalText=iFinalText+iNombreDivisions-2;
    if(iFinalText>strbTextTabulaSmaragdina.length()) {
      iIniciText=0;
      iFinalText=iNombreDivisions-2;
    }

    //Contador de rectes dibuixades
    iConrRectDib=iConrRectDib+1;

    //Canvi del punt de partida de la tortuga
    dCentreX=dNouCentreX;
    dCentreY=dNouCentreY;
  }

  //Escriu el text sobre la recta
  private void escriuRecta(double dX0,double dY0,double dX1,double dY1,String strTextTabulaSmaragdina,boolean bFlagDuesIgualsInici,boolean bFlagDuesIgualsFinal){

    //Construcció del vector que defineix la recta
    double dX=dX1-dX0;
    double dY=dY1-dY0;

    //Classificació de la recta:
    //VU= Vertical UP (verical, en sentit cap amunt)
    //VD= Vertical Down (verical, en sentit cap abaix)
    //HR= Horitzontal Right (horitzontal, en sentit cap a la dreta)
    //HL= Horitzontal Left (horitzontal, en sentit cap a l'esquerra)
    /*Nota: s'ha de tenir en compte que el sistema de coordenades en el Panel
     és: 0 en la cantonada esquerra, amunt de tot. L'eix de les X es positiu des
     de l'origen cap a la dreta, i l'eix de les Y és positiu des de l'origen cap abaix.
     És a dir, la única diferència amb un sistema coordenat standard és que l'eix de les Y
     és positiu en sentit contràri*/
     String strTipusRecta="";
     if(dX==0 && dY<0) strTipusRecta="VU";
     if(dX==0 && dY>0) strTipusRecta="VD";
     if(dX>0 && dY==0) strTipusRecta="HR";
     if(dX<0 && dY==0) strTipusRecta="HL";

     float fLongRecta=(float) Math.sqrt(dX*dX+dY*dY);  //Càlcul del mòdul del vector que defineix cada recta
     float fLongDivisioRectaBuida=fLongRecta/20; //literal arbitràri

     //Amplada d'una divisió normal de tots els segments de recta (allí on s'hi ubicarà cada lletra 'q','o', etc.)
     float fLongDivisioRecta=0;
     if(bFlagDuesIgualsInici==false && bFlagDuesIgualsFinal==false){
       fLongDivisioRecta=(fLongRecta-2*fLongDivisioRectaBuida)/(iNombreDivisions-iNombreEspaisBuits);
     } else if (bFlagDuesIgualsInici==true || bFlagDuesIgualsFinal==true){
       fLongDivisioRecta=(2*fLongRecta-2*fLongDivisioRectaBuida)/(2*iNombreDivisions-2*iNombreEspaisBuits);
     }

     fAlturaLletra=7;//Parmàtere arbitràri

     int j=0;
     float Xlletra=0;
     float Ylletra=0;

     for(int i=0;i<iNombreDivisions;i++){
       if(i==0 || i==iNombreDivisions-1){ //Divisions nº 0 i 6
         if(bFlagDuesIgualsInici==true){
           if (strTipusRecta == "HR") {
             dX0 = dX0 + fLongDivisioRectaBuida;
           }
           else if (strTipusRecta == "HL") {
             dX0 = dX0 - fLongDivisioRectaBuida;
           }
           else if (strTipusRecta == "VD") {
             dY0 = dY0 + fLongDivisioRectaBuida;
           }
           else if (strTipusRecta == "VU") {
             dY0 = dY0 - fLongDivisioRectaBuida;
           }
         } else if (bFlagDuesIgualsFinal==true){
           if (strTipusRecta == "HR") {
             dX0 = dX0 ;
           }
           else if (strTipusRecta == "HL") {
             dX0 = dX0 ;
           }
           else if (strTipusRecta == "VD") {
             dY0 = dY0 ;
           }
           else if (strTipusRecta == "VU") {
             dY0 = dY0 ;
           }
         } else {

           if (strTipusRecta == "HR") {
             dX0 = dX0 + fLongDivisioRectaBuida;
           }
           else if (strTipusRecta == "HL") {
             dX0 = dX0 - fLongDivisioRectaBuida;
           }
           else if (strTipusRecta == "VD") {
             dY0 = dY0 + fLongDivisioRectaBuida;
           }
           else if (strTipusRecta == "VU") {
             dY0 = dY0 - fLongDivisioRectaBuida;
           }
         }
       }
       else if (i != 0 && i != iNombreDivisions - 1) { //Divisions de la nº 1 a la 5
         char cLletraTabulaSmaragdina=strTextTabulaSmaragdina.charAt(j);
         float fAmpladaLletra = (float) fm.charWidth(cLletraTabulaSmaragdina);
         if (strTipusRecta == "HR") {
           Xlletra = (float) dX0 + fLongDivisioRecta / 2 - fAmpladaLletra / 2;
           Ylletra = (float) dY0 + fAlturaLletra/2;
           dX0 = dX0 + fLongDivisioRecta;
         }
         else if (strTipusRecta == "HL") {
           Xlletra = (float) dX0 - fLongDivisioRecta / 2 - fAmpladaLletra / 2;
           Ylletra = (float) dY0 + fAlturaLletra/2;
           dX0 = dX0 - fLongDivisioRecta;
         }
         else if (strTipusRecta == "VD") {
           Xlletra = (float) dX0 - fAmpladaLletra / 2;
           Ylletra = (float) dY0 + fLongDivisioRecta / 2 + fAlturaLletra/2;
           dY0 = dY0 + fLongDivisioRecta;
         }
         else if (strTipusRecta == "VU") {
           Xlletra = (float) dX0 - fAmpladaLletra / 2;
           Ylletra = (float) dY0 - fLongDivisioRecta / 2 + fAlturaLletra/2;
           dY0 = dY0 - fLongDivisioRecta;
         }
         g2d.drawString(Character.toString(cLletraTabulaSmaragdina), Xlletra, Ylletra);
         if(j<iNombreDivisions-iNombreEspaisBuits) j=j+1; else j=0;
       }
     }
  }

  //Carrega la biblioteca d'ordres possibles que pot entendre la tortuga (privat)
  private void carregaBibliotecaOrdresTortuga(){
    arrayBibliotecaOrdresTortuga.clear();
    //Definició de l'array que conté TOTES les ordres.
    arrayBibliotecaOrdresTortuga.add("F");
    arrayBibliotecaOrdresTortuga.add("+F");
    arrayBibliotecaOrdresTortuga.add("-F");
    iNumCaractMaxOrdres=2;//Nombre de caracters màxims que pot contenir una ordre.
  }

  //Carrega la biblioteca d'operadors ("+","-", etc.)
  private void carregaBibliotecaOp(){
    arrayBibliotecaOp.clear();
    //Definició de l'array que conté TOTES les ordres.
    arrayBibliotecaOp.add("+");
    arrayBibliotecaOp.add("-");
  }

  public Graphics2D getGraphics2D(){
    return g2d;
  }

  public BufferedImage getBufferedImage(){
    return bi;
  }

}