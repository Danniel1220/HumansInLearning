package com.crystal.beam;

import com.crystal.beam.utils.BeamUtils;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.values.PBegin;
import org.apache.beam.sdk.values.PCollection;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PCollectionTest {
    private static final String FILE_1 = "tmp/beam/1";
    private static final String FILE_2 = "tmp/beam/2";

    @BeforeClass
    public static void writeFiles() throws IOException {
        FileUtils.writeStringToFile(new File(FILE_1), "1\n2\n3\n4", "UTF-8");
        FileUtils.writeStringToFile(new File(FILE_2), "5\n6\n7\n8", "UTF-8");
    }

    @AfterClass
    public static void deleteFiles() {
        FileUtils.deleteQuietly(new File(FILE_1));
        FileUtils.deleteQuietly(new File(FILE_2));
    }

    @Test
    public void should_construct_pCollection_from_list_in_memory() {
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e", "e");

        Pipeline pipeline = BeamUtils.createPipeline("Creating PCollection from memory");
        PTransform<PBegin, PCollection<String>> input = Create.of(letters);
        PCollection<String> lettersCollection = PBegin.in(pipeline).apply(input);

        PAssert.that(lettersCollection).containsInAnyOrder("a", "b", "c", "d", "e", "e");

        PipelineResult.State pipelineResult = pipeline.run().waitUntilFinish();
        System.out.println(pipelineResult);
    }

    @Test
    public void should_construct_pCollection_without_applying_transformation_on_it() {
        Pipeline pipeline = BeamUtils.createPipeline("Creating PCollection from file");

        TextIO.Read reader = TextIO.read().from("/tmp/beam/*");
        PCollection<String> readNumbers = pipeline.apply(reader);

        PAssert.that(readNumbers).containsInAnyOrder("1", "2", "3", "4", "5", "6", "7", "8");
        pipeline.run().waitUntilFinish();
    }

    @Test
    public void should_not_modify_input_pCollection_after_applying_the_transformation() {
        List<String> letters = Arrays.asList("a", "b", "c", "d", "a");
        Pipeline pipeline = BeamUtils.createPipeline("Transforming a PCollection");

        PCollection<String> lettersCollection = pipeline.apply(Create.of(letters));
        PCollection<String> aLetters = lettersCollection.apply(Filter.equal("a"));

        PAssert.that(lettersCollection).containsInAnyOrder("a", "b", "c", "d","a");
        PAssert.that(aLetters).containsInAnyOrder("a","a");
        pipeline.run().waitUntilFinish();
    }
}
