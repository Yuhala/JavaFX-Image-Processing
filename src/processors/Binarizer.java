/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processors;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author peterson
 */
public class Binarizer {

    private BufferedImage img;
    private int level1, level2;
    private ArrayList<Integer> rgb=new ArrayList<Integer>();

    public Binarizer(BufferedImage bi, int below, int above) {
        this.img = bi;
        this.level1 = below;
        this.level2 = above;
        this.rgb.add(0);this.rgb.add(1);this.rgb.add(1);

    }

    public BufferedImage binarise(BufferedImage bi) {
      
        int width = bi.getWidth();
        int height = bi.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int colors = bi.getRGB(i, j);

                int r = (colors >> 16) & 0xff;
                int g = (colors >> 8) & 0xff;
                int b = (colors >> 0) & 0xff;
               // this.rgb.set(0,r);this.rgb.set(1,g);this.rgb.set(2,b);
                double avg=(r+g+b)/3;
                boolean test=(avg<=level2&&avg>=level1);
                // 
               if(test){
               r=g=b=255;
               }
               
               else {
               r=g=b=0;
               
               }
                
                
      
          //((blue&0xFF)+((green&0xFF) << 8)+((red&0xFF) << 16)+(0xFF << 24))
                int val= ((b&0xFF)+((g&0xFF) << 8)+((r&0xFF) << 16)+(0xFF << 24));
                bi.setRGB(i, j, val);
            }

            this.img = bi;

        }
        return this.img;
    }
}
