package mapreduce.writablecomparable;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PhoneflowBean implements WritableComparable<PhoneflowBean> {

    private long sumFlow;

    public PhoneflowBean(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    @Override
    public String toString() {
        return "sumFlow=" + sumFlow +
                " }";
    }

    public PhoneflowBean() {
    }

    /**
     * 序列化方法（必须在反序列化方法之前）
     * @param dataOutput：框架提供的数据出口
     * @throws IOException
     */
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(sumFlow);

    }

    /**
     * 反序列化方法（必须在序列化方法之后）
     * @param dataInput：框架提供的数据来源
     * @throws IOException
     */
    public void readFields(DataInput dataInput) throws IOException {
        sumFlow = dataInput.readLong();

    }

    @Override
    public int compareTo(PhoneflowBean o) {
        return Long.compare(o.sumFlow, this.sumFlow);
    }
}
