package ru.hacker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataManipulator {
    private final Connection connection;

    public DataManipulator(String url, String name, String pass) throws SQLException {
        this.connection = DriverManager.getConnection(url, name, pass);
    }

    public void insert(EntryDTO entryDTO) throws SQLException {
        try (PreparedStatement st=connection.prepareStatement("INSERT INTO test_JDBC VALUES (?, ?)"))
        {
            st.setInt (1, entryDTO.getId());
            st.setString (2, entryDTO.getName());
            st.execute();
        }
    }

    public EntryDTO get(int index) throws SQLException {
        try (PreparedStatement st=connection.prepareStatement("SELECT * FROM test_JDBC WHERE id=(?)"))
        {
            st.setInt(1, index);
            ResultSet rs=st.executeQuery();
            rs.next();
            int id=rs.getInt(1);
            String name=rs.getString(2);
            return new EntryDTO(id, name);
        }
    }

    public List<EntryDTO> getAll() throws SQLException {
        try (Statement st= connection.createStatement())
        {
            ResultSet rs=st.executeQuery("SELECT * FROM test_JDBC");
            List<EntryDTO> res=new ArrayList<>();
            while (rs.next())
            {
                int id=rs.getInt(1);
                String name=rs.getString(2);
               res.add(new EntryDTO(id, name));
            }
            return res;
        }
    }
    public void update(EntryDTO entryDTO) throws SQLException
    {
        try (PreparedStatement st=connection.prepareStatement("UPDATE test_JDBC SET NAME=(?) WHERE id=(?)"))
        {
            st.setInt(2, entryDTO.getId());
            st.setString(1,entryDTO.getName());
            st.executeUpdate();
        }
    }
}
