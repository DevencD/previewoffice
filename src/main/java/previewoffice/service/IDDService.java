package previewoffice.service;

import org.springframework.stereotype.Service;

import previewoffice.vo.DataDictionaryVO;

public interface IDDService
{
    DataDictionaryVO getDDVOByKey(String key);
    String getDDValueByKey(String key);
    

}
