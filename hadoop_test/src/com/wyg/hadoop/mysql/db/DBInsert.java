package com.wyg.hadoop.mysql.db;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.lib.db.DBConfiguration;
import org.apache.hadoop.mapred.lib.db.DBInputFormat;
import org.apache.hadoop.mapred.lib.db.DBOutputFormat;

import com.wyg.hadoop.mysql.bean.DBRecord;
import com.wyg.hadoop.mysql.mapper.WriteDB;

public class DBInsert {
	public static void main(String[] args) throws Exception {

		 

        JobConf conf = new JobConf(WriteDB.class);
        // 设置输入输出类型

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(DBOutputFormat.class);

        // 不加这两句，通不过，但是网上给的例子没有这两句。
        //Text, DBRecord
        conf.setMapOutputKeyClass(Text.class);
        conf.setMapOutputValueClass(DBRecord.class);
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(DBRecord.class);
        // 设置Map和Reduce类
        conf.setMapperClass(WriteDB.Map.class);
        conf.setReducerClass(WriteDB.Reduce.class);
        // 设置输如目录
        FileInputFormat.setInputPaths(conf, new Path("hdfs://192.168.44.129:9000/user/root/dbout"));
        // 建立数据库连接
        DBConfiguration.configureDB(conf,"com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.112:3306/mathuap","admin","324oiua231!ZDBRT");
        String[] fields = {"id","title","content" };
        DBOutputFormat.setOutput(conf, "wu_testhadoop", fields);
        JobClient.runJob(conf);
    }

}
