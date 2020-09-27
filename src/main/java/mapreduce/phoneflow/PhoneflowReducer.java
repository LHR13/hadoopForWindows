package mapreduce.phoneflow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PhoneflowReducer extends Reducer<Text, PhoneflowBean, Text, PhoneflowBean> {
    private PhoneflowBean sum = new PhoneflowBean();

    @Override
    protected void reduce(Text key, Iterable<PhoneflowBean> values, Context context) throws IOException, InterruptedException {
        long sumUpFlow = 0;
        long sumDownFlow = 0;

        for (PhoneflowBean value : values) {
            sumUpFlow += value.getUpFlow();
            sumDownFlow += value.getDownFlow();
        }
        sum.set(sumUpFlow, sumDownFlow);
        context.write(key, sum);
    }
}
