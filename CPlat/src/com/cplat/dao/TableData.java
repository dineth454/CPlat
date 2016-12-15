package com.cplat.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TableData {
	Configuration configuration = new Configuration().configure();

    // 2. create sessionfactory
    SessionFactory sessionFactory = configuration.buildSessionFactory();

    // 3. Get Session object
    Session session = sessionFactory.openSession();

    // 4. Starting Transaction
    Transaction transaction = session.beginTransaction();
    
    public List<?> getData(String selectQuery){
		List<?> list = null;

    	try {
    		String sql = selectQuery;
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
          
            List<?> result = (List<?>) query.list();
         
            transaction.commit();
            System.out.println("\n\n Retrieve count \n");
            return result;

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
    	return list;
    }
	
}
