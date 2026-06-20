package dto;

public class Knowledge {
	private int KnowLedgeNum;
	private String Trivia;
	
	private int RecipeNum;
    private String RecipeName;
    private String ImagePath;
	
	// 豆知識用コンストラクタ
	public Knowledge(int knowLedgeNum, String trivia) {
		super();
		KnowLedgeNum = knowLedgeNum;
		Trivia = trivia;
	}
	// レシピ用コンストラクタ
	public Knowledge(int recipeNum, String recipeName, String imagePath) {
		super();
		RecipeNum = recipeNum;
		RecipeName = recipeName;
		ImagePath = imagePath;
	}

	// 以下ゲッタセッタ
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
	public int getRecipeNum() {
		return RecipeNum;
	}
	public void setRecipeNum(int recipeNum) {
		RecipeNum = recipeNum;
	}
	public String getRecipeName() {
		return RecipeName;
	}
	public void setRecipeName(String recipeName) {
		RecipeName = recipeName;
	}
	public String getImagePath() {
		return ImagePath;
	}
	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}
	
}
