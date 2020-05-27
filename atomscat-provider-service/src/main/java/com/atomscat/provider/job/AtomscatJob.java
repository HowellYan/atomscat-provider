package com.atomscat.provider.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AtomscatJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("AtomscatJob-定时任务");
    }
}
