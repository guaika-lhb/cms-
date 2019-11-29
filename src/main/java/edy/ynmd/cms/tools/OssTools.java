package edy.ynmd.cms.tools;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import java.io.File;


public class OssTools {

    private COSCredentials cred;

    private ClientConfig clientConfig;

    private COSClient cosClient;

    private String bucketName = "cms-1259095689";

    public void init(){

        // 1 初始化用户身份信息（secretId, secretKey）。
         cred = new BasicCOSCredentials("AKIDCA2qfIAs63pzueOOqWyPettiJl4Xv0tg", "d2e3GEwSmkDflFVyEOK0RdGkANPEoAYj");
// 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
// clientConfig中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
         clientConfig = new ClientConfig(new Region("ap-chengdu"));
// 3 生成 cos 客户端。
         cosClient = new COSClient(cred, clientConfig);
// bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式



    }

    public String uploadFireForUrl(String filename,File touplaodfile){

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filename, touplaodfile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        return putObjectResult.getRequestId();
    }


}
