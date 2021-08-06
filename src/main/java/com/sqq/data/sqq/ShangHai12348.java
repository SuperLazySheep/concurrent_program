package com.sqq.data.sqq;

import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author sqq
 */
public class ShangHai12348 {

    public static void main(String[] args) throws IOException {
//        String filePath = "F:/lawers/上海法网/organization.xlsx";
        getData("F:\\lawers\\上海法网\\organization.xlsx");
//        List<Organization> list = new ArrayList();
//        Organization organization = new Organization();
//        organization.setAddress("11");
//        organization.setTel("18091314787");
//        organization.setName("shabaksja");
//        organization.setStreet("难啊");
//        organization.setArea("dakdnak");
//        list.add(organization);
//        ExcelWriter writer = ExcelUtil.getBigWriter(filePath);
//        //自定义标题
//        writer.addHeaderAlias("area","区");
//        writer.addHeaderAlias("street","街道");
//        writer.addHeaderAlias("organizationType","机构类型");
//        writer.addHeaderAlias("name","机构名称");
//        writer.addHeaderAlias("tel","机构电话");
//        writer.addHeaderAlias("address","机构地址");
//        writer.addHeaderAlias("longitudeGD","经度高德");
//        writer.addHeaderAlias("latitudeGD","纬度高德");
//
//        // 一次性写出内容，使用默认样式，强制输出标题
//        writer.write(list);
//        // 关闭writer，释放内存
//        writer.close();
    }

    public static void getData(String filePath) throws IOException {
        ArrayList<String> areaList = new ArrayList<>();
        //区
        String firstUrl = "https://sh.12348.gov.cn/service/rest/orgstruct.Map/collection/mapgroupdata?";
        String s = HttpUtil.get(firstUrl);
        ObjectMapper objectMapper = new ObjectMapper();
        Iterator<JsonNode> elements = objectMapper.readTree(s).elements();
        while(elements.hasNext()){
            JsonNode code = elements.next().get("code");
            String s1 = code.asText();
            if(!s1.equals("310151")){
                areaList.add(s1);
            }
        }
        ArrayList<CodeCount> streetList = new ArrayList<>();
        //街道
        String secondUrl = "https://sh.12348.gov.cn/service/rest/orgstruct.Map/collection/mapgroupdata?&zoneCode=310104&pageSize=-1&typeId=1309ace1040f457cb27f48d3b66e13cf";
        areaList.forEach( area ->{
            String s1 = HttpUtil.get("https://sh.12348.gov.cn/service/rest/orgstruct.Map/collection/mapgroupdata?&zoneCode=" + area + "&pageSize=-1&typeId=1309ace1040f457cb27f48d3b66e13cf");
            try {
                Iterator<JsonNode> streetElement = objectMapper.readTree(s1).elements();
                while(streetElement.hasNext()){
                    CodeCount codeCount = new CodeCount();
                    JsonNode s2 = streetElement.next();
                    codeCount.setStreetCode(s2.get("code").asText());
                    codeCount.setCount(s2.get("count").asInt());
                    codeCount.setAreaCode(area);
                    streetList.add(codeCount);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // 机构
        List<Organization> list =  new ArrayList<>();
        for (CodeCount codeCount : streetList) {
            String s1 = "https://sh.12348.gov.cn/service/rest/orgstruct.Map/collection/mapdata?&" +
                    "zoneCode=" + codeCount.getAreaCode() + "&typeId=1309ace1040f457cb27f48d3b66e13cf&towncode=" + codeCount.getStreetCode() + "&pageSize=" + codeCount.getCount() + "&page=1";
            String organizationStr = HttpUtil.get(s1);
            try {
                JsonNode jsonNode = objectMapper.readTree(organizationStr);
                Iterator<JsonNode> elements1 = jsonNode.elements();
                while (elements1.hasNext()) {
                    JsonNode next = elements1.next();
                    Organization organization = new Organization();
                    JsonNode properties = next.get("properties");
                    //机构类型
                    organization.setOrganizationType(next.get("typeName").asText());
                    // 区
                    organization.setArea(properties.get("zoneName").asText());
                    // 街道
                    organization.setStreet(properties.get("township").asText());
                    // 名称
                    organization.setName(properties.get("name").asText());
                    // 电话
                    organization.setTel(properties.get("phone").asText());
                    // 地址
                    organization.setAddress(properties.get("address").asText());

//                    //经度
//                    if(properties.has("longitude")){
//                        organization.setLongitude(properties.get("longitude").asDouble());
//                    }
//                    //纬度
//                    if(properties.has("latitude")){
//                        organization.setLatitude(properties.get("latitude").asDouble());
//                    }
                    organization.setLongitudeGD(properties.get("longitudeGD").asText());
                    organization.setLatitudeGD(properties.get("latitudeGD").asText());
//                    FileUtil.writeFile(filePath, objectMapper.writeValueAsString(organization) + "\r\n", true, "utf-8");
                    list.add(organization);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ExcelWriter writer = ExcelUtil.getBigWriter(filePath);
        //自定义标题
        writer.addHeaderAlias("area","区");
        writer.addHeaderAlias("street","街道");
        writer.addHeaderAlias("organizationType","机构类型");
        writer.addHeaderAlias("name","机构名称");
        writer.addHeaderAlias("tel","机构电话");
        writer.addHeaderAlias("address","机构地址");
        writer.addHeaderAlias("longitudeGD","经度高德");
        writer.addHeaderAlias("latitudeGD","纬度高德");

        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list);
        // 关闭writer，释放内存
        writer.close();
    }


}
