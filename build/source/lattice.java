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

// Declaring some classes // Need to be capitalized
Colorwheel c;
Linetool l;
Dottool d;
Gridtool g;

// Colorwheel Variables
float primarySat = constrain(100,1,100);
float primaryBright = constrain(50,1,100);
float primaryHue = constrain(180,0,360);
float complimentaryHue = 360;

// Variables
char mode = 'd';
boolean mouse = false;

float penStrokeWeight = 5;
char input;

int maxSize = 20;
int margin = 80;



public void setup() {
  // Objects
  c = new Colorwheel();
  l = new Linetool();
  d = new Dottool();
  g = new Gridtool();

  // Defining Color Mode and size
  colorMode(HSB, 360, 100, 100);
  
  background(primaryHue,100,100);
}

public void draw() {
  if(mouse == true) {
    if(mode == 'd') {
      d.dotTool();
    } else if(mode == 'l') {
      l.lineTool();
    }
  }
}

public void mousePressed() {
  mouse = true;
}

public void mouseReleased() {
  mouse = false;
}

public void keyPressed() {
    char keyboard = key;
    switch(keyboard) {
      // Turn on lines
      case 'l':
      case 'L':
        mode = 'l';
        break;
      case 'k':
      case 'K':
        mode = 'd';
        break;
      // Making stroke weight smaller
      case '[':
        penStrokeWeight = constrain(penStrokeWeight - 1, 0, maxSize);
        break;
      // Making stroke weight bigger
      case ']':
        penStrokeWeight = constrain(penStrokeWeight+1, 0, maxSize);
        break;
      // Increasing grid
      case '\'':
        maxSize = constrain(maxSize*2,10,80);
        break;
      // Decreasing grid
      case ';':
        maxSize = constrain(maxSize/2,10,80);
        break;
      // Hue Shifting the Background
      case 'h':
      case 'H':
        primaryHue = c.hueShift(primaryHue);
        background(primaryHue,100,100);
        break;
      case 'q':
        g.makeGrid();
      default:
        mouse = false;
        break;
    }

}

public void keyReleased() {
  if (key == 'q') {
      g.killGrid();
  }
}
class Colorwheel {
  
  Colorwheel() {


  }
  // Shift the hue by 30 degrees
  public float hueShift(float p) {
    if (p >= 331) {
      p = 0;
    } else {
      p = p + 30;
    }
    return p;
  }

  // Turn colors complimentary
  public float complimentaryHue(float p, float c) {
    if (p >= 180) {
      c = (p)-180;
    } else {
      c = (p)+180;
    }
    return c;
  }

  public void splitComplimentaryHue() {
    // half and then plus and minus 30 on color wheel
  }

  public void analagousHue() {
    // plus and minus 30 on color wheel
  }

  public void triadicHue() {
    // plus 120 and minus 120
  }

}
class Dottool {

  float msx = 0;
  float msy = 0;

  Dottool() {

  }

  public void dotTool() {
    if(mouseX%2 != 0) {
      msx = constrain(((pmouseX+(maxSize/2))/maxSize)*maxSize, margin, width - margin);
    } else {
      // Offscreen Need to Fix
      msx = -100;
    }

    if(mouseY%2 != 0){
      msy = constrain(((pmouseY+(maxSize/2))/maxSize)*maxSize, margin, width - margin);
    } else {
      // Offscreen Need to Fix
      msy = -100;
    }

        stroke(constrain(c.complimentaryHue(primaryHue, complimentaryHue), 0, 360),100,(100-(penStrokeWeight*2)));
        strokeWeight(penStrokeWeight);
        line(msx,msy,msx,msy);
  }

}
class Gridtool {

  float gridX = margin;
  float gridY = margin;

  Gridtool() {

  }
  public void makeGrid() {

    while (gridX < width - (margin - 1)) {
      stroke(c.complimentaryHue(primaryHue, complimentaryHue),100,100);
      line(gridX, 0, gridX, height);
      gridX = gridX + 40;
    }
  }

  public void killGrid() {
    gridX = margin;
    gridY = margin;
    println ("kill grid");
    while (gridX < width - (margin - 1)) {
      stroke(primaryHue, 100, 100);
      line(gridX, 0, gridX, height);
      gridX = gridX + 40;
    }
  }
}
class Linetool {

  Linetool() {

  }

  public void lineTool() {
    stroke(constrain(c.complimentaryHue(primaryHue, complimentaryHue), 0, 360),100,(100-(penStrokeWeight*2)));
    strokeWeight(penStrokeWeight);
    // Must be a better way to write this
    if(mouseX > margin && mouseX < width - margin && mouseY > margin && mouseY < width - margin && pmouseX > margin && pmouseX < width - margin && pmouseY > margin && pmouseY < width - margin) {
      line(pmouseX,pmouseY,mouseX,mouseY);
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
