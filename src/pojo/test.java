package pojo;

public class test {
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String testname;
	
	private String option0;
	
	private String option1;
	
	private String option2;
	
	private String option3;
	
	private String answer;
	
	private Integer cid;

	public String getTestname() {
		return testname;
	}

	public void setTestname(String testname) {
		this.testname = testname;
	}

	public String getOption0() {
		return option0;
	}

	public void setOption0(String option0) {
		this.option0 = option0;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@Override
	public String toString() {
		return "test [testname=" + testname + ", option0=" + option0
				+ ", option1=" + option1 + ", option2=" + option2
				+ ", option3=" + option3 + ", answer=" + answer + ", cid="
				+ cid + "]";
	}
	
}
