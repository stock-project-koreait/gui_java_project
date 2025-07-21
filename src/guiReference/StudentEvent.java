package pub2504.gui;

public class StudentEvent {
	
	private StudentUI studentUI;
	private StudentRest studentRest;
	
	public StudentEvent(StudentUI studentUI, StudentRest studentRest) {
		this.studentUI = studentUI;
		this.studentRest = studentRest;
	}
	
	public void initEvent() {
		studentUI.getGetBtn().addActionListener(e -> studentRest.loadStudents());
		studentUI.getPostBtn().addActionListener(e -> studentRest.openAddDialog());
		studentUI.getPutBtn().addActionListener(e -> studentRest.updateStudent());
		studentUI.getDelBtn().addActionListener(e -> studentRest.deleteStudent());
	}

}
