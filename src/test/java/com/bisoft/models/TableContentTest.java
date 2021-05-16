package com.bisoft.models;


import com.bisoft.interfaces.ISavedFormat;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.*;

public class TableContentTest {
    
    @Mock
    Connection connection;

    MockitoSession session;
    
    @BeforeMethod
    public void before() {
        //Statement s = mock(Statement.class);
        //ResultSet rs = mock(ResultSet.class);
        //ISavedFormat sf = mock(ResultSet.class);
        session = Mockito.mockitoSession()
          .initMocks(this)
          .startMocking();
    }
    
    @Test
    public void shouldGetTableContent() throws SQLException {

//        Statement stmt = connection.createStatement();
        //TableContent tlb = new TableContent(connection, "table1");
        //tlb.save();
    }
    
    @AfterMethod
    public void after() {
        session.finishMocking();
    }
}
