package cadastrobd.model.util;

import java.sql.*;

public class SequenceManager {
    public static int getValue(String sequenceName) {
        int value = -1;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConectorBD.getConnection();
            stmt = conn.prepareStatement("SELECT NEXT VALUE FOR " + sequenceName);
            rs = stmt.executeQuery();
            if (rs.next()) {
                value = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(stmt);
            ConectorBD.close(conn);
        }
        return value;
    }
}
