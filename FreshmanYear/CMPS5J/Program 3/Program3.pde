/*
   Background includes a dark blue sky with a stationary moon.
   Clouds loop around the background.
   Rotating star can be moved along the x axis with depending on the mouse location.
   Bus moves along the highway and color can be changed depending which key is pressed. (R, G, B)
   Billboard appears above the highway and has art drawn into it randomly.
 */
void setup()
{
  size(800, 600);
  //Dark Blue Sky Color
  background(25, 25, 112);

  //Creates the white canvas for the billboard
  //Is in setup instead of draw to keep it still/permanent
  rectMode(CENTER);
  stroke(0);
  fill(255);
  rect(xboardLocation, yboardLocation, boardWidth, boardHeight);
}

void draw()
{
  smooth();
  moon();
  stars();
  clouds();
  billboard();
  street();
  bus();
}

//Location for the moon
int xmoonLocation = 700 ;
int ymoonLocation = 100;

//Diameter of the moon
int moonSize = 60;

void moon()
{
  //Creates a dark blue rect 
  //Necessary since background isnt located in draw.
  noStroke();
  fill(25, 25, 112);
  rect(width/2, 100, width, height/2);

  //Creates the white/grey moon
  stroke(0, 50);
  fill(225, 225, 225);
  ellipse(xmoonLocation, ymoonLocation, moonSize, moonSize);
}

//Location for big cloud
int yLocationCloud1 = 150;
//Diameter of middle cloud
int middleCloudSize1 = 100;
//Diameter of side clouds
int sideCloudSize1 = 60;

//Location for small cloud
int yLocationCloud2 = 50;
//Diameter of middle cloud
int middleCloudSize2 = 40;
//Diameter of side clouds
int sideCloudSize2 = 20;

void clouds()
{
  //Grey clouds with no stroke
  noStroke();
  fill(192, 192, 192);

  //Set location of the clouds to be off the screen (Left side)
  int cloudLocation = frameCount-275;

  //Enough time has passed for the clouds to be off the screen (Right side)
  if (frameCount < 1200)
  {
    //Creates big cloud
    ellipse(cloudLocation, yLocationCloud1, middleCloudSize1, middleCloudSize1);
    ellipse(cloudLocation-60, yLocationCloud1, sideCloudSize1, sideCloudSize1);
    ellipse(cloudLocation+60, yLocationCloud1, sideCloudSize1, sideCloudSize1);

    //Creates small cloud
    ellipse(cloudLocation+100, yLocationCloud2, middleCloudSize2, middleCloudSize2);
    ellipse(cloudLocation-25+100, yLocationCloud2, sideCloudSize2, sideCloudSize2);
    ellipse(cloudLocation+25+100, yLocationCloud2, sideCloudSize2, sideCloudSize2);
  } else
  {
    //Reset cloud location
    frameCount = 0;
  }
}

//Location of sidewalk
int xsidewalkLocation = 400;
int ysidewalkLocation = 400;

//Dimensions of the sidewalk
int sidewalkWidth = 800;
int sidewalkHeight = 40;

//Location of asphault
int xasphaultLocation = xsidewalkLocation;
int yasphaultLocation = ysidewalkLocation+110;

//Dimensions of asphault
int asphaultWidth = sidewalkWidth;
int asphaultLength = sidewalkHeight+140;

//Dimensions of yellow line
int lineLength = 100;
int lineHeight = 10;

void street()
{
  //Creates sidewalk
  stroke(0);
  rectMode(CENTER);
  fill(220, 220, 220);
  rect(xsidewalkLocation, ysidewalkLocation, sidewalkWidth, sidewalkHeight);

  //Text
  textSize(20);
  fill(255, 255, 0);
  text("Try holding any of the following keys: R G B", xsidewalkLocation-375, ysidewalkLocation+5);

  //Create the asphault
  rectMode(CENTER);
  fill(0);
  rect(xasphaultLocation, yasphaultLocation, asphaultWidth, asphaultLength);

  //Creates the yellow line 
  rectMode(CENTER);
  fill(255, 255, 0);
  rect(xasphaultLocation-xasphaultLocation, yasphaultLocation, lineLength, lineHeight);
  rect(xasphaultLocation-xasphaultLocation/2, yasphaultLocation, lineLength, lineHeight);
  rect(xasphaultLocation, yasphaultLocation, lineLength, lineHeight);
  rect(xasphaultLocation+xasphaultLocation/2, yasphaultLocation, lineLength, lineHeight);
  rect(xasphaultLocation+xasphaultLocation, yasphaultLocation, lineLength, lineHeight);
}

