import java.awt.Image;  
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Feather extends Sprite{
	
	static boolean loaded = false;
	//static ArrayList<Image> frameSprites;
	static int numSprites = 6;
	
	Feather(Model ref){
		this.mod_ref = ref;
		//Load sprites
		//if (loaded == false){
		//	loaded = true;
			//this.loadImages(numSprites);
		//}
			
	}
	
	void update(){}
	void draw(Graphics g_ref){}
	void leftClick(){}
	void rightClick(){}
	void collision(){}
	
	void loadImages(int num){
		//frameSprites.add(this.loadImage("resources/feather1.png"));
	}
	
	Image loadImage(String file){
		Image img = null;
		try {
			img = ImageIO.read(new File(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
}
