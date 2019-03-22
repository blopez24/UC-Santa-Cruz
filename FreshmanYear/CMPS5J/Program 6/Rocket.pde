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

class Rocket {
  /**
   Initial location of the rocket.
   @param startX - horizontal location
   @param startY - vertical location
   */

  //List of variables for Rocket Class

  //Colors for various parts
  color rocketBody = color(155);
  color rocketWindow = color(0, 0, 255);
  color rocketFins = color(255, 0, 0);
  color rocketThrust = color(255, 0, 0);
  color noseCone = color(0);
  color fuelGague = color(119, 244, 66);
  color explosion = color(255, 0, 0);
  color launchpad = color(0);
  color mountain = color(153, 76, 0);

  //Varibles for rocket dimensions
  int rocketWidth = 25;
  int rocketHeight = 50;
  int windowX = 0;
  int windowY = 10;
  int windowDiameter = 10;
  int rocketBodyX = 0;
  int rocketBodyY = 0;
  int noseConeX = 0;
  int thrustX = 0;
  int thrustY = 27;
  float rocketFinX = 12.5;
  float rocketFinY = 10;

  //Positioning text values for various variables
  int textXVel = 20;
  int textYVel = 50;
  int textFuelX = 20;
  int textFuelY = 100;
  int textThrustX = 20;
  int textThrustY = 125;
  //Text size for variables above
  int valTextSize = 16;

  //Stroke weights for various variables
  int strokeWeightFuelGague = 0;
  int strokeWeightThrust = 3;
  int strokeWeightNoseCone = 2;
  int strokeWeightWindow = 1;

  int landingTextSize = 32;
  int centerX = width/2;
  int centerY = height/2;

  float fuelX = width * .9;
  float fuelY = height * .1;
  float fuelWidth = 25;
  float fuelHeight = 50;

  //Safe landing velocities
  float safeXVel = 0.5;
  float safeYVel = 0.5;

  int timeLeft = 30;
  float explosionDiameter = 0;
  float explosionDiameterChange = 0.7;

  boolean crashed = false;
  boolean explode = false;

  private float x, y, xVel, yVel, thrust = GRAVITY, tilt = HALF_PI;
  //The values below were arrived at by trial and error
  //For something that had the responsiveness desired
  static final float GRAVITY = 0.001;
  static final float MAX_THRUST = 5*GRAVITY;
  static final float TILT_INC = 0.005;
  static final int FLAME_SCALE = 5000; // multiplier to determine how long the flame should be based on thrust

  Rocket(int startX, int startY) {
    x = startX;
    y = startY;
  }

  /**
   Decrease the thrust by the specified amount where decreasing by 100 will
   immediately reduce thrust to zero.
   */
  void setThrust(int amount) {
    amount = constrain(amount, 0, 100);
    if (amount > 0)
    {
      if (fuelHeight <= 0)
      {
        fuelHeight = 0;
        thrust = 0;
      } else
        thrust = MAX_THRUST*amount/100;
      fuelHeight -= amount * .0009;
    }
  }

  /**
   Rotate the rocket positive means right or clockwise, negative means
   left or counter clockwise.
   */
  void rotateRocket(int amt) {
    tilt = tilt + amt*TILT_INC;
  }

