package cuoiKy;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class giaoDienChinh extends JFrame {
	final String sanPham[] = { "Mã SP", "Tên SP", "Loại SP", "Hãng", "Số lượng", "Giá bán" };
	final String DonHang[] = { "Mã DH", "Tên Khách hàng", "Số điện thoại", "Tổng tiền" };
	final String CTDH[] = { "Mã DH", "Mã SP", "Tên SP", "Số lượng", "Giá" };
	final DefaultTableModel modelSanPham = new DefaultTableModel(sanPham, 0);
	final DefaultTableModel modelGioHang = new DefaultTableModel(sanPham, 0);
	final DefaultTableModel modelDonHang = new DefaultTableModel(DonHang, 0);
	final DefaultTableModel modelChiTietDonHang = new DefaultTableModel(CTDH, 0);
	ConnectDB cn = new ConnectDB();
	Connection conn = null;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaSP, txtTenSP, txtSoLuong, txtHangSX, txtGiaBan, txtTimKiem;
	private JTable tableSanPham1, tableSanPham2, tableGioHang, tableDonHang, tableChiTietDH;
	private JPanel panelLeft, panelLogo, panelTop, panelBanHang, panelHome, panelCard, panelSanPham, panelDonHang;
	private JLabel lblTitle, lblLogo;
	private JComboBox<String> boxLoaiSP;
	private JTextField txtMaDH, txtTenKH, txtSDT, txtTienThua, txtTienKhachTra, txtThanhTien;
	private JButton btnThem, btnXoa, btnCapNhat, btnThemGioHang, btnThanhToan, btnHuyDon;

	public giaoDienChinh() {
		setTitle("Quản lý bán hàng linh kiện máy tính");
		setSize(950, 550);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		URL urlIcon = giaoDienChinh.class.getResource("jframe_icon.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urlIcon);
		setIconImage(img);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		panelLeft = new JPanel();
		panelLeft.setBounds(5, 5, 160, 511);
		panelLeft.setFocusable(false);
		panelLeft.setBorder(new BevelBorder(BevelBorder.RAISED));
		panelLeft.setBackground(new Color(22, 99, 214));

		JButton btnBanHang = new JButton("Bán hàng");
		btnBanHang.setBounds(30, 171, 100, 35);
		btnBanHang.setForeground(new Color(46, 196, 245));
		btnBanHang.setBackground(new Color(0, 64, 128));
		btnBanHang.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBanHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTitle.setText("Bán Hàng");
				panelCard.removeAll();
				panelCard.add(panelBanHang);
				panelCard.repaint();
				panelCard.revalidate();
			}
		});

		JButton btnSanPham = new JButton("Sản phẩm");
		btnSanPham.setBounds(30, 214, 100, 35);
		btnSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTitle.setText("Sản Phẩm");
				panelCard.removeAll();
				panelCard.add(panelSanPham);
				panelCard.repaint();
				panelCard.revalidate();
			}
		});
		btnSanPham.setForeground(new Color(46, 196, 245));
		btnSanPham.setBackground(new Color(0, 64, 128));
		btnSanPham.setFont(new Font("Tahoma", Font.BOLD, 13));

		JButton btnDonHang = new JButton("Đơn hàng");
		btnDonHang.setBounds(30, 257, 100, 35);
		btnDonHang.setForeground(new Color(46, 196, 245));
		btnDonHang.setBackground(new Color(0, 64, 128));
		btnDonHang.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDonHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTitle.setText("Đơn Hàng");
				panelCard.removeAll();
				panelCard.add(panelDonHang);
				panelCard.repaint();
				panelCard.revalidate();
			}
		});

		JButton btnHome = new JButton("Home");
		btnHome.setBounds(30, 128, 100, 35);
		btnHome.setForeground(new Color(46, 196, 245));
		btnHome.setBackground(new Color(0, 64, 128));
		btnHome.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTitle.setText("HOME");
				panelCard.removeAll();
				panelCard.add(panelHome);
				panelCard.repaint();
				panelCard.revalidate();
			}
		});

		JButton btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setBounds(30, 475, 100, 23);
		btnDangXuat.setForeground(new Color(255, 255, 255));
		btnDangXuat.setBackground(new Color(243, 1, 19));
		btnDangXuat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dangNhap dn = new dangNhap();
				setVisible(false);
				dn.setVisible(true);
			}
		});

		JButton btnDoiMatKhau = new JButton("Đổi mật khẩu");
		btnDoiMatKhau.setBounds(25, 445, 110, 23);
		btnDoiMatKhau.setForeground(Color.WHITE);
		btnDoiMatKhau.setBackground(Color.GRAY);
		btnDoiMatKhau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DoiMatKhau();

			}
		});

		panelLogo = new JPanel();
		panelLogo.setBounds(10, 11, 140, 77);
		panelLogo.setBackground(new Color(0, 128, 255));
		panelLogo.setBorder(new BevelBorder(BevelBorder.LOWERED));

		JLabel lblVinh = new JLabel("Vinh");
		lblVinh.setBounds(40, 7, 59, 29);
		lblVinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblVinh.setForeground(Color.WHITE);
		lblVinh.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblVinh.setBackground(new Color(0, 128, 255));

		JLabel lblComputer = new JLabel("Computer");
		lblComputer.setBounds(14, 41, 111, 29);
		lblComputer.setHorizontalAlignment(SwingConstants.CENTER);
		lblComputer.setForeground(Color.WHITE);
		lblComputer.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblComputer.setBackground(new Color(0, 128, 255));

		panelTop = new JPanel();
		panelTop.setBounds(165, 5, 774, 38);
		panelTop.setBackground(new Color(22, 99, 214));
		panelTop.setBorder(new BevelBorder(BevelBorder.RAISED));

		lblTitle = new JLabel("HOME");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));

		panelCard = new JPanel();
		panelCard.setBounds(165, 43, 774, 473);
		panelCard.setLayout(new CardLayout(0, 0));

		panelHome = new JPanel();
		panelHome.setBackground(Color.WHITE);

		panelBanHang = new JPanel();

		lblLogo = new JLabel();
		panelLogo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lblLogo.setBounds(0, 0, 774, 473);
		URL urlLogo = giaoDienChinh.class.getResource("logo.png");
		lblLogo.setIcon(new ImageIcon(urlLogo));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);

		panelSanPham = new JPanel();

		JLabel lblMaSP = new JLabel("Mã sản phẩm:");
		lblMaSP.setBounds(50, 30, 97, 19);
		lblMaSP.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaSP.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblTenSP = new JLabel("Tên sản phẩm:");
		lblTenSP.setBounds(50, 60, 97, 19);
		lblTenSP.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenSP.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblLoaiSP = new JLabel("Loại sản phẩm:");
		lblLoaiSP.setBounds(50, 90, 97, 19);
		lblLoaiSP.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoaiSP.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setBounds(420, 30, 97, 19);
		lblSoLuong.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblHangSX = new JLabel("Hãng sản xuất:");
		lblHangSX.setBounds(420, 60, 97, 19);
		lblHangSX.setHorizontalAlignment(SwingConstants.LEFT);
		lblHangSX.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblGiaBan = new JLabel("Giá bán:");
		lblGiaBan.setBounds(420, 90, 97, 19);
		lblGiaBan.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaBan.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txtMaSP = new JTextField();
		txtMaSP.setBounds(155, 31, 150, 19);
		txtMaSP.setEditable(false);

		txtTenSP = new JTextField();
		txtTenSP.setBounds(155, 61, 150, 19);

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(536, 31, 148, 19);

		txtHangSX = new JTextField();
		txtHangSX.setBounds(536, 61, 148, 19);

		txtGiaBan = new JTextField();
		txtGiaBan.setBounds(536, 91, 150, 19);

		String[] loaiSP = { "", "Màn hình", "CPU", "Card đồ họa", "Main", "Ram", "SSD", "HDD", "Tản nhiệt", "Case",
				"Bàn phím", "Chuột", "Tai nghe", "Loa", "Khác" };
		boxLoaiSP = new JComboBox<>(loaiSP);
		boxLoaiSP.setBounds(155, 92, 148, 19);
		boxLoaiSP.setBackground(Color.WHITE);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(140, 140, 95, 33);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				conn = cn.getConnection();
				try {
					if (txtTenSP.getText().equals("") || txtSoLuong.getText().equals("")
							|| txtHangSX.getText().equals("") || txtGiaBan.getText().equals("")
							|| boxLoaiSP.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(contentPane, "bạn cần nhập đủ thông tin sản phẩm!");
					} else {
						Statement st = conn.createStatement();
						int tangMaSP;
						try {
							tangMaSP = Integer.parseInt(
									(String) tableSanPham1.getValueAt(tableSanPham1.getRowCount() - 1, 0)) + 1;
						} catch (Exception e2) {
							tangMaSP = 1;
						}

						String sql = "Insert into SanPham values('" + String.valueOf(tangMaSP) + "',N'"
								+ txtTenSP.getText() + "',N'" + boxLoaiSP.getSelectedItem() + "',N'"
								+ txtHangSX.getText() + "','" + txtSoLuong.getText() + "','" + txtGiaBan.getText()
								+ "')";
						st = conn.createStatement();
						int kq = st.executeUpdate(sql);
						if (kq > 0) {
							JOptionPane.showMessageDialog(contentPane, "Thêm mới thành công!", "Thông báo!",
									JOptionPane.INFORMATION_MESSAGE);
							setDefaultSanPham();
						}
						st.close();
					}
					conn.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
				tableSanPham1.clearSelection();
			}
		});

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(260, 140, 95, 33);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXoa.setEnabled(false);
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				conn = cn.getConnection();
				try {
					String sql = "Delete SanPham where MaSP ='" + txtMaSP.getText() + "'";
					Statement st = conn.createStatement();
					int chk = JOptionPane.showConfirmDialog(btnXoa, "Bạn có chắc chắn muốn xóa không?", " Thông báo!",
							JOptionPane.YES_NO_OPTION);
					if (chk == JOptionPane.YES_OPTION) {
						st.executeUpdate(sql);
						JOptionPane.showMessageDialog(btnXoa, "Xóa thành công", "Thông báo!",
								JOptionPane.INFORMATION_MESSAGE);
						setDefaultSanPham();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBounds(380, 140, 95, 33);
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCapNhat.setEnabled(false);
		btnCapNhat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				conn = cn.getConnection();
				try {
					if (txtMaSP.getText().equals("") || txtTenSP.getText().equals("") || txtSoLuong.getText().equals("")
							|| txtHangSX.getText().equals("") || txtGiaBan.getText().equals("")
							|| boxLoaiSP.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(contentPane, "bạn cần nhập đủ dữ liệu!", "Thông báo!",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						Statement st = conn.createStatement();
						String sql = "Update SanPham set TenSP =N'" + txtTenSP.getText() + "',LoaiSP=N'"
								+ boxLoaiSP.getSelectedItem().toString() + "',HangSX=N'" + txtHangSX.getText()
								+ "',SoLuong='" + txtSoLuong.getText() + "',GiaBan='" + txtGiaBan.getText()
								+ "' where MaSP = '" + txtMaSP.getText() + "'";
						st = conn.createStatement();
						int kq = st.executeUpdate(sql);
						if (kq > 0) {
							JOptionPane.showMessageDialog(btnCapNhat, "Cập nhật thành công!", "Thông báo!",
									JOptionPane.INFORMATION_MESSAGE);
							setDefaultSanPham();
							tableSanPham1.clearSelection();
						}
						st.close();
					}
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		JButton btnHuy = new JButton("Hủy");
		btnHuy.setBounds(500, 140, 95, 33);
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setDefaultSanPham();
				txtTimKiem.setText("");
			}
		});

		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(527, 194, 118, 20);

		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(651, 193, 95, 23);
		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				conn = cn.getConnection();
				try {
					if (txtTimKiem.getText().equals("")) {
						JOptionPane.showMessageDialog(btnTimKiem, "Bạn chưa nhập sản phẩm muốn tìm!", "Thông báo!",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						int number;
						Vector<String> row;
						String sql = "select * from SanPham where TenSP LIKE N'%" + txtTimKiem.getText() + "%'";
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery(sql);
						ResultSetMetaData metadata = rs.getMetaData();
						number = metadata.getColumnCount();
						modelSanPham.setRowCount(0);
						while (rs.next()) {
							row = new Vector<String>();
							for (int i = 1; i <= number; i++) {
								row.addElement(rs.getString(i));
							}
							modelSanPham.addRow(row);
							tableSanPham1.setModel(modelSanPham);
						}
						rs.close();
						st.close();
					}
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});

		tableSanPham1 = new JTable();
		tableSanPham1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tableSanPham1.getSelectedRow() >= 0) {
					txtMaSP.setText(tableSanPham1.getValueAt(tableSanPham1.getSelectedRow(), 0) + "");
					txtTenSP.setText(tableSanPham1.getValueAt(tableSanPham1.getSelectedRow(), 1) + "");
					boxLoaiSP.setSelectedItem(tableSanPham1.getValueAt(tableSanPham1.getSelectedRow(), 2) + "");
					txtHangSX.setText(tableSanPham1.getValueAt(tableSanPham1.getSelectedRow(), 3) + "");
					txtSoLuong.setText(tableSanPham1.getValueAt(tableSanPham1.getSelectedRow(), 4) + "");
					txtGiaBan.setText(tableSanPham1.getValueAt(tableSanPham1.getSelectedRow(), 5) + "");
					txtMaSP.setEditable(false);
					btnXoa.setEnabled(true);
					btnCapNhat.setEnabled(true);
					btnThem.setEnabled(false);
				}
			}
		});

		tableSanPham2 = new JTable();
		tableSanPham2.setEnabled(false);
		tableSanPham2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnThemGioHang.setEnabled(true);
			}
		});

		JScrollPane scrollPane = new JScrollPane(tableSanPham1);
		scrollPane.setBounds(0, 220, 774, 253);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(new Color(208, 233, 253));

		panelHome.setLayout(null);
		panelHome.add(lblLogo);

		panelBanHang.setBounds(0, 0, 774, 473);
		panelBanHang.setLayout(null);

		JScrollPane scrollPane_SanPham2 = new JScrollPane(tableSanPham2);
		scrollPane_SanPham2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_SanPham2.setBounds(0, 251, 440, 222);
		panelBanHang.add(scrollPane_SanPham2);

		tableGioHang = new JTable(modelGioHang);

		JScrollPane scrollPane_GioHang = new JScrollPane(tableGioHang);
		scrollPane_GioHang.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_GioHang.setBounds(0, 25, 440, 190);
		panelBanHang.add(scrollPane_GioHang);

		JLabel lblTitleSP = new JLabel("Sản phẩm");
		lblTitleSP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitleSP.setBounds(10, 235, 69, 14);
		panelBanHang.add(lblTitleSP);

		JLabel lblGioHang = new JLabel("Giỏ hàng");
		lblGioHang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGioHang.setBounds(10, 0, 56, 26);
		panelBanHang.add(lblGioHang);

		JPanel panelTTDH = new JPanel();
		panelTTDH.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Thông tin đơn hàng",
				TitledBorder.CENTER, TitledBorder.TOP));
		panelTTDH.setBounds(450, 25, 314, 437);
		panelTTDH.setLayout(null);
		panelBanHang.add(panelTTDH);

		JLabel lblMaDH = new JLabel("Mã ĐH: ");
		lblMaDH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaDH.setBounds(10, 24, 79, 28);
		panelTTDH.add(lblMaDH);

		JLabel lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenKH.setBounds(10, 63, 109, 28);
		panelTTDH.add(lblTenKH);

		JLabel lblSDT = new JLabel("SĐT:");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSDT.setBounds(10, 102, 35, 28);
		panelTTDH.add(lblSDT);

		JLabel lblThanhTien = new JLabel("Thành tiền:");
		lblThanhTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThanhTien.setBounds(10, 141, 79, 28);
		panelTTDH.add(lblThanhTien);

		JLabel lblTienKhachTra = new JLabel("Tiền khách trả:");
		lblTienKhachTra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTienKhachTra.setBounds(10, 180, 109, 28);
		panelTTDH.add(lblTienKhachTra);

		JLabel lblTienThua = new JLabel("TIền thừa:");
		lblTienThua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTienThua.setBounds(10, 219, 79, 28);
		panelTTDH.add(lblTienThua);

		txtMaDH = new JTextField();
		txtMaDH.setBounds(129, 26, 175, 24);
		panelTTDH.add(txtMaDH);

		txtTenKH = new JTextField();
		txtTenKH.setBounds(129, 63, 175, 24);
		panelTTDH.add(txtTenKH);

		txtSDT = new JTextField();
		txtSDT.setBounds(129, 106, 175, 24);
		panelTTDH.add(txtSDT);

		txtTienThua = new JTextField();
		txtTienThua.setForeground(Color.RED);
		txtTienThua.setEditable(false);
		txtTienThua.setBounds(129, 223, 175, 24);
		panelTTDH.add(txtTienThua);

		txtTienKhachTra = new JTextField();
		txtTienKhachTra.setBounds(129, 186, 175, 24);
		txtTienKhachTra.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				tinhTienThua();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				tinhTienThua();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				tinhTienThua();
			}

			private void tinhTienThua() {
				try {
					int tienKhachTra = Integer.parseInt(txtTienKhachTra.getText());
					int thanhTien = Integer.parseInt(txtThanhTien.getText());
					int tienthua = tienKhachTra - thanhTien;
					txtTienThua.setText(String.valueOf(tienthua));
					if (tienthua < 0) {
						btnThanhToan.setEnabled(false);
						txtTienThua.setForeground(Color.red);
					} else
						txtTienThua.setForeground(Color.green);
					btnThanhToan.setEnabled(true);
				} catch (Exception e) {
					txtTienThua.setText("");
				}
			}
		});

		panelTTDH.add(txtTienKhachTra);

		txtThanhTien = new JTextField();
		txtThanhTien.setForeground(Color.red);
		txtThanhTien.setEditable(false);
		txtThanhTien.setBounds(129, 147, 175, 24);
		panelTTDH.add(txtThanhTien);

		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setBackground(Color.GREEN);
		btnThanhToan.setBounds(87, 358, 140, 50);
		btnThanhToan.setEnabled(false);
		btnThanhToan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				conn = cn.getConnection();
				if (txtMaDH.getText().equals("") || txtTenKH.getText().equals("") || txtSDT.getText().equals("")
						|| txtTienKhachTra.getText().equals("")) {
					JOptionPane.showMessageDialog(panelBanHang, "Bạn cần nhập đủ thông tin", "Thông báo!",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {
						String sql = "Insert into DonHang values('" + txtMaDH.getText() + "',N'" + txtTenKH.getText()
								+ "','" + txtSDT.getText() + "','" + txtThanhTien.getText() + "')";
						Statement st = conn.createStatement();
						st.executeUpdate(sql);
						for (int i = 0; i < tableGioHang.getRowCount(); i++) {
							String sql1 = "insert into ChiTietDonHang(MaDH, MaSp, TenSP, SoLuong, Gia) values('"
									+ txtMaDH.getText() + "','" + tableGioHang.getValueAt(i, 0) + "',N'"
									+ tableGioHang.getValueAt(i, 1) + "','" + tableGioHang.getValueAt(i, 4) + "','"
									+ tableGioHang.getValueAt(i, 5) + "')";
							st.execute(sql1);
						}
						JOptionPane.showMessageDialog(panelBanHang, "Thanh toán thành công", "Thông báo!",
								JOptionPane.INFORMATION_MESSAGE);
						st.close();
						conn.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					setDefaultDonHang();
					tableSanPham2.setEnabled(false);
					tableSanPham2.clearSelection();
					btnThanhToan.setEnabled(false);
					btnHuyDon.setEnabled(false);
					modelGioHang.setRowCount(0);
					loadBangDH();
				}
			}
		});
		panelTTDH.add(btnThanhToan);

		btnHuyDon = new JButton("Hủy");
		btnHuyDon.setBackground(new Color(254, 118, 118));
		btnHuyDon.setBounds(178, 291, 100, 40);
		btnHuyDon.setEnabled(false);
		btnHuyDon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setDefaultDonHang();
				tableSanPham2.setEnabled(false);
				tableSanPham2.clearSelection();

				btnThemGioHang.setEnabled(false);
				conn = cn.getConnection();
				try {
					if (tableGioHang.getRowCount() > 0) {
						for (int i = 0; i < tableGioHang.getRowCount(); i++) {
							Statement st = conn.createStatement();
							int sl = Integer.parseInt((String) tableGioHang.getValueAt(0, 4));
							String sql = "UPDATE SanPham SET SoLuong = SoLuong+"
									+ (String) tableGioHang.getValueAt(i, 4) + " WHERE MaSP='"
									+ (String) tableGioHang.getValueAt(i, 0) + "'";
							int kq = st.executeUpdate(sql);
							st.close();
						}
						loadBangSP();
						conn.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				modelGioHang.setRowCount(0);
				btnThanhToan.setEnabled(false);
				btnHuyDon.setEnabled(false);
				btnThemGioHang.setEnabled(false);
			}
		});
		panelTTDH.add(btnHuyDon);

		JButton btnTaoDon = new JButton("Tạo đơn");
		btnTaoDon.setBounds(40, 291, 100, 40);
		btnTaoDon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int tangMaDH = Integer.parseInt((String) tableDonHang.getValueAt(tableDonHang.getRowCount() - 1, 0))
							+ 1;
					txtMaDH.setText(tangMaDH + "");
					tableSanPham2.setEnabled(true);
					txtTenKH.setEditable(true);
					txtSDT.setEditable(true);
					txtTienKhachTra.setEditable(true);
					btnHuyDon.setEnabled(true);
				} catch (Exception e2) {
					txtMaDH.setText("1");
					tableSanPham2.setEnabled(true);
					txtTenKH.setEditable(true);
					txtSDT.setEditable(true);
					txtTienKhachTra.setEditable(true);
					btnHuyDon.setEnabled(true);
				}
			}
		});
		panelTTDH.add(btnTaoDon);

		btnThemGioHang = new JButton("Thêm vào giỏ hàng");
		btnThemGioHang.setEnabled(false);
		btnThemGioHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sl = JOptionPane.showInputDialog(btnThemGioHang, "Nhập số lượng muốn mua", "Số lượng",
							JOptionPane.DEFAULT_OPTION);
					if (sl != null) {
						int gia = Integer.parseInt((String) tableSanPham2.getValueAt(tableSanPham2.getSelectedRow(), 5))
								* Integer.parseInt(sl);
						if (Integer.parseInt(sl) > 0) {
							try {
								conn = cn.getConnection();
								int slBanDau = Integer
										.parseInt((String) tableSanPham2.getValueAt(tableSanPham2.getSelectedRow(), 4));
								if (slBanDau < Integer.parseInt(sl)) {
									JOptionPane.showMessageDialog(btnThemGioHang, "Quá số lượng hiện có!", "Thông báo!",
											JOptionPane.ERROR_MESSAGE);
								} else {
									String sql = "update SanPham set SoLuong = '" + (slBanDau - Integer.parseInt(sl))
											+ "' where MaSP= '"
											+ (String) tableSanPham2.getValueAt(tableSanPham2.getSelectedRow(), 0)
											+ "'";
									Statement st = conn.createStatement();
									st.executeUpdate(sql);
									Object[] gioHang = { tableSanPham2.getValueAt(tableSanPham2.getSelectedRow(), 0),
											tableSanPham2.getValueAt(tableSanPham2.getSelectedRow(), 1),
											tableSanPham2.getValueAt(tableSanPham2.getSelectedRow(), 2),
											tableSanPham2.getValueAt(tableSanPham2.getSelectedRow(), 3), sl,
											String.valueOf(gia) };
									modelGioHang.addRow(gioHang);
									int tinhtien = 0;
									for (int i = 0; i < tableGioHang.getRowCount(); i++) {
										tinhtien += Integer.parseInt((String) tableGioHang.getValueAt(i, 5));
									}
									String tien = tinhtien + "";
									txtThanhTien.setText(tien);
									loadBangSP();
									btnThemGioHang.setEnabled(false);
									st.close();
									conn.close();
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}

						} else

							JOptionPane.showMessageDialog(btnThemGioHang, "Số lượng không phù hợp", "Thông báo!",
									JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(btnThemGioHang, "vui lòng nhập số!", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnThemGioHang.setBounds(317, 226, 123, 23);
		panelBanHang.add(btnThemGioHang);

		panelDonHang = new JPanel();
		panelDonHang.setLayout(null);
		tableDonHang = new JTable();
		tableDonHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tableDonHang.getSelectedRow() >= 0) {
					try {
						conn = cn.getConnection();
						int number;
						Vector<String> row;
						String sql = "select MaDH, MaSP, TenSP, SoLuong, Gia from ChiTietDonHang where MaDH ='"
								+ (String) tableDonHang.getValueAt(tableDonHang.getSelectedRow(), 0) + "'";
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery(sql);
						ResultSetMetaData metadata = rs.getMetaData();
						number = metadata.getColumnCount();
						modelChiTietDonHang.setRowCount(0);
						while (rs.next()) {
							row = new Vector<String>();
							for (int i = 1; i <= number; i++) {
								row.addElement(rs.getString(i));
							}
							modelChiTietDonHang.addRow(row);
							tableChiTietDH.setModel(modelChiTietDonHang);
						}
						st.close();
						rs.close();
						conn.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		tableChiTietDH = new JTable();
		tableChiTietDH.setEnabled(false);
		JScrollPane scrollPane_DonHang = new JScrollPane(tableDonHang);
		scrollPane_DonHang.setBounds(0, 31, 375, 431);
		panelDonHang.add(scrollPane_DonHang);

		JScrollPane scrollPane_CTDH = new JScrollPane(tableChiTietDH);
		scrollPane_CTDH.setBounds(410, 31, 354, 431);
		panelDonHang.add(scrollPane_CTDH);

		JLabel lblDH = new JLabel("Đơn hàng:");
		lblDH.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDH.setBounds(10, 10, 80, 20);
		panelDonHang.add(lblDH);

		JLabel lblCTDH = new JLabel("Chi tiết đơn hàng:");
		lblCTDH.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCTDH.setBounds(421, 10, 120, 20);
		panelDonHang.add(lblCTDH);

		panelSanPham.setLayout(null);
		panelSanPham.add(lblMaSP);
		panelSanPham.add(txtMaSP);
		panelSanPham.add(lblSoLuong);
		panelSanPham.add(txtSoLuong);
		panelSanPham.add(lblTenSP);
		panelSanPham.add(txtTenSP);
		panelSanPham.add(lblHangSX);
		panelSanPham.add(txtHangSX);
		panelSanPham.add(lblLoaiSP);
		panelSanPham.add(boxLoaiSP);
		panelSanPham.add(lblGiaBan);
		panelSanPham.add(txtGiaBan);
		panelSanPham.add(btnThem);
		panelSanPham.add(btnXoa);
		panelSanPham.add(btnCapNhat);
		panelSanPham.add(btnHuy);
		panelSanPham.add(txtTimKiem);
		panelSanPham.add(btnTimKiem);
		panelSanPham.add(scrollPane);
		panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelTop.add(lblTitle);

		panelLogo.add(lblVinh);
		panelLogo.add(lblComputer);

		panelLeft.setLayout(null);
		panelLeft.add(panelLogo);
		panelLeft.add(btnHome);
		panelLeft.add(btnBanHang);
		panelLeft.add(btnSanPham);
		panelLeft.add(btnDonHang);
		panelLeft.add(btnDangXuat);
		panelLeft.add(btnDoiMatKhau);

		panelCard.add(panelHome, "home");

		contentPane.add(panelLeft);
		contentPane.add(panelTop);
		contentPane.add(panelCard);
		setDefaultDonHang();
		loadBangSP();
		loadBangDH();
		setVisible(true);
	}

	private void setDefaultSanPham() {
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtGiaBan.setText("");
		txtHangSX.setText("");
		txtSoLuong.setText("");
		boxLoaiSP.setSelectedIndex(0);
		btnXoa.setEnabled(false);
		btnCapNhat.setEnabled(false);
		btnThem.setEnabled(true);
		loadBangSP();
	}

	private void setDefaultDonHang() {
		txtMaDH.setText("");
		txtTenKH.setText("");
		txtSDT.setText("");
		txtThanhTien.setText("");
		txtTienKhachTra.setText("");
		txtTienThua.setText("");
		txtMaDH.setEditable(false);
		txtTenKH.setEditable(false);
		txtSDT.setEditable(false);
		txtTienKhachTra.setEditable(false);

	}

	public void loadBangSP() {
		try {
			conn = cn.getConnection();
			int number;
			Vector<String> row;
			String sql = "select * from SanPham";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData metadata = rs.getMetaData();
			number = metadata.getColumnCount();
			modelSanPham.setRowCount(0);
			while (rs.next()) {
				row = new Vector<String>();
				for (int i = 1; i <= number; i++) {
					row.addElement(rs.getString(i));
				}
				modelSanPham.addRow(row);
				tableSanPham2.setModel(modelSanPham);
				tableSanPham1.setModel(modelSanPham);
			}
			st.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadBangDH() {
		try {
			conn = cn.getConnection();
			int number;
			Vector<String> row;
			String sql = "select * from DonHang";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData metadata = rs.getMetaData();
			number = metadata.getColumnCount();
			modelDonHang.setRowCount(0);
			while (rs.next()) {
				row = new Vector<String>();
				for (int i = 1; i <= number; i++) {
					row.addElement(rs.getString(i));
				}
				modelDonHang.addRow(row);
				tableDonHang.setModel(modelDonHang);
			}
			st.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new dangNhap();
	}

}
