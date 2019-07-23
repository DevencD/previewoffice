package previewoffice.util;


import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;


public class Office2PDF
{

    private static OpenOfficeConnection officeConnection;

    private static String openofficeIP;

    private static int openofficePort;

    /**
     * 使Office2003-2007全部格式的文档(.doc|.docx|.xls|.xlsx|.ppt|.pptx) 转化为pdf文件<br>
     * 
     * @param inputFilePath
     *            源文件路径，如："e:/test.docx"
     * @param outputFilePath
     *            目标文件路径，如："e:/test_docx.pdf"
     * @return
     */
    public static boolean openOfficeToPDF(Map<String, String> config, String inputFilePath,
                                          String outputFilePath)
    {
        return office2pdf(config, inputFilePath, outputFilePath);
    }

    /**
     * 根据操作系统的名称，获取OpenOffice.org 3的安装目录<br> 如我的OpenOffice.org 3安装在：C:/Program Files
     * (x86)/OpenOffice.org 3<br>
     * 
     * @return OpenOffice.org 3的安装目录
     */
    public String getOfficeHome()
    {
        String osName = System.getProperty("os.name");
        System.out.println("操作系统名称:" + osName);
        if (Pattern.matches("Linux.*", osName))
        {
            return "/opt/openoffice.org3";
        }
        else if (Pattern.matches("Windows.*", osName))
        {
            return "F:\\OpenOffice";
        }
        else if (Pattern.matches("Mac.*", osName))
        {
            return "/Application/OpenOffice.org.app/Contents";
        }
        return null;
    }

    /**
     * 连接OpenOffice.org 并且启动OpenOffice.org
     * 
     * @return
     */
    public static OpenOfficeConnection getOfficeConnection()
    {
        // OpenOfficeConfiguration config = new DefaultOfficeManagerConfiguration();
        // // 获取OpenOffice.org 3的安装目录
        // String officeHome = getOfficeHome();
        // config.setOfficeHome(officeHome);
        // // 启动OpenOffice的服务
        // OfficeManager officeManager = config.buildOfficeManager();
        // officeManager.start();
        if (null == officeConnection)
        {
            officeConnection = new SocketOpenOfficeConnection(openofficeIP, openofficePort);
        }
        return officeConnection;
    }

    /**
     * 转换文件
     * 
     * @param inputFile
     * @param outputFilePath_end
     * @param inputFilePath
     * @param outputFilePath
     * @param converter
     */
    public static void converterFile(File inputFile, String outputFilePath_end,
                                     String inputFilePath, String outputFilePath,
                                     OpenOfficeDocumentConverter converter)
    {
        File outputFile = new File(outputFilePath_end);
        // 假如目标路径不存在,则新建该路径
        if (!outputFile.getParentFile().exists())
        {
            outputFile.getParentFile().mkdirs();
        }
//        Size A3 = new Size(29700, 42000);
        DocumentFormat format = new DocumentFormat("Microsoft Excel", DocumentFamily.SPREADSHEET,
            "application/vnd.ms-excel", "xls");
        DefaultDocumentFormatRegistry formatRegistry = new DefaultDocumentFormatRegistry();
        DocumentFormat documentFormat = formatRegistry.getFormatByFileExtension("pdf");

        converter.convert(inputFile, outputFile);
        System.out.println("文件：" + inputFilePath + "\n转换为\n目标文件：" + outputFile + "\n成功！");
    }

    /**
     * 使Office2003-2007全部格式的文档(.doc|.docx|.xls|.xlsx|.ppt|.pptx) 转化为pdf文件<br>
     * 
     * @param inputFilePath
     *            源文件路径，如："e:/test.docx"
     * @param outputFilePath
     *            目标文件路径，如："e:/test_docx.pdf"
     * @return
     */
    public static boolean office2pdf(Map<String, String> config, String inputFilePath,
                                     String outputFilePath)
    {
        boolean flag = false;
        openofficeIP = config.get("openofficeIP");
        openofficePort = Integer.parseInt(config.get("openofficePort"));
        OpenOfficeConnection officeConnection = getOfficeConnection();
        // 连接OpenOffice
        OpenOfficeDocumentConverter converter = new OpenOfficeDocumentConverter(officeConnection);

        long begin_time = new Date().getTime();
        if (null != inputFilePath)
        {
            File inputFile = new File(inputFilePath);
            // 判断目标文件路径是否为空
            if (null == outputFilePath)
            {
                // 转换后的文件路径
                String outputFilePath_end = getOutputFilePath(inputFilePath);
                if (inputFile.exists())
                {// 找不到源文件, 则返回
                    converterFile(inputFile, outputFilePath_end, inputFilePath, outputFilePath,
                        converter);
                    flag = true;
                }
            }
            else
            {
                if (inputFile.exists())
                {// 找不到源文件, 则返回
                    converterFile(inputFile, outputFilePath, inputFilePath, outputFilePath,
                        converter);
                    flag = true;
                }
            }
            // officeConnection.disconnect();
        }
        else
        {
            System.out.println("con't find the resource");
        }
        long end_time = new Date().getTime();
        System.out.println("文件转换耗时：[" + (end_time - begin_time) + "]ms");
        return flag;
    }

    /**
     * 获取输出文件
     * 
     * @param inputFilePath
     * @return
     */
    public static String getOutputFilePath(String inputFilePath)
    {
        String outputFilePath = inputFilePath.replaceAll("." + getPostfix(inputFilePath), ".pdf");
        return outputFilePath;
    }

    /**
     * 获取inputFilePath的后缀名，如："e:/test.pptx"的后缀名为："pptx"<br>
     * 
     * @param inputFilePath
     * @return
     */
    public static String getPostfix(String inputFilePath)
    {
        return inputFilePath.substring(inputFilePath.lastIndexOf(".") + 1);
    }

//    public static void main(String[] args)
//    {
//        openOfficeToPDF("F:\\download_薪酬发放表附带页眉页脚打印模板（A3纸）_20181127.xlsx", "F:\\download_薪酬发放表附带页眉页脚打印模板（A3纸）_20181127.pdf");
////        openOfficeToPDF("F:\\会计核算软件数据接口(行政事业单位).doc", "F:\\会计核算软件数据接口(行政事业单位).pdf");
//       
//    }

//    public static void main(String[] args)
//    {
//        BigDecimal basePay = new BigDecimal("7500");
//        BigDecimal maxPay = new BigDecimal("0");
//        if(maxPay != null && basePay.compareTo(maxPay)<0) {
//            basePay = maxPay;
//        }
//        System.out.println(basePay);
//        openOfficeToPDF("F:\\excel\\download_薪酬发放表打印模板（A4纸）_20190220.xls", "F:\\excel\\download_薪酬发放表打印模板（A4纸）_20190220.pdf");
//    }
}
