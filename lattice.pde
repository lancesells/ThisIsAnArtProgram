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



void setup() {
  // Objects
  c = new Colorwheel();
  l = new Linetool();
  d = new Dottool();
  g = new Gridtool();

  // Defining Color Mode and size
  colorMode(HSB, 360, 100, 100);
  size(560, 560);
  background(primaryHue,100,100);
}

void draw() {
  if(mouse == true) {
    if(mode == 'd') {
      d.dotTool();
    } else if(mode == 'l') {
      l.lineTool();
    }
  }
}

void mousePressed() {
  mouse = true;
}

void mouseReleased() {
  mouse = false;
}

void keyPressed() {
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

void keyReleased() {
  if (key == 'q') {
      g.killGrid();
  }
}
