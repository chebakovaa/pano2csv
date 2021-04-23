package com.bisoft.models;


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
        session = Mockito.mockitoSession()
          .initMocks(this)
          .startMocking();
    }
    
    @Test
    public void shouldGetTableContent() throws SQLException {

        Statement stmt = connection.createStatement();
        
        assertThat(new TableContent(stmt, "table1").load()).isTrue();

    }
    
    @AfterMethod
    public void after() {
        session.finishMocking();
    }
}
