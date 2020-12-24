package org.vinay.learnJdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.vinay.learnJdbc.dao.HibernateDaoImpl;
import org.vinay.learnJdbc.dao.JdbcDaoImpl;
import org.vinay.learnJdbc.model.Circle;

public class JdbcDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Circle circle = new JdbcDaoImpl().getCircle(2);.
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		HibernateDaoImpl dao = ctx.getBean("hibernateDaoImpl",HibernateDaoImpl.class);
		//Circle circle = dao.getCircle(1);
		//System.out.println(circle.getName());
		//System.out.println(dao.getCircleName(2));
		//System.out.println(dao.getCircleforId(1).getName());
		//System.out.println(dao.getAllCircles().size());
		//dao.insertCircle(new Circle(5,"fifth circle"));
		//dao.insertCirlceByNamedParameter(new Circle(6,"sixth circle"));
		//System.out.println(dao.getAllCircles().size());
		//dao.createTriangleTable();
		System.out.println(dao.getCircleCount());
		
	}

}
