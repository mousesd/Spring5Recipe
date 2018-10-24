package net.homenet.s2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Component
public class SpringExecutorMain {
    @Autowired
    private SimpleAsyncTaskExecutor asyncTaskExecutor;
    @Autowired
    private SyncTaskExecutor syncTaskExecutor;
    @Autowired
    private TaskExecutorAdapter taskExecutorAdapter;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private SampleRunnableImpl task;

    @PostConstruct
    public void submitJobs() {
        asyncTaskExecutor.execute(task);
        syncTaskExecutor.execute(task);
        taskExecutorAdapter.submit(task);

        for (int count = 0; count < 500; count++) {
            threadPoolTaskExecutor.submit(task);
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(SpringExecutorConfiguration.class);
        context.registerShutdownHook();
    }
}
