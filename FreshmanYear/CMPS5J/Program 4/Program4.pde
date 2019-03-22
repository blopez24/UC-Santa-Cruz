/* This program creates a ocean with a dock.
 By clicking the mouse within the "Call Ship" sign, it will call a ship. 
 When it moves offscreen it will reset the ship's x coordinate back to its original position.
 This program also creates looping clouds, and a looping star that rotates.
 Fish jump out the water by clicking on the sea. 
 Fireworks can be actived once by clicking on the button.
 */

int cloudX = -325;
int cloudY = 100;
int cloudSize = 100;

int dockX = 700;
int dockY = 650;
int dockLineY1 = 0;
int dockLineY2 = 150;

int shipX = 1200;
int shipY = 700;
int shipWait = 100;

int signX = 750;
int signY = 579;

int starX = -100;

float degree = 320;
int fishX = 0;
int fishY = 0;

boolean fishCalled = false;
boolean fireworkButtonPushed = false;
boolean shipCalled = false;

int radius = 200;
int numDots = 10;
int dotSize = 10;
float angle = 0.0;

int transparent = -200;
int transparent2 = 255;
int dotY = 750;

int transparent3 = -200;
int transparent4 = 255;
int dotY2 = 750;
float angle2 = 0.0;

void setup()
{
  size(1000, 1000);
}

void draw()
{
  //Sky Color
  background(0, 51, 102);

  //If you click the water fish will jump from the water
  if (mousePressed == true && mouseX > 0 && mouseX < width && mouseY > 700)
  {
    fishCalled = true;
  }

  pushMatrix();
  translate(50, 800);

  if (fishY > 0)
  {
    fishX = 0;
    fishY = 0;
    degree = 320;
    fishCalled = false;
  } 
  else if (fishCalled == true)
  {
    //This makes the fish
    for (int i = 0; i < 3; i++)
    {
      //Tail fin
      rotate(radians(degree));
      fill(125, 125, 125);
      triangle(fishX, fishY, fishX - 20, fishY - 20, fishX - 20, fishY + 20);
      //Body
      ellipse(fishX + 20, fishY, 50, 30);
      //Eye
      fill(0);
      ellipse(fishX + 32, fishY - 2, 10, 10);
      rotate(radians(-degree));
      translate(200, 0);
    }
    if (degree < 350)
    {
      degree += .25;
      fishY--;
      fishX += 2;
    } 
    else if (degree >= 350 && degree < 430)
    {
      degree += .7;
    } 
    else
    {
      fishY += 2;
      fishX += 2;
    }
  }
  popMatrix();

  rectMode(CORNER);

  //By pressing the firework button on the dock it calls fireworks
  if (mousePressed && mouseX > signX + 185 && mouseX < signX + 205 && mouseY > signY + 10 && mouseY < signY + 20)
  {
    fireworkButtonPushed = true;
  }

  if (fireworkButtonPushed)
  {
    fireWorks();
  }

  //Sea 
  fill(65, 105, 225);
  noStroke();
  rect(0, 700, width, height);

  //Moving Clouds
  fill(255);
  noStroke();
  ellipse(cloudX, cloudY, cloudSize, cloudSize);
  ellipse(cloudX - 45, cloudY, cloudSize - 25, cloudSize - 25);
  ellipse(cloudX + 45, cloudY, cloudSize - 25, cloudSize - 25);

  ellipse(cloudX + 200, cloudY - 25, cloudSize + 10, cloudSize + 10);
  ellipse(cloudX + 150, cloudY - 25, cloudSize - 35, cloudSize - 35);
  ellipse(cloudX + 250, cloudY - 25, cloudSize - 35, cloudSize - 35);

  //Resets cloud's x position
  if (cloudX >= 1100)
  {
    cloudX = -325;
  } 
  else
  {
    cloudX +=2;
  }

  //Ship Cabin
  fill(128, 128, 128);
  stroke(0);
  strokeWeight(.5);
  rect(shipX + 130, shipY - 196, 250, 100, 7);

  //Ship Body
  fill(0);
  quad(shipX, shipY, shipX + 400, shipY, shipX + 425, shipY - 100, shipX - 50, shipY - 125);

  //Ship Life Preservers
  noFill();
  stroke(255);
  strokeWeight(5);
  ellipse(shipX + 50, shipY - 50, 30, 30);
  ellipse(shipX + 150, shipY - 50, 30, 30);
  ellipse(shipX + 250, shipY - 50, 30, 30);

  //Ship Windows
  fill(0, 191, 255);
  stroke(0);
  strokeWeight(.5);
  ellipse(shipX + 230, shipY - 150, 30, 30);
  ellipse(shipX + 280, shipY - 150, 30, 30);
  ellipse(shipX + 330, shipY - 150, 30, 30);

  //Dock
  fill(182, 155, 76);
  stroke(0);
  strokeWeight(1);
  rect(dockX, dockY, 300, 150);
  //Legs
  rect(dockX, dockY + 150, 75, 100);
  rect(dockX + 262, dockY + 150, 75, 100);

  //Lines on Dock
  pushMatrix();
  stroke(0);
  strokeWeight(1);
  translate(dockX, dockY);

  for (int i = 0; i <= 300; i += 10)
  {
    line(i, dockLineY1, i, dockLineY2);
  }
  popMatrix();

  //Ship Sign
  rectMode(CENTER);
  rect(signX, signY + 46, 30, 50);
  rect(signX, signY, 100, 50);
  fill(0);
  textSize(18);
  text("Call Ship", 710, 576);

  //Firework Sign
  rectMode(CENTER);
  fill(182, 155, 76);
  rect(signX + 195, signY + 46, 30, 50);
  rect(signX + 195, signY, 100, 50);
  fill(0);
  textSize(18);
  text("Fireworks", 906, 576);

  //Firework button
  noStroke();
  fill(255, 0, 0);
  rect(signX + 195, signY + 10, 20, 20);

  //Resets star's x location
  if (starX == 1050)
  {
    starX = -100;
  }
  else
  {
    //Yellow and no stroke
    fill(255, 255, 0);
    noStroke();

    //Sets origin in the location of mouseX and 100
    //Rotates according to the framerate
    pushMatrix();
    translate(starX, 100);
    rotate(radians(frameCount));

    //Specific points to create the star
    beginShape();
    vertex(0, -50);
    vertex(14, -20);
    vertex(47, -15);
    vertex(23, 7);
    vertex(29, 40);
    vertex(0, 25);
    vertex(-29, 40);
    vertex(-23, 7);
    vertex(-47, -15);
    vertex(-14, -20);
    endShape(CLOSE);

    popMatrix();
    starX++;
  }

  if (fireworkButtonPushed == true)
  {
    fill(0, 255, 0);
    rect(signX + 195, signY + 10, 20, 20);
  }

  //Red square button
  fill(255, 0, 0);
  rect(signX, signY + 10, 20, 20);

  //By clicking the mouse within the red button it will call a ship
  if (mousePressed && mouseX > signX - 10 && mouseX < signX + 10 && mouseY > signY && mouseY < signY + 20)
  {
    shipCalled = true;
  }
  
  if (shipCalled)
  {
    //Red button becomes green when pressed
    fill(0, 255, 0);
    rect(signX, signY + 10, 20, 20);

    //Smog appears if the ship is moving
    noStroke();
    fill(200, 200, 200, 244);
    ellipse(shipX + 332, shipY +- 279, 72, 59);
    ellipse(shipX + 362, shipY +- 315, 115, 85);
    ellipse(shipX + 408, shipY +- 301, 95, 75);
    ellipse(shipX + 435, shipY +- 359, 147, 86);
    ellipse(shipX + 494, shipY +- 322, 143, 97);
    ellipse(shipX + 494, shipY +- 349, 150, 150);

    ellipse(shipX + 262, shipY +- 258, 62, 48);
    ellipse(shipX + 274, shipY +- 280, 58, 42);
    ellipse(shipX + 289, shipY +- 262, 47, 37);
    ellipse(shipX + 313, shipY +- 302, 73, 43);
    ellipse(shipX + 273, shipY +- 268, 77, 49);

    //This makes the ship stop at the dock, then it eventually moves
    if (shipX > 700)
    {
      shipX -= 4;
    } 
    else if (shipWait == 0)
    {
      shipX -= 4;
    } 
    else if (shipX == 700)
    {
      shipWait--;
    }
  }

  //If the ship is off screen, then it will reset the ship to the right off the screen
  if (shipX < -700)
  {
    shipX = 1200;
    shipCalled = false;
    shipWait = 100;
  }

  //Ship Smokestacks
  fill(0);
  noStroke();
  rect(shipX + 335, shipY - 233, 50, 75, 3);
  rect(shipX + 270, shipY - 223, 50, 55, 3);
}

