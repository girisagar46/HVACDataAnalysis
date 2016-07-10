import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * Created by linuxsagar on 7/10/16.
 */
public class HVACAnalysisDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        String input = "input/CombinedResult.csv";
        Configuration configuration = new Configuration();
        Job hvacJob = Job.getInstance(configuration);

        FileInputFormat.setInputPaths(hvacJob, input);
        FileOutputFormat.setOutputPath(hvacJob,new Path("overview.csv"));

        hvacJob.setInputFormatClass(TextInputFormat.class);
        hvacJob.setOutputFormatClass(TextOutputFormat.class);

        hvacJob.setOutputKeyClass(IntWritable.class);
        hvacJob.setOutputValueClass(Text.class);

        hvacJob.setMapperClass(HVACMapper.class);
        hvacJob.setReducerClass(HVACReducer.class);

        hvacJob.setJarByClass(HVACAnalysisDriver.class);
        hvacJob.setJobName("HVAC data analysis job");

        hvacJob.waitForCompletion(true);
    }
}
