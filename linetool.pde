class Linetool {

  Linetool() {

  }

  void lineTool() {
    stroke(constrain(c.complimentaryHue(primaryHue, complimentaryHue), 0, 360),100,(100-(penStrokeWeight*2)));
    strokeWeight(penStrokeWeight);
    // Must be a better way to write this
    if(mouseX > margin && mouseX < width - margin && mouseY > margin && mouseY < width - margin && pmouseX > margin && pmouseX < width - margin && pmouseY > margin && pmouseY < width - margin) {
      line(pmouseX,pmouseY,mouseX,mouseY);
    }

  }

}