//Activates fireworks
void fireWorks()
{
  noStroke();

  //If the click is inside the box. It creates the fireworks
  if (fireworkButtonPushed)
  {
    pushMatrix();

    //Red Dot that is moving up is still visible
    if (transparent <= 255)
    {
      //Draw red dot moving up 
      fill(255, 0, 0, transparent2);
      ellipse(width/4, dotY, dotSize, dotSize);

      //Make dot move up until it reaches a certain point
      if (dotY >= height/3)
      {
        dotY -= 5;
      }

      translate(width/4, height/3);
      angle = (angle + .1);

      //Creates the pattern of red dots. After main dot disappears.
      for (int i = 0; i < 30; i++)
      {
        for (int j = 0; j < numDots; j++)
        {
          fill(255, 0, 0, transparent);
          ellipse(j * radius/numDots, 0, j * 1.1, j * 1.1);
        }
        rotate(2 * PI/numDots);
      }
      transparent += 2;
      transparent2 -= 2;
    } 
    else if (transparent > 255)
    {
      transparent = -200;
      transparent2 = 255;
      dotY = 750;
    }
    popMatrix();

    pushMatrix();
    //Yellow Dot that is moving up is still visible
    if (transparent3 <= 255)
    {
      //Draw yellow dot moving up
      fill(255, 255, 0, transparent4);
      ellipse(width * 3/4, dotY2, dotSize, dotSize);

      //Make dot move up until it reaches a certain point
      if (dotY2 >= height/3)
      {
        dotY2 -= 5;
      }

      translate(width * 3/4, height / 3);
      angle2 = (angle2 + .1);

      //Creates the pattern of yellow dots. After main dot disappears.
      for (int k = 0; k < 30; k++)
      {
        for (int l = 0; l < numDots; l++)
        {
          fill(255, 255, 0, transparent3);
          ellipse(l * radius/numDots, 0, l * 1.1, l * 1.1);
        }
        rotate(2 * PI/numDots);
      }
      transparent3 +=2;
      transparent4 -= 2;
    } 
    else if (transparent3 > 255)
    {
      transparent3 = -200;
      transparent4 = 255;
      dotY2 = 750;
      fireworkButtonPushed = false;
    }
    popMatrix();
  }
}