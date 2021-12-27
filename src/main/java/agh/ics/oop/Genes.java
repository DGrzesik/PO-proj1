package agh.ics.oop;

import java.util.Arrays;
import java.util.Random;

public class Genes {
    int[] genes=new int[32];
    public Genes(){
        for(int i=0;i<32;i++){
            genes[i]=new Random().nextInt(8);
        }
        fixgenes();
    }
    public void fixgenes(){
        int[] ilegenow;
        int maxgen;
        int maxilosc;
        int gen0;
        int ilepositive;
        while(true){
            ilegenow=new int[]{0,0,0,0,0,0,0,0};
            maxgen=-1;
            maxilosc=-1;
            gen0=-1;
            ilepositive=0;
            for (int i=0;i<32;i++){
                ilegenow[genes[i]]+=1;
            }
            for(int i=0;i<8;i++){
                if(ilegenow[i]==0){
                    if (gen0==-1){
                        gen0=i;
                    }
                }
                if (ilegenow[i]>0){
                    ilepositive+=1;
                }
                if(ilegenow[i]>maxilosc){
                    maxgen=i;
                    maxilosc=ilegenow[i];
                }
            }
            if(ilepositive==8){
                break;
            }
            for(int i=0;i<32;i++){
                if (genes[i]==maxgen){
                    genes[i]=gen0;
                    break;
                }
            }
            Arrays.sort(genes);

//            int check=0;
//            int tmp=0;
//            for (int i=0;i<8;i++){
//                for (int j=0;j<32;j++){
//                    if (i==genes[j]){
//                        check+=1;
//                        tmp+=1;
//                        break;
//                    }
//                }
//                if (tmp==0){
//                    genes[new Random().nextInt(31)]=i;
//                }
//            }
//            if (check==8) {
//                break;
//            }
//        }
//        Arrays.sort(genes);
        }
    }
    public void makegenes(int x,int[] left,int[] right){
        for(int i=0;i<x;i++){
            genes[i]=left[i];
        }
        for(int i=x;i<32-x;i++){
            genes[i]=right[i];
        }
        fixgenes();
    }
}
