package com.crystal.beam.utils;

import org.apache.beam.runners.direct.DirectOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;

public class BeamUtils {

    // DirectRunner
    public static Pipeline createPipeline(String jobName) {
        PipelineOptions options = PipelineOptionsFactory.create();
        options.setJobName(jobName);
        return Pipeline.create(options);
    }

    // DirectRunner
    public static Pipeline createPipeline(String jobName, int workers) {
        DirectOptions options = PipelineOptionsFactory.create().as(DirectOptions.class);
        options.setJobName(jobName);
        options.setTargetParallelism(workers);
        return Pipeline.create(options);
    }
}
