package com.atguigu.guli.service.edu.service.impl;

import com.atguigu.guli.service.edu.entity.Teacher;
import com.atguigu.guli.service.edu.entity.vo.TeacherQueryVo;
import com.atguigu.guli.service.edu.mapper.TeacherMapper;
import com.atguigu.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-07-25
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {


    @Override
    public IPage<Teacher> selectPage(Page<Teacher> pageParam, TeacherQueryVo teacherQueryVo) {
        //显示分页查询列表
        //1、排序：按照sort字段排序
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        //2、分页查询
        if (teacherQueryVo != null) {
            return baseMapper.selectPage(pageParam,queryWrapper);
        }
        //3、条件查询
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();

        if (!StringUtils.isEmpty(name)){
            queryWrapper.likeRight("name",name);
        }
        if (level!=null){
            queryWrapper.likeRight("level",level);
        }
        if (!StringUtils.isEmpty(joinDateBegin)){
            queryWrapper.ge("join_data",joinDateBegin);
        }
        if (!StringUtils.isEmpty(joinDateEnd)){
            queryWrapper.le("join_data",joinDateEnd);
        }

        return baseMapper.selectPage(pageParam,queryWrapper);
    }
}
