package CoNhao;
import java.util.Set;

import javax.swing.JOptionPane;

import java.awt.Image;
import java.util.HashMap;
import java.util.HashSet;


public class ChessBoard {
	final static int cols=5;
	final static int rows=5;
	boolean isRed;
	int isNull[][] = new int[5][5];
	int countR = 10;
	int countB = 10;

	private boolean isRedTurn = true;



	private Set<Piece> ps = new HashSet<>(); // hashset luu thong tin cua quan		
	ChessBoard(){
		// them cac quan co vao vi tri
		//quan do
		ps.add(new Piece(0,0,true, "RP")); 
		ps.add(new Piece(1,0,true, "RP"));
		ps.add(new Piece(2,0,true, "RP")); 
		ps.add(new Piece(3,0,true, "RP"));
		ps.add(new Piece(4,0,true, "RP")); 
		ps.add(new Piece(0,1,true, "RP")); 
		ps.add(new Piece(1,1,true, "RP"));
		ps.add(new Piece(2,1,true, "RP")); 
		ps.add(new Piece(3,1,true, "RP"));
		ps.add(new Piece(4,1,true, "RP")); 
		//quan den
		ps.add(new Piece(0,4,false, "BP")); 
		ps.add(new Piece(1,4,false, "BP"));
		ps.add(new Piece(2,4,false, "BP")); 
		ps.add(new Piece(3,4,false, "BP"));
		ps.add(new Piece(4,4,false, "BP")); 
		ps.add(new Piece(0,3,false, "BP")); 
		ps.add(new Piece(1,3,false, "BP"));
		ps.add(new Piece(2,3,false, "BP")); 
		ps.add(new Piece(3,3,false, "BP"));
		ps.add(new Piece(4,3,false, "BP")); 

	} 
	// getter qua class Nchesspanel
	Set<Piece> getPiece() {
		return ps;
	}

	// kiem tra co ra khoi board
	private boolean outBoard(int col, int row) {
		return col < 0 || col > 4 || row < 0 || row > 4;
	}

	// kiem tra di thang hoac ngang
	private boolean isStraight(int fromCol, int fromRow, int toCol, int toRow) {
		return fromCol == toCol || fromRow == toRow;
	}


	// kiem tra di cheo
	private boolean isDiagonal(int fromCol, int fromRow, int toCol, int toRow) {
		return Math.abs(fromCol - toCol) == Math.abs(fromRow - toRow);
	} 

	//kiem tra cheo hoac thang
	private int steps(int fromCol, int fromRow, int toCol, int toRow) {
		if (fromCol == toCol) {
			return Math.abs(fromRow - toRow);
		} else if (fromRow == toRow) {
			return Math.abs(fromCol - toCol);
		} else if (isDiagonal(fromCol, fromRow, toCol, toRow)) {
			return Math.abs(fromRow - toRow);
		}
		return 0; // ko thang hoac cheo
	}
	// kiem tra nhung vi tri ko dc di cheo
	public boolean notDiag(int fromcol, int fromrow) {
		if(fromcol %2==0 && fromrow == 1 || fromrow == 3) {
			return true;
		} else if (fromcol== 1 || fromcol== 3 && fromrow %2==0 ) {
			return true;
		} 
		return true;
	}

