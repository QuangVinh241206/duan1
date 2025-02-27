package cuoiKy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DoiMatKhau extends JFrame {
	ConnectDB cn = new ConnectDB();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMatKhauCu, txtMatKhauMoi;

	public DoiMatKhau() {
		setTitle("Đổi mật khẩu");
		setResizable(false);
		setSize(400, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		URL urlIcon = DoiMatKhau.class.getResource("jframe_icon.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urlIcon);
		setIconImage(img);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblTenDangNhap = new JLabel("Mật khẩu cũ:");
		lblTenDangNhap.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTenDangNhap.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenDangNhap.setBounds(40, 50, 115, 30);
		contentPane.add(lblTenDangNhap);

		txtMatKhauCu = new JTextField();
		txtMatKhauCu.setBounds(180, 50, 170, 30);
		contentPane.add(txtMatKhauCu);

		JLabel lblMatKhau = new JLabel("Mật khẩu mới:");
		lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMatKhau.setHorizontalAlignment(SwingConstants.LEFT);
		lblMatKhau.setBounds(40, 100, 115, 30);
		contentPane.add(lblMatKhau);

		txtMatKhauMoi = new JTextField();
		txtMatKhauMoi.setBounds(180, 100, 170, 30);
		contentPane.add(txtMatKhauMoi);

		JButton btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnHuy.setBackground(Color.WHITE);
		btnHuy.setBounds(220, 160, 100, 30);
		btnHuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
		contentPane.add(btnHuy);

		JButton btnXacNhan = new JButton("Xác Nhận");
		btnXacNhan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnXacNhan.setBackground(Color.WHITE);
		btnXacNhan.setBounds(80, 160, 100, 30);
		btnXacNhan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connection conn = cn.getConnection();
				String matKhauCu = txtMatKhauCu.getText();
				String matKhauMoi = txtMatKhauMoi.getText();
				try {
					if (txtMatKhauCu.getText().equals("") || txtMatKhauMoi.getText().equals("")) {
						JOptionPane.showMessageDialog(btnXacNhan, "Bạn cần nhập đủ dữ liệu!", "Thông báo!",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						String sqlDoiMatKhau = "update Users set MatKhau= '" + matKhauMoi + "' where MatKhau='"
								+ matKhauCu + "'";
						Statement st = conn.createStatement();
						int kq = st.executeUpdate(sqlDoiMatKhau);
						if (kq > 0) {
							JOptionPane.showMessageDialog(btnXacNhan, "Đổi mật khẩu thành công!", "Thông báo!",
									JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
						} else
							JOptionPane.showMessageDialog(btnXacNhan, "Mật khẩu cũ không đúng!", "Thông báo!",
									JOptionPane.ERROR_MESSAGE);

					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		contentPane.add(btnXacNhan);
		setVisible(true);

	}

}
