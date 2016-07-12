import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by linuxsagar on 7/10/16.
 */
public class HVACMapper extends Mapper<LongWritable, Text, IntWritable, Text> {

    private Splitter splitter;
    private Joiner joiner;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException{
        splitter = Splitter.on(",").trimResults();
        joiner = Joiner.on(",");
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
        // make an arraylist of each row in CSV
        List<String> values = Lists.newArrayList(splitter.split(value.toString()));

        // Get building ID, i.e. value ar index 0
        String buildingId = values.get(0);

        // now remove the building id from the list
        values.remove(0);

        // now append other values except the buildingID
        String others = joiner.join(values);

        // map buildingID with each others
        context.write(new IntWritable(Integer.parseInt(buildingId)), new Text(others));
    }
}
