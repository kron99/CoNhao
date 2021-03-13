package CoNhao;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;

import javax.swing.*;

public class NChess {
	static Map<String, Image> KeyNameImage = new HashMap<String, Image>();

	public NChess() {
		try {
			Set<String> imgNames = new HashSet<>(Arrays.asList("BP", "RP"));
			for (String imgName : imgNames) {
				Image img = ImageIO.read(getClass().getResource("/" + imgName + ".png"))
						.getScaledInstance(NChessPanel.side-70, NChessPanel.side-70, Image.SCALE_SMOOTH);
				KeyNameImage.put(imgName, img);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		JFrame f = new JFrame("Co Nhao");
		f.setSize(800, 800);
		f.setLocation(50, 50);
		NChessPanel NCPanel = new NChessPanel(new ChessBoard());
		f.add(NCPanel);
		f.setVisible(true);

	}

	public static void main(String[] args) throws IOException {
		
//		ChessBoard cb=new ChessBoard(); 
//		System.out.println(cb);
//		cb.move(4, 1, 4, 2);
//		System.out.println(cb);
//		cb.move(4, 3, 4, 2);
//		System.out.println(cb);
		//cb.Checknull(2, 2)
		new ChessBoard();
		new NChess();
	
	}
		
}
