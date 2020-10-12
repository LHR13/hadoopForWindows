package mapreduce.writablecomparable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SortMapper extends Mapper<LongWritable, Text, PhoneflowBean, Text> {

    private PhoneflowBean phoneflowBean = new PhoneflowBean();
    private Text phone = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(" ");
        phoneflowBean.setSumFlow(Long.parseLong(fields[3]));
        phone.set(fields[0]);
        context.write(phoneflowBean, phone);

    }
}
