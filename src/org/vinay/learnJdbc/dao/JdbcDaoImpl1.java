package org.vinay.learnJdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.vinay.learnJdbc.model.Circle;

@Component
public class JdbcDaoImpl1 {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;


	public Circle getCircle(int circleId){

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM circle where id = ?");
			ps.setInt(1,circleId);

			Circle circle = null;
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				circle = new Circle(circleId,rs.getString("name"));
			}

			rs.close();
			ps.close();
			return circle;
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	//below functions are replacing the above code sample which was used from long time
	public int getCircleCount(){
		//using jdbc we can avoid lot of code
		String sql = "SELECT COUNT(*) FROM CIRCLE";
		//jdbcTemplate.setDataSource(dataSource); //Setting datasource can be done in setter from spring
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public String getCircleName(int circleId){
		String sql = "SELECT NAME FROM CIRCLE WHERE ID = ?";
		return jdbcTemplate.queryForObject(sql,new Object[]{circleId},String.class);//return string 
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		//this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Circle getCircleforId(int circleId){
		String sql = "SeLECT * FROM CIRCLE WHERE ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{circleId},new CircleMapper()); //mapper is called for every row got from db

	}

	public List<Circle> getAllCircles(){
		String sql = "select * from circle";
		return jdbcTemplate.query(sql, new CircleMapper());
	}

	private static final class CircleMapper implements RowMapper<Circle>{

		//row mapper is used to contruct cutome model classes
		@Override
		public Circle mapRow(ResultSet resultSet, int rowNum) throws SQLException {

			Circle circle = new Circle();
			circle.setId(resultSet.getInt("ID"));
			circle.setName(resultSet.getString("NAME"));
			return circle;
		}

	}


}
