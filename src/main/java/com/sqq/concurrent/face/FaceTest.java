//package com.sqq.concurrent.face;
//
//import com.arcsoft.face.*;
//import com.arcsoft.face.enums.DetectMode;
//import com.arcsoft.face.enums.DetectOrient;
//import com.arcsoft.face.enums.ErrorInfo;
//import com.arcsoft.face.toolkit.ImageInfo;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;
//
///**
// * @author sqq
// * @Date 2021/4/21
// */
//public class FaceTest {
//
//    public static void main(String[] args) {
//        String appId = "AqhoaUxDGWGuH2fT5DdCwDhW5mAYBRZDNnLeSm8X3kd1";
//        String sdkKey = "AqoGBzUS8EKzvnBPCK5kjZuoXZS5DVpNVYMSZqHnHwEh";
//        FaceEngine faceEngine = new FaceEngine();
//        //激活引擎
//        int errorCode = faceEngine.activeOnline(appId, sdkKey);
//
//        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
//            System.out.println("引擎激活失败");
//        }
//
//
//        ActiveFileInfo activeFileInfo=new ActiveFileInfo();
//        errorCode = faceEngine.getActiveFileInfo(activeFileInfo);
//        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
//            System.out.println("获取激活文件信息失败");
//        }
//
//        //引擎配置
//        EngineConfiguration engineConfiguration = new EngineConfiguration();
//        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
//        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
//        engineConfiguration.setDetectFaceMaxNum(10);
//        engineConfiguration.setDetectFaceScaleVal(16);
//        //功能配置
//        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
//        functionConfiguration.setSupportAge(true);
//        functionConfiguration.setSupportFace3dAngle(true);
//        functionConfiguration.setSupportFaceDetect(true);
//        functionConfiguration.setSupportFaceRecognition(true);
//        functionConfiguration.setSupportGender(true);
//        functionConfiguration.setSupportLiveness(true);
//        functionConfiguration.setSupportIRLiveness(true);
//        engineConfiguration.setFunctionConfiguration(functionConfiguration);
//
//
//        //初始化引擎
//        errorCode = faceEngine.init(engineConfiguration);
//
//        if (errorCode != ErrorInfo.MOK.getValue()) {
//            System.out.println("初始化引擎失败");
//        }
//
//        //人脸检测1
//        ImageInfo imageInfo = getRGBData(new File("C:\\Users\\Administrator\\Desktop\\微信图片_20210420170631.jpg"));
//        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
//        errorCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
//        System.out.println(faceInfoList);
//        //特征提取1
//        FaceFeature faceFeature = new FaceFeature();
//        errorCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);
//        System.out.println("特征值大小：" + faceFeature.getFeatureData().length);
//
//        //人脸检测2
//        ImageInfo imageInfo2 = getRGBData(new File("C:\\Users\\Administrator\\Desktop\\身份证.png"));
//        List<FaceInfo> faceInfoList2 = new ArrayList<FaceInfo>();
//        errorCode = faceEngine.detectFaces(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(),imageInfo.getImageFormat(), faceInfoList2);
//        System.out.println(faceInfoList);
//
//        //特征提取2
//        FaceFeature faceFeature2 = new FaceFeature();
//        errorCode = faceEngine.extractFaceFeature(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(), imageInfo.getImageFormat(), faceInfoList2.get(0), faceFeature2);
//        System.out.println("特征值大小：" + faceFeature.getFeatureData().length);
//
//        //特征比对
//        FaceFeature targetFaceFeature = new FaceFeature();
//        targetFaceFeature.setFeatureData(faceFeature.getFeatureData());
//        FaceFeature sourceFaceFeature = new FaceFeature();
//        sourceFaceFeature.setFeatureData(faceFeature2.getFeatureData());
//        FaceSimilar faceSimilar = new FaceSimilar();
//
//        errorCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
//
//        System.out.println("相似度：" + faceSimilar.getScore());
//
//        //引擎卸载
//        errorCode = faceEngine.unInit();
//
//    }
//}
