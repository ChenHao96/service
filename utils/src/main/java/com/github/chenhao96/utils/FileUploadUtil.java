package com.github.chenhao96.utils;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.github.chenhao96.utils.aliyun.model.AliyunProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by ChenHao on 2016/8/2.
 * 文件上传工具
 */
public class FileUploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);
    public static final String FILE_PATH_STATIC = System.getProperty("user.home") + "/file_upload";

    private String filePath;
    private AliyunProperties properties;

    static {
        init();
    }

    public FileUploadUtil(AliyunProperties properties) {
        this.properties = properties;
    }

    private FileUploadUtil(String filePath) {
        this.filePath = filePath;
    }

    private static void init() {

        File file = new File(FILE_PATH_STATIC);
        if (file.setWritable(true)) {
            logger.info("filePath writable fail,create path:{}", FILE_PATH_STATIC);
            return;
        }

        if (!file.exists()) {
            if (file.mkdirs()) {
                logger.info("filePath not exists,create path:{}", FILE_PATH_STATIC);
            }
        }
    }

    /**
     * 文件上传
     *
     * @param file spring框架的上传主件
     * @return 存放后的完整文件路径
     */
    public static String uploadFileLocal(MultipartFile file, String fileName) {
        return uploadFileLocal(file, FILE_PATH_STATIC, fileName);
    }

    /**
     * 文件上传
     *
     * @param file     上传的文件流
     * @param filePath 文件存放的位置
     * @param fileName 文件名称
     * @return 存放后的完整文件路径
     */
    public static String uploadFileLocal(MultipartFile file, String filePath, String fileName) {
        return new FileUploadUtil(filePath).uploadFileOutPath(file, fileName);
    }

    /**
     * 文件上传
     *
     * @param file     spring框架的上传主件
     * @param fileName 文件名称
     * @return 存放后的完整文件路径
     */
    private String uploadFileOutPath(MultipartFile file, String fileName) {

        fileName = checkFileGetName(file, fileName);
        String dateDirectory = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String relativelyPath = File.separator + dateDirectory;

        File targetFile = new File(filePath + relativelyPath, fileName);
        logger.info("targetFile:{}", targetFile.toString());

        if (!targetFile.exists() && !targetFile.mkdirs()) {

            throw new RuntimeException("创建目录失败");
        } else {

            try {
                file.transferTo(targetFile);
            } catch (Exception e) {
                throw new RuntimeException("传输失败", e);
            }

            String absolutePath = targetFile.getAbsolutePath();
            logger.info("file absolutePath:{}", absolutePath);

            return (relativelyPath + "\\" + fileName).replaceAll("\\\\", "/");
        }
    }

    private String checkFileGetName(MultipartFile file, String fileName) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("上传文件不能为空");
        }

        if (StringUtils.isNotBlank(file.getOriginalFilename())) {
            fileName = file.getOriginalFilename();
        }
        logger.info("fileName:{}", fileName);

        String extName = fileName.substring(fileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replace("-", "");
        fileName = uuid + extName;
        logger.info("new fileName:{}", fileName);

        return fileName;
    }

    public String uploadWebFileOSS(MultipartFile file, String fileName) throws IOException {

        //获取文件新名称
        fileName = checkFileGetName(file, fileName);

        //获取文件流
        InputStream inputStream = file.getInputStream();
        return uploadFileOSS(fileName, inputStream);

    }

    public String uploadFileOSS(String fileName, InputStream inputStream) {

        //创建OSS连接实例
        ClientConfiguration config = new ClientConfiguration();
        config.setMaxErrorRetry(properties.getMaxErrorRetry());
        config.setSocketTimeout(properties.getSocketTimeout());
        config.setMaxConnections(properties.getMaxConnections());
        config.setConnectionTimeout(properties.getConnectionTimeout());
        CredentialsProvider credsProvider = new DefaultCredentialProvider(
                properties.getAccessKeyId(), properties.getAccessKeySecret());
        OSSClient ossClient = new OSSClient(properties.getEndpoint(), credsProvider, config);

        //写数据
        ossClient.putObject(properties.getBucketName(), fileName, inputStream);

        //创建访问路径
        Date expiration = new Date(System.currentTimeMillis() + 31536000000L);
        URL url = ossClient.generatePresignedUrl(properties.getBucketName(), fileName, expiration);

        //关闭OSS连接实例
        ossClient.shutdown();
        String resultUrl = url.toString();
        //将多余参数去除
        return resultUrl.substring(0, resultUrl.indexOf("?"));
    }
}
