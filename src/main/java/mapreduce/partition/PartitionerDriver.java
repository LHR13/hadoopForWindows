package mapreduce.partition;

import mapreduce.phoneflow.PhoneflowBean;
import mapreduce.phoneflow.PhoneflowMapper;
import mapreduce.phoneflow.PhoneflowReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class PartitionerDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取Job实例
        Job job = Job.getInstance(new Configuration());

        //设置类路径
        job.setJarByClass(PartitionerDriver.class);

        //设置Mapper和Reducer
        job.setMapperClass(PhoneflowMapper.class);
        job.setReducerClass(PhoneflowReducer.class);

        //设置分区数
        job.setNumReduceTasks(5);
        job.setPartitionerClass(MyPartitioner.class);

        //设置输入输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(PhoneflowBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(PhoneflowBean.class);

        //设置输入输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //提交
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
