import java.util.Random;

public class GameLogic {
	private static GameLogic instance = new GameLogic();
	private int digit[] = new int[9];
	private int emptyIdx;
	private Random rand = new Random();
	/*
	 * state:
	 * 	0: playing
	 *  1: win
	 */
	private int state;	

	private GameLogic() {
		init();
	}

	
	public void init() {
		for (int i = 0; i < 8; i++)
			digit[i] = 1 + i;
		digit[8] = 0;
		emptyIdx = 8;
		state=0;
	}

	public void shuffle() {
		for (int i = 0; i < 20; i++) {
			randomMove();
		}
	}
	
	public int getDigit(int i){
		return digit[i];
	}
	public int getEmptyIdx(){
		return emptyIdx;
	}
	public int getState(){
		return state;
	}
	
	private void randomMove() {
		while (true) {
			switch (Math.abs(rand.nextInt()) % 4) {
			case 0:
				if (moveUp())
					return;
				else
					break;
			case 1:
				if (moveDown())
					return;
				else
					break;
			case 2:
				if (moveLeft())
					return;
				else
					break;
			case 3:
				if (moveRight())
					return;
				else
					break;
			}
		}
	}

	public static GameLogic getInstance() {
		return instance;
	}

	private void swapDigit(int i, int j) {
		int t = digit[i];
		digit[i] = digit[j];
		digit[j] = t;
	}

	private void updateState(){
		for(int i=0;i<8;i++){
			if(digit[i]!=1+i){
				state=0;
				return;
			}
		}
		/*
		 * 	else, win already
		 * */
		state=1;	
	}
	public boolean moveDown() {
		if (emptyIdx <= 2)
			return false;
		swapDigit(emptyIdx, emptyIdx - 3);
		emptyIdx -= 3;
		updateState();
		return true;
	}

	public boolean moveUp() {
		if (emptyIdx >= 6)
			return false;
		swapDigit(emptyIdx, emptyIdx + 3);
		emptyIdx += 3;
		updateState();
		return true;
	}

	public boolean moveLeft() {
		if (emptyIdx % 3 == 2)
			return false;
		swapDigit(emptyIdx, emptyIdx + 1);
		emptyIdx += 1;
		updateState();
		return true;
	}

	public boolean moveRight() {
		if (emptyIdx % 3 == 0)
			return false;
		swapDigit(emptyIdx, emptyIdx - 1);
		emptyIdx -= 1;
		updateState();
		return true;
	}

	@Override
	public String toString() {
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < 9; i++) {
			if (digit[i] == 0) {
				res.append(" ");
			} else {
				res.append(Integer.toString(digit[i]));
			}
			res.append(i % 3 == 2 ? '\n' : ' ');
		}
		return res.toString();
	}

}
