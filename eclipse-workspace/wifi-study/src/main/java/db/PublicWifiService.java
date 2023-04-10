package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PublicWifiService {
	public List<PublicWifi> list() {
    	List<PublicWifi> wifiList = new ArrayList<>();

        // jdbc:DB_VENDER://IP_ADDR:IP_PORT/INSTANCE
        String url = "http://openapi.seoul.go.kr:8088/4d4f6c4462746d6435306d44446e4d/xml/TbPublicWifiInfo/1/100/";
        String dbUserId = "wifi_user";
        String dbPassword = "zerobase";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // connection 객체 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            
            // statement 객체 생성
            String sql = " select km, mgrNo, wrdofc, mainNM, adres1, adres2, instlFloor, instlTV, instlMbv, svcSe, cmcwr, cnstcYear, inoutDoor, remars3, LAT, LNT, workDttm " +
                    " from wifi_db " +
                    " where LAT = ? and LNT = ? " ;

            preparedStatement = connection.prepareStatement(sql);

            // 쿼리 실행
            rs = preparedStatement.executeQuery();

            // 결과 수행
            while (rs.next()) {
                Double km = rs.getDouble("km");
                String mgrNo = rs.getString("mgrNo");
                String wrdofc = rs.getString("wrdofc");
                String mainNM = rs.getString("mainNM");
                String adres1 = rs.getString("adres1");
                String adres2 = rs.getString("adres2");
                String instlFloor = rs.getString("instlFloor");
                String instlTV = rs.getString("instlTV");
                String instlMbv = rs.getString("instlMbv");
                String svcSe = rs.getString("svcSe");
                String cmcwr = rs.getString("cmcwr");
                String cnstcYear = rs.getString("cnstcYear");
                String inoutDoor = rs.getString("inoutDoor");
                String remars3 = rs.getString("remars3");
                Double LAT = rs.getDouble("LAT");
                Double LNT = rs.getDouble("LNT");
                Date workDttm = rs.getDate("workDttm");
                
                PublicWifi wifi = new PublicWifi();
                wifi.setKm(km);
                wifi.setMgrNo(mgrNo);
                wifi.setWrdofc(wrdofc);
                wifi.setMainNM(mainNM);
                wifi.setMainNM(adres1);
                wifi.setMainNM(adres2);
                wifi.setMainNM(instlFloor);
                wifi.setMainNM(instlTV);
                wifi.setMainNM(instlMbv);
                wifi.setMainNM(svcSe);
                wifi.setMainNM(cmcwr);
                wifi.setMainNM(cnstcYear);
                wifi.setMainNM(inoutDoor);
                wifi.setMainNM(remars3);
                wifi.setLAT(LAT);
                wifi.setLNT(LNT);
                wifi.setWorkDttm(workDttm);
                
                wifiList.add(wifi);

                System.out.println(km + ", " + mgrNo + ", " + wrdofc + ", " + mainNM + ", " + adres1 + ", " + adres2 + ", " 
                					+ instlFloor + ", " + instlTV + ", " + instlMbv + ", " + svcSe + ", " + cmcwr + ", " + cnstcYear + ", " + inoutDoor + ", " + remars3 + ", " + LAT + ", " + LNT + ", " + workDttm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 객체 연결 해제
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return wifiList;
    }
}
