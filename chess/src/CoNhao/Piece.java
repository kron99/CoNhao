package CoNhao;

class Piece {
	int col;
	int row;
	boolean isRed;
	String imgName;

	Piece(int col, int row, boolean isRed, String imgName) {
		this.col = col;
		this.row = row;
		this.isRed = isRed;
		//this.rank = rank;
		this.imgName = imgName;
	} 
}
