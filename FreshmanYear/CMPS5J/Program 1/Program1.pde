/*
Project: Program 1
Name: John Mai 
Name: Bryan Lopez
Date: 10/02/17
*/

void setup()
{
  size(500,500);
  background(135,206,250);
  
  
  //Sun
  noStroke();
  fill(255,255,0);
  ellipse(450,50,90,90);
  
  //tree
  stroke(0);
  fill(139,69,19); 
  rectMode(CENTER);
  rect(300,300,50,200);
  //tree leaves
  fill(0,128,0);
  ellipseMode(CENTER);
  ellipse(300,185,100,100);
  ellipse(250,200,50,50);
  ellipse(350,200,60,60);
  
  //UCSC Board
  fill(139,69,19);
  rectMode(CENTER);
  rect(300,300,300,95);
  
  
  
  //UCSC Text
  textSize(25);
  fill(255,255,0);
  text("University Of California", 160, 285);
  text("Santa Cruz", 235, 325);
  
  //White Blocks
  stroke(0);
  fill(255,255,255);
  rect(225,360,25,25);
  rect(375,360,25,25);
  
  //Granite Block
  noStroke();
  fill(208,202,196);
  rectMode(CENTER);
  rect(300,375,350,30);
  
  //grass
  rectMode(CORNER);
  fill(154,205,50);
  rect(0,375,500,125);
  
  //Head
  fill(195,130,0);
  ellipseMode(CENTER);
  ellipse(75,300,50,50);
  
  //Eyes
  fill(0,0,0);
  ellipseMode(CENTER);
  //left 
  ellipse(65,295,5,5);
  //right
  ellipse(85,295,5,5);
  
  //Nose
  stroke(0); 
  line(75,300,80,305);
  line(80,305,75,305); 
  
  //mouth
  stroke(0); 
  bezier(65, 310, 68, 315, 80, 315, 85, 310); 
  
  //Body
  fill(255,69,0);
  rectMode(CENTER);
  rect(75,375,50,100);
  
  //arms
  stroke(0);
  //left
  line(35,400,50,350);
  //right
  line(100,350,115,400);
  
  //left leg
  line(35,475,50,425);
  //right leg
  line(100,425,115,475);
  
  
  //clouds
  noStroke();
  fill(240,248,255);
  ellipseMode(CENTER);
  ellipse(50,50,50,50);
  ellipse(25,50,35,35);
  ellipse(75,50,35,35);
  
  ellipse(300,70,70,70);
  ellipse(275,70,55,55);
  ellipse(325,70,55,55);
  
  ellipse(175,110,50,50);
  ellipse(150,110,35,35);
  ellipse(200,110,35,35);
}
