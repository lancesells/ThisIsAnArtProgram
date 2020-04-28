class Gridtool {

  float gridX;
  float gridY;

  Gridtool() {
  }

  void makeGrid() {
    gridX = margin;
    gridY = margin;
    while (gridX < width - (margin - 1)) {
      stroke(c.complimentaryHue(primaryHue, complimentaryHue),100,100);
      line(gridX, 0, gridX, height);
      gridX = gridX + 40;
    }
  }

  void killGrid() {
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
