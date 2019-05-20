package com.atomikos.jta.test;

import java.net.Inet4Address;
import java.sql.*;
import java.util.Random;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import com.microsoft.sqlserver.jdbc.SQLServerXADataSource;

public class test2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		 PreparedStatement ps = null; // (这里也可以使用statement,视情况而定)
		 Connection ct = null;
		 ResultSet rs = null;
		//
		// // 1.加载驱动
		// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 String url = "jdbc:sqlserver://192.168.229.133:1433;databaseName=message";
		// String user = "root";// sa超级管理员
		// String password = "123456";// 密码
		// // 2.连接
		// ct = DriverManager.getConnection(url, user, password);
		// // 3.创建发送端
		//
		// ps = ct.prepareStatement("select * from messageinfo");
		// rs = ps.executeQuery();
		// while (rs.next()) {
		// String cno = rs.getString(1);
		// String cname = rs.getString(2);
		// String tno = rs.getString(3);
		// System.out.println("id " + cno + " cname " + cname + " age " + tno);
		// }

		// ps = ct.prepareStatement("INSERT INTO test(id, name, age) VALUES (?, ?, ?)");
		// ps.setInt(1, 1);
		// ps.setString(2, "kefaming");
		// ps.setString(3, "30");
		// ps.executeUpdate();

		System.out.println("连接成功！");

		// Create variables for the connection string.
		String prefix = "jdbc:sqlserver://";
		String serverName = "192.168.229.133";
		int portNumber = 1433;
		String databaseName = "messsage";
		String user = "root";
		String password = "123456";
		String connectionUrl = prefix + serverName + ":" + portNumber + ";databaseName=" + databaseName;
		System.out.println(connectionUrl);

		try {
			// Establish the connection.
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			Connection con = DriverManager.getConnection(connectionUrl,user,password);
			Connection con = DriverManager.getConnection(url, user, password);

			// Create a test table.
			Statement stmt = con.createStatement();
			try {
				stmt.executeUpdate("DROP TABLE XAMin");
			} catch (Exception e) {
			}
			stmt.executeUpdate("CREATE TABLE XAMin (f1 int, f2 varchar(max))");
			stmt.close();
			con.close();

			// Create the XA data source and XA ready connection.
			SQLServerXADataSource ds = new SQLServerXADataSource();
			ds.setUser("root");
			ds.setPassword("123456");
			ds.setServerName("192.168.229.133");
			ds.setPortNumber(1433);
			ds.setDatabaseName("message");
			XAConnection xaCon = ds.getXAConnection();
			con = xaCon.getConnection();

			// Get a unique Xid object for testing.
			XAResource xaRes = null;
			Xid xid = null;
			xid = XidImpl.getUniqueXid(1);

			// Get the XAResource object and set the timeout value.
			xaRes = xaCon.getXAResource();
			xaRes.setTransactionTimeout(0);

			// Perform the XA transaction.
			System.out.println("Write -> xid = " + xid.toString());
			
			xaRes.start(xid, XAResource.TMNOFLAGS);
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO XAMin (f1,f2) VALUES (?, ?)");
			pstmt.setInt(1, 1);
			pstmt.setString(2, xid.toString());
			pstmt.executeUpdate();

			// Commit the transaction.
			xaRes.end(xid, XAResource.TMSUCCESS);
			xaRes.commit(xid, true);

			// Cleanup.
			con.close();
			xaCon.close();

			
			ct = DriverManager.getConnection(url, user, password);
			ps = ct.prepareStatement("select * from messageinfo");
			rs = ps.executeQuery();
			while (rs.next()) {
				String cno = rs.getString(1);
				String cname = rs.getString(2);
				String tno = rs.getString(3);
				System.out.println("id " + cno + " cname " + cname + " age " + tno);
			}
			
			
			
			
			// Open a new connection and read back the record to verify that it worked.
//			con = DriverManager.getConnection(connectionUrl,user,password);
//			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM XAMin");
//			rs.next();
//			System.out.println("Read -> xid = " + rs.getString(2));
			rs.close();
			con.close();
		}

		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class XidImpl implements Xid {

	public int formatId;
	public byte[] gtrid;
	public byte[] bqual;

	public byte[] getGlobalTransactionId() {
		return gtrid;
	}

	public byte[] getBranchQualifier() {
		return bqual;
	}

	public int getFormatId() {
		return formatId;
	}

	XidImpl(int formatId, byte[] gtrid, byte[] bqual) {
		this.formatId = formatId;
		this.gtrid = gtrid;
		this.bqual = bqual;
	}

	public String toString() {
		int hexVal;
		StringBuffer sb = new StringBuffer(512);
		sb.append("formatId=" + formatId);
		sb.append(" gtrid(" + gtrid.length + ")={0x");
		for (int i = 0; i < gtrid.length; i++) {
			hexVal = gtrid[i] & 0xFF;
			if (hexVal < 0x10)
				sb.append("0" + Integer.toHexString(gtrid[i] & 0xFF));
			else
				sb.append(Integer.toHexString(gtrid[i] & 0xFF));
		}
		sb.append("} bqual(" + bqual.length + ")={0x");
		for (int i = 0; i < bqual.length; i++) {
			hexVal = bqual[i] & 0xFF;
			if (hexVal < 0x10)
				sb.append("0" + Integer.toHexString(bqual[i] & 0xFF));
			else
				sb.append(Integer.toHexString(bqual[i] & 0xFF));
		}
		sb.append("}");
		return sb.toString();
	}

	// Returns a globally unique transaction id.
	static byte[] localIP = null;
	static int txnUniqueID = 0;

	static Xid getUniqueXid(int tid) {

		Random rnd = new Random(System.currentTimeMillis());
		txnUniqueID++;
		int txnUID = txnUniqueID;
		int tidID = tid;
		int randID = rnd.nextInt();
		byte[] gtrid = new byte[64];
		byte[] bqual = new byte[64];
		if (null == localIP) {
			try {
				localIP = Inet4Address.getLocalHost().getAddress();
			} catch (Exception ex) {
				localIP = new byte[] { 0x01, 0x02, 0x03, 0x04 };
			}
		}
		System.arraycopy(localIP, 0, gtrid, 0, 4);
		System.arraycopy(localIP, 0, bqual, 0, 4);

		// Bytes 4 -> 7 - unique transaction id.
		// Bytes 8 ->11 - thread id.
		// Bytes 12->15 - random number generated by using seed from current time in
		// milliseconds.
		for (int i = 0; i <= 3; i++) {
			gtrid[i + 4] = (byte) (txnUID % 0x100);
			bqual[i + 4] = (byte) (txnUID % 0x100);
			txnUID >>= 8;
			gtrid[i + 8] = (byte) (tidID % 0x100);
			bqual[i + 8] = (byte) (tidID % 0x100);
			tidID >>= 8;
			gtrid[i + 12] = (byte) (randID % 0x100);
			bqual[i + 12] = (byte) (randID % 0x100);
			randID >>= 8;
		}
		return new XidImpl(0x1234, gtrid, bqual);
	}
}
