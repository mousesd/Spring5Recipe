package net.homenet;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.util.Map;

public class FileReplicationJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Map map = context.getJobDetail().getJobDataMap();
        FileReplicator fileReplicator = (FileReplicator) map.get("fileReplicator");

        try {
            fileReplicator.replicate();
        } catch (IOException e) {
            throw new JobExecutionException(e);
        }
    }
}
