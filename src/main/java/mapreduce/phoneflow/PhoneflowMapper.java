package mapreduce.phoneflow;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PhoneflowMapper extends Mapper<LongWritable, Text, Text, PhoneflowBean> {
    private Text phone = new Text();
    private PhoneflowBean flow = new PhoneflowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split("\t");
        System.out.println(fields.length);
        phone.set(fields[1]);
        long upflow = Long.parseLong(fields[fields.length - 3]);
        long downflow = Long.parseLong(fields[fields.length - 2]);
        flow.set(upflow, downflow);
        context.write(phone, flow);
    }
}
