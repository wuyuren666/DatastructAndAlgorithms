package com.wyr.dp.zuoshen.p1;

import jdk.nashorn.internal.ir.CallNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class HanZiTest {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int eachRowNum;
        double score;
        while(true) {
            List<Character> commonChineseCharacters = new ArrayList<>(); //词库
            List<String> tiKu = new ArrayList<>(); //题库
            eachRowNum=10;
            score = 0.0;
            loadCommonChineseCharacters(commonChineseCharacters);
            Collections.shuffle(commonChineseCharacters);
            generateTiKu(eachRowNum,commonChineseCharacters,tiKu);
            System.out.println("欢迎参加汉字练习小游戏！");
            Thread.sleep(2000);
            System.out.println("在六分钟内，录入汉字，录入正确得0.37分，共270个汉字,满分100。");
            Thread.sleep(2000);
            System.out.println("现在开始！");
            Thread.sleep(2000);
            long startTime = System.currentTimeMillis();
            long endTime = startTime + 6* 60 * 1000;

            for (int i = 0; i < tiKu.size(); i++) {
                List<Character> inCorrects = new ArrayList<>();
                String ti = tiKu.get(i);
                if (endTime - System.currentTimeMillis() < 1000) {
                    System.out.println("时间到！");
                    break;
                } else if (endTime - System.currentTimeMillis() < 6500) {
                    System.out.println("[题目:]  " + ti.substring(0, 5) + "<------------>" + "请一定按顺序逐字输入：");
                    System.out.print("请输入：  ");
                    long t1 = System.currentTimeMillis();
                    String userAnswer = scanner.nextLine();
                    long t2 = System.currentTimeMillis();
                    score += compareStrings(userAnswer, ti, inCorrects);
                    if (inCorrects.size() != 0) {
                        System.out.print("[统计:]" +"  耗时:"+ (t2 - t1) / 1000 + "秒" + "，输入错误汉字：");
                        for (Character inCorrect : inCorrects) {
                            System.out.print(inCorrect + " ");
                        }
                        System.out.println();
                        System.out.println();
                    } else {
                        System.out.println("[统计:]" + "  耗时:"+(t2 - t1) / 1000 + "秒" + "，输入无错误 ^_^");
                        System.out.println();
                    }
                } else {
                    System.out.println("[题目:]  " + ti + "<------------>" + "请一定按顺序逐字输入：");
                    System.out.print("请输入：  ");
                    long t1 = System.currentTimeMillis();
                    String userAnswer = scanner.nextLine();
                    long t2 = System.currentTimeMillis();
                    score += compareStrings(userAnswer, ti, inCorrects);
                    if (inCorrects.size() != 0) {
                        System.out.print("[统计:]" + "  耗时:"+(t2 - t1) / 1000 + "秒" + "，输入错误汉字：");
                        for (Character inCorrect : inCorrects) {
                            System.out.print(inCorrect + " ");
                        }
                        System.out.println();
                        System.out.println();
                    } else {
                        System.out.println("[统计:]" +"  耗时:"+ (t2 - t1) / 1000 + "秒" + "，输入无错误 ^_^");
                        System.out.println();
                    }
                }
                // 检查时间和得分
                if (System.currentTimeMillis() > endTime) {
                    System.out.println("时间到！");
                    break;
                }
            }

            System.out.println("游戏结束！");
            System.out.println("你的得分是: " + String.format("%.4f",score) + " / " + "100");
            System.out.println();
            Thread.sleep(5000);
            System.out.println("是否继续练习?，输入"+"yes"+"继续练习，按任意键退出");
            String s = scanner.nextLine();
            if("yes".equals(s)){
                System.out.println();
                continue;
            }else {
                break;
            }
        }
        scanner.close();
    }



    public static double compareStrings(String str1, String str2,List<Character> inCorrects) {
        double score=0.0;
        int i = 0;
        for (; i < str1.length(); i++) {
            try {
                if (str1.charAt(i) == str2.charAt(i)) {
                    score+=0.37;
                }else {
                    inCorrects.add(str2.charAt(i));
                }
            }catch (Exception e){
                return score;
            }
        }
        while (i<str2.length()){
            inCorrects.add(str2.charAt(i++));
        }
        return score;
    }

    public static void generateTiKu(int eachRowNum,List<Character> commonChineseCharacters,List<String> tiKu ){
        Collections.shuffle(commonChineseCharacters);
        StringBuilder sb=new StringBuilder();
        for (Character c:commonChineseCharacters) {
            sb.append(c);
            if(sb.length()==270) break;
        }
        String s = sb.toString();
        int length = s.length();
        for (int i = 0; i < length; i += eachRowNum) {
            if (i + eachRowNum <= length) {
                tiKu.add(s.substring(i, i + eachRowNum));
            } else {
                tiKu.add(s.substring(i));
                break;
            }
        }
    }

    public static void loadCommonChineseCharacters(List<Character> commonChineseCharacters) {
        Set<Character> set=new HashSet<>();
        String txt = "备品线天内开金以党党明铁外观教称土存美受眼她间办养比活资价派身社料取织拉调入号行结林平本科行全月拉列京线江社受际连备全已步走调又列计办约使她力车必更南级文路现行式实斯快所学容外天市利广公斗内资过往当高列然由革队平石示取江门次处议然各积教品并色争外音技成即然技增务办风期机做阶正则然气海包作自传革则万道合五定起效派声劳东界特办在水其利海京应程共存求四干计受表装外教车民车温利式规济单布约物研列声者化维际品学方复然温设解知意力济重其边放江县县化思提效利样表拉特比究带展切观省象角许取月间存温价革断品细决步斗持研手高省别解温华自思三口马设义意温四课了小不要里皮拍冬有个口叫家起早和丁来学校同鸟花开可捉摘叶秋到树只在刀旁护科容易夫席戏次延演位站秩序乱定肯桔刘岁朱房挂盏灯笼奖瓣急等最捆沙潮毫策速井沿际蜜峰引列莫斯附养谈派从采讶竿溜好众顽踩笨滚哄稳插喝骑缕踪粪萝卜拔猪幼循遮墩肤躯纱拄杖槐丈妻踮幛穆泣腮矫枫瑜遣渡幔硝硫栓叨哎辫叽喳烘裹滋润株俊俏剪凑的一是在人有我他们好来年大学国时和地到说生要出会可也你对能就着那里很没这个上们子时候去能过小没看得天起还里只事把想生样知道所家然种事成方多经么权法说于个给门最主调开院完可问必么几真学高而外少经经本和东么量间但力正有给即无性气制果做别面明两自已感信方者给间分进路走很命张等部原员工应位立军果月明目运经家事党议由务化制元务高解列老相于统别志四然所区接车联影完部真切斯重采重准行政整研究论科业自但织级安工史力安定治流关重要系展心计比受员内还存通学元明华业将技后力造物间次共战已先东动许再新受家影西省它被条流每机东业与法利南海容色真照增变加光受各也水取定发指再发事者法真加许数准面思五先然教东知风南决完如更身空计自等建美即完基表七相天政斯公府等报许战社提头总真队好新始便结切水比合动派新政内如管打共规圆今军群写才把教真在达单法手设内素合声证层向地社部用如及西系许今新劳深技化角问变教实话师思决速则走农采共参各效布决府果住存金空并美示至织和别技料列金部些领管光王都流四单公面住活术会位分眼活两正制意真教平越学什再分质制清二管每增入究使本高备规农省科准级低记持照金认组原率放布温二果最养学认们员则且已求劳年万至光中道基出指年准在油治总队北和行必到速局按手南设生究状办织平外教车民车温利式规济单布约物研列声者化维际品学方复然温设解知意力济重其边放江县县化思提效利样表拉特比究带展切观省象角许取月间存温价革断品细决步斗持研手高省别解温华自思三口马设义意温温院往较流定办号群研边料资明家习手切十养品共争证特青根持术指达业新派传个果争权都东油斗化流温快认信和方红受点新青增引市济传交位维活四社温济天北往矿向切高精速细价名海战每质更都行使受同提取水质持流内指基件计示流求张在半究术受光斯同党资观条际活流地连况求持速务治任厂东参至电养报新它劳通京流列指路设取矿外原议实化中经得求军才眼特许济其技位期再路放产劳林界个持增提布同马定治形接程采究生取党别结图传场次她设细派组矿选参求往传江包示况应达做存矿构每名斯科斯实样复品况队生业教增通然油达业色效参再程件参期展步金光名变次界持权往斯展增传过万始化道存明将制次江及造斯领命指分江传传自马期代应原光线设持华西求集当办车管学质取高志度层江解应列层工设照往则指取增设许真真因图设至整世再存装办规取级行品她列列历思次把具派果级历解分连前计北院度部总派特之样张办把活长制率设京总基根取江矿利做专发办习京亲书列外通才金办次争关经江生作规县统速国切江构列石步劳斗领定目起七究立状军次总形学术活图度规";
        for (int i = 0; i < txt.length(); i++) {
            set.add(txt.charAt(i));
        }
        for (Character character : set) {
            commonChineseCharacters.add(character);
        }
        Collections.shuffle(commonChineseCharacters);
    }
}

