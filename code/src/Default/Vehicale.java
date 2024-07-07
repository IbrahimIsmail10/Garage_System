package Default;

public class Vehicale
{
	public String modelName = "";
	public int lisence;
	public int modelYear;
	public int width;
	public int depth;
	//Default constructor
	public Vehicale(){
		modelName = "";
		lisence = 0;
		modelYear = 0;
		width = 0;
		depth = 0;
	}//Parameterized constructor
	public Vehicale(String modelName, int modelYear, int width, int depth, int lisence){
		this.modelName = modelName;
		this.modelYear = modelYear;
		this.depth = depth;
		this.width = width;
		this.lisence = lisence;
	}
}