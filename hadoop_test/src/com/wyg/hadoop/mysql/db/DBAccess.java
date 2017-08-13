package com.wyg.hadoop.mysql.db;
import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.lib.IdentityReducer;
import org.apache.hadoop.mapred.lib.db.DBConfiguration;
import org.apache.hadoop.mapred.lib.db.DBInputFormat;

import com.wyg.hadoop.mysql.bean.DBRecord;
import com.wyg.hadoop.mysql.mapper.DBRecordMapper;

public class DBAccess {
      public static void main(String[] args) throws IOException {
             JobConf conf = new JobConf(DBAccess.class);
             conf.setOutputKeyClass(LongWritable.class);
             conf.setOutputValueClass(Text.class);
             conf.setInputFormat(DBInputFormat.class);
             Path path = new Path("hdfs://192.168.44.129:9000/user/root/dbout");
             FileOutputFormat.setOutputPath(conf, path);
             DBConfiguration.configureDB(conf,"com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.112:3306/mathuap","admin","324oiua231!ZDBRT");
             String [] fields = {"id", "title", "content"};
             DBInputFormat.setInput(conf, DBRecord.class, "wu_testhadoop",
                        null, "id", fields);
             conf.setMapperClass(DBRecordMapper.class);
             conf.setReducerClass(IdentityReducer.class);
             JobClient.runJob(conf);
      }

 

}