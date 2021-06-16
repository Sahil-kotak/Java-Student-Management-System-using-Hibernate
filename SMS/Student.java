class Student {
	private int rno;
	private String name;
	private int sm1;
	private int sm2;
	private int sm3;
	
	public Student() {
		rno = 0;
		name = null;
		sm1 = 0;
		sm2 = 0;
		sm3 = 0;
	}

	public Student(int rno, String name, int sm1, int sm2, int sm3) {
		this.rno = rno;
		this.name =  name;
		this.sm1 = sm1;
		this.sm2 = sm2;
		this.sm3 = sm3;
	}
	
	public String toString() {
		return "rno = " + rno + " name= " + name + " Sub Marks1= " + sm1 + " Sub Marks2= " + sm2 + " Sub Marks3= " + sm3;
	}
	
	public void setRno(int rno) {
		this.rno = rno;
	}
	
	public int getRno() {
		return rno;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}	

	public void setSm1(int sm1) {
		this.sm1 = sm1;
	}
	
	public int getSm1() {
		return sm1;
	}

	public void setSm2(int sm2) {
		this.sm2 = sm2;
	}
	
	public int getSm2() {
		return sm2;
	}
	
	public void setSm3(int sm3) {
		this.sm3 = sm3;
	}
	
	public int getSm3() {
		return sm3;
	}
}
	