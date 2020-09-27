package mapreduce.mywordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WcDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //申请一个Job实例
        Job job = Job.getInstance(new Configuration());

        //设置类路径
        job.setJarByClass(WcDriver.class);

        //设置Mapper和Reducer
        job.setMapperClass(WcMapper.class);
        job.setReducerClass(WcReducer.class);

        //设置Mapper和Reducer输出的路径
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //设置输入输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //提交Job
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
