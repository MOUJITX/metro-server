package com.moujitx.metro.server.service.impl;

import com.moujitx.metro.server.entity.SystemUserRole;
import com.moujitx.metro.server.mapper.SystemRoleMapper;
import com.moujitx.metro.server.mapper.SystemUserRoleMapper;
import com.moujitx.metro.server.service.ISystemUserRoleService;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.stream.Collectors;

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
public class SystemUserRoleServiceImpl extends ServiceImpl<SystemUserRoleMapper, SystemUserRole>
        implements ISystemUserRoleService {

    private final SystemRoleMapper systemRoleMapper;

    public List<SystemUserRole> getUserRolesByUserId(String userId) {
        QueryWrapper<SystemUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return this.list(queryWrapper);
    }

    public void saveOrUpdateUserRoles(String userId, List<String> roleIds) {
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

    }

    @Override
    public List<String> getUserRoleNamesByUserId(String userId) {
        return this.getUserRolesByUserId(userId)
                .stream()
                .map(role -> systemRoleMapper.selectById(role.getRoleId()).getName())
                .collect(Collectors.toList());
    }

}