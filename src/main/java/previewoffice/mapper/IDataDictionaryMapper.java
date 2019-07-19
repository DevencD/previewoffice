package previewoffice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import previewoffice.vo.DataDictionaryVO;

@Mapper
@Repository
public interface IDataDictionaryMapper
{
    DataDictionaryVO getDDByKey(@Param("DDkey") String key);
    DataDictionaryVO getDDByID(@Param("id") int id);
    String getDDValueByKey(@Param("DDkey") String key);

}