  /**
   Adjust the position and velocity, and draw the rocket.
   */
  void update() {
    //Creates fuel gague
    fill(fuelGague);
    stroke(strokeWeightFuelGague);
    rect(fuelX, fuelY, fuelWidth, fuelHeight);
    //Manages thrust and positioning
    x = x + xVel;
    y = y + yVel;
    xVel = xVel - cos(tilt)*thrust;
    yVel = yVel - sin(tilt)*thrust + GRAVITY;
    // to make it more stable set very small values to 
    if (abs(xVel) < 0.00005) xVel = 0;
    if (abs(yVel) < 0.00005) yVel = 0;
    // draws rocket
    pushMatrix();
    translate(x, y);
    rotate(tilt - HALF_PI);
    // draw the rocket body
    fill(rocketBody);
    //main body
    ellipse(rocketBodyX, rocketBodyY, rocketWidth, rocketHeight);
    //Nose cone at top
    strokeWeight(strokeWeightNoseCone);
    stroke(noseCone);
    line(noseConeX, -25, noseConeX, -37);
    //Windows
    strokeWeight(strokeWeightWindow);
    fill(rocketWindow);
    ellipse(windowX, -windowY, windowDiameter, windowDiameter);
    ellipse(windowX, windowY, windowDiameter, windowDiameter);
    //Red fins on side
    fill(rocketFins);
    //X 12.5
    //Y 10
    triangle(-rocketFinX, -rocketFinY, -rocketFinX - 8.5, rocketFinY + 8, -rocketFinX + 2, rocketFinY + 8);
    triangle(rocketFinX, -rocketFinY, rocketFinX + 8.5, rocketFinY + 8, rocketFinX - 2, rocketFinY + 8);
    //Thrust
    strokeWeight(strokeWeightThrust);
    stroke(rocketThrust);
    line(thrustX, thrustY, thrustX, thrustY + thrust * FLAME_SCALE);
    popMatrix();
  }

  //Determines if rocket has landed on launchpad successfully, if the rocket has crashed landed, and if the rocket has crashed into the mountain 
  void touches() {
    edgeDetect();
    textAlign(CENTER);
    textSize(landingTextSize);
    if (launchpad == get((int) x, (int) (y + 25))) {
      landing();
    } else if (crashed) {
      displayCrash();
    } else if (fuelHeight <= 0) {
      outOfFuel();
      update();
      displayValues();
    } else {
      displayValues();
      update();
    }
  }

  //Checks if spaceship has crashed into mountain
  void edgeDetect()
  {  
    if (mountain == get((int)(x-12.5), (int)(y + 25) ) || mountain == get((int)(x+12.5), (int)(y + 25)))
    {
      crashed = true;
    }
  }

  //Displays crash if rocket has made contact with the mountain
  void displayCrash()
  {
    text("Crashed into the Mountain!", centerX, centerY);
    explosion();
  }

  //Displays an explosion if crashed == true
  void explosion()
  {
    fill(explosion);
    ellipse(x, y, explosionDiameter, explosionDiameter);

    if (explosionDiameter < 50 && explode == false)
    {
      explosionDiameter += explosionDiameterChange;
    } else if (explosionDiameter < 0)
    {
    } else
    {
      explode = true;
      explosionDiameter -= explosionDiameterChange;
    }
  }

  //Checks to see if you have landed at a safe velocity, and prints on the screen whether you did or not
  void landing()
  {
    if (yVel <= safeYVel && xVel <= safeXVel)
    {
      text("You have successfully landed safely!", centerX, centerY);
    } else if (yVel > safeYVel && xVel > safeXVel)
    {
      text("You have crashed due to high horizontal & vertical velocities!", centerX, centerY);
      explosion();
    } else if (yVel > safeYVel)
    {
      text("You have crashed due to high vertical velocity!", centerX, centerY);
      explosion();
    } else
    {
      text("You have crashed due to high horizontal velocity!", centerX, centerY);
      explosion();
    }
  }

  //This method makes it so once you run out of fuel, you have a set amount of time before the astronauts die from oxygen deprivation
  void outOfFuel()
  {
    if (timeLeft > 0)
    { 
      text("Time Left Before Oxygen Runs Out: " + timeLeft, centerX, centerY);
      if (frameCount % 60 == 0)
        timeLeft--;
    } else
    {
      text("You have run out of fuel and oxygen!", centerX, centerY);
    }
  }

  //This displays the velocities, amount of fuel remaining, and thrust at the top left of the screen
  void displayValues()
  {
    textAlign(LEFT);
    textSize(valTextSize);
    text("X Velocity: " + abs(xVel), textXVel, textYVel);
    text("Y Velocity: " + abs(yVel), textXVel, textYVel + 25);
    text("Fuel Remaing: " + (int)fuelHeight*2 + "%", textFuelX, textFuelY);
    text("Thrust: " + thrust, textThrustX, textThrustY);
  }
}