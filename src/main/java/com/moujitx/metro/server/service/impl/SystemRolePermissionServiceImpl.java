package com.moujitx.metro.server.service.impl;

import com.moujitx.metro.server.entity.SystemRolePermission;
import com.moujitx.metro.server.mapper.SystemRolePermissionMapper;
import com.moujitx.metro.server.service.ISystemRolePermissionService;
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
public class SystemRolePermissionServiceImpl extends ServiceImpl<SystemRolePermissionMapper, SystemRolePermission>
        implements ISystemRolePermissionService {

    public List<SystemRolePermission> getRolePermissionsByRoleId(String roleId) {
        QueryWrapper<SystemRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        return this.list(queryWrapper);
    }

}