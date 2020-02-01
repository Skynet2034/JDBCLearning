package ru.hacker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataManipulator {
    private final Connection connection;

    public DataManipulator(String url, String name, String pass) throws SQLException {
        this.connection = DriverManager.getConnection(url, name, pass);
    }

    public void insert(TestJDBC testJDBC) throws SQLException {
        try (PreparedStatement st=connection.prepareStatement("INSERT INTO test_JDBC VALUES (?, ?)"))
        {
            st.setInt (1, testJDBC.getId());
            st.setString (2, testJDBC.getName());
            st.execute();
        }
    }

    public TestJDBC get(int index) throws SQLException {
        try (PreparedStatement st=connection.prepareStatement("SELECT * FROM test_JDBC WHERE id=?"))
        {
            st.setInt(1, index);
            ResultSet rs=st.executeQuery();
            int id=rs.getInt(1);
            String name=rs.getString(2);
            return new TestJDBC(id, name);
        }
    }

    public List<TestJDBC> getAll() throws SQLException {
        try (Statement st= connection.createStatement())
        {
            ResultSet rs=st.executeQuery("SELECT * FROM test_JDBC");
            List<TestJDBC> res=new ArrayList<>();
            while (rs.next())
            {
                int id=rs.getInt(1);
                String name=rs.getString(2);
               res.add(new TestJDBC(id, name));
            }
            return res;
        }
    }
    public void update(TestJDBC testJDBC)
    {

    }
}
