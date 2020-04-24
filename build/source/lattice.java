import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class lattice extends PApplet {

// Variables
int pen = 0;
int msx;
int msy;
int penStroke = 0;
float penStrokeWeight = 8;
float backgroundHue = constrain(180,10,360);
int backgroundSat = constrain(80,1,100);
int backgroundBright = constrain(50,1,100);
float complimentBackgroundHue = 100;
char input;
int backgroundSize = 600;
int maxSize = 20;
PShape bg;

float gridX;
float gridY;

public void setup(){
  // Defining Color Mode
  colorMode(HSB, 360, 100, 100);
  // Setting up the space
  
  background(backgroundHue,100,100);
//    bg = createShape(RECT, 20, 20, 200, 200);
//  bg.setFill(color(random(255)));
//  pushMatrix();
//translate(200, 220, -100);
//shape(bg);
//popMatrix();

  //stroke(constrain(255-round(penStrokeWeight*10), 0, 255));
  //fill(constrain(255-round(penStrokeWeight*10), 0, 255));
  //strokeWeight(penStrokeWeight);
  //ellipse(20,20,10,10);
}

public void draw(){
  // If pen is true
  if(pen == 1){

    // Even or Odd Number
    if(mouseX%2 != 0){
     msx = constrain(((pmouseX+(maxSize/2))/maxSize)*maxSize,80,480);
    } else {
      // Offscreen Need to Fix
     msx = -100;
    }

    if(mouseY%2 != 0){
      msy = constrain(((pmouseY+(maxSize/2))/maxSize)*maxSize,80,480);
    } else {
      // Offscreen Need to Fix
      msy = -100;
    }
    //stroke(backgroundColor);
    //strokeWeight(20);
    // line(msx,msy,msx,msy);
    //fill(constrain(255-round(penStrokeWeight*12.75), 0, 255));
    // println("Background Hue: " + (backgroundHue));
    if(backgroundHue >=180) {
    complimentBackgroundHue = (backgroundHue)-180;
    } else {
      complimentBackgroundHue = (backgroundHue)+180;
    }
    // println("Compliment Background Hue: " + (backgroundHue/2));
    // println("Saturation of Stroke: " + (100-penStrokeWeight));
    stroke(constrain(complimentBackgroundHue, 0, 360),100,(100-(penStrokeWeight*2)));
    strokeWeight(penStrokeWeight);
    line(msx,msy,msx,msy);


  } else if(pen==2){

    stroke(constrain(complimentBackgroundHue, 0, 360),100,(100-(penStrokeWeight*2)));
    strokeWeight(penStrokeWeight);
    line(constrain(pmouseX,80,480),pmouseY,mouseX,mouseY);

  }

}

public void mousePressed(){
  if (penStroke==0) {
  pen = 1;
  } else {
    pen = 2;
  }
}

public void mouseReleased() {
  pen = 0;
}

public void keyPressed() {
    if (key == 'l' || key == 'L') {
      penStroke = 1;
    } else if (key == '[') {
      penStrokeWeight = constrain(penStrokeWeight-1, 0, maxSize);
    } else if (key == ']') {
      penStrokeWeight = constrain(penStrokeWeight+1, 0, maxSize);
    } else if (key == '\'') {
      maxSize = constrain(maxSize*2,10,80);
    } else if (key == ';') {
      maxSize = constrain(maxSize/2,10,80);
    } else if (key == 'h' || key == 'H') {
      if (backgroundHue >= 350) {
        backgroundHue = 10;
      } else {
      backgroundHue = backgroundHue+10;
      //bg.setFill(color(random(255)));
      //shape(bg);
      background(backgroundHue,100,100);
      }
    } else if (key == 'q') {
      gridX = 40;
      while (gridX < width) {
        stroke(complimentBackgroundHue,100,100);
        line(gridX, 0, gridX, height);
        gridX = constrain(gridX + 40,40,560);
      }
  } else {
    penStroke = 0;
  }
}

public void keyReleased() {
  if (key == 'q') {
    gridX = 0;
    while (gridX < width) {
      stroke(backgroundHue,100,100);
      line(gridX, 0, gridX, height);
      gridX = gridX + 40;
    }
  }
}
  public void settings() {  size(560, 560); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "lattice" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
