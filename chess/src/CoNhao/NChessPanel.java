package CoNhao;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
public class NChessPanel extends JPanel implements MouseListener {
	int orgX = 83; int orgY = 83; static int side=140;
	private ChessBoard brd;
	private Point fromColRow;
	


	public NChessPanel(ChessBoard brd) {
		this.brd = brd;
		addMouseListener(this);
	}
	//doi toa do xy xang col row
	private Point xyToColRow(Point xy) {
		return new Point ((xy.x - orgX + side/2)/side, (xy.y - orgY + side/2)/side);
	}

	// ve quan co
	private void DrawPiece(Graphics g) {
		for(Piece p : brd.getPiece() ) {
			Image img = NChess.KeyNameImage.get(p.imgName);
			g.drawImage(img, orgX + side * p.col -side/4 , 
					orgY + side * p.row	 - side/4, this);
		}
	}
	//goi ban co va quan co
	public void paint(Graphics g) {
		super.paint(g);
		drawBrd(g);
		DrawPiece(g);
		
	}

	// ve ban co
	private void drawBrd(Graphics g) {	
		for (int i=0; i < ChessBoard.cols; i++) {
			g.drawLine(orgX+ i * side, orgY, orgX+ i * side , orgY+ 4 * side);

		}
		for (int i=0; i < ChessBoard.rows; i++) {
			g.drawLine(orgX , orgY + i * side, orgX+ 4 * side , orgY+ i * side);
		}
		//duong cheo chinh trai qua phai 
		g.drawLine(orgX, orgY, orgX + 4 * side, orgY + 4 * side);
		//duong cheo phu phai qua trai
		g.drawLine(orgX + 4* side, orgY, orgX, orgY + 4 *side);
		//duong cheo 1
		g.drawLine(orgX + 2 * side , orgY, orgX, orgY + 2 * side);
		//duong cheo 2
		g.drawLine(orgX + 2 * side, orgY, orgX + 4 * side, orgY + 2 * side);
		//duong cheo 3
		g.drawLine(orgX + 4 * side, orgY + 2 * side, orgX + 2 * side , orgY + 4 * side);
		// duong cheo 4
		g.drawLine(orgX , orgY + 2 * side, orgX + 2 * side, orgY + 4 * side ); 
	} 


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent me) {
		Point mouseTip = me.getPoint();
	    System.out.println("mousepress at: " + mouseTip);
		fromColRow= xyToColRow(me.getPoint());
		System.out.println("change to fromcolrow: " + fromColRow);
		
	}


	@Override
	public void mouseReleased(MouseEvent me) {
		 	Point mouseTip = me.getPoint();
		 System.out.println("mouseReleased at (" + mouseTip.x + ", " + mouseTip.y + ")");
		   Point toColRow = xyToColRow(me.getPoint());
		  System.out.println("change to colrow: " + toColRow);
		if(fromColRow == null) {
			return;
			}
		if (brd.validMove(fromColRow.x, fromColRow.y, toColRow.x, toColRow.y) ) {
		
				brd.move(fromColRow.x, fromColRow.y, toColRow.x, toColRow.y);
				System.out.println(brd);
				repaint();
				 if(brd.whoWin()==1){ JOptionPane.showMessageDialog(this, "Black is Win!");}
				else if(brd.whoWin()==2) {JOptionPane.showMessageDialog(this, "Red is Win!");}
				//else {JOptionPane.showMessageDialog(this, "Invalid move!");}
		} 
	}

}
