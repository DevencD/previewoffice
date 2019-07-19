package previewoffice.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import previewoffice.mapper.IDataDictionaryMapper;
import previewoffice.vo.DataDictionaryVO;

@Service
public class DDServiceImpl implements IDDService
{
    @Autowired
    private DataSource dataSouce;
    
    @Autowired
    private Environment enviroment;
    
    @Autowired
    private IDataDictionaryMapper ddDao;
    
    @Override
    public DataDictionaryVO getDDVOByKey(String key)
    {
        return ddDao.getDDByKey(key);
    }
    
    private ResultSet getResultSet(String sql) throws ClassNotFoundException, SQLException {
        return  getStatement().executeQuery(sql);
    }
    
    private Statement getStatement() throws ClassNotFoundException, SQLException {
        return getConnection().createStatement();
    }
    
    private Connection getConnection()
        throws ClassNotFoundException,
        SQLException
    {
        java.sql.Connection connection = null;
        String driverClass = enviroment.getProperty("spring.datasource.druid.driver-class-name");
        Class.forName(driverClass);
        String url = enviroment.getProperty("spring.datasource.druid.url");
        String username = enviroment.getProperty("spring.datasource.druid.username");
        String password = enviroment.getProperty("spring.datasource.druid.password");
        connection = java.sql.DriverManager.getConnection(url, username, password);
        return connection;
    }

    @Override
    public String getDDValueByKey(String key)
    {
        return ddDao.getDDValueByKey(key);
    }

}
