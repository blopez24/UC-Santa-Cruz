/*
  Red car on a highway with moving clouds and a light blue background.
 Car can be moved around with the mouse.
 The sun changes color depending on the mouseX location.
 Clouds move through the sky and background color can be changed with key pressed and mouse click.
 */

void setup()
{
  //Window Size
  size(1000, 800);
}

//Holds values for backgroud
//Allows me to change from light blue background to dark midnight background 
int one = 135;
int two = 206;
int three = 250;

void draw()
{
  //Light Blue Background
  background(one, two, three);

  //Sun
  ellipseMode(CENTER);
  noStroke();
  fill(mouseX, mouseX/5, 0);
  ellipse(800, 100, 100, 100);

  //Clouds
  noStroke();
  fill(255, 250, 250);
  //Allows clouds to move by using frame count for x 
  int cloudLocation = frameCount-275;
  if (frameCount < 1400)
  {
    ellipse(cloudLocation, 150, 100, 100);
    ellipse(cloudLocation-60, 150, 60, 60);
    ellipse(cloudLocation+60, 150, 60, 60);

    ellipse(cloudLocation+200, 250, 50, 50);
    ellipse(cloudLocation-25+200, 250, 30, 30);
    ellipse(cloudLocation+25+200, 250, 30, 30);

    ellipse(cloudLocation+100, 50, 40, 40);
    ellipse(cloudLocation-25+100, 50, 25, 25);
    ellipse(cloudLocation+25+100, 50, 25, 25);
  }
  //Changes the value of frame count to continue the loop
  else
  {
    frameCount = 0;
  }

  //Trees

  //Trunk
  noStroke();
  fill(139, 69, 19); 
  rectMode(CENTER);
  rect(300, 500, 75, 200);

  rect(750, 500, 90, 200);

  //Leaves
  stroke(100, 228, 100, 100);
  fill(0, 128, 0);
  ellipseMode(CENTER);
  ellipse(250, 400, 150, 150);
  ellipse(250, 475, 100, 100);
  ellipse(350, 465, 60, 60);
  ellipse(300, 375, 200, 200);

  ellipse(700, 400, 150, 150);
  ellipse(700, 475, 100, 100);
  ellipse(800, 465, 60, 60);
  ellipse(750, 375, 200, 200);

  //UCSC Sign

  //UCSC Board
  stroke(0);
  fill(139, 69, 19);
  rectMode(CENTER);
  rect(506, 550, 300, 95);

  //UCSC Text
  textSize(25);
  fill(255, 255, 0);
  text("University Of California", 366, 540);
  text("Santa Cruz", 441, 575);

  //Side walk
  rectMode(CORNER);
  strokeWeight(2);
  stroke(128, 128, 128);
  fill(220, 220, 220);
  rect(0, 600, 1000, 20);

  //Asphault
  rectMode(CORNER);
  noStroke();
  fill(0, 0, 0);
  rect(0, 620, 1000, 180);

  //Yellow Lines
  rectMode(CORNER);
  fill(255, 255, 0);
  rect(25, 710, 100, 10);
  rect(175, 710, 100, 10);
  rect(325, 710, 100, 10);
  rect(475, 710, 100, 10);
  rect(625, 710, 100, 10);
  rect(775, 710, 100, 10);
  rect(925, 710, 100, 10);

  //Car

  //Red Body
  rectMode(CENTER);
  stroke(0);
  fill(255, 40, 0);
  rect(mouseX, 700, 300, 120);

  //Window
  fill(255, 255, 255, 50);
  quad(mouseX-100, 700-60, mouseX-80, 700-135, mouseX+50, 700-135, mouseX+80, 700-60);

  //Door Lines
  line(mouseX-20, 700-135, mouseX-20, 700+60);
  line(mouseX+80, 700-60, mouseX+80, 700+60);

  //Front Bumper
  fill(230, 230, 230);
  rect(mouseX+135, 700+55, 60, 30);

  //Back Bumper
  fill(230, 230, 230);
  rect(mouseX-135, 700+55, 60, 30);

  //Taillight
  fill(255, 165, 0);
  rect(mouseX-140, 700-40, 20, 15);

  //Headlight
  fill(255, 215, 0);
  ellipse(mouseX+135, 700-40, 10, 20);

  //Door Handle
  fill(230, 230, 230);
  rect(mouseX, 700-40, 30, 10);

  //Wheels

  //Outer Black Wheels
  fill(0);
  ellipse(mouseX-95, 700+60, 75, 75);
  ellipse(mouseX+95, 700+60, 75, 75);

  //Inner Grey Wheels
  fill(192, 192, 192);
  ellipse(mouseX-95, 700+60, 55, 55);
  ellipse(mouseX+95, 700+60, 55, 55);

  //Text for background colors
  textSize(20);
  fill(0, 127);
  text("Press any key to change background color.", 50, 50);
  text("Click on mouse to change background color.", 50, 100);
}

//Whenever a user presses a key the code written inside changes the values of the int
void keyPressed()
{
  one = 25;
  two = 25;
  three = 112;
}

//Whenever a user clicks the code written inside changes the values of the int
void mousePressed()
{
  one = 135;
  two = 206;
  three = 250;
}