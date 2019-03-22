/**
 At the beginning it displays a rocket at the center of the sketchpad.
 To increase thrust on the rocket, move mouse to top half of sketchpad.
 To decrease thrust on the rocket, move mouse to bottom half of sketchpad.
 To control positioning of rocket, use left and right arrow keys.
 The rocket will explode if you crash into the mountain.
 The rocket will explode if you land at too high of a horizontal or vertical velocity on the launchad.
 The rocket will explode if you attempt to land on the nose cone of the rocket.
 The fuel gague will deplete more as you increase the thrust.
 After the rocket fuel runs out, you will have a limited amount of time before the oxygen in the spaceship runs out.
 Everyone inside the spaceship will die, if the rocket runs out of oxygen after all fuel is expended.
 @Base coded provided by Charlie McDowell
 */

void setup() {
  size(1000, 1000);
  seed = (int)random(10000);
  rocket = new Rocket(width/2, height/4);
}

//List of variables and an object for LunarLander class
Rocket rocket;
int seed;

color mountain = color(153, 76, 0);
color background = color(173, 216, 230);

int launchPadWidth = 100;
int launchPadHeight = 10;

void draw() {
  background(background);
  mountain();
  adjustControls(rocket);
  rocket.touches();
  randomSeed(seed);
}

/*
  Control the rocket using mouseY for thrust and 'a' or left-arrow for rotating
 counter-clockwise and 'd' or right-arrow for rotating clockwise.
 It takes a single parameter, which is the rocket being controlled.
 */
void adjustControls(Rocket rocket) {
  //Control thrust with the y-position of the mouse
  if (mouseY < height/2) {
    rocket.setThrust(height/2 - mouseY);
  } else {
    rocket.setThrust(0);
  }
  //Allow for right handed control with arrow keys or
  //Left handed control with 'a' and 'd' keys

  //Right hand rotate controls
  if (keyPressed) {
    if (key == CODED) { //Tells us it was a "special" key
      if (keyCode == RIGHT) {
        rocket.rotateRocket(3);
      } else if (keyCode == LEFT) {
        rocket.rotateRocket(-3);
      }
    }
    //Left hand rotate controls
    else if (key == 'a') {
      rocket.rotateRocket(-3);
    } else if (key == 'd') {
      rocket.rotateRocket(3);
    }
  }
}

//Creates a mountain
void mountain() {
  float mountainX = 0;
  float mountainY = height;
  float x1;
  float y1;
  float alternate = 1;
  //Location for highest point to set launchpad
  float highestP = height;
  float padX = 0;
  pushMatrix();
  strokeWeight(2);
  stroke(0);
  fill(mountain);
  beginShape();
  vertex(-1, 1001);

  while (mountainX < width) 
  {
    stroke(mountain);
    x1 = mountainX + random(25, 50);
    y1 = mountainY - alternate * random(200, 300);
    alternate *= -1;vertex(mountainX, mountainY);
    vertex(x1, y1);mountainX = x1;
    mountainY = y1;

    //Determines the highest point of the mountain, and chooses that as the location for the launchpad
    if (highestP > y1 && x1 > 0 && x1 < width) {
      padX = x1;
      highestP = y1;
    }
  }
  vertex(1001, 1001);endShape();
  popMatrix();
  //Creates lanuch pad
  rectMode(CENTER);
  noStroke();
  fill(0);
  rect(padX, highestP, launchPadWidth, launchPadHeight);
}