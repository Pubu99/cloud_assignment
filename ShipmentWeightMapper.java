import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ShipmentWeightMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    private Text destinationPort = new Text();
    private DoubleWritable weight = new DoubleWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // Skip header line
        if (key.get() == 0 && value.toString().contains("name")) {
            return;
        }
        String[] fields = value.toString().split(",");
        if (fields.length == 8) {
            try {
                String port = fields[7].trim();
                double wt = Double.parseDouble(fields[2].trim());
                destinationPort.set(port);
                weight.set(wt);
                context.write(destinationPort, weight);
            } catch (NumberFormatException e) {
                // Skip invalid records
            }
        }
    }
}
