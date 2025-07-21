package pub2504.gui;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class StudentRest {
	
	private StudentUI studentUI;
	private DefaultTableModel model;
	private int maxId;
	
	private final String API_URL
		= "http://localhost:7777/studentscore";
	
	private final OkHttpClient client = new OkHttpClient();
	
	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();	
	
	public StudentRest(StudentUI studentUI) {
		this.studentUI = studentUI;
		this.model = studentUI.getModel();
	}
	
	public void loadStudents() {
		
		Request request = new Request.Builder()
				.url(API_URL)
				.build();
		
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String json = Objects.requireNonNull(response.body()).string();
				List<Student> students 
					= gson.fromJson(json, new TypeToken<List<Student>>() {}.getType());
				
				setMaxId(students.stream()
					.map(Student::getId)
					.mapToInt(Integer::parseInt)
					.max()
					.orElse(0));
				
				SwingUtilities.invokeLater(() -> {
					model.setRowCount(0);
					for (Student student : students) {
						Score score = student.getScore();
						model.addRow(new Object[] {
							student.getId(), student.getName(),
							score.getKor(), score.getMath(), score.getEng(),
							score.getKor() + score.getMath() + score.getEng(),
							(score.getKor() + score.getMath() + score.getEng()) / 3
						});
					}
				});
			}
			@Override
			public void onFailure(Call call, IOException ioe) {
				showError("데이터 로딩 실패! " + ioe.getMessage());
			}
		});
		
	}
	
	public void openAddDialog() {
		
		JDialog dialog = new JDialog(studentUI, "Student 추가", true);
		dialog.setSize(300, 250);
		dialog.setLayout(new GridLayout(5, 2));
		
		JTextField tfName = new JTextField();
		JTextField tfKor = new JTextField();
		JTextField tfMath = new JTextField();
		JTextField tfEng = new JTextField();
		
		dialog.add(new JLabel("이름: "));
		dialog.add(tfName);
		dialog.add(new JLabel("국어: "));
		dialog.add(tfKor);
		dialog.add(new JLabel("수학: "));
		dialog.add(tfMath);
		dialog.add(new JLabel("영어: "));
		dialog.add(tfEng);
		
		JButton ok = new JButton("추가");
		JButton cancel = new JButton("취소");
		
		dialog.add(ok);
		dialog.add(cancel);
		
		ok.addActionListener(e -> {
			
			Student student = new Student(
				String.valueOf(maxId+1), // 등록시 최대Id +1 적용
				tfName.getText(),
				new Score(
					Integer.parseInt(tfKor.getText()),
					Integer.parseInt(tfMath.getText()),
					Integer.parseInt(tfEng.getText())
				)
			);
			
			RequestBody body = RequestBody.create(
				MediaType.get("application/json"),
				gson.toJson(student)
			);
			
			Request request = new Request.Builder()
				.url(API_URL)
				.post(body)
				.build();
			
			client.newCall(request).enqueue(new Callback() {
				
				@Override
				public void onResponse(Call call, Response response) throws IOException {
					SwingUtilities.invokeLater(() -> {
						JOptionPane.showMessageDialog(dialog, "Student 추가 완료!");
						dialog.dispose();
						loadStudents();
					});
				}
				
				@Override
				public void onFailure(Call call, IOException ioe) {
					showError("Student 추가 실패: " + ioe.getMessage());
				}
				
			});
						
		});
		
		cancel.addActionListener(e -> dialog.dispose());
		dialog.setLocationRelativeTo(studentUI);
		dialog.setVisible(true);
		
	}
	
	public void updateStudent() {
		
		int row = studentUI.getTable().getSelectedRow();
		if (row == -1) {
			showError("수정할 행을 선택하세요!");
			return;
		}
		
		Student student = new Student(
			model.getValueAt(row, 0).toString(),
			model.getValueAt(row, 1).toString(),
			new Score(
				Integer.parseInt(model.getValueAt(row, 2).toString()),
				Integer.parseInt(model.getValueAt(row, 3).toString()),
				Integer.parseInt(model.getValueAt(row, 4).toString())
			)
		);
		
		RequestBody body = RequestBody.create(
			MediaType.get("application/json"),
			gson.toJson(student)
		);
		
		Request request = new Request.Builder()
			.url(API_URL + "/" + student.getId())
			.put(body)
			.build();
		
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				SwingUtilities.invokeLater(() -> {
					loadStudents();
					JOptionPane.showMessageDialog(null, "Student 수정 완료!");
					response.body().close(); // 메모리 누수 방지
				});
			}
			@Override
			public void onFailure(Call call, IOException ioe) {
				showError("Student 수정 실패! " + ioe.getMessage());
			}
		});
		
	}
	
	public void deleteStudent() {		
		
		int row = studentUI.getTable().getSelectedRow();
		if (row == -1) {
			showError("삭제할 행을 선택하세요!");
			return;
		}
		
		Request request = new Request.Builder()
			.url(API_URL + "/" + model.getValueAt(row, 0).toString())
			.delete()
			.build();
		
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				SwingUtilities.invokeLater(() -> {
					loadStudents();
					JOptionPane.showMessageDialog(null, "Student 삭제 완료!");
					response.body().close(); // 메모리 누수 방지
				});
			}
			@Override
			public void onFailure(Call call, IOException ioe) {
				showError("Student 삭제 실패! " + ioe.getMessage());
			}
		});
	}
	
	public void showError(String msg) {
		SwingUtilities.invokeLater(() -> {
			JOptionPane.showMessageDialog(studentUI, msg, "오류 발생!", JOptionPane.ERROR_MESSAGE);
		});
	}	
	
	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}

}
