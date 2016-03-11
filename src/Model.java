import java.util.Collections; 
import java.util.Random;
import java.util.LinkedList;
import java.io.IOException;
import java.util.ListIterator;

class Model
{
    Bird bird;
    LinkedList<Sprite> sprites; //ArrayList of sprite objects
    Random rand; 
    int points; //Number of obstacles cleared
    boolean end; //End model
    boolean start; //Start model
    boolean kill; //Kill sequence
    ListIterator<Sprite> it;
    
// How far to set back first tree

    
    //Default constructor, initialize member objects
	Model() {
		try {
			reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	public void update() {	
		if (this.end == false){
			Collections.sort(sprites);
			this.it = this.sprites.listIterator(); //Reset iterator
			while (it.hasNext()){
				Sprite current = it.next(); //Set current to next value in list
				current.update(); //Update all sprites
				//System.out.println("Number of elements = " + this.sprites.size());
			}
		}else{
			this.start = false;
			System.out.println("Game Over...");
		}
	}
	public void onClick(int type){
		if (this.start == false){
			this.start = true;
		}else{
			if (type == 1){
				for (int i = 0; i <this.sprites.size(); i++){
					this.sprites.get(i).leftClick(); 
				}
			}else if (type == 3){
				for (int i = 0; i <this.sprites.size(); i++){
					this.sprites.get(i).rightClick(); 
				}
			}
		}
	}
	
	
	public void reset() throws IOException{
		this.rand = new Random();
		this.points = 0;
		this.start = false;
		this.end = false;
		this.kill = false;
		this.sprites = new LinkedList<Sprite>();
		this.it = this.sprites.listIterator(); //Reset iterator
		this.sprites.add( new Bird(this));
		this.sprites.add( new Tree(this));
	}
}
