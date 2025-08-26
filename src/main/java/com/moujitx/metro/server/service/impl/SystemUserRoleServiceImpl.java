package com.moujitx.metro.server.service.impl;

import com.moujitx.metro.server.entity.SystemUserRole;
import com.moujitx.metro.server.mapper.SystemUserRoleMapper;
import com.moujitx.metro.server.service.ISystemUserRoleService;
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
public class SystemUserRoleServiceImpl extends ServiceImpl<SystemUserRoleMapper, SystemUserRole>
        implements ISystemUserRoleService {

    public List<SystemUserRole> getUserRolesByUserId(String userId) {
        QueryWrapper<SystemUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return this.list(queryWrapper);
    }

}