package scoreManageSystem;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ScoreForm extends JFrame implements ActionListener{
	private ArrayList<ScoreDTO> list;
	private Score score;
	private JButton insertBtn, printBtn, searchBtn, deleteBtn, sortBtn, saveBtn, loadBtn;
	private JTextField numT, nameT, korT, engT, mathT;
	private JTable table;
	private DefaultTableModel model;
	// ���� ���͸� �ް� ���̺��� ���� �޴´� 
	// ���Ͱ� �����͸� �� ���� ����ְ� ���� �����͸� �������ش�(����, �߰�)
	// ���̺��� view�� ����� �Ѵ�. 
	public ScoreForm() {
		
		super("���������ý���");
		
		// ���� - �Է�
		
		JPanel numP = new JPanel();
		JLabel numL = new JLabel("����");
		numT = new JTextField(20);
		numP.add(numL);
		numP.add(numT);
		JPanel nameP = new JPanel();
		JLabel nameL = new JLabel("�̸�");
		nameT = new JTextField(20);
		nameP.add(nameL);
		nameP.add(nameT);
		JPanel korP = new JPanel();
		JLabel korL = new JLabel("����");
		korT = new JTextField(20);
		korP.add(korL);
		korP.add(korT);
		JPanel engP = new JPanel();
		JLabel engL = new JLabel("����");
		engT = new JTextField(20);
		engP.add(engL);
		engP.add(engT);
		JPanel mathP = new JPanel();
		JLabel mathL = new JLabel("����");
		mathT = new JTextField(20);
		mathP.add(mathL);
		mathP.add(mathT);
		
		JPanel leftP = new JPanel();
		leftP.setLayout(new GridLayout(5, 1, 3, 5));
		leftP.add(numP);
		leftP.add(nameP);
		leftP.add(korP);
		leftP.add(engP);
		leftP.add(mathP);
		
		// ������ ���̺� 
		Vector<String> v = new Vector<String>();
		v.add("�й�");
		v.add("�̸�");
		v.add("����");
		v.add("����");
		v.add("����");
		v.add("����");
		v.add("���");
		model = new DefaultTableModel(v, 0);
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		
		JPanel rightP = new JPanel();
		rightP.setLayout(new GridLayout(1, 1));
		rightP.add(scroll);
		
		
		// ���� + ������ 
		JPanel centerP = new JPanel();
		centerP.setLayout(new GridLayout(1, 2));
		centerP.add(leftP);
		centerP.add(rightP);
		
		score = new ScoreImpl();
		// ������ �ڽ����� ��´�. 
		// �θ�� �ڽĿ� �ִ� �͵��� ������ �� �ִ�(������)
		
		
		// ��ư
		insertBtn = new JButton("�Է�");
		printBtn = new JButton("���");
		searchBtn = new JButton("�˻�");
		deleteBtn = new JButton("����");
		sortBtn = new JButton("��ŷ");
		saveBtn = new JButton("��������");
		loadBtn = new JButton("���Ͽ���");
		
		JPanel btnP = new JPanel();
		btnP.add(insertBtn);
		btnP.add(printBtn);
		btnP.add(searchBtn);
		btnP.add(deleteBtn);
		btnP.add(sortBtn);
		btnP.add(saveBtn);
		btnP.add(loadBtn);
		
		
		Container c = getContentPane();
		c.add("Center", centerP);
		c.add("South", btnP);
		

		setBounds(700, 500, 950, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}// ScoreForm
	public void event() {
		insertBtn.addActionListener(this);
		printBtn.addActionListener(this);
		searchBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		sortBtn.addActionListener(this);
		saveBtn.addActionListener(this);
		loadBtn.addActionListener(this);
		
	}
	
	// �� �޾Ƽ� impl�� ����
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == insertBtn) {
			// TextField ���� �����´�. 
			ScoreDTO dto = new ScoreDTO();
			
			dto.setNum(numT.getText());
			dto.setName(nameT.getText());
			dto.setKor(Integer.parseInt(korT.getText()));
			dto.setEng(Integer.parseInt(engT.getText())); 
			dto.setMath(Integer.parseInt(mathT.getText()));
			dto.calc();
			// impl�� ���� ��������. 
			score.insert(dto);
			
			JOptionPane.showMessageDialog(this, "��� �Ϸ�");
			numT.setText("");
			nameT.setText("");
			korT.setText("");
			engT.setText("");
			mathT.setText("");

		}else if(e.getSource() == printBtn) {
			// table�� �����͸� �������� model���� �����͸� �־��ش�. 
			// table�� ���� �����͸� ������ �� ���� ���� �� �߰��� model���� �̷������ ����
			score.print(model);
			
		}else if(e.getSource() == searchBtn) {
			// impl���� ����� ���� ���� �� dialog�� frame �ۿ� �����Ǳ� ������ ���⼭ �����. 
			 String num = JOptionPane.showInputDialog(this, "�˻��� �й��� �Է��ϼ���");
			 if(num == null || num.equals("")) return; // ������ ������ 
			 
			 int sw = score.search(model, num);
			 // ã���� �ϴ� num�� model�� �Բ� �Ѱ����� 
			 // ����� table�� �Ѹ� ���̱� ������ model ���� ������ ����
			 if(sw == 0) JOptionPane.showMessageDialog(this, "ã�� ������ �����ϴ�");
			 
		}else if(e.getSource() == deleteBtn) {
			// �����ϰ��� �ϴ� ���� ã�´� 
			ScoreDTO dto = new ScoreDTO();
			String num = JOptionPane.showInputDialog(this, "������ �й��� �Է��ϼ��� ");
			if(num == null || num.equals("")) return;
		
			int sw = score.delete(dto, num);
			if(sw == 1) JOptionPane.showMessageDialog(this, "���� �Ϸ�");
			if(sw == 0) JOptionPane.showMessageDialog(this, "������ ������ �����ϴ�.");
		}else if(e.getSource() == sortBtn) {
			// �������� ��������
			score.sort();
			score.print(model); // ���ĵ� ������ ��� 
		}else if(e.getSource() == saveBtn) {
			score.save();
		}else if(e.getSource() == loadBtn) {
			score.load();
			score.print(model);
			// �ҷ��� ���� ������ print()�� �ѷ��ش�. 
			
		}
	}
}