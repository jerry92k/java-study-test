package study.inheritance;

public class HighSchool extends School{

	public HighSchool() {
		super();
		printSchoolType();
	}

	@Override
	protected void printSchoolType(){
		System.out.println("high school");
	}
}
