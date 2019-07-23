package previewoffice.service;


import previewoffice.vo.DataDictionaryVO;


public interface IDDService
{
    DataDictionaryVO getDDVOByKey(String key);

    String getDDValueByKey(String key);

    String createDD(DataDictionaryVO dataDictionaryVO);

}
