class Colorwheel {
  
  Colorwheel() {


  }
  // Shift the hue by 30 degrees
  float hueShift(float p) {
    if (p >= 331) {
      p = 0;
    } else {
      p = p + 30;
    }
    return p;
  }

  // Turn colors complimentary
  float complimentaryHue(float p, float c) {
    if (p >= 180) {
      c = (p)-180;
    } else {
      c = (p)+180;
    }
    return c;
  }

  void splitComplimentaryHue() {
    // half and then plus and minus 30 on color wheel
  }

  void analagousHue() {
    // plus and minus 30 on color wheel
  }

  void triadicHue() {
    // plus 120 and minus 120
  }

}
