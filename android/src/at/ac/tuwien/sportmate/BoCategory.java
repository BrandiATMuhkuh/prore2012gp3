package at.ac.tuwien.sportmate;

public class BoCategory {

	int category_id; 
	String category_name; 
	double category_intensity; 

	
	BoCategory(int category_id, String category_name, double category_intensity)
	{
		this.category_id = category_id; 
		this.category_name = category_name; 
		this.category_intensity = category_intensity; 
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public double getCategory_intensity() {
		return category_intensity;
	}

	public void setCategory_intensity(double category_intensity) {
		this.category_intensity = category_intensity;
	}
	
	
}
