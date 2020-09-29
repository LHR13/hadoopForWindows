package mapreduce.partition;

import mapreduce.phoneflow.PhoneflowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartitioner extends Partitioner<Text, PhoneflowBean> {
    public int getPartition(Text text, PhoneflowBean phoneflowBean, int numPartitions) {
        String phone = text.toString();

        switch (phone.substring(0, 3)) {
            case "136":
                return 0;
            case "137":
                return 1;
            case "138":
                return 2;
            case "139":
                return 3;
            default:
                return 4;
        }
    }
}
