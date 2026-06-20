package dto;

public class Knowledge {
	private int KnowLedgeNum;
	private String Trivia;
	
	// コンストラクタ
	public Knowledge(int knowLedgeNum, String trivia) {
		super();
		KnowLedgeNum = knowLedgeNum;
		Trivia = trivia;
	}
	
	public int getKnowLedgeNum() {
		return KnowLedgeNum;
	}
	public void setKnowLedgeNum(int knowLedgeNum) {
		KnowLedgeNum = knowLedgeNum;
	}
	public String getTrivia() {
		return Trivia;
	}
	public void setTrivia(String trivia) {
		Trivia = trivia;
	}
	
}