//Location of billboard
int xboardLocation = 600; 
int yboardLocation = 300;

//Dimensions of billboard
int boardWidth = 200;
int boardHeight = 100;

//Variable to hold color and alpha
float r;
float g;
float b;
float a;

//Variable to hold the dimesion and location of the circles
float circleSize;
float x;
float y;

//Variable to keep track of the number of circles
int count = 0;

void billboard()
{
  //Creates the black bars underneath the billboard
  fill(0);
  rect(xboardLocation, yboardLocation+60, boardWidth+20, boardHeight/25);
  rect(xboardLocation-50, 375, 10, 50);
  rect(xboardLocation+50, 375, 10, 50);

  //Creates text
  textSize(20);
  fill(255, 0, 0);
  text("Modern Art", xboardLocation-25, yboardLocation+40);

  //Assigns a random float to the color and alpha variables
  r = random(255);
  g = random(255);
  b = random(255);
  a = random(255);

  //Assigns a ramdom float to the diameter of the circles between 5-10
  circleSize = random(5, 10);

  //Assigns a random float for the location of the circle with specific limits
  x = random(510, 690);
  y = random(260, 320);

  //Checks if the number of circles is below 501
  if (count <501)
  {
    //Creates circles
    noStroke();
    fill(r, g, b, a);
    ellipse(x, y, circleSize, circleSize);

    //Increases count
    count++;
  }
}

//Location of bus
int xbusLocation = -250;
int ybusLocation = 500;

//Dimension of the bus
int busWidth = 300;
int busHeight = 120;

//Dimension of the engine/right rect
int engineWidth = 70;
int engineHeight = 60;

//Dimension of the door
int doorWidth = 40;
int doorHeight = 100;

//Dimension of the door
int windowWidth = 180;
int windowHeight = 40;
//Diameter of the wheel
int wheelSize = 50;

void bus()
{
  //Checks if the bus location is less than 1000
  if ( xbusLocation < 1000)
  {
    //Increases bus location by 2
    xbusLocation+=2;
  } 
  else
  {
    //Resets bus loction off the screen to the left
    xbusLocation = -250;
  }
  
  //Variables to hold the bus colors 
  int rBus = 0;
  int gBus = 0;
  int bBus = 0;
  
  //If keys are being pressed
  if (keyPressed)
  {
    //If the 'r' key is pressed
    if (key == 'r')
    {
      //Changes red value to 255
      rBus = 255;
    } 
    //If the 'g' key is pressed
    else if (key == 'g')
    {
      //Change green value to 255
      gBus = 255;
    } 
    //If the 'b' key is pressed
    else if (key == 'b')
    {
      //Change blue value to 255
      bBus = 255;
    }
  }

  //Creates the body of the bus
  rectMode(CENTER);
  fill(rBus, gBus, bBus);
  stroke(255);
  rect(xbusLocation, ybusLocation, busWidth, busHeight);

  //Creates the engine of the bus (Right rect)
  rect(xbusLocation+185, ybusLocation+30, engineWidth, engineHeight);
  
  //Creates the door for the bus
  fill(100, 149, 237);
  rect(xbusLocation+100, ybusLocation, doorWidth, doorHeight);

  //Creates the window for the bus
  rect(xbusLocation-40, ybusLocation-20, windowWidth, windowHeight);

  //Sets the new origin to the top left corner of the window
  //Creates lines in the window 
  pushMatrix();
  translate(xbusLocation-130, ybusLocation-40);
  line(windowWidth/4, 0, windowWidth/4, 40);
  line(windowWidth/2, 0, windowWidth/2, 40);
  line(windowWidth*3/4, 0, windowWidth*3/4, 40);
  popMatrix();
  
  //Creates the inner and outer wheels of the bus
  fill(127, 127, 127);
  ellipse(xbusLocation-50, ybusLocation+60, wheelSize, wheelSize);
  ellipse(xbusLocation+150, ybusLocation+60, wheelSize, wheelSize);
  fill(0);
  ellipse(xbusLocation-50, ybusLocation+60, wheelSize-20, wheelSize-20);
  ellipse(xbusLocation+150, ybusLocation+60, wheelSize-20, wheelSize-20);
}


void stars()
{
  //Yellow and no stroke
  fill(255, 255, 0);
  noStroke();
  
  //Sets origin in the location of mouseX and 100
  //Rotates according to the framerate
  pushMatrix();
  translate(mouseX, 100);
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
}