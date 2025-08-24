package com.moujitx.metro.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moujitx.metro.server.entity.Line;
import com.moujitx.metro.server.service.LineService;
import com.moujitx.metro.server.mapper.LineMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【line】的数据库操作Service实现
* @createDate 2025-08-24 18:59:02
*/
@Service
public class LineServiceImpl extends ServiceImpl<LineMapper, Line>
    implements LineService{

}




