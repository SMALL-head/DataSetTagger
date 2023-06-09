package com.zyc.datasettagger.mapper;

import com.zyc.common.data.DataSetInfo;
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
    List<DataSetEntity> selectAllWithLimitation(Integer offset, Integer limitation, Integer publisherId);

    int selectCountAll();

    int insertDataSetEntity(DataSetEntity entity);

    DataSetEntity selectByDatasetId(@Param("dataSetId") String dataSetId);

    int updateDataSetInfo(DataSetEntity dataSetInfo);

    int deleteDatasetById(String id);
    List<DataSetEntity> selectAuthWithLimitation(Integer offset, Integer limitation, Integer userId);
    int getAmountWithAuth(Integer userId);
}
