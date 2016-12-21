package getBlob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.sql.BLOB;

	public class TestOracleDB {
	    public static void main(String[] args) {
	        // testByte2String();

	        // insertOracleEmptyBlob();
	        // updateOracleBlob();
	        // updateMysqlBlob();
	        // readOracleBlob();
	        //readMysqlBlob();
	    	insertMysql();
	    }

	   /* public static void updateOracleBlob() {
	        JDBCUtil jdbc = new JDBCUtil("esbTest01", "Oracle", "IESB", "IESB", "10.18.224.73", 1521);
	        Connection conn = jdbc.getConnection();
	        Statement statement = null;
	        ResultSet resultSet = null;
	        try {
	            statement = conn.createStatement();
	            resultSet = statement.executeQuery("SELECT * FROM Oracle_Test1 where int_id=47 FOR UPDATE NOWAIT");
	            int columns = resultSet.getMetaData().getColumnCount();
	            while (resultSet.next()) {
	                BLOB blob = (BLOB) resultSet.getBlob("blobtest");
	                // BLOB blob = BLOB.getEmptyBLOB();

	                OutputStream outStream = blob.getBinaryOutputStream();

	                File file = new File("C:/Documents and Settings/Administrator/桌面/2.jpg");
	                FileInputStream fin = new FileInputStream(file);

	                byte[] b = new byte[fin.available()];
	                int len = 0;
	                while ((len = fin.read(b)) != -1) {
	                    outStream.write(b, 0, len);
	                }
	                fin.close();
	                outStream.flush();
	                outStream.close();
	                conn.setAutoCommit(true);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            closeAll(resultSet, statement, conn);
	        }
	    }

	    public static void updateMysqlBlob() {
	        JDBCUtil jdbc = new JDBCUtil("test", "MySQL", "root", "root", "10.18.221.152", 3306);
	        Connection conn = jdbc.getConnection();
	        PreparedStatement statement = null;
	        ResultSet resultSet = null;
	        try {
	            statement = conn.prepareStatement("update studentinfo  set oracle_blob = ? where oracle_varchar2='111'");
	            File file = new File("C:/Documents and Settings/Administrator/桌面/2.jpg");
	            FileInputStream fin = new FileInputStream(file);
	            byte[] b = new byte[fin.available()];
	            // fin.read(b);

	            Blob blob = (Blob) conn.createBlob();
	            OutputStream out = blob.setBinaryStream(1);
	            int length;
	            while ((length = fin.read(b)) != -1) {
	                out.write(b, 0, length);
	            }

	            // statement.setBlob(1, fin);
	            statement.setBlob(2, blob);

	            statement.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            closeAll(resultSet, statement, conn);
	        }
	    }

	    public static void insertOracleEmptyBlob() {
	        JDBCUtil jdbc = new JDBCUtil("esbTest01", "Oracle", "IESB", "IESB", "10.18.224.73", 1521);
	        Connection conn = jdbc.getConnection();
	        Statement statement = null;
	        ResultSet resultSet = null;
	        try {
	            conn.setAutoCommit(false);// 取消自动提交功能
	            statement = conn.createStatement();
	            statement.executeUpdate("UPDATE Oracle_Test1 SET BLOBTEST=EMPTY_BLOB() WHERE int_id=47");
	            conn.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            closeAll(resultSet, statement, conn);
	        }
	    }

	    public static void readOracleBlob() {
	        JDBCUtil jdbc = new JDBCUtil("esbTest01", "Oracle", "IESB", "IESB", "10.18.224.73", 1521);
	        Connection conn = jdbc.getConnection();
	        Statement statement = null;
	        ResultSet resultSet = null;
	        try {
	            statement = conn.createStatement();
	            resultSet = statement.executeQuery("SELECT * FROM Oracle_Test1 where int_id=47");
	            while (resultSet.next()) {
	                if (resultSet.getObject("blobtest") instanceof oracle.sql.BLOB) {
	                    BLOB blob = (BLOB) resultSet.getBlob("blobtest");
	                    InputStream inputStream = blob.getBinaryStream();
	                    File file = new File("C:/Documents and Settings/Administrator/桌面/11.jpg");
	                    FileOutputStream fout = new FileOutputStream(file);

	                    byte[] b = new byte[blob.getBufferSize()];
	                    int len = 0;
	                    while ((len = inputStream.read(b)) != -1) {
	                        fout.write(b, 0, len);
	                    }
	                    fout.close();
	                    fout.flush();
	                    inputStream.close();
	                }

	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            closeAll(resultSet, statement, conn);
	        }
	    }

	    public static void readMysqlBlob() {
	        JDBCUtil jdbc = new JDBCUtil("test", "Mysql", "root", "root", "10.18.221.152", 3306);
	        Connection conn = jdbc.getConnection();
	        Statement statement = null;
	        ResultSet resultSet = null;
	        try {
	            statement = conn.createStatement();
	            resultSet = statement.executeQuery("SELECT * FROM studentinfo where oracle_varchar2='111'");
	            while (resultSet.next()) {
	                if (resultSet.getObject("oracle_blob") instanceof byte[]) {
	                    byte[] b = resultSet.getBytes("oracle_blob");
	                    File file = new File("C:/Documents and Settings/Administrator/桌面/11.jpg");
	                    FileOutputStream fout = new FileOutputStream(file);
	                    fout.write(b);
	                    fout.close();
	                    fout.flush();
	                }

	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            closeAll(resultSet, statement, conn);
	        }
	    }

	    public static void testByte2String() {
	        byte bytes[] = new byte[] { 50, 0, -1, 28, -24 };
	        String string = new String(bytes);
	        byte[] ret = string.getBytes();

	        String isoString;
	        byte[] isoret = null;
	        try {
	            isoString = new String(bytes, "ISO-8859-1");
	            isoret = isoString.getBytes("ISO-8859-1");
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }

	        System.out.println(System.getProperty("file.encoding"));
	        System.out.println(ret);
	        System.out.println(isoret);
	    }
*/
	   
	    
	    //get blob from oracle
	    public static Blob getBlob(){
	    	JDBCUtil jdbc = new JDBCUtil("esbTest01", "Oracle", "IESB", "IESB", "10.18.224.73", 1521);
	        Connection conn = jdbc.getConnection();
	        Statement statement = null;
	        ResultSet resultSet = null;
	        BLOB blob = null;
	        try {
	            statement = conn.createStatement();
	            resultSet = statement.executeQuery("SELECT * FROM Oracle_Test1 where int_id=47");
	            while (resultSet.next()) {
	                if (resultSet.getObject("blobtest") instanceof oracle.sql.BLOB) {
	                    blob = (BLOB) resultSet.getBlob("blobtest");
	                    InputStream inputStream = blob.getBinaryStream();
	                    File file = new File("C:/Documents and Settings/Administrator/桌面/11.jpg");
	                    FileOutputStream fout = new FileOutputStream(file);

	                    byte[] b = new byte[blob.getBufferSize()];
	                    int len = 0;
	                    while ((len = inputStream.read(b)) != -1) {
	                        fout.write(b, 0, len);
	                    }
	                    fout.close();
	                    fout.flush();
	                    inputStream.close();
	                }

	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            closeAll(resultSet, statement, conn);
	        }
			return blob;
	    }
	    
	    //insert into mysql
	    public static void insertMysql(){
	    	Blob blob;
	    	 JDBCUtil jdbc = new JDBCUtil("esbTest01", "Oracle", "IESB", "IESB", "10.18.224.73", 1521);
		        Connection conn = jdbc.getConnection();
		        Statement statement = null;
		        ResultSet resultSet = null;
		        blob = getBlob();
		        try {
		            conn.setAutoCommit(false);// 取消自动提交功能
		            statement = conn.createStatement();
		            statement.executeUpdate("UPDATE Oracle_Test1 SET BLOBTEST="+blob+" WHERE int_id=47");
		            conn.commit();
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            closeAll(resultSet, statement, conn);
		        }
	    }
	    
	    // 关闭相应数据库连接
	    public static void closeAll(ResultSet rs, Statement stmt, Connection conn) {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
