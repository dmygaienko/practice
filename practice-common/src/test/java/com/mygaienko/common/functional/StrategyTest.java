package com.mygaienko.common.functional;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by enda1n on 11.10.2016.
 */
public class StrategyTest {

    @Test
    public void testName() throws Exception {
        Path inFile = null;
        File outFile = null;

        Compressor gzipCompressor = new Compressor(GZIPOutputStream::new);
        gzipCompressor.compress(inFile, outFile);

        Compressor zipCompressor = new Compressor(ZipOutputStream::new);
        zipCompressor.compress(inFile, outFile);

    }

    static class Compressor {
        private final CompressionStrategy strategy;

        public Compressor(CompressionStrategy strategy) {
            this.strategy = strategy;
        }
        public void compress(Path inFile, File outFile) throws IOException {
            try (OutputStream outStream = new FileOutputStream(outFile)) {
                Files.copy(inFile, strategy.compress(outStream));
            }
        }
    }

    interface CompressionStrategy {
        OutputStream compress(OutputStream data) throws IOException;
    }
}
