package com.seuslab.application.service.pet;

import com.google.common.collect.Lists;
import com.seuslab.application.entity.UserEntity;
import com.seuslab.application.excel.ExcelEntityWriter;
import com.seuslab.application.exception.VKRequestException;
import com.seuslab.application.service.UserService;
import com.seuslab.application.vk.VKApiConst;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class BaseReaderService {

    private final UserService userService;

    private final BaseFillService baseFillService;

    private final ExcelEntityWriter excelEntityWriter;


    @Value("${service.threads}")
    private String serviceThreads;

    public BaseReaderService(UserService userService, BaseFillService baseFillService, ExcelEntityWriter excelEntityWriter) {
        this.userService = userService;
        this.baseFillService = baseFillService;
        this.excelEntityWriter = excelEntityWriter;
    }

    public void startReadingFromBase() throws InterruptedException {
        final List<Long> userEntities = userService.getNotWrittenEntities();

        final int threads = Integer.parseInt(serviceThreads);
        final List<List<Long>> splittedList = getLists(userEntities, threads);
       
        final List<Thread> startedThreads = new ArrayList<>();
        final List<UserEntity> createdUsers = Collections.synchronizedList(new ArrayList<>());
        
        for (List<Long> longs : splittedList) {
            startedThreads.add(fillBaseWithMultithreading(longs, createdUsers));
        }

        //Дожидаемся выполнения всех поток, чтобы корректно заполнить Excel
        for (int i = 0; i < startedThreads.size(); i++) {
            startedThreads.get(i).join();
        }

        for (int i = 0; i < createdUsers.size(); i++) {
            UserEntity userEntity = userService.saveUser(createdUsers.get(i));
            excelEntityWriter.writeToExcel(userEntity);
        }

        excelEntityWriter.buildFile();
    }

    private List<List<Long>> getLists(List<Long> userEntities, int threads) {
        final int userEntitiesSize = userEntities.size();
        final int minimumThreads = divideAndRoundUp(userEntitiesSize, VKApiConst.MAX_ELEMENTS_ON_USER_GET_REQUEST);

        if(minimumThreads > threads || threads < 1 || threads < userEntitiesSize) {
            throw new VKRequestException("Минимальное количество потоков для запуска парсинга: " + minimumThreads);
        }

        List<List<Long>> splittedList = Lists.partition(userEntities, divideAndRoundUp(userEntitiesSize, threads));
        return splittedList;
    }

    private int divideAndRoundUp(int dividend, int divisor) {
        return (int) Math.ceil((double) dividend / divisor);
    }


    private Thread fillBaseWithMultithreading(List<Long> ids, List<UserEntity> userEntities) {
        Thread thread = new Thread(() -> {
                userEntities.addAll(baseFillService.getUserEntities(ids));
        });
        thread.start();
        return thread;
    }
}
