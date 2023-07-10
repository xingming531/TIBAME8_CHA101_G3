package com.hotel.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HotelOwnerDao implements HotelOwnerDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/gp3?serverTimezone=Asia/Taipei";
	String userid = "root";
	String password = "123";
	// 新增
	private static final String INSERT_STMT = "INSERT INTO hotel_owner (hotel_name,hotel_address,hotel_status,hotel_lic_id,"
			+ "hotel_lic_pic,review_score_people,review_score_total,owner_account,owner_password,"
			+ "owner_name,owner_id,owner_bank,owner_phone,owner_email,owner_access) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	// 刪除
	private static final String DELETE = "DELETE FROM hotel_owner where hotel_id = ?";
	// 修改
	private static final String UPDATE_STMT = "UPDATE hotel_owner SET hotel_name = ?, hotel_address = ?, hotel_status = ?, hotel_lic_id = ?,  hotel_lic_pic = ?, "
			+ "review_score_people = ?, review_score_total = ?, owner_account = ?, owner_password = ?, "
			+ "owner_name = ?, owner_id = ?, owner_bank = ?, owner_phone = ?, owner_email = ?, "
			+ "owner_access = ? WHERE hotel_id = ?";
	// 查詢

	private static final String GET_ALL_STMT = "SELECT hotel_id, hotel_name, hotel_address, hotel_status, hotel_lic_id, "
			+ "hotel_lic_pic, review_score_people, review_score_total, owner_account, "
			+ "owner_password, owner_name, owner_id, owner_bank, owner_phone, owner_email, " + "owner_access "
			+ "FROM hotel_owner order by hotel_id";

	private static final String GET_ONE_STMT = "SELECT hotel_id, hotel_name, hotel_address, hotel_status, hotel_lic_id, "
			+ "hotel_lic_pic, review_score_people, review_score_total, owner_account, "
			+ "owner_password, owner_name, owner_id, owner_bank, owner_phone, owner_email, " + "owner_access "
			+ "FROM hotel_owner where hotel_id = ? ";

	@Override
	public void insert(HotelOwnerVO hotelOwnerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver); // JDBC4.0之後版本不再需要寫
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, hotelOwnerVO.getHotelName());
			pstmt.setString(2, hotelOwnerVO.getHotelAddress());
			pstmt.setString(3, hotelOwnerVO.getHotelStatus());
			pstmt.setString(4, hotelOwnerVO.getHotelLicId());
			pstmt.setBytes(5, hotelOwnerVO.getHotelLicPic());
			pstmt.setInt(6, hotelOwnerVO.getReviewScorePeople());
			pstmt.setInt(7, hotelOwnerVO.getReviewScoreTotal());
			pstmt.setString(8, hotelOwnerVO.getOwnerAccount());
			pstmt.setString(9, hotelOwnerVO.getOwnerPassword());
			pstmt.setString(10, hotelOwnerVO.getOwnerName());
			pstmt.setString(11, hotelOwnerVO.getOwnerId());
			pstmt.setString(12, hotelOwnerVO.getOwnerBank());
			pstmt.setString(13, hotelOwnerVO.getOwnerPhone());
			pstmt.setString(14, hotelOwnerVO.getOwnerEmail());
			pstmt.setString(15, hotelOwnerVO.getOwnerAccess());
			
			

			pstmt.executeUpdate();

