package main;

public class FloatRegister {

	public static int number=0;
	String name;
	float value;
	String qj;
	
	
	public FloatRegister(float value) {
		name="F"+number;
		this.value=value;
		number++;
	}

}
