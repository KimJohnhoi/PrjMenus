package menus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Dao: Data Access Object
// CRUD 작업: Create(Insert), Read(Select), Update, Delete
public class MenuDao {
// 데이터 추가
	public void addMenu(String menu_id, String menu_name, int menu_seq) {
		DBConn db = new DBConn();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "";
		sql       += "INSERT INTO MENUS VALUES(?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menu_id);
			pstmt.setString(2, menu_name);
			pstmt.setInt(3, menu_seq);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
// 데이터 조회(목록전체)
	public ArrayList<MenuDto> getMenuList() {
		DBConn db = new DBConn();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		sql += "SELECT* FROM MENUS ";
		sql += " ORDER BY MENU_SEQ ASC";
		
		ArrayList<MenuDto> menuList = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); // rs 를 jsp 에 줄 수 없음. dto 에 전달함
						
			while (rs.next()) {
				String menu_id   = rs.getString("menu_id");
				String menu_name = rs.getString("menu_name");
				int menu_seq     = rs.getInt("menu_seq");
				
				MenuDto menuDto = new MenuDto();
				menuDto.setMenu_id(menu_id);
				menuDto.setMenu_name(menu_name);
				menuDto.setMenu_seq(menu_seq);
				
				menuList.add(menuDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)     rs.close();
				if (pstmt != null)	pstmt.close();
				if (conn!= null) 	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return menuList;
	} // end of getMenuList()
		
// 데이터 조회(개별조회)
	public MenuDto getMenu(String in_menu_id) {
		DBConn db = new DBConn();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
						
		String sql = "";
		sql += "SELECT * FROM  MENUS ";
		sql += " WHERE MENU_ID LIKE ? ";
		
		MenuDto menuDto = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, in_menu_id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String menu_id = rs.getString("menu_id");
				String menu_name = rs.getString("menu_name");
				int menu_seq = rs.getInt("menu_seq");
				
				menuDto = new MenuDto();
				menuDto.setMenu_id(menu_id);
				menuDto.setMenu_name(menu_name);
				menuDto.setMenu_seq(menu_seq);			
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)     rs.close();
				if (pstmt != null)	pstmt.close();
				if (conn!= null) 	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}		
		return menuDto;
	} // end of getMenu()
	
// 데이터 수정
	public void updateMenu(String menu_id, String menu_name, int menu_seq) {
		DBConn db = new DBConn();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "";
		sql += "UPDATE MENUS "; 
		sql += " SET MENU_NAME = ? ,"; 
		sql += " MENU_SEQ = ? ";
		sql += " WHERE MENU_ID = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menu_name);
			pstmt.setInt(2, menu_seq);
			pstmt.setString(3, menu_id);
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			try {
				if (pstmt != null)	pstmt.close();
				if (conn!= null) 	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}	
	} // end of updateMenu()	
	
	
// 데이터 삭제
	public void delMenu(String menu_id) {
		DBConn db = new DBConn();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "";
		sql += "DELETE FROM MENUS "; 
		sql += " WHERE MENU_ID = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menu_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			try {
				if (pstmt != null)	pstmt.close();
				if (conn!= null) 	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}	
	} // end of delMenu()	
}
