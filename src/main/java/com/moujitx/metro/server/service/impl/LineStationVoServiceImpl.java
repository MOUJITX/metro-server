package com.moujitx.metro.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moujitx.metro.server.entity.LineStationVo;
import com.moujitx.metro.server.service.LineStationVoService;
import com.moujitx.metro.server.mapper.LineStationVoMapper;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【line_station_vo】的数据库操作Service实现
 * @createDate 2025-08-24 21:08:07
 */
@Service
public class LineStationVoServiceImpl extends ServiceImpl<LineStationVoMapper, LineStationVo>
        implements LineStationVoService {

}