	// kiem tra di chuyen 
	public boolean validMove(int fromcol, int fromrow, int tocol, int torow) {
		if (fromcol == tocol && fromrow == torow || outBoard(tocol,torow) ){
			return false;
		}
		Piece p = pieceAt(fromcol, fromrow);
		if (p ==null || p.isRed != isRedTurn ) {
			return false;
		}
		if (steps(fromcol, fromrow, tocol, torow) !=1) {
			return false;
		}
		return true;
	}
	public int cantdi(int fromrow, int fromcol ) {
		int count=0;
		int [][] arr = new int[5][5];
		for(int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				arr[i][j]= i + j +count;
			}
			count +=4;
		}	
		//System.out.print(arr[fromcol][fromrow]);
		return arr[fromrow][fromcol];
	}

	//di chuyen tong quat
	void move(int fromcol,int fromrow, int tocol, int torow ) {
		Piece tar = pieceAt(tocol,torow);

		if(tar == null && validMove(fromcol, fromrow, tocol, torow))  //if tong  quat
		{
			if(cantdi(fromrow, fromcol)%2 !=0 ) {  //if phu cua normal
				norgoStt(fromcol,fromrow,tocol,torow);
				
				isRedTurn = !isRedTurn;
				for(int row = 0; row < 5; row++) {for(int col = 0; col < 5; col++) {Piece p= pieceAt(col, row); 
					if(p==null) {isNull[col][row]= 0;}else {isNull[col][row]= 1;} }}
				
				if ( isNull[fromcol][fromrow]==1) {isRedTurn = !isRedTurn;}
				//System.out.println("from co NUll: " +isNull[fromcol][fromrow]);
				//System.out.println("to co NUll: " +isNull[tocol][torow]);
				//System.out.println(countR);
				//System.out.println(countB);

			} else {
				if (isStraight(fromcol,fromrow, tocol, torow)) { //if phu cua phu normal
					norgoStt(fromcol,fromrow,tocol,torow);
					
					isRedTurn = !isRedTurn;
					for(int row = 0; row < 5; row++) {for(int col = 0; col < 5; col++) {Piece p= pieceAt(col, row); 
						if(p==null) {isNull[col][row]= 0;}else {isNull[col][row]= 1;} }}
					
					if ( isNull[fromcol][fromrow]==1) {isRedTurn = !isRedTurn;}
					//System.out.println("from co NUll: " +isNull[fromcol][fromrow]);
					//System.out.println("to co NUll: " +isNull[tocol][torow]);
					//System.out.println(countR);
					//System.out.println(countB);
				}else {
					norgoDiag(fromcol, fromrow, tocol, torow);
					
					isRedTurn = !isRedTurn;
					for(int row = 0; row < 5; row++) {for(int col = 0; col < 5; col++) {Piece p= pieceAt(col, row); 
						if(p==null) {isNull[col][row]= 0;}else {isNull[col][row]= 1;} }}
					
					if ( isNull[fromcol][fromrow]==1) {isRedTurn = !isRedTurn;}
					//System.out.println(countR);
					//System.out.println(countB);
				}

			} //ket thuc if phu
		}else if (tar !=null && validMove(fromcol, fromrow, tocol, torow)) //else tong quat
		{
			if(cantdi(fromrow, fromcol)%2 !=0) { //if phu cua nhao
				goStt(fromcol,fromrow,tocol,torow);
				if(isRedTurn) {countB -=1;} else {countR -=1;};
				isRedTurn = !isRedTurn;
				for(int row = 0; row < 5; row++) {for(int col = 0; col < 5; col++) {Piece p= pieceAt(col, row); 
					if(p==null) {isNull[col][row]= 0;}else {isNull[col][row]= 1;  } }}
				
				if ( isNull[fromcol][fromrow]==1) {isRedTurn = !isRedTurn; if(isRedTurn) {countB +=1;}else{countR +=1;}}
				
				whoWin();
				//System.out.println("from co NUll: " +isNull[fromcol][fromrow]);
				//System.out.println("to co NUll: " +isNull[tocol][torow]);
				System.out.println(countR);
				System.out.println(countB);
			} else {
				if(isStraight(fromcol,fromrow, tocol, torow) && cantdi(fromrow, fromcol)%2 ==0) { // if phu cua phu nhao
					goStt(fromcol,fromrow,tocol,torow);
					if(isRedTurn) {countB -=1;} else {countR -=1;}
					isRedTurn = !isRedTurn;
					for(int row = 0; row < 5; row++) {for(int col = 0; col < 5; col++) {Piece p= pieceAt(col, row); 
						if(p==null) {isNull[col][row]= 0;}else {isNull[col][row]= 1; } }}
					if ( isNull[fromcol][fromrow]==1) {isRedTurn = !isRedTurn; if(isRedTurn) {countB +=1;}else{countR +=1;}}
					
					whoWin();
					//System.out.println("from co NUll: " +isNull[fromcol][fromrow]);
					//System.out.println("to co NUll: " +isNull[tocol][torow]);
					System.out.println(countR);
					System.out.println(countB);
				} else {
					goDiag(fromcol, fromrow, tocol, torow);
					if(isRedTurn) {countB -=1;} else {countR -=1;}
					isRedTurn = !isRedTurn;
					
					for(int row = 0; row < 5; row++) {for(int col = 0; col < 5; col++) {Piece p= pieceAt(col, row); 
						if(p==null) {isNull[col][row]= 0;}else {isNull[col][row]= 1; } }}
					
					if ( isNull[fromcol][fromrow]==1) {isRedTurn = !isRedTurn; if(isRedTurn) {countB +=1;}else{countR +=1;}}
					
					whoWin();
					System.out.println(countR);
					System.out.println(countB);
				}
			}

		} // dong else tong


	}/// ket thuc ham move


	public int whoWin() {
		int t = 0;
		if(countR==1 || countB==1) {
			
		}
		if(countR < 1 && countR<countB) {
			//System.out.println("Black is Win");
			return t=1;
		} 
		if (countB < 1 && countB<countR) {
			//System.out.println("Red is Win");
			return t=2;
		}
		return t;
	}
	






	// kiem tra co null ko
	public int  Checknull(int tocol,int torow) {
		System.out.println(isNull[tocol][torow]);
		return isNull[tocol][torow];
	}


	public void norgoStt(int fromcol, int fromrow, int tocol, int torow) {
		int rsc=fromcol-tocol;
		int rsr=fromrow-torow;
		Piece mp = pieceAt(fromcol, fromrow);
		if (rsc==0 && rsr==1 ) { // di len
			ps.add(new Piece (tocol,torow, mp.isRed, mp.imgName)); 
			ps.remove(mp);
		} else if (rsc==0 && rsr==-1  ) { //xuong
			ps.add(new Piece (tocol,torow, mp.isRed, mp.imgName));
			ps.remove(mp);
		} else if (rsc==-1 && rsr==0   ) {// ngang qua phai	
			ps.add(new Piece (tocol,torow, mp.isRed, mp.imgName));
			ps.remove(mp);
		} else if (rsc==1 && rsr==0  )  {// ngay qua trai
			ps.add(new Piece (tocol,torow, mp.isRed, mp.imgName));
			ps.remove(mp);
		}
	}
	public void norgoDiag(int fromcol, int fromrow, int tocol, int torow){
		int rsc=fromcol-tocol;
		int rsr=fromrow-torow;
		Piece mp = pieceAt(fromcol, fromrow);

		if (rsc==1 && rsr==1  ) { // di xeo len trai	
			int tc=tocol;
			int tr=torow;
			ps.add(new Piece (tc,tr, mp.isRed, mp.imgName)); 
			ps.remove(mp);
		} else if (rsc==-1 && rsr==1  ) { // di xeo len phai	
			int tc=tocol;
			int tr=torow;
			ps.add(new Piece (tc,tr, mp.isRed, mp.imgName));
			ps.remove(mp);
		} else if (rsc==-1 && rsr==-1   ) {// di xeo xuong phai 
			int tc=tocol;
			int tr=torow;
			ps.add(new Piece (tc,tr, mp.isRed, mp.imgName));
			ps.remove(mp);
		} else if (rsc==1 && rsr==-1  )  {// di xeo xuong trai
			int tc=tocol;
			int tr=torow;
			ps.add(new Piece (tc,tr, mp.isRed, mp.imgName));
			ps.remove(mp);
		}
	}
	public void goStt(int fromcol, int fromrow, int tocol, int torow) {
		int rsc=fromcol-tocol;
		int rsr=fromrow-torow;
		Piece mp = pieceAt(fromcol, fromrow);
		Piece tar = pieceAt(tocol, torow);
		if (rsc==0 && rsr==1 && isNull[tocol][torow-1] == 0 ) { // di len
			ps.remove(tar);
			ps.remove(mp);
			int tr=torow-1;
			ps.add(new Piece (tocol,tr, mp.isRed, mp.imgName)); 

		} else if (rsc==0 && rsr==-1  && isNull[tocol][torow+1] == 0 ) { //xuong
			ps.remove(tar);
			ps.remove(mp);
			int tr=torow+1;
			ps.add(new Piece (tocol,tr, mp.isRed, mp.imgName));

		} else if (rsc==-1 && rsr==0  &&  isNull[tocol+1][torow] == 0 ) {// ngang qua phai
			ps.remove(tar);
			ps.remove(mp);
			int tc=tocol+1;
			ps.add(new Piece (tc,torow, mp.isRed, mp.imgName));

		} else if (rsc==1 && rsr==0  &&  isNull[tocol-1][torow] == 0 )  {// ngay qua trai
			ps.remove(tar);
			ps.remove(mp);
			int tc=tocol-1;
			ps.add(new Piece (tc,torow, mp.isRed, mp.imgName));

		}
	}
	public void goDiag(int fromcol, int fromrow, int tocol, int torow){
		int rsc=fromcol-tocol;
		int rsr=fromrow-torow;
		Piece mp = pieceAt(fromcol, fromrow);
		Piece tar = pieceAt(tocol, torow);
		if (rsc==1 && rsr==1 && isNull[tocol-1][torow-1] == 0 ) { // di xeo len trai
			ps.remove(tar);
			int tc=tocol-1;
			int tr=torow-1;
			ps.add(new Piece (tc,tr, mp.isRed, mp.imgName)); 
			ps.remove(mp);
		} else if (rsc==-1 && rsr==1  && isNull[tocol+1][torow-1] == 0 ) { // di xeo len phai
			ps.remove(tar);
			int tc=tocol+1;
			int tr=torow-1;
			ps.add(new Piece (tc,tr, mp.isRed, mp.imgName));
			ps.remove(mp);
		} else if (rsc==-1 && rsr==-1  &&  isNull[tocol+1][torow+1] == 0 ) {// di xeo xuong phai 
			ps.remove(tar);
			int tc=tocol+1;
			int tr=torow+1;
			ps.add(new Piece (tc,tr, mp.isRed, mp.imgName));
			ps.remove(mp);
		} else if (rsc==1 && rsr==-1  &&  isNull[tocol-1][torow+1] == 0 )  {// di xeo xuong trai
			ps.remove(tar);
			int tc=tocol-1;
			int tr=torow+1;
			ps.add(new Piece (tc,tr, mp.isRed, mp.imgName));
			ps.remove(mp);
		}
	}


	Piece pieceAt(int col, int row) {
		for (Piece piece : ps) {
			if (piece.col == col && piece.row == row) {
				return piece;
			}
		}
		return null;
	}

	public String toString() {
		String brdStr = new String();
		brdStr = brdStr + " ";
		for (int i = 0; i < 5; i++) {
			brdStr += " " + i;
		}
		brdStr += "\n";

		for (int row = 0; row < 5; row++) {
			brdStr += row + "";
			for (int col = 0; col < 5; col++) {
				Piece p= pieceAt(col, row);
				if(p==null) {
					brdStr += " .";
				} else {
					if(p.isRed) {
						brdStr += " R";

					} else { 
						brdStr += " B";
					}			
				}
			}
			brdStr += "\n";
		}	

		return brdStr;

	}
}
