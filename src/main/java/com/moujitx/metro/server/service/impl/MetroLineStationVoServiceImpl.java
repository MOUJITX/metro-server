package com.moujitx.metro.server.service.impl;

import com.moujitx.metro.server.entity.MetroLineStationVo;
import com.moujitx.metro.server.mapper.MetroLineStationVoMapper;
import com.moujitx.metro.server.service.IMetroLineStationService;
import com.moujitx.metro.server.service.IMetroLineStationVoService;

import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MOUJITX
 */
@Service
@RequiredArgsConstructor
public class MetroLineStationVoServiceImpl extends ServiceImpl<MetroLineStationVoMapper, MetroLineStationVo>
        implements IMetroLineStationVoService {

    private final IMetroLineStationService metroLineStationService;

    public List<MetroLineStationVo> getStationsByLine(String line) {
        QueryWrapper<MetroLineStationVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("line_code", line);
        List<MetroLineStationVo> stations = this.list(queryWrapper);
        stations.forEach(station -> station.setTransferLine(
                metroLineStationService.getLinesByStationId(station.getStationCode())));

        return stations;
    }

}