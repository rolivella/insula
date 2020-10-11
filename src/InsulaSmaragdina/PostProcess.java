package InsulaSmaragdina;

import java.io.*;
import java.util.*;
import javax.media.jai.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.image.renderable.*;

class PostProcess {

  private ArrayList arrayX = new ArrayList();
  private ArrayList arrayY = new ArrayList();
  private float fXactual=0;
  private float fYactual=0;
  float fMinAbsolutX,fMaxAbsolutX,fMinAbsolutY,fMaxAbsolutY=0;

  public void setXY(float Xlletra,float Ylletra){
    arrayX.add(new Float(Xlletra));
    arrayY.add(new Float(Ylletra));
  }

  public void calculaMinMaxAbsolutX(){
    fMinAbsolutX = Float.parseFloat(arrayX.get(0).toString());
    fMaxAbsolutX = fMinAbsolutX;
    for (int m = 0; m < arrayX.size(); m++) {
      fXactual = Float.parseFloat(arrayX.get(m).toString());
      if (fXactual < fMinAbsolutX) fMinAbsolutX = fXactual;
      if (fXactual > fMaxAbsolutX) fMaxAbsolutX = fXactual;
    }
  }

  public void calculaMinMaxAbsolutY(){
    fMinAbsolutY = Float.parseFloat(arrayY.get(0).toString());
    fMaxAbsolutY = fMinAbsolutY;
    for (int m = 0; m < arrayY.size(); m++) {
      fYactual = Float.parseFloat(arrayY.get(m).toString());
      if (fYactual < fMinAbsolutY) fMinAbsolutY = fYactual;
      if (fYactual > fMaxAbsolutY) fMaxAbsolutY = fYactual;
    }
  }

  /*public static RenderedOp cropImage(PlanarImage image, Rectangle rect){
   ParameterBlock pb = new ParameterBlock();
   pb.addSource(image);
   pb.add((float)rect.x);
   pb.add((float)rect.y);
   pb.add((float)rect.width);
   pb.add((float)rect.height);
   return JAI.create("crop", pb);
   }*/

  public String FileOps(BufferedImage bi,String strDirOutputImg){

   String strMissatgeRetorn="";
   boolean bFlagFileNotFound=false;

   //popr.calculaMinMaxAbsolutX();
   //popr.calculaMinMaxAbsolutY();
   //g2d.drawLine((int)popr.fMinAbsolutX-3,(int)(popr.fMinAbsolutY-fAlturaLletra-3),(int)(popr.fMaxAbsolutX+fAlturaLletra+1),(int)(popr.fMinAbsolutY-fAlturaLletra-3));//recta superior
   //g2d.drawLine((int)(popr.fMaxAbsolutX+fAlturaLletra+1),(int)(popr.fMinAbsolutY-fAlturaLletra-2),(int)(popr.fMaxAbsolutX+fAlturaLletra+1),(int)popr.fMaxAbsolutY+5);//recta dreta
   //g2d.drawLine((int)(popr.fMaxAbsolutX+fAlturaLletra+1),(int)popr.fMaxAbsolutY+5,(int)popr.fMinAbsolutX-3,(int)popr.fMaxAbsolutY+5);//recta inferior
   //g2d.drawLine((int)popr.fMinAbsolutX-3,(int)popr.fMaxAbsolutY+5,(int)popr.fMinAbsolutX-3,(int)(popr.fMinAbsolutY-fAlturaLletra-2));//recta esquerra

   PlanarImage source = null;
   ParameterBlock pb = new ParameterBlock();
   FileOutputStream fop = null;
   pb.add(bi);

   try{
           fop = new FileOutputStream(strDirOutputImg);
           source = (PlanarImage)JAI.create("awtImage",pb);
           //source=popr.cropImage(source,new Rectangle(200,200,400,400));
           source = JAI.create("encode", source,fop, "PNG", null);
   } catch (FileNotFoundException e){
           bFlagFileNotFound=true;
   } catch (OutOfMemoryError ome){
           strMissatgeRetorn="No hi ha prou memòria necessària.\n";
   }
   if(bFlagFileNotFound) {
     strMissatgeRetorn="Directòri o nom de l'arxiu no vàlid";
   } else {
     //strMissatgeRetorn="Fet! Nombre de rectes "+iConrRectDib+", nombre de lletres "+iConrRectDib*5+".";
     strMissatgeRetorn="Fet! ";
   }
  return strMissatgeRetorn;
 }

}
