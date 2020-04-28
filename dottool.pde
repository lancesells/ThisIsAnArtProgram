class Dottool {

  float msx = 0;
  float msy = 0;

  Dottool() {

  }

  void dotTool() {
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
