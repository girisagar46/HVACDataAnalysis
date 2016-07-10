import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOExceptionWithCause;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.aggregate.DoubleValueSum;

import java.io.IOException;
import java.util.List;

/**
 * Created by linuxsagar on 7/10/16.
 */
public class HVACReducer extends Reducer<IntWritable, Text, NullWritable, Text>{

    private Splitter splitter;
    private Joiner joiner;
    boolean header;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        splitter = Splitter.on(",").trimResults();
        joiner = Joiner.on(",");
        header = true;
    }

    @Override
    protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        double totalExcepted = 0.0;
        double totalActual = 0.0;
        String model = "";
        int totalCount = 0;

        for (Text each: values){
            List<String> rows = Lists.newArrayList(splitter.split(each.toString()));
            totalExcepted += Double.parseDouble(rows.get(rows.size() - 2));
            totalActual += Double.parseDouble(rows.get(rows.size() - 1));
            model = rows.get(1);
            totalCount++;
        }

        if(header){
            context.write(null, new Text("BID, Model, Expected, Actual"));
            header = false;
        }

        String mergedValue = String.valueOf(key)+","+model+","+(totalExcepted/totalCount)+","+(totalActual/totalCount);

        context.write(null, new Text(mergedValue));
    }
}