//　　　　不寫Class.forName(driver)所以這個錯誤捕捉不用寫
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(HotelOwnerVO hotelOwnerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver); // JDBC4.0之後版本不再需要寫
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, hotelOwnerVO.getHotelName());
			pstmt.setString(2, hotelOwnerVO.getHotelAddress());
			pstmt.setString(3, hotelOwnerVO.getHotelStatus());
			pstmt.setString(4, hotelOwnerVO.getHotelLicId());
			pstmt.setBytes(5, hotelOwnerVO.getHotelLicPic());
			pstmt.setInt(6, hotelOwnerVO.getReviewScorePeople());
			pstmt.setInt(7, hotelOwnerVO.getReviewScoreTotal());
			pstmt.setString(8, hotelOwnerVO.getOwnerAccount());
			pstmt.setString(9, hotelOwnerVO.getOwnerPassword());
			pstmt.setString(10, hotelOwnerVO.getOwnerName());
			pstmt.setString(11, hotelOwnerVO.getOwnerId());
			pstmt.setString(12, hotelOwnerVO.getOwnerBank());
			pstmt.setString(13, hotelOwnerVO.getOwnerPhone());
			pstmt.setString(14, hotelOwnerVO.getOwnerEmail());
			pstmt.setString(15,String.valueOf(hotelOwnerVO.getOwnerAccess()));
			pstmt.setInt(16, hotelOwnerVO.getHotelId());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer hotelId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver); // JDBC4.0之後版本不再需要寫
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, hotelId);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public HotelOwnerVO findByPrimaryKey(Integer hotelId) {
		
		HotelOwnerVO hotelOwnerVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, hotelId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				hotelOwnerVO = new HotelOwnerVO();
				hotelOwnerVO.setHotelId(rs.getInt("hotel_id"));
	            hotelOwnerVO.setHotelName(rs.getString("hotel_name"));
	            hotelOwnerVO.setHotelAddress(rs.getString("hotel_address"));
	            hotelOwnerVO.setHotelStatus(rs.getString("hotel_status"));
	            hotelOwnerVO.setHotelLicId(rs.getString("hotel_lic_id"));
	            hotelOwnerVO.setHotelLicPic(rs.getBytes("hotel_lic_pic"));
	            hotelOwnerVO.setReviewScorePeople(rs.getInt("review_score_people"));
	            hotelOwnerVO.setReviewScoreTotal(rs.getInt("review_score_total"));
	            hotelOwnerVO.setOwnerAccount(rs.getString("owner_account"));
	            hotelOwnerVO.setOwnerPassword(rs.getString("owner_password"));
	            hotelOwnerVO.setOwnerName(rs.getString("owner_name"));
	            hotelOwnerVO.setOwnerId(rs.getString("owner_id"));
	            hotelOwnerVO.setOwnerBank(rs.getString("owner_bank"));
	            hotelOwnerVO.setOwnerPhone(rs.getString("owner_phone"));
	            hotelOwnerVO.setOwnerEmail(rs.getString("owner_email"));
	            hotelOwnerVO.setOwnerAccess(rs.getString("owner_access"));
			}


		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return hotelOwnerVO;
	}

	@Override
	public List<HotelOwnerVO> getAll() {
		List<HotelOwnerVO> list = new ArrayList<HotelOwnerVO>();
		HotelOwnerVO hotelOwnerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);		
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				hotelOwnerVO = new HotelOwnerVO();
				hotelOwnerVO.setHotelId(rs.getInt("hotel_id"));
	            hotelOwnerVO.setHotelName(rs.getString("hotel_name"));
	            hotelOwnerVO.setHotelAddress(rs.getString("hotel_address"));
	            hotelOwnerVO.setHotelStatus(rs.getString("hotel_status"));
	            hotelOwnerVO.setHotelLicId(rs.getString("hotel_lic_id"));
	            hotelOwnerVO.setHotelLicPic(rs.getBytes("hotel_lic_pic"));
	            hotelOwnerVO.setReviewScorePeople(rs.getInt("review_score_people"));
	            hotelOwnerVO.setReviewScoreTotal(rs.getInt("review_score_total"));
	            hotelOwnerVO.setOwnerAccount(rs.getString("owner_account"));
	            hotelOwnerVO.setOwnerPassword(rs.getString("owner_password"));
	            hotelOwnerVO.setOwnerName(rs.getString("owner_name"));
	            hotelOwnerVO.setOwnerId(rs.getString("owner_id"));
	            hotelOwnerVO.setOwnerBank(rs.getString("owner_bank"));
	            hotelOwnerVO.setOwnerPhone(rs.getString("owner_phone"));
	            hotelOwnerVO.setOwnerEmail(rs.getString("owner_email"));
	            hotelOwnerVO.setOwnerAccess(rs.getString("owner_access"));
	            list.add(hotelOwnerVO);
			}


		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	public static void main(String[] args) {
		HotelOwnerDao dao= new HotelOwnerDao();
		
		//===============================新增=======================================//
//		HotelOwnerVO hotelOwnerVO1 =new HotelOwnerVO();
//		hotelOwnerVO1.setHotelName("SSS寵物旅館");
//	    hotelOwnerVO1.setHotelAddress("台北市");
//	    hotelOwnerVO1.setHotelStatus("0");
//	    hotelOwnerVO1.setHotelLicId("12345678");
//	    hotelOwnerVO1.setHotelLicPic(null); // 這裡可以設定 byte[] 類型的圖片資料
//	    hotelOwnerVO1.setReviewScorePeople(100);
//	    hotelOwnerVO1.setReviewScoreTotal(200);
//	    hotelOwnerVO1.setOwnerAccount("12345678");
//	    hotelOwnerVO1.setOwnerPassword("1234567890");
//	    hotelOwnerVO1.setOwnerName("三上優雅");
//	    hotelOwnerVO1.setOwnerId("A123456789");
//	    hotelOwnerVO1.setOwnerBank("12345678");
//	    hotelOwnerVO1.setOwnerPhone("0912345678");
//	    hotelOwnerVO1.setOwnerEmail("XXX@gmai.com");
//	    hotelOwnerVO1.setOwnerAccess("1");
//
//	    // 呼叫 DAO 的新增方法
//	    dao.insert(hotelOwnerVO1);
//
//	    System.out.println("新增成功");
	  //===============================修改=======================================//
//	    HotelOwnerVO hotelOwnerVO2 = new HotelOwnerVO();
//	    hotelOwnerVO2.setHotelId(16);
//	    hotelOwnerVO2.setHotelName("寵物天國");
//	    hotelOwnerVO2.setHotelAddress("台北市");
//	    hotelOwnerVO2.setHotelStatus("0");
//	    hotelOwnerVO2.setHotelLicId("12345678");
//	    hotelOwnerVO2.setHotelLicPic(null); // 這裡可以設定 byte[] 類型的圖片資料
//	    hotelOwnerVO2.setReviewScorePeople(100);
//	    hotelOwnerVO2.setReviewScoreTotal(200);
//	    hotelOwnerVO2.setOwnerAccount("12345678");
//	    hotelOwnerVO2.setOwnerPassword("1234567890");
//	    hotelOwnerVO2.setOwnerName("三上優雅");
//	    hotelOwnerVO2.setOwnerId("A123456789");
//	    hotelOwnerVO2.setOwnerBank("12345678");
//	    hotelOwnerVO2.setOwnerPhone("0912345678");
//	    hotelOwnerVO2.setOwnerEmail("XXX@gmai.com");
//	    hotelOwnerVO2.setOwnerAccess("0");
//	    dao.update(hotelOwnerVO2);
		
		//===============================刪除=======================================//
//		dao.delete(16);
		
		//===============================單查=======================================//
		
//		HotelOwnerVO hotelOwnerVO3 = dao.findByPrimaryKey(1);
//		System.out.println(hotelOwnerVO3.getHotelName());
		
		//===============================多查=======================================//
		List<HotelOwnerVO> list = dao.getAll();
		for(HotelOwnerVO ho : list) {
			System.out.println(ho.getHotelAddress());
		}
		
	    
	}

}
	

