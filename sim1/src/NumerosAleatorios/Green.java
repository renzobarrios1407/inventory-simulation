

package NumerosAleatorios;

import java.util.ArrayList;
import NumerosAleatorios.Fibonacci;
import java.util.Calendar;
import javax.swing.JOptionPane;

 
public class Green {

    public Green() {
    }
     private  ArrayList<Float> metodoAditivoFibonacci(int m,int x, int x0){
        ArrayList<Float> nums = new ArrayList<Float>();
        int i=0;
        if(m>0 && (x>0 || x0>0)){
            do{
                int aux=x;
                int r = (x0+x)%m;
                x0=aux;
                x=r;
                float resultado=(float)r/m;
                nums.add(new Float(resultado));
                //System.out.println(i);
                i++;
            }while(i<m);
        }
        return nums;
    }
    
      public ArrayList<Float> metodoAditivoGreen(int m,int k){
        ArrayList<Float> nums = new ArrayList<Float>();
//        Calendar cal= Calendar.getInstance();
        ArrayList<Float> vectorGreen=new ArrayList<Float> ();
//        vectorGreen.addAll(metodoAditivoFibonacci(k, cal.get(Calendar.MILLISECOND)/1000, cal.get(Calendar.SECOND)/60));
        vectorGreen.addAll(metodoAditivoFibonacci(m, 1, 0));
//        System.out.println(vectorGreen.size());
        if(m>k&&k>=16){
            for(int i=0;i<m;i++){
                int r =0;
                int xn = (int) (vectorGreen.get(vectorGreen.size() - k) * m);
                int xnk = (int) (vectorGreen.get(i)*m);
                r= (xn+xnk)%m;
//                System.out.println("x: "+xn);
                float resultado=(float)r/m;
                vectorGreen.add(new Float(resultado));
                nums.add(new Float(resultado));
    //            System.out.println(nums.size());
            }
        }else{
            JOptionPane.showMessageDialog(null, "k debe de ser >= 16 y m > k");
        }

        return nums;
    }
      
    
    
    
}
