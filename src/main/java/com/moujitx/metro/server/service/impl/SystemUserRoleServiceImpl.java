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

    public Boolean saveOrUpdateUserRoles(String userId, List<String> roleIds) {
        this.getUserRolesByUserId(userId).forEach(
                userRole -> {
                    if (!roleIds.contains(userRole.getRoleId())) {
                        this.removeById(userRole.getId());
                    }
                });

        roleIds.forEach(
                roleId -> {
                    if (this.getUserRolesByUserId(userId).stream()
                            .noneMatch(userRole -> userRole.getRoleId().equals(roleId))) {
                        SystemUserRole userRole = new SystemUserRole();
                        userRole.setUserId(userId);
                        userRole.setRoleId(roleId);
                        this.save(userRole);
                    }
                });

        return true;
    }

}