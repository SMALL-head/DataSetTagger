package com.zyc.datasettagger.mapper;

import com.zyc.common.entity.DataSetEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zyc
 * @version 1.0
 */
@Mapper
public interface DataSetMapper {
    DataSetEntity selectById(Integer id);

    List<DataSetEntity> selectAllWithLimitation(Integer page, Integer limitation);

    int selectCountAll();

}
