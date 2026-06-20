package dto;

import java.io.Serializable;

public class Word implements Serializable{
	
	private int Word;
	private String WordOfDay;
	
	// コンストラクタ
	public Word(int word, String wordOfDay) {
		super();
		Word = word;
		WordOfDay = wordOfDay;
	}
	
	// 以下ゲッタセッタ
	protected int getWord() {
		return Word;
	}
	protected void setWord(int word) {
		Word = word;
	}
	protected String getWordOfDay() {
		return WordOfDay;
	}
	protected void setWordOfDay(String wordOfDay) {
		WordOfDay = wordOfDay;
	}
	
	
}