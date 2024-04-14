//package level;
//
//import java.util.Random;
//
//public class RandomLevel extends Level
//{
//	private static final Random random = new Random();
//	
//	 public RandomLevel(int width, int height)
//	 {
//		 super(width, height);
//	 }
//	 
//	 protected void generateLevel()
//	 {
//		 for(int j = 0; j < height; j++) {
//			 for(int i = 0; i < width; i++)
//			 {
//				 tilesInt[i + j * width ] = random.nextInt(4); 
//			 }
//		 }
//	 }
//}
