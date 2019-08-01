package previewoffice.util;


import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 文件上传控制器
 */
public class UploadActionUtil
{

    public static List<Map<String,String>> uploadFile(HttpServletRequest request)
        throws Exception
    {
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
            request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request))
        {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            Iterator<String> iterator = multiRequest.getFileNames();
            while (iterator.hasNext())
            {
                Map<String,String> oneFile = new HashMap<String,String>();
                String filePath = "";
                String fileName = "";
                long fileSize = 0;
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iterator.next());
                if (file != null)
                {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    fileName = myFileName;
                    fileSize = file.getSize();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "")
                    {
                        String fileTyps = myFileName.substring(myFileName.lastIndexOf("."));
                        // String tempName="demo"+fileTyps;
                        String tempName = UUID.randomUUID().toString() + fileTyps;
                        // 创建文件夹
                        String folderPath = ProjectConstant.SAVEFILEPATH + File.separator
                                            + folderName();
                        File fileFolder = new File(folderPath);
                        if (!fileFolder.exists() && !fileFolder.isDirectory())
                        {
                            fileFolder.mkdirs();
                        }
                        filePath = folderPath + File.separator + tempName;
                        File uploadFile = new File(filePath);
                        file.transferTo(uploadFile);
                        
                        oneFile.put("fileName", fileName);
                        oneFile.put("filePath", filePath);
                        oneFile.put("fileSize", fileSize+"");
                        list.add(oneFile);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 得年月日的文件夹名称
     *
     * @return
     */
    public static String getCurrentFilderName()
        throws Exception
    {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR) + "" + (now.get(Calendar.MONTH) + 1) + ""
               + now.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 创建文件夹
     *
     * @param filderName
     */
    public static void createFilder(String filderName)
        throws Exception
    {
        File file = new File(filderName);
        // 如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory())
        {
            file.mkdirs();
        }
    }

    /**
     * 文件扩展名
     *
     * @param fileName
     * @return
     */
    public static String extFile(String fileName)
        throws Exception
    {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 当前日期当文件夹名
     *
     * @return
     */
    public static String folderName()
        throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String str = sdf.format(new Date());
        return str;
    }
}
