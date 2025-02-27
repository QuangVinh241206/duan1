package cuoiKy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class dangNhap extends JFrame {
	ConnectDB cn1 = new ConnectDB();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTenDangNhap;
	private JPasswordField passwordField;

	public dangNhap() {
		setTitle("Đăng nhập");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		URL urlIcon = dangNhap.class.getResource("jframe_icon.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urlIcon);
		setIconImage(img);
		setSize(500, 320);
		setLocationRelativeTo(null);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel logoLogin = new JLabel();
		ImageIcon icon = new ImageIcon(new ImageIcon("C:\\Users\\vinhn\\OneDrive\\Pictures\\310818.png").getImage()
				.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		logoLogin.setIcon(icon);
		logoLogin.setBounds(213, 10, 60, 60);
		contentPane.add(logoLogin);

		JLabel lblTenDangNhap = new JLabel("Tên đăng nhập:");
		lblTenDangNhap.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTenDangNhap.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenDangNhap.setBounds(66, 100, 115, 30);
		contentPane.add(lblTenDangNhap);

		txtTenDangNhap = new JTextField();
		txtTenDangNhap.setBounds(224, 100, 170, 30);
		contentPane.add(txtTenDangNhap);
		txtTenDangNhap.setColumns(10);

		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMatKhau.setHorizontalAlignment(SwingConstants.LEFT);
		lblMatKhau.setBounds(66, 140, 115, 30);
		contentPane.add(lblMatKhau);

		passwordField = new JPasswordField();
		passwordField.setBounds(224, 140, 170, 30);
		contentPane.add(passwordField);

		JButton btnDangNhap = new JButton("Đăng Nhập");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = cn1.getConnection();
				String tenDangNhap = txtTenDangNhap.getText();
				String matKhau = String.valueOf(passwordField.getPassword());
				try {
					if (txtTenDangNhap.getText().equals("") || String.valueOf(passwordField.getPassword()).equals("")) {
						JOptionPane.showMessageDialog(contentPane, "bạn cần nhập đủ thông tin!", "Thông báo!",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						String sql = "select TenDangNhap, MatKhau from Users where TenDangNhap = ? and MatKhau = ?";
						PreparedStatement st = conn.prepareStatement(sql);
						st.setString(1, tenDangNhap);
						st.setString(2, matKhau);
						ResultSet rs = st.executeQuery();
						if (rs.next()) {
							giaoDienChinh i = new giaoDienChinh();
							i.setVisible(true);
							setVisible(false);
						} else
							JOptionPane.showMessageDialog(btnDangNhap, "Tên đăng nhập hoặc mật khẩu không đúng!",
									"Thông báo!", JOptionPane.ERROR_MESSAGE);
						st.close();
						rs.close();
					}
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDangNhap.setBackground(Color.WHITE);
		btnDangNhap.setBounds(192, 200, 100, 30);
		contentPane.add(btnDangNhap);
		setVisible(true);
	}

}
