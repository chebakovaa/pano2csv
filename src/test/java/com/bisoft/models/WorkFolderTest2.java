package com.bisoft.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class WorkFolderTest2 {
    private final String directoryName = "dir_for_test";
    private final File directory = new File(Paths.get(System.getProperty("java.io.tmpdir"), directoryName).toUri());

    @BeforeEach
    public void before(){
        if (! directory.exists()){
            directory.mkdir();
        }
        try {
            File.createTempFile("tst","clear_1", directory);
            File.createTempFile("tst","clear_2", directory);
            File.createTempFile("tst","clear_3", directory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void after(){
        String[] children = directory.list();
        for (int i=0; i<children.length; i++) {
            new File(directory, children[i]).deleteOnExit();
        }
        directory.deleteOnExit();
    }


    @Test
    public void shouldWorkCorrectly()
    {
        assertDoesNotThrow(() -> new WorkFolder(directory).prepare());
        assertTrue(directory.list().length == 0);
    }

}
