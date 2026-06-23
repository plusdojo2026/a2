package dto;

import java.io.Serializable;

public class Word implements Serializable{
	
	private int Word;
	private String word_of_day;
	//コンストラクタ
	public Word(int word, String word_of_day) {
		super();
		Word = word;
		this.word_of_day = word_of_day;
	}
	//getset
	public int getWord() {
		return Word;
	}

	public void setWord(int word) {
		Word = word;
	}
	public String getWord_of_day() {
		return word_of_day;
	}
	public void setWord_of_day(String word_of_day) {
		this.word_of_day = word_of_day;
	}
	
}