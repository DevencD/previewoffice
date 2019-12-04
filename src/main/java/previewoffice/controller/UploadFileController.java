package previewoffice.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;

import previewoffice.mapper.IAttachementPartMapper;
import previewoffice.mapper.IAttachmentMapper;
import previewoffice.service.IOperRecordService;
import previewoffice.util.OperType;
import previewoffice.util.ProjectConstant;
import previewoffice.util.State;
import previewoffice.util.UploadActionUtil;
import previewoffice.vo.AttachmentPartVO;
import previewoffice.vo.AttachmentVO;


@RestController
@RequestMapping("/uploadFile")
public class UploadFileController
{

    @Autowired
    private IAttachmentMapper attachmentDao;

    @Autowired
    private IAttachementPartMapper attachmentPartDao;

    @Autowired
    private IOperRecordService recordOper;

    @GetMapping("/getAttList")
    public Map<String, List<AttachmentVO>> getAttachmentList()
    {
        Map<String, List<AttachmentVO>> result = new HashMap<String, List<AttachmentVO>>();
        result.put("result", attachmentDao.queryAll());
        return result;
    }

    @GetMapping("/deleteAtt")
    public String deleteAttachment(HttpServletRequest httpServletRequest)
    {
        String fileId = httpServletRequest.getParameter("fileId");
        AttachmentVO attachment = attachmentDao.getFileById(fileId);
        String filePath = attachment.getFilePath();
        if (!StringUtils.isEmpty(filePath))
        {
            File file = new File(filePath);
            if (file.exists())
            {
//                file.delete();
            }
        }
        recordOper.recordOper(httpServletRequest, OperType.DELETE.getValue(), fileId);
        attachmentDao.deleteAttachmentById(fileId);
        attachmentPartDao.deleteAttachmentPartByFileId(fileId);
        return null;
    }

