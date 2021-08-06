package com.sqq;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
@RunWith(SpringRunner.class)
class ConcurrentProgramApplicationTests {

    @Test
    public  String wipeRegex(String str, String strPattern) {
        Pattern pattern = Pattern.compile(strPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll("");
    }

    @Test
    public void ss(){
        String str = "<p class=\"ql-align-justify\">一个现代化国家必然是法治国家，一支现代化军队必然是法治军队。人民军队在中国特色强军之路上迈出坚实步伐，事业生机勃发又井然有序，离不开以法治理念引领、以法治方式推进、以法治力量保障。</p><p>\n" +
                "<img src=\"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=670328284,1476163699&amp;fm=173&amp;s=92A1D9A420D33BF9D6B891370300C081&amp;w=608&amp;h=350&amp;img.JPEG\">\n" +
                "</p><p><span style=\"color: rgb(51, 51, 51);\">依法治军、从严治军，是我们党建军治军的基本方略。对这个问题，习主席一直看得很重。“依法治军、从严治军是强军之基”“推进强军事业、建设强大军队，没有法治引领和保障不行”“军队法治建设不抓紧，到时候就跟不上趟了”“军队越是现代化，越是信息化，越是要法治化”……党的十八大以来，习主席站在实现中国梦强军梦的战略高度，深刻阐述了依法治军、从严治军的基础性、全局性、战略性地位。把依法治军、从严治军体现为党的意志，纳入依法治国总体布局，既明晰战略定位、作出顶层设计，又明确目标要求、规划方法路径；擘画我军法治建设的总蓝图，描绘全面依法治国的“军事篇”、实现强军目标的“法治篇”、中国特色军事法治建设的“创新篇”……党的领袖、军队统帅以尊崇法治的战略清醒、厉行法治的历史担当，开创了法治军队建设崭新局面。</span></p><p>\n" +
                "<img src=\"https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=594584437,2368874318&amp;fm=173&amp;s=C2907D834E9A1ECC329049B6010080A2&amp;w=640&amp;h=427&amp;img.JPEG\">\n" +
                "</p><p class=\"ql-align-justify\">时间是真理的忠实听众。五年来，全军按照习主席决策指示，紧紧围绕党在新形势下的强军目标，着眼全面加强军队革命化现代化正规化建设，坚持党对军队绝对领导，坚持战斗力标准，坚持官兵主体地位，狠抓依法治军、从严治军方针落实。从健全完善军事法规制度体系，到加大军事法规执行力度；从强化军队法治工作机构职能作用，到大力培塑军事法治文化；从在法治轨道上积极稳妥推进国防和军队改革，到以法治手段推进作风建设常治长效……人民军队建军治军方式，发生了一场深刻的历史变革。法治的春风劲吹，强军之魂铸得更牢、强军之要聚力更准、强军之基夯得更实。</p><p><br></p><p>\n" +
                "<img src=\"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3085511270,3669715249&amp;fm=173&amp;s=0ECA7223099045ED0B544DD20100C0B0&amp;w=400&amp;h=248&amp;img.JPEG\">\n" +
                "</p><p class=\"ql-align-justify\">法治信仰、法治思维日益深入人心。“首先要让法治精神、法治理念深入人心，使全军官兵信仰法治、坚守法治。没有这一条，依法治军、从严治军是难以推进的。”遵循习主席强调的“法治良方”，各级深入开展宣传教育、立体培塑法治精神。广大官兵把法治内化为政治信念和道德修养，外化为行为准则和自觉行动，法治意识不断增强、法治信仰不断强固。忠实崇尚法治、坚定捍卫法治，成为官兵的共同追求。办事依法、遇事找法、解决问题用法、化解矛盾靠法的思维习惯正在形成。</p><p class=\"ql-align-justify\">治军理念、治军方式发生深刻转变。各项建设纳入法治轨道，各项活动严守法治标准。过去那种以言代法、以权压法的长官意志，那种“黑头不如红头、红头不如白头、白头不如口头”的人治思维，那种“一个将军一个令”、搞土政策土规定的顽瘴痼疾，失去了滋生的土壤、没有了存在的空间。习主席提出的“三个根本性转变”在全军落地推开，工作循于法、秩序统于法、忙乱止于法的崭新气象已经呈现，党委依法决策、机关依法指导、部队依法行动、官兵依法履职的良好局面初步形成。</p><p class=\"ql-align-justify\">依法治官、依法治权取得显著成效。习主席鲜明指出：“依法治军关键是依法治官、依法治权。”各级坚持依法设定权力、规范权力、制约权力、监督权力，推行权力清单，法无授权不可为；明确责任清单，法定职责必须为。领导干部带头学法尊法守法用法，谋划工作运用法治思维，处理问题运用法治方式，做到在法治之下而不是法治之外、更不是法治之上想问题、作决策、办事情，正确用权、谨慎用权、干净用权更加自觉。</p><p class=\"ql-align-justify\">“法令既行，纪律自正，则无不治之国，无不化之民。”站在新的历史起点，把法治军队建设推向更高水平、更高境界，需要有“永远在路上”的坚守、“不到长城非好汉”的坚持。我们坚信，在以习近平同志为核心的党中央坚强领导下，只要全军更加坚定地推行依法治军、从严治军，善织法纪之网、强化法治之力，就一定能把我军建成一支律令如山、能打胜仗的精兵劲旅。</p>";
        String s = wipeRegex(str, "<img[^>]*>");
        System.out.println(s);
    }

    @Test
    public void cc(){
        File inputWord = new File("C:\\Users\\Administrator\\Desktop\\宋奇奇-年总结.docx");
        File outputFile = new File("C:\\Users\\Administrator\\Desktop\\宋奇奇-年总结.pdf");
//        try  {
//            InputStream docxInputStream = new FileInputStream(inputWord);
//            OutputStream outputStream = new FileOutputStream(outputFile);
//            IConverter converter = LocalConverter.builder().build();
//            boolean execute = converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
//            outputStream.close();
//            System.out.println("success");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
