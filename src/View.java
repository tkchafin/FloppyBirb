import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Font;

class View extends JPanel {
	
	Model model;
	Image background;
	Image startMenu;
	Image endMenu;

	View(Model m) throws IOException {
		this.model = m;		
		this.background = ImageIO.read(new File("resources/background.png"));
		this.startMenu = ImageIO.read(new File("resources/start.png"));
		this.endMenu= ImageIO.read(new File("resources/end.png"));
	}

	public void paintComponent(Graphics g) {
		int fontSize = 16;
        Font f = new Font(g.getFont().getFontName(), Font.BOLD, fontSize);
        g.setFont(f);
		if (this.model.start == true){
			g.drawImage(this.background, -10,-10,null); //Draw background
			for (int i = 0; i <this.model.sprites.size(); i++){
				this.model.sprites.get(i).draw(g); 
			}
			g.drawString("Score:", 380, 460);
			g.drawString(Integer.toString(this.model.points), 440, 460);
		}else if (this.model.start == false && this.model.end == false){
			g.drawImage(this.startMenu, -10, -10, null);
		}else if (this.model.end == true){
			g.drawImage(this.endMenu, -10,-10,null);
			g.drawString("Your final score:", 170, 370);
			g.drawString(Integer.toString(this.model.points), 320, 370);
			try {
				this.model.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