    @GetMapping("/download")
    public String downloadAttachment(HttpServletRequest httpServletRequest,
                                     HttpServletResponse response)
        throws UnsupportedEncodingException
    {
        String fileId = httpServletRequest.getParameter("fileId");
        if (StringUtils.isEmpty(fileId))
        {
            return null;
        }
        else
        {
            recordOper.recordOper(httpServletRequest, OperType.DOWNLOAD.getValue(), fileId);
            AttachmentVO attachment = attachmentDao.getFileById(fileId);
            if (null != attachment)
            {
                String fileName = attachment.getFileName();
                String filePath = attachment.getFilePath();
                if (StringUtils.isEmpty(filePath))
                {
                    OutputStream outputStream = null;
                    try
                    {
                        StringBuffer fileContent = new StringBuffer();
                        List<AttachmentPartVO> attachmentParts = attachmentPartDao.getAttachementByFileID(
                            fileId);
                        for (int i = 0, size = attachmentParts.size(); i < size; i++ )
                        {
                            AttachmentPartVO attachmentPartVO = attachmentParts.get(i);
                            fileContent.append(attachmentPartVO.getFileContentPart());
                        }
                        byte[] b = Base64.decodeBase64(fileContent.toString());
                        for (int i = 0; i < b.length; i++ )
                        {
                            if (b[i] < 0)
                            {
                                b[i] += 256;
                            }
                        }
                        // 取得当前上传文件的文件名称
                        String myFileName = attachmentDao.getFileNameById(fileId);
                        // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                        if (myFileName.trim() != "")
                        {
                            String fileTyps = myFileName.substring(myFileName.lastIndexOf("."));
//                      // String tempName="demo"+fileTyps;
                            String tempName = UUID.randomUUID().toString() + fileTyps;
                            // 创建文件夹
                            String folderPath;
                            folderPath = ProjectConstant.SAVEFILEPATH + File.separator
                                         + folderName();

                            File fileFolder = new File(folderPath);
                            if (!fileFolder.exists() && !fileFolder.isDirectory())
                            {
                                fileFolder.mkdirs();
                            }
                            filePath = folderPath + File.separator + tempName;

                        }
                        outputStream = new FileOutputStream(filePath);
                        outputStream.write(b);
                        outputStream.flush();
                        outputStream.close();
                        attachmentDao.updateFilePath(fileId, filePath);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            if (null != outputStream) outputStream.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                File file = new File(filePath);
                if (file.exists())
                {
                    response.reset();
                    response.setContentType("application/x-download");
                    response.setCharacterEncoding("utf-8");
                    response.setContentLength((int)file.length());
                    response.setHeader("Content-Disposition",
                        "attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
                    byte[] buff = new byte[1024];
                    BufferedInputStream bis = null;
                    BufferedOutputStream out = null;
                    try
                    {
                        out = new BufferedOutputStream(response.getOutputStream());
                        bis = new BufferedInputStream(new FileInputStream(file));
                        int len = 0;
                        while ((len = bis.read(buff)) != -1)
                        {
                            out.write(buff, 0, len);
                            out.flush();
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        try
                        {
                            bis.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    @PostMapping("/upload")
    public String upload(HttpServletRequest httpServletRequest)
        throws Exception
    {
        List<Map<String, String>> list = UploadActionUtil.uploadFile(httpServletRequest);
        Map<String, String> oneFile = list.get(0);
        String fileName = oneFile.get("fileName");
        String filePath = oneFile.get("filePath");
        String fileSize = oneFile.get("fileSize");
        String fileID = UUID.randomUUID().toString().replace("-", "")
                        + ProjectConstant.ATTACHMENTID_SUFFIX;
        AttachmentVO file = new AttachmentVO();
        file.setId(fileID);
        file.setFileName(fileName);
        file.setFilePath(filePath);
        file.setFileSeriaLength(Long.parseLong(fileSize));
        file.setState(State.ENABLE.getValue());
        file.setFileCompleteTime(new Date());
        file.setFileCreateTime(new Date());
        attachmentDao.createAttachmentByVO(file);
        recordOper.recordOper(httpServletRequest, OperType.CREATE.getValue(), fileID);
        return fileID;
    }

    @GetMapping("/transfer/getFileId")
    public Map<String, String> getFileID(HttpServletRequest httpServletRequest)
    {
        String fileName = httpServletRequest.getParameter("fileName");
        String fileId = UUID.randomUUID().toString().replace("-", "")
                        + ProjectConstant.ATTACHMENTID_SUFFIX;
        attachmentDao.createAttachment(fileId, fileName, State.ENABLE.getValue(), new Date());
        Map<String, String> result = new HashMap<String, String>();
        result.put("fileId", fileId);
        recordOper.recordOper(httpServletRequest, OperType.CREATE.getValue(), fileId);
        return result;
    }

    @GetMapping("/transfer")
    public Map<String, String> transfer(HttpServletRequest httpServletRequest)
        throws Exception
    {
//        InputStream stream = httpServletRequest.getInputStream();
//        int lengthOfContent = httpServletRequest.getContentLength();
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
//        byte[] data = new byte[lengthOfContent];  
//        int count = -1;  
//        while((count = stream.read(data,0,lengthOfContent)) != -1)  
//            outStream.write(data, 0, count);  
//        data = null;   
//        String fileContent = new String(outStream.toByteArray(),"UTF-8");
//      fileName = URLDecoder.decode(new String(org.apache.commons.codec.binary.Base64.decodeBase64(fileName)));
        Map<String, String> result = new HashMap<String, String>();
        String fileId = httpServletRequest.getParameter("fileID");
        String fileComplete = httpServletRequest.getParameter("fileComplete");
        String fileContentPart = httpServletRequest.getParameter("fileContentPart");
        String fileSeria = httpServletRequest.getParameter("fileSeria");
        String attachmentPartId = UUID.randomUUID().toString().replace("-", "")
                                  + ProjectConstant.ATTACHMENTPARTID_SUFFIX;
        AttachmentPartVO attachmentPart = new AttachmentPartVO();
        attachmentPart.setId(attachmentPartId);
        attachmentPart.setFileId(fileId);
        attachmentPart.setFileSeria(Integer.parseInt(fileSeria));
        attachmentPart.setFileContentPart(fileContentPart);
        attachmentPart.setFileCreateTime(new Date());
        attachmentPartDao.createAttachmentPart(attachmentPart);
        if (Boolean.toString(false).equals(fileComplete))
        {
            result.put("fileComplete", Boolean.toString(false));
            return result;
        }
        else
        {
            attachmentDao.updateComplete(fileId, Integer.parseInt(fileSeria), new Date());
            result.put("fileComplete", Boolean.toString(true));
//            result.put("filePath", myFileName);
            int filePartCount = attachmentPartDao.getPartCount4Att(fileId);
            result.put("filePartCount", filePartCount + "");
            result.put("maxFileSeria", fileSeria);
            return result;
        }

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
