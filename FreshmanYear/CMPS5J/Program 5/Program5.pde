/*
  Draws a quilt. Everytime a key is pressed it draws different quilts of different sizes.
  Also, it draws 3 different types of panels.
*/

void setup()
{
  size(800, 800);
}

int vertexX1 = 0;
int vertexY1 = -50;
int vertexX2 = 14;
int vertexY2= -20;
int vertexX3 = 47;
int vertexY3 = -15;
int vertexX4 = 23;
int vertexY4 = 7;
int vertexX5 = 29;
int vertexY5 = 40;
int vertexX6 = 0;
int vertexY6 = 25;
int vertexX7 = -29;
int vertexY7 = 40;
int vertexX8 = -23;
int vertexY8 = 7;
int vertexX9 = -47;
int vertexY9 = -15;
int vertexX10 = -14;
int vertexY10 = -20;
int seed;

int randomX;
int randomY;
int randomNum;
int r;
int g;
int b;

int ellipseX;
int ellipseY;
int ellipseSize;

int randNums;
int randCols;
int randSize;

void draw()
{
  randNums = (int)(random(5, 10));
  randCols = (int)(random(5, 10));
  if (randNums < randCols)
    randSize = width/randCols;
  else if (randNums > randCols)
    randSize = width/randNums;
  else
    randSize = width/randCols;

  background(255);
  drawQuilt(randNums, randCols, 0, 0, randSize);

  randomSeed(seed);
}

//Draws the quilt with random numbers of columns and rows
void drawQuilt(int numCols, int numRows, int x, int y, int panelSize)
{
  int columns = 0;
  while (columns < numCols)
  {
    drawRow( numRows, x, y, panelSize);
    y += panelSize;
    columns++;
  }
}

//Draws one panel in the quilt
void drawPanel(int x, int y, int panelSize)
{
  randomNums(panelSize);
  PGraphics img = createGraphics(panelSize, panelSize);
  img.beginDraw();
  ellipseX = (int)random(panelSize);
  ellipseY = (int)random(panelSize);
  ellipseSize = (int)random(25, 50);
  img.background(r, g, b);
  if (randomNum == 1) {
    img.fill(b, r, g);
    img.ellipse(ellipseX, ellipseY, ellipseSize, ellipseSize);
  } else if (randomNum == 2) {
    img.fill(g, b, r);
    img.rect(randomX, randomY, panelSize/4, panelSize/4);
  } else {
    img.fill(r, r, b);
    drawStar(img, panelSize);
  }
  img.endDraw();
  image(img, x, y);
}

//Draws rows
void drawRow(int numRows, int x, int y, int panelSize)
{
  int rows = 0;
  while (rows < numRows)
  {
    drawPanel(x, y, panelSize);
    x += panelSize;
    rows++;
  }
}

//Draws a star
void drawStar(PGraphics img, int panelSize)
{
  int randX = (int)random(0, panelSize);
  int randY = (int)random(0, panelSize);
  img.pushMatrix();
  img.translate(randX, randY);
  img.rotate(radians(frameCount));
  //Specific points to create the star
  img.beginShape();
  img.vertex(vertexX1, vertexY1);
  img.vertex(vertexX2, vertexY2);
  img.vertex(vertexX3, vertexY3);
  img.vertex(vertexX4, vertexY4);
  img.vertex(vertexX5, vertexY5);
  img.vertex(vertexX6, vertexY6);
  img.vertex(vertexX7, vertexY7);
  img.vertex(vertexX8, vertexY8);
  img.vertex(vertexX9, vertexY9);
  img.vertex(vertexX10, vertexY10);
  img.endShape(CLOSE);
  img.popMatrix();
}

//Every time a key is pressed, it makes different types of quilts
void keyPressed()
{
  seed = (int)random(100000);
}

//Makes random numbers for panels
void randomNums(int panelSize)
{
  randomX = (int)random(panelSize/4, panelSize*3/4);
  randomY = (int)random(panelSize/4, panelSize*3/4);
  randomNum = (int)random(4);
  r = (int)random(255);
  g = (int)random(255);
  b = (int)random(255);
}