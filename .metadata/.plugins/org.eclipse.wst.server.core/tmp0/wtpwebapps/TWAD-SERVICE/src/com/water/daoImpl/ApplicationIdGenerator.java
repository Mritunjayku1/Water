package com.water.daoImpl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;


public class ApplicationIdGenerator implements IdentifierGenerator{

    @Override
    public Serializable generate(SessionImplementor session, Object object)
            throws HibernateException {

        String prefix = "APP";
        Connection connection = session.connection();
Random random=new Random();
        try {
            Statement statement=connection.createStatement();

            ResultSet rs=statement.executeQuery("select count(APP_ID) as Id from APP");

            if(rs.next())
            {
            	int x=random.nextInt(900);
                int id=rs.getInt(1)+101;
                String generatedId = prefix + new Integer(x).toString();
              //  System.out.println("Generated Id: " + generatedId);
                return generatedId;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return null;
    }

}