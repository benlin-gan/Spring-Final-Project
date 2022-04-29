public class Piece{
  public int[][] rotate(int[][] input){
    //rotation about the center of a square grid
    int[][] output = new int[input.length][input[0].length];
    for(int i = 0; i < input.length / 2 + 1; i++){
      for(int j = 0; j < input[0].length / 2 + 1; j++){
        output[i][j] = input[j][input.length - i - 1];
        output[i][output.length - j - 1] = input[j][i];
        output[output.length - i - 1][output.length - j - 1] = input[j][input.length - i - 1];
        output[output.length - i - 1][j] = input[input.length - j - 1][input.length - i - 1];
      }
    }
    return output;
  }
}