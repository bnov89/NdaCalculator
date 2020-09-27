package org.bnov.nda.file;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

class FileCreatorTest {

    @Test
    void givenAllArguments_shouldFillFileProperly() {
        File file = FileCreator.createFile("path", "nice_name", "_suffix", "ext");
        assertThat(file).hasName("nice_name_suffix.ext");
        assertThat(file.getAbsolutePath()).contains("path");
    }
}
