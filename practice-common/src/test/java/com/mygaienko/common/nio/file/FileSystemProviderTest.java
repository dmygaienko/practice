package com.mygaienko.common.nio.file;

import org.junit.Test;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;

/**
 * Created by enda1n on 23.10.2016.
 */
public class FileSystemProviderTest {

    @Test
    public void testInstalledProviders() throws Exception {
        List<FileSystemProvider> providers =
                FileSystemProvider.installedProviders();
        providers.forEach(System.out::println);
    }

    @Test
    public void testFileStores() throws Exception {
        FileSystem fileSystem = FileSystems.getDefault();
        fileSystem.getFileStores().forEach(System.out::println);
    }

    @Test
    public void testSupportedFileAttributeViews() throws Exception {
        FileSystem fileSystem = FileSystems.getDefault();
        fileSystem.supportedFileAttributeViews().forEach(System.out::println);
    }
}
