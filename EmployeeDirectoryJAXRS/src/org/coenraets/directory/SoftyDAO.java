/*
 
    http://www.vogella.com/articles/REST/article.html
	http://www.ibm.com/developerworks/library/wa-aj-tomcat/
	http://coenraets.org/blog/2011/12/restful-services-with-jquery-and-java-using-jax-rs-and-jersey/
	http://zetcode.com/db/postgresqljavatutorial/
	
*/

package org.coenraets.directory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SoftyDAO {

    public List<UserPass> findAll() {
        List<UserPass> list = new ArrayList<UserPass>();
        Connection c = null;
    	String sql = "SELECT up.user_id as uid , up.secret as password FROM userpass as up ";

        try {
            c = ConnectionHelperPg.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processSummaryRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger lgr = Logger.getLogger(SoftyDAO.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
		} finally {
			ConnectionHelperPg.close(c);
		}
        return list;
    }

    public List<UserPass> findAllAgents() {
        List<UserPass> list = new ArrayList<UserPass>();
        Connection c = null;
/*
			select PersoanaAgent.denumire as Agent , PersoanaAgent.persoana_id , utilizator.parola , zona.denumire
			from persoana as PersoanaAgent , utilizator , zona 
				where 
				   utilizator.utilizator_id=PersoanaAgent.persoana_id
			       and utilizator.zona_id=zona.zona_id
*/
    	String sql = " select PersoanaAgent.denumire as agent , PersoanaAgent.persoana_id , utilizator.parola as password, zona.denumire as zone " +
    			" from persoana as PersoanaAgent , utilizator , zona " +
    			" where utilizator.utilizator_id=PersoanaAgent.persoana_id " +
    			" and utilizator.zona_id=zona.zona_id ";

        try {
            c = ConnectionHelperPg.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processUserPassRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger lgr = Logger.getLogger(SoftyDAO.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
		} finally {
			ConnectionHelperPg.close(c);
		}
        return list;
    }

    public UserPass findAgentById(int agent_id) {
    	String sql = " select PersoanaAgent.denumire as agent , PersoanaAgent.persoana_id , utilizator.parola as password, zona.denumire as zone " +
    			" from persoana as PersoanaAgent , utilizator , zona " +
    			" where utilizator.utilizator_id=PersoanaAgent.persoana_id " +
    			" and utilizator.zona_id=zona.zona_id and PersoanaAgent.persoana_id = ? ";
    	UserPass up = new UserPass();
        Connection c = null;
        try {
            c = ConnectionHelperPg.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, agent_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                up = processUserPassRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger lgr = Logger.getLogger(SoftyDAO.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
		} finally {
			ConnectionHelperPg.close(c);
		}
        return up;
    }

    public UserPass confirmAgentByPassId(int agent_id, String agent_password) {
    	String sql = " select PersoanaAgent.denumire as agent , PersoanaAgent.persoana_id , utilizator.parola as password, zona.denumire as zone " +
    			" from persoana as PersoanaAgent , utilizator , zona " +
    			" where utilizator.utilizator_id=PersoanaAgent.persoana_id " +
    			" and utilizator.zona_id=zona.zona_id and PersoanaAgent.persoana_id = ? and utilizator.parola = ? ";
    	UserPass up = new UserPass();
        Connection c = null;
        try {
            c = ConnectionHelperPg.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, agent_id);
            ps.setString(2, agent_password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                up = processUserPassRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger lgr = Logger.getLogger(SoftyDAO.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
		} finally {
			ConnectionHelperPg.close(c);
		}
        return up;
    }

    
    protected UserPass processUserPassRow(ResultSet rs) throws SQLException {
    	UserPass up = new UserPass();
    	up.setId(rs.getInt("persoana_id"));
    	up.setPassword(rs.getString("password"));
    	up.setUserName(rs.getString("agent"));
    	up.setZone(rs.getString("zone"));
    	return up;
    }

    
    public List<OrderData> findAllOrders() {
        List<OrderData> list = new ArrayList<OrderData>();
        Connection c = null;
    	String sql = "SELECT so.client as client , so.product as product , so.pieces as pieces , so.lineorder as lineorder FROM softyorders2 as so ";

        try {
            c = ConnectionHelperPg.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processOrderRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger lgr = Logger.getLogger(SoftyDAO.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
		} finally {
			ConnectionHelperPg.close(c);
		}
        return list;
    }

    
   
    // http://zetcode.com/db/postgresqljavatutorial/
    // import java.util.logging.Level;
    // import java.util.logging.Logger;
    // Transaction support
    
    
    // public List<ClientRoute> GetRoutesByAgent(String agentid){
    public List<ClientRoute> GetRoutesByAgent(){
    	List<ClientRoute> list = new ArrayList<ClientRoute>();
        
    	/* SELECT PersoanaClient.denumire AS Client , zona.denumire AS Zona, rute.denumire as Ruta, PersoanaAgent.denumire as Agent, 
                	  PersoanaAgent.persoana_id as agent_id
                                FROM   clienti_rute , rute , client , persoana as PersoanaClient , zona , utilizator, persoana as PersoanaAgent
                                where clienti_rute.client_id=client.client_id
                                and client.client_id= PersoanaClient.persoana_id
                                and client.zona_id=Zona.zona_id
                                and clienti_rute.ruta_id=rute.rute_id
                                and rute.agent_id=utilizator.utilizator_id
                                and utilizator.utilizator_id=PersoanaAgent.persoana_id
                                and PersoanaAgent.persoana_id=1
        */
        Connection c = null;
    	String sql = " SELECT PersoanaClient.denumire AS Client , zona.denumire AS Zona, rute.denumire as Ruta, PersoanaAgent.denumire as Agent, " + 
    			                   " PersoanaAgent.persoana_id as agent_id " +
    			     " FROM clienti_rute , rute , client , persoana as PersoanaClient , zona , utilizator, persoana as PersoanaAgent" +
    			     " where clienti_rute.client_id=client.client_id " +
    			     " and client.client_id= PersoanaClient.persoana_id " +
    			     " and client.zona_id=Zona.zona_id " +
    			     " and clienti_rute.ruta_id=rute.rute_id " +
    			     " and rute.agent_id=utilizator.utilizator_id " + 
    			     " and utilizator.utilizator_id=PersoanaAgent.persoana_id " + 
    			     " and PersoanaAgent.persoana_id=1 "   ;

        try {
            c = ConnectionHelperPg.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRouteRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger lgr = Logger.getLogger(SoftyDAO.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);

            throw new RuntimeException(e);
		} finally {
			ConnectionHelperPg.close(c);
		}

    	return list;
    }

    
    public List<ClientRoute> GetRoutesByAgent(int agent_id) {
    	List<ClientRoute> list = new ArrayList<ClientRoute>();
    	Connection c = null;
    	String sql = " SELECT PersoanaClient.denumire AS Client , zona.denumire AS Zona, rute.denumire as Ruta, PersoanaAgent.denumire as Agent, " + 
                " PersoanaAgent.persoana_id as agent_id " +
                " FROM clienti_rute , rute , client , persoana as PersoanaClient , zona , utilizator, persoana as PersoanaAgent" +
                " where clienti_rute.client_id=client.client_id " +
                " and client.client_id= PersoanaClient.persoana_id " +
                " and client.zona_id=Zona.zona_id " +
                " and clienti_rute.ruta_id=rute.rute_id " +
                " and rute.agent_id=utilizator.utilizator_id " + 
                " and utilizator.utilizator_id=PersoanaAgent.persoana_id " + 
                " and PersoanaAgent.persoana_id=? "   ;
    	try {
    		c = ConnectionHelperPg.getConnection();
    		PreparedStatement ps = c.prepareStatement(sql);
    		ps.setInt(1, agent_id);
    		ResultSet rs = ps.executeQuery();
    		while (rs.next()) {
    			list.add(processRouteRow(rs));
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
            Logger lgr = Logger.getLogger(SoftyDAO.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);

    		throw new RuntimeException(e);
    	} finally {
    		ConnectionHelperPg.close(c);
    	}
    	return list;
    }

    
    
    protected ClientRoute processRouteRow(ResultSet rs) throws SQLException {
    	
    	ClientRoute cr = new ClientRoute();
    	cr.setAgent      (rs.getString     ("Agent"     ));
    	cr.setAgentID    (rs.getInt        ("agent_id"  ));
    	cr.setClient     (rs.getString     ("Client"    ));
    	cr.setRoute      (rs.getString     ("Ruta"      ));
    	cr.setZone       (rs.getString     ("Zona"      ));
    	
    	return cr;
    }

    
    public List<Product> findAllProducts() {
    	List<Product> list = new ArrayList<Product>();
    	
        Connection c = null;
    	String sql = "SELECT pr.product_id , pr.product_name  , pr.class_id , pr.price , pr.symbol FROM product as pr ";

        try {
            c = ConnectionHelperPg.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {

            	list.add(processProductRow(rs));
            	
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger lgr = Logger.getLogger(SoftyDAO.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);

            throw new RuntimeException(e);
		} finally {
			ConnectionHelperPg.close(c);
		}
    	
    	
    	return list;
    }
    
    protected Product processProductRow(ResultSet rs) throws SQLException {
    	
    	Product pr = new Product();
    	pr.setProductName(rs.getString     ("product_name"));
    	pr.setSymbolName (rs.getString     ("symbol"      ));
    	pr.setPrice      (rs.getBigDecimal ("price"       ));
    	pr.setProductID  (rs.getInt        ("product_id"  ));
    	pr.setClassID    (rs.getInt        ("class_id"    ));
    	
    	return pr;
    }
    
    protected OrderData processOrderRow(ResultSet rs) throws SQLException {
    	OrderData oD = new OrderData();
    	// oD.setProduct(rs.getInt("uid"));
    	oD.setLine(rs.getInt("lineorder"));
    	// oD.setLine(rs.getInt(4));
    	oD.setProduct(rs.getString("product"));
    	oD.setClient(rs.getString("client"));
    	oD.setPieces(rs.getString("pieces"));
    	return oD;
    }
    
    

    public OrderData insertOrder(OrderData theOrder) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelperPg.getConnection();
            ps = c.prepareStatement("INSERT INTO softyorders2 (lineorder, client, product, pieces, discount ) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, theOrder.getLine());
            ps.setString(2, theOrder.getClient());
            ps.setString(3, theOrder.getProduct());
            ps.setString(4, theOrder.getPieces());
            ps.setString(5, theOrder.getDiscount());
            ps.executeUpdate();
            // ConnectionHelper prepareStatement getGeneratedKeys :=>  
            // http://docs.oracle.com/javase/1.4.2/docs/guide/jdbc/getstart/preparedstatement.html
            // ResultSet rs = ps.getGeneratedKeys();
            // rs.next();
            // Update the id in the returned object. This is important as this value must be returned to the client.
            // int id = rs.getInt(1);
            // employee.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
            Logger lgr = Logger.getLogger(SoftyDAO.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);

            throw new RuntimeException(e);
		} finally {
			ConnectionHelperPg.close(c);
		}
        return theOrder;
    }

    
    
    public List<Employee> findByName(String name) {
        List<Employee> list = new ArrayList<Employee>();
        Connection c = null;
    	String sql = "SELECT e.id, e.firstName, e.lastName, e.title, e.picture, count(r.id) reportCount FROM employee as e " +
			"LEFT JOIN employee r ON e.id = r.managerId " +
			"WHERE UPPER(CONCAT(e.firstName, ' ', e.lastName)) LIKE ? " +	
			"GROUP BY e.id " +
			"ORDER BY e.firstName, e.lastName";
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, "%" + name.toUpperCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // list.add(processSummaryRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger lgr = Logger.getLogger(SoftyDAO.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);

            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }
    
    public List<Employee> findByManager(int managerId) {
    	List<Employee> list = new ArrayList<Employee>();
    	Connection c = null;
    	String sql = "SELECT e.id, e.firstName, e.lastName, e.title, e.picture, count(r.id) reportCount FROM employee as e " +
			"LEFT JOIN employee r ON e.id = r.managerId " +
			"WHERE e.managerId=? " +	
			"GROUP BY e.id " +
			"ORDER BY e.firstName, e.lastName";
    	try {
    		c = ConnectionHelper.getConnection();
    		PreparedStatement ps = c.prepareStatement(sql);
    		ps.setInt(1, managerId);
    		ResultSet rs = ps.executeQuery();
    		while (rs.next()) {
    			// list.add(processSummaryRow(rs));
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new RuntimeException(e);
    	} finally {
    		ConnectionHelper.close(c);
    	}
    	return list;
    }
    
    public Employee findById(int id) {
    	String sql = "SELECT e.id, e.firstName, e.lastName, e.managerId, e.title, e.department, e.city, e.officePhone, e.cellPhone, " + 
			"e.email, e.picture, m.firstName managerFirstName, m.lastName managerLastName, count(r.id) reportCount FROM employee as e " +
			"LEFT JOIN employee m ON e.managerId = m.id " + 
			"LEFT JOIN employee r ON e.id = r.managerId " +
			"WHERE e.id = ? " +
			"GROUP BY e.id";
        Employee employee = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger lgr = Logger.getLogger(SoftyDAO.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);

            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return employee;
    }

    public Employee save(Employee employee)
	{
		return employee.getId() > 0 ? update(employee) : create(employee);
	}    
    
    public Employee create(Employee employee) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement("INSERT INTO employee (firstName, lastName, title, department, managerId, city, officePhone, cellPhone, email, picture) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                new String[] { "ID" });
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getTitle());
            ps.setString(4, employee.getDepartment());
            ps.setInt(5, employee.getManager() == null ? 0 : employee.getManager().getId());
            ps.setString(6, employee.getCity());
            ps.setString(7, employee.getOfficePhone());
            ps.setString(8, employee.getCellPhone());
            ps.setString(9, employee.getEmail());
            ps.setString(10, employee.getPicture());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            // Update the id in the returned object. This is important as this value must be returned to the client.
            int id = rs.getInt(1);
            employee.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
            Logger lgr = Logger.getLogger(SoftyDAO.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);

            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return employee;
    }

    public Employee update(Employee employee) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE employee SET firstName=?, lastName=?, title=?, deptartment=?, managerId=?, city=?, officePhone, cellPhone=?, email=?, picture=? WHERE id=?");
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getTitle());
            ps.setString(4, employee.getDepartment());
            ps.setInt(5, employee.getManager().getId());
            ps.setString(6, employee.getCity());
            ps.setString(7, employee.getOfficePhone());
            ps.setString(8, employee.getCellPhone());
            ps.setString(9, employee.getEmail());
            ps.setString(10, employee.getPicture());
            ps.setInt(11, employee.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            Logger lgr = Logger.getLogger(SoftyDAO.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);

            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return employee;
    }

    public boolean remove(Employee employee) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM employee WHERE id=?");
            ps.setInt(1, employee.getId());
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            Logger lgr = Logger.getLogger(SoftyDAO.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);

            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
    }

    protected Employee processRow(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setFirstName(rs.getString("firstName"));
        employee.setLastName(rs.getString("lastName"));
        employee.setTitle(rs.getString("title"));
        employee.setDepartment(rs.getString("department"));
        employee.setCity(rs.getString("city"));
        employee.setOfficePhone(rs.getString("officePhone"));
        employee.setCellPhone(rs.getString("cellPhone"));
        employee.setEmail(rs.getString("email"));
        employee.setPicture(rs.getString("picture"));
        int managerId = rs.getInt("managerId");
        if (managerId>0)
        {
        	Employee manager = new Employee();
        	manager.setId(managerId);
            manager.setFirstName(rs.getString("managerFirstName"));
            manager.setLastName(rs.getString("managerLastName"));
        	employee.setManager(manager);
        }
        employee.setReportCount(rs.getInt("reportCount"));
        return employee;
    }
    
    protected UserPass processSummaryRow(ResultSet rs) throws SQLException {
    	UserPass uP = new UserPass();
    	uP.setId(rs.getInt("uid"));
    	uP.setPassword(rs.getString("password"));
    	return uP;
    }
    
}
