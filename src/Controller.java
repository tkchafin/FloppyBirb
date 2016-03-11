import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


class Controller implements MouseListener
{
	
	Model model;


	Controller(Model m) {
		this.model = m;

	}

	public void mousePressed(MouseEvent e) {
		this.model.onClick(e.getButton());
	}

    public void mouseReleased(MouseEvent e) {    }
    public void mouseEntered(MouseEvent e) {    }
    public void mouseExited(MouseEvent e) {    }
    public void mouseClicked(MouseEvent e) {    }


}

