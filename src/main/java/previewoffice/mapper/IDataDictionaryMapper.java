package previewoffice.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import previewoffice.vo.DataDictionaryVO;


@Mapper
@Repository
public interface IDataDictionaryMapper
{
    DataDictionaryVO getDDByKey(@Param("DDkey") String key);

    DataDictionaryVO getDDByID(@Param("id") String id);

    String getDDValueByKey(@Param("DDkey") String key);

    List<DataDictionaryVO> getDDList();

    void deleteDDByID(@Param("id") String id);

    void createDD(@Param("DD") DataDictionaryVO dataDictionaryVO);

    void updateDD(@Param("DD") DataDictionaryVO dataDictionaryVO);
}
