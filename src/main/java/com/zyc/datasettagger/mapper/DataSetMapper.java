package com.zyc.datasettagger.mapper;

import com.zyc.common.entity.DataSetEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    int insertDataSetEntity(DataSetEntity entity);

    DataSetEntity selectByDatasetId(@Param("dataSetId") String dataSetId);

}
