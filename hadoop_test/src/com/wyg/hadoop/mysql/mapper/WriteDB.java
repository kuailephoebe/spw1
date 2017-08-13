package com.wyg.hadoop.mysql.mapper;

import java.io.IOException;
import java.io.DataInput;
import java.io.DataOutput;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.StringTokenizer;

 


import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.lib.db.DBOutputFormat;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import org.apache.hadoop.mapred.lib.db.DBConfiguration;

import com.wyg.hadoop.mysql.bean.DBRecord;

 

public class WriteDB {

    // Map处理过程

    public static class Map extends MapReduceBase implements

            Mapper<Object, Text, Text, DBRecord> {

 

        private final static DBRecord one = new DBRecord();

        private Text word = new Text();

 

        @Override

        public void map(Object key, Text value,

            OutputCollector<Text, DBRecord> output, Reporter reporter)

                throws IOException {

            String line = value.toString();
            String[] infos = line.split(" ");
            String id = infos[0].split("	")[1];
            one.setId(new Integer(id));
            one.setTitle(infos[1]);
            one.setContent(infos[2]);
            word.set(id);
            output.collect(word, one);
        }

    }

    public static class Reduce extends MapReduceBase implements
		    Reducer<Text, DBRecord, DBRecord, Text> {
		@Override
		public void reduce(Text key, Iterator<DBRecord> values,
				OutputCollector<DBRecord, Text> collector, Reporter reporter)
				throws IOException {
			DBRecord record = values.next();
		    collector.collect(record, new Text());
		}
	}
}