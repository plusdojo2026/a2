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
	public Word() {
	}
	
	// 以下ゲッタセッタ
	public int getWord() {
		return Word;
	}
	public void setWord(int word) {
		Word = word;
	}
	public String getWordOfDay() {
		return WordOfDay;
	}
	public void setWordOfDay(String wordOfDay) {
		WordOfDay = wordOfDay;
	}
	
	
}