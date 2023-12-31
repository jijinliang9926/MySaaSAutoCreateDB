package com.kyrie.controller;

import com.kyrie.pojo.CreateDbInfo;
import com.kyrie.service.CreateDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/create")
public class CreateDBController {

    @Autowired
    private CreateDBService createDBService;

    /**
     *  1.自动建库 2.创建数据源 3.把数据源添加到多数据源 4.把多数据源的名字添加到response请求头
     * @param createDBDTO
     * @return
     */
    @PostMapping("/initDB")
    public String initDB(@RequestBody CreateDbInfo createDBDTO) {
        return createDBService.initDB(createDBDTO);
    }

}
