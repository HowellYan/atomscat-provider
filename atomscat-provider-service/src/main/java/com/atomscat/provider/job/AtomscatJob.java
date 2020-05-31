package com.atomscat.provider.job;

import com.atomscat.provider.mapper.UserMapper;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class AtomscatJob implements SimpleJob {


    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("AtomscatJob-定时任务");
    }
}
