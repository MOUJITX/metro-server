package com.moujitx.metro.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moujitx.metro.server.entity.Station;
import com.moujitx.metro.server.service.StationService;
import com.moujitx.metro.server.mapper.StationMapper;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【station】的数据库操作Service实现
 * @createDate 2025-08-24 19:21:57
 */
@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, Station>
        implements StationService {

}
