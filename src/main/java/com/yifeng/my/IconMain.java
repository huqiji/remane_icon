package com.yifeng.my;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.extra.pinyin.PinyinUtil;

import java.io.File;
import java.util.List;

/**
 * TODO
 *
 *
 *
 *
 * @Author RockeyCai
 * @Date: 2023/2/22 9:08
 */
//drop table if exists icon;
/*==============================================================*/
/* Table: icon                                                  */
/*==============================================================*/
/*
create table icon
(
i_id                 bigint not null auto_increment comment '图标主键',
it_id                bigint comment '图标类型id',
i_name               varchar(100) comment '图标名称',
i_path               varchar(100) comment '图标路径',
i_state              int comment '状态（1显示）',
i_sort               int comment '排序',
primary key (i_id)
);
alter table icon comment '图标';
*/
public class IconMain {

    public static void main(String[] args) {


        IconMain iconMain = new IconMain();
        iconMain.readPathFile();


    }

    /**
     * 读取文件
     */
    public void readPathFile(){

        String tyPath = "F:\\share\\icon\\通用";
        String newTyPath = "F:\\share\\icon\\ty";
        createYh(tyPath , newTyPath , 1);

        String rootPath = "F:\\share\\icon\\银行";
        String newRootPath = "F:\\share\\icon\\bank";
        createYh(rootPath , newRootPath , 2);

        String wlPath = "F:\\share\\icon\\网络";
        String newWlPathPath = "F:\\share\\icon\\wl";
        createYh(wlPath , newWlPathPath , 3);


    }


    public void createYh(String rootPath, String  newRootPath , int type){
        FileUtil.del(newRootPath);

        List<String> icons = FileUtil.listFileNames(rootPath);
        for (String icon:icons ) {

            if(icon.indexOf(".png") <= -1){
                continue;
            }

            String iconPath = rootPath + File.separator + icon;
            String name = FileNameUtil.mainName(iconPath);
            String extName = FileNameUtil.extName(iconPath);
            String pinyin = PinyinUtil.getFirstLetter(name , "");
            String path = pinyin + "@3x." + extName;
            String newPath = newRootPath + File.separator + path;

            FileUtil.copy(iconPath ,newPath , false);


            String insert = String.format("INSERT INTO `icon`(it_id,i_name,i_path,i_state ) VALUES ( %d, '%s', '%s', 1);" , type,name,pinyin,1);
            System.out.println(insert);

        }
    }


}
