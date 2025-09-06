package com.moujitx.metro.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moujitx.metro.server.entity.MetroLine;
import com.moujitx.metro.server.entity.MetroLineStation;
import com.moujitx.metro.server.mapper.MetroLineMapper;
import com.moujitx.metro.server.mapper.MetroLineStationMapper;
import com.moujitx.metro.server.mapper.MetroStationMapper;
import com.moujitx.metro.server.service.IMetroLineStationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MOUJITX
 */
@Service
@RequiredArgsConstructor
public class MetroLineStationServiceImpl extends ServiceImpl<MetroLineStationMapper, MetroLineStation>
        implements IMetroLineStationService {

    private final MetroLineMapper metroLineMapper;
    private final MetroStationMapper metroStationMapper;

    public List<MetroLineStation> getLinesByStationId(String stationId) {
        QueryWrapper<MetroLineStation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("station_code", stationId);
        List<MetroLineStation> list = this.list(queryWrapper);

        String transferStation = metroStationMapper.selectById(stationId).getTransferStation();
        if (transferStation != null) {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("station_code", transferStation);
            list.addAll(this.list(queryWrapper));
        }

        list.forEach(station -> {
            MetroLine line = metroLineMapper.selectById(station.getLineCode());
            station.setLineShortName(line.getLineCode());
            station.setLineColor(line.getLineColor());
        });
        return list;
    }
}