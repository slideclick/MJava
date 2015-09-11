public class MazeModel extends Object{

   static private int NORTH = 0, 
                      SOUTH = 1,
                      EAST = 2,
                      WEST = 3,
                      FAILED = 4;
   
   static private int ROW_MAX = 15, COL_MAX = 30;
   private char[][] maze;
   private int startRow, startCol;
   private MazeView view;

   public MazeModel(MazeView mv){
      view = mv;
      maze = new char[ROW_MAX][COL_MAX];
      initMaze();
   }

   public boolean solveMaze(){
   // Precondition -- The user has set the maze.
      findStartPos();
      maze[startRow][startCol] = ' ';
      boolean solved = getOut(startRow, startCol);
      maze[startRow][startCol] = 'P';
      view.printMaze();
      return solved;
   }
   
   private void initMaze(){
      for (int row = 0; row < ROW_MAX; row++)
         for (int col = 0; col < COL_MAX; col++)
            maze[row][col] = '*';
   }

   public void setMaze(String mazeStr){
   // Precondition -- The user has entered a ROW_MAX by COL_MAX maze
   //                 with a single start ('P') and termination ('T').
      for (int row = 0; row < ROW_MAX; row++){
         int strPos = row * COL_MAX + row;
         for (int col = 0; col < COL_MAX; col++){
            maze[row][col] = mazeStr.charAt(strPos);
            strPos++;
         }
      }
   }
   
   public String getMaze(){
      String str = "";
      for (int row = 0; row < ROW_MAX; row++){
         for (int col = 0; col < COL_MAX; col++)
            str = str + maze[row][col];
         str = str + "\n";
      }
      return str;
   }
   
   private void findStartPos(){
      for (int row = 0; row < ROW_MAX; row++)
         for (int col = 0; col < COL_MAX; col++)
            if (maze[row][col] == 'P'){
                startRow = row;
                startCol = col;
                return;
            }   
   }
   
   private boolean getOut(int row, int col){
      // Check for the border of the maze
      if (row < 0 || row >= ROW_MAX || col < 0 || col >= COL_MAX)
         return false;

      // Check for the exit position
      else if (maze[row][col] == 'T') 
         return true;

      // Check for a wall or a position already visited
      else if (maze[row][col] == '*' || 
               maze[row][col] == 'X' || 
               maze[row][col] == '.') 
         return false;
      else{
         maze[row][col] = 'X';                 // Mark position as visited
         view.printMaze();   
         boolean found = false;
         int direction = NORTH;
         while (! found && direction != FAILED){     // Try all directions
            if (direction == NORTH)
               found = getOut(row - 1, col);
            else if (direction == SOUTH)
               found = getOut(row + 1, col);
            else if (direction == EAST)
               found = getOut(row, col + 1);
            else if (direction == WEST)
               found = getOut(row, col - 1);
            direction++;
         }
         if (! found){                    // Mark cell as on dead end path
            maze[row][col] = '.';
            view.printMaze();
         }
         return found;
      }
   }
}
