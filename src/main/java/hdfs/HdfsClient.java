package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class HdfsClient {
    @Test
    public void put() throws IOException, InterruptedException {
        FileSystem fileSystem = FileSystem.get(
                URI.create("hdfs://192.168.234.11:9000"),
                new Configuration(),"atguigu");
        fileSystem.copyFromLocalFile(new Path(
                "C:\\Users\\李昊儒\\Desktop\\winutils-master"),
                new Path("/"));
        fileSystem.close();
    }

    @Test
    public void delete() throws IOException, InterruptedException {
        boolean b = true;
        FileSystem fileSystem = FileSystem.get(
                URI.create("hdfs://192.168.234.11:9000"),
                new Configuration(),"atguigu");
        fileSystem.delete(new Path("/winutils-master"), b);
    }

    @Test
    public void look() throws IOException, InterruptedException {
        FileSystem fileSystem = FileSystem.get(
                URI.create("hdfs://192.168.234.11:9000"),
                new Configuration(),"atguigu");
        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(new Path("/"), true);
        while (files.hasNext()) {
            System.out.println(files.next().getPath());
        }
    }


}
