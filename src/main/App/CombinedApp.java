package src.main.App;

import com.toedter.calendar.JDateChooser;
import src.main.model.Dao;
import src.main.model.Mart;




import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CombinedApp extends JFrame {
	private JDateChooser dateChooser;
	private JTable calendarTable;
	private DefaultTableModel tableModel;
	private JLabel monthYearLabel;
	private String formattedDate;
	private int count;
	private JTextField textField;
	private DefaultTableModel model;
	private ArrayList<Mart> arrayList;
	
	public CombinedApp() {
		setTitle("Java Swing Combined App");
		count = 0; // Khởi tạo count ban đầu
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Tạo bảng lịch
		tableModel = new DefaultTableModel(new Object[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }, 0);
		calendarTable = new JTable(tableModel);
		JScrollPane calendarScrollPane = new JScrollPane(calendarTable);

		/**
		 * Tạo một JLabel để hiển thị tháng và năm
		 */
		monthYearLabel = new JLabel("", SwingConstants.CENTER);

		// Thêm JLabel vào phía trên của bảng
		calendarScrollPane.setColumnHeaderView(monthYearLabel);

		/**
		 * tạo JDateChooser
		 */
		dateChooser = new JDateChooser();

		dateChooser.setDateFormatString("yyyy-MM-dd"); // Định dạng ngày tháng

		/**
		 * Đặt sự kiện cho việc chọn ngày
		 */

		// Sử dụng JSplitPane để chia giao diện thành hai phần
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createDataPanel(), calendarScrollPane);
		splitPane.setResizeWeight(0.3);

		// Thêm JSplitPane vào giao diện chính
		add(splitPane, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
		
	}

	Dao dao = new Dao();



	public boolean check() {
		return false;
	}
	private JPanel createDataPanel() {
     

        JPanel dataPanel = new JPanel(new BorderLayout());
		// Thêm JDateChooser vào JPanel ở phía trên
		dataPanel.add(dateChooser, BorderLayout.NORTH);
		
		
		// Lấy danh sách thứ ngày cho cột đầu tiên
		String[] dayOfWeek = getDayOfWeek();
		int  count1 = dao.count("M.Nam", "2023-09-23");
		// Thay đổi số dòng và số cột thành 10 và 3
		DefaultTableModel model = new DefaultTableModel(9, count1 + 1);
		JTable dataTable = new JTable(model);
		 arrayList = new ArrayList<Mart>();
		int preferredWidth = 200; // Đặt giá trị tuỳ thuộc vào nhu cầu của bạn
		   dateChooser.getDateEditor().addPropertyChangeListener(e -> {
	            if ("date".equals(e.getPropertyName())) {
	                Date selectedDate = dateChooser.getDate();
	                if (selectedDate != null) {
	                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                    String formattedDate = dateFormat.format(selectedDate);
	                    System.out.println("Selected Date1: " + formattedDate);

	                    count = dao.count("M.Nam", formattedDate);
	                    model.setColumnCount(count + 1); // Update the column count
	                    arrayList = dao.cheacked("M.Nam", formattedDate);
	                    int rowHeight = 50;
	            		int rowHeight1 = 120;
	            		int rowIndexToSetHeight = 2;
	            		int rowIndexToSetHeight1 = 4;
	            		int rowIndexToSetHeight2 = 5;
	            		dataTable.setRowHeight(rowIndexToSetHeight, rowHeight);
	            		dataTable.setRowHeight(rowIndexToSetHeight1, rowHeight1);
	            		dataTable.setRowHeight(rowIndexToSetHeight2, rowHeight);

	            		// Đặt header cho cột đầu tiên
	            		dataTable.getColumnModel().getColumn(0).setHeaderValue(dayOfWeek[0]);
	            	
	            		int columnCount = Math.min(count, arrayList.size()); // Set headers for at most 3 columns

	            		// Set headers for columns 2 and 3
	            		for (int i = 1; i <= columnCount; i++) {
	            			// Check if there are enough elements in arrayList
	            			if (i - 1 < arrayList.size()) {
	            				dataTable.getColumnModel().getColumn(i).setHeaderValue(arrayList.get(i - 1).getProvice());
	            			} else {
	            				// Handle the case where there are fewer elements in arrayList than columns
	            				System.out.println("Insufficient elements in arrayList to set headers for all columns.");
	            				break;
	            			}
	            		}

	            		// Repaint the table header
	            		dataTable.getTableHeader().repaint();
	            		for (int i = 0; i < 9; i++) {
	            			model.setValueAt("Giải tám", 0, 0);

	            			model.setValueAt("Giải bảy", 1, 0);
	            			model.setValueAt("Giải sáu", 2, 0);
	            			model.setValueAt("Giải năm", 3, 0);
	            			model.setValueAt("Giải tư", 4, 0);
	            			model.setValueAt("Giải ba", 5, 0);
	            			model.setValueAt("Giải nhì", 6, 0);
	            			model.setValueAt("Giải nhất", 7, 0);
	            			model.setValueAt("Giải Đặc biệt", 8, 0);

	            		}

	            		for (int i = 1; i <= columnCount; i++) {
	            			// Check if there are enough elements in arrayList

	            			if (i - 1 < arrayList.size()) {
	            				double giaiTamValue = Double.parseDouble(arrayList.get(i - 1).getGiai_tam());
	            				double giaibay = Double.parseDouble(arrayList.get(i - 1).getGiai_bay1());
	            				double giaisau1 = Double.parseDouble(arrayList.get(i - 1).getGiai_sau1());
	            				double giaisau2 = Double.parseDouble(arrayList.get(i - 1).getGiai_sau2());
	            				double giaisau3 = Double.parseDouble(arrayList.get(i - 1).getGiai_sau3());
	            				double giainam = Double.parseDouble(arrayList.get(i - 1).getGiai_nam1());
	            				double giaitu1 = Double.parseDouble(arrayList.get(i - 1).getGiai_tu1());
	            				double giaitu2 = Double.parseDouble(arrayList.get(i - 1).getGiai_tu2());
	            				double giaitu3 = Double.parseDouble(arrayList.get(i - 1).getGiai_tu3());
	            				double giaitu4 = Double.parseDouble(arrayList.get(i - 1).getGiai_tu4());
	            				double giaitu5 = Double.parseDouble(arrayList.get(i - 1).getGiai_tu5());
	            				double giaitu6 = Double.parseDouble(arrayList.get(i - 1).getGiai_tu6());
	            				double giaitu7 = Double.parseDouble(arrayList.get(i - 1).getGiai_tu7());
	            				double giaiba1 = Double.parseDouble(arrayList.get(i - 1).getGiai_ba1());
	            				double giaiba2 = Double.parseDouble(arrayList.get(i - 1).getGiai_ba2());
	            				double giainhi = Double.parseDouble(arrayList.get(i - 1).getGiai_nhi1());
	            				double nhat = Double.parseDouble(arrayList.get(i - 1).getGiai_nhat());
	            				double db = Double.parseDouble(arrayList.get(i - 1).getGiai_db());
	            				model.setValueAt((int) giaiTamValue, 0, i);
	            				model.setValueAt((int) giaibay, 1, i);

	            				model.setValueAt(
	            						"<html>" + (int) giaisau1 + "<br>" + (int) giaisau2 + "<br>" + (int) giaisau3 + "</html>", 2,
	            						i);

	            				model.setValueAt((int) giainam, 3, i);
	            				model.setValueAt("<html>" + (int) giaitu1 + "<br>" + (int) giaitu2 + "<br>" + (int) giaitu3 + "<br>"
	            						+ (int) giaitu4 + "<br>" + (int) giaitu5 + "<br>" + (int) giaitu6 + "<br>" + (int) giaitu7
	            						+ "</html>", 4, i);
	            				model.setValueAt("<html>" + (int) giaiba1 + "<br>" + (int) giaiba2 + "</html>", 5, i);
	            				model.setValueAt((int) giainhi, 6, i);
	            				model.setValueAt((int) nhat, 7, i);
	            				model.setValueAt((int) db, 8, i);
	            			} else {

	            				break;
	            			}
	            		}
	                } else {
	                    System.out.println("No date selected.");
	                }
	                updateCalendar(selectedDate);
	            }
	        });
		   int rowHeight = 50;
			int rowHeight1 = 120;
			int rowIndexToSetHeight = 2;
			int rowIndexToSetHeight1 = 4;
			int rowIndexToSetHeight2 = 5;
			dataTable.setRowHeight(rowIndexToSetHeight, rowHeight);
			dataTable.setRowHeight(rowIndexToSetHeight1, rowHeight1);
			dataTable.setRowHeight(rowIndexToSetHeight2, rowHeight);
		
			  ArrayList<Mart> arrayList = dao.cheacked("M.Nam", "2023-09-23");
           
			// Đặt header cho cột đầu tiên
			dataTable.getColumnModel().getColumn(0).setHeaderValue(dayOfWeek[0]);
		
			int columnCount = Math.min(count1, arrayList.size() ); // Set headers for at most 3 columns

			// Set headers for columns 2 and 3
			for (int i = 1; i <= columnCount; i++) {
				// Check if there are enough elements in arrayList
				if (i - 1 < arrayList.size()) {
					dataTable.getColumnModel().getColumn(i).setHeaderValue(arrayList.get(i - 1).getProvice());
				} else {
					// Handle the case where there are fewer elements in arrayList than columns
					System.out.println("Insufficient elements in arrayList to set headers for all columns.");
					break;
				}
			}

			// Repaint the table header
			dataTable.getTableHeader().repaint();
			for (int i = 0; i < 9; i++) {
				model.setValueAt("Giải tám", 0, 0);

				model.setValueAt("Giải bảy", 1, 0);
				model.setValueAt("Giải sáu", 2, 0);
				model.setValueAt("Giải năm", 3, 0);
				model.setValueAt("Giải tư", 4, 0);
				model.setValueAt("Giải ba", 5, 0);
				model.setValueAt("Giải nhì", 6, 0);
				model.setValueAt("Giải nhất", 7, 0);
				model.setValueAt("Giải Đặc biệt", 8, 0);

			}

			for (int i = 1; i <= columnCount; i++) {
				// Check if there are enough elements in arrayList

				if (i - 1 < arrayList.size()) {
					double giaiTamValue = Double.parseDouble(arrayList.get(i - 1).getGiai_tam());
					double giaibay = Double.parseDouble(arrayList.get(i - 1).getGiai_bay1());
					double giaisau1 = Double.parseDouble(arrayList.get(i - 1).getGiai_sau1());
					double giaisau2 = Double.parseDouble(arrayList.get(i - 1).getGiai_sau2());
					double giaisau3 = Double.parseDouble(arrayList.get(i - 1).getGiai_sau3());
					double giainam = Double.parseDouble(arrayList.get(i - 1).getGiai_nam1());
					double giaitu1 = Double.parseDouble(arrayList.get(i - 1).getGiai_tu1());
					double giaitu2 = Double.parseDouble(arrayList.get(i - 1).getGiai_tu2());
					double giaitu3 = Double.parseDouble(arrayList.get(i - 1).getGiai_tu3());
					double giaitu4 = Double.parseDouble(arrayList.get(i - 1).getGiai_tu4());
					double giaitu5 = Double.parseDouble(arrayList.get(i - 1).getGiai_tu5());
					double giaitu6 = Double.parseDouble(arrayList.get(i - 1).getGiai_tu6());
					double giaitu7 = Double.parseDouble(arrayList.get(i - 1).getGiai_tu7());
					double giaiba1 = Double.parseDouble(arrayList.get(i - 1).getGiai_ba1());
					double giaiba2 = Double.parseDouble(arrayList.get(i - 1).getGiai_ba2());
					double giainhi = Double.parseDouble(arrayList.get(i - 1).getGiai_nhi1());
					double nhat = Double.parseDouble(arrayList.get(i - 1).getGiai_nhat());
					double db = Double.parseDouble(arrayList.get(i - 1).getGiai_db());
					model.setValueAt((int) giaiTamValue, 0, i);
					model.setValueAt((int) giaibay, 1, i);

					model.setValueAt(
							"<html>" + (int) giaisau1 + "<br>" + (int) giaisau2 + "<br>" + (int) giaisau3 + "</html>", 2,
							i);

					model.setValueAt((int) giainam, 3, i);
					model.setValueAt("<html>" + (int) giaitu1 + "<br>" + (int) giaitu2 + "<br>" + (int) giaitu3 + "<br>"
							+ (int) giaitu4 + "<br>" + (int) giaitu5 + "<br>" + (int) giaitu6 + "<br>" + (int) giaitu7
							+ "</html>", 4, i);
					model.setValueAt("<html>" + (int) giaiba1 + "<br>" + (int) giaiba2 + "</html>", 5, i);
					model.setValueAt((int) giainhi, 6, i);
					model.setValueAt((int) nhat, 7, i);
					model.setValueAt((int) db, 8, i);
				} else {

					break;
				}
			}
		JScrollPane dataScrollPane = new JScrollPane(dataTable);
		// Thêm JScrollPane vào JPanel
		dataPanel.add(dataScrollPane, BorderLayout.CENTER);
		/* thêm panel */

		return dataPanel;
    }
	
		
	

	private String[] getDayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		int firstDayOfWeek = calendar.getFirstDayOfWeek();
		String[] dayOfWeek = new String[7];

		for (int i = 0; i < 7; i++) {
			dayOfWeek[i] = new DateFormatSymbols().getShortWeekdays()[(firstDayOfWeek + i) % 7];
		}

		return dayOfWeek;
	}

	private void updateCalendar(Date currentDate) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);

		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);

		// Cập nhật nội dung của JLabel với thông tin tháng và năm
//		monthYearLabel.setText(new DateFormatSymbols().getMonths()[month] + " " + year);

		int firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
		int numberOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		// Xóa dữ liệu cũ trong bảng
		tableModel.setRowCount(0);

		// Thêm dữ liệu mới vào bảng
		int dayOfMonth = 1;
		for (int i = 0; i < 6; i++) {
			tableModel.addRow(new Object[7]);
			for (int j = 0; j < 7; j++) {
				if ((i == 0 && j < firstDayOfMonth - 1) || (dayOfMonth > numberOfDaysInMonth)) {
					// Nếu ô trống hoặc vượt quá số ngày trong tháng, bỏ qua
					continue;
				}
				tableModel.setValueAt(dayOfMonth, i, j);
				dayOfMonth++;
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			CombinedApp combinedApp = new CombinedApp();
			combinedApp.updateCalendar(new Date()); // Hiển thị lịch cho ngày hiện tại
			combinedApp.setVisible(true);
		});
	}
}