import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.commons.lang.StringUtils;

public class TwitterMapper extends Mapper<Object, Text, IntWritable, IntWritable> {
    //constant variable stores a value of one
    private final IntWritable one = new IntWritable(1);
    //mapper method takes takes as input the text file
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
    // Format per tweet is id;date;hashtags;tweet;
    
    String dump = value.toString();
    if(StringUtils.ordinalIndexOf(dump,";",4)>-1){
        int startIndex = StringUtils.ordinalIndexOf(dump,";",3) + 1;
        String tweet = dump.substring(startIndex,dump.lastIndexOf(';'));
	//store tweet length in a variable
        IntWritable tweetSize=new IntWritable(tweet.length());
	//send tweet length and its occurrence to reducer
	context.write(tweetSize,one);

      }//END if statement

  }//END map method

}//END TwitterMapper
