import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Tree extends Sprite{
 
	boolean first; 
	int rand_y; 
	boolean passed = false;
	boolean counted = false;
    int treeMax = 380; 
    int treeMin = 100;
    int distMax = 560;
    int distMin = 370;
    int setBack = 250; 
    double retract;
    double vHor = -4;
    double vFall = 0.0;
    
    //Statics
	static Image upTree = null;
	static Image downTree = null;
	static int dist = 0; 
    
	
	//Constructor, with height and orientation specifiied
	Tree(Model ref) throws IOException{
		this.height = 400;
		this.width=86;
		this.x = 700; //Set back to prevent removal of all trees glitch
		this.z = 2; //Trees render at layer 2
		this.mod_ref = ref;
		//Sample a height
		int temp_height = (int)Math.round(mod_ref.rand.nextInt(this.treeMax - this.treeMin)+ this.treeMin);
		this.y = temp_height;
		this.dat = mod_ref.rand.nextInt(2); //Randomly choose orientation
		if (this.dat != 0){
			this.y -= 460;
		}
		if (upTree == null){
			upTree = ImageIO.read(new File("resources/tree1.png"));
		}
		if (downTree == null){
			downTree = ImageIO.read(new File("resources/tree2.png"));
		}
		if (dist == 0){
			dist = newDist(mod_ref.rand);
		}
	}
	
	public void update(){
		this.x += this.vHor;
		this.y += this.vFall;
		//If tree position less than sampled distance for next tree
		if (this.x < dist){
			if (this.passed == false){ //And tree has not been passed yet
				//Try to add new tree, catch exception if issue with image loading
				try {
					mod_ref.it.add(new Tree(mod_ref)); //Add new tree
					//System.out.println("New tree at " + dist); //Debug print
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
					System.exit(1);
				}			
				this.setPassed(); //Set current tree to passed (by treegen)
				dist = newDist(mod_ref.rand); //Sample new distance
			}
		}
	
		//If tree is passed, count it for points
		if (this.counted == false && this.x < 100 && this.mod_ref.kill == false){
			this.counted = true; 
			mod_ref.points++;
			System.out.println(mod_ref.points);
		}
	
		//If tree out of view, remove from arraylist
		if(this.x < -100){
			mod_ref.it.remove();
		}else if (this.y < -500 || this.y > 600){
			if (this.counted == false){
				mod_ref.points++;
				System.out.println(mod_ref.points);
				mod_ref.it.remove();
			}
			
		}
		//Case of kill sequence
		if (mod_ref.kill == true){
			this.vHor = 0.0;
		}
	}
	
	public void draw(Graphics g_ref){
		if (this.dat == 0){
			g_ref.drawImage(upTree, this.x, this.y, null);
		}else{
			g_ref.drawImage(downTree, this.x, this.y, null);
		}
	}
	
	public void setPassed(){
		this.passed=true;
	}
	
	public void leftClick(){}
	public void rightClick(){}
	
	public int newDist(Random gen){
		int ret = (int)Math.round(gen.nextInt(this.distMax - this.distMin) + this.distMin);
		return ret;
	}
	
	public void collision(){
		if (this.dat == 0){
			this.vFall = 6.0;
		}else{
			this.vFall = -6.0;
		}
	}
}
