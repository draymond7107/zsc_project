package com.zsc.constants;
/**
 * 功能说明：<br>
 * 模块名称：lottery<br>
 * 功能描述：edu<br>
 * 文件名称: com.zsc.constants<br>
 * 系统名称：彩票<br>
 * 软件著作权：-[.com]版权所有<br>
 * 开发人员：icelove(lujun) <br>
 * 开发时间：2019-01-2019/1/23 11:14<br>
 * 系统版本：1.0.0<br>
 */
public interface Constants {
    String RET = "ret";
    String MSG = "msg";

    int ZERO			= 0;
    double ZERO_DOUBLE	= 0D;

    int ONE				= 1;

    int YES = 1;
    int NO	= 0;

    Integer objYes = YES;
    Integer objNo = NO;
    Integer objTwo = 2;

    boolean TRUE	= true;
    boolean FALSE	= false;


    String ADMIN_LOG_KEY			= "_ADMIN_LOG_KEY";
    String ADMIN_LOG_USER_ID_KEY	= "_ADMIN_USERID_KEY";

    String ADMIN_SESSION_KEY		= "ADMIN_PERTECH";
    String ADMIN_DEFAULT_PASSWORD	= "123-123";//后台新建用户默认密码

    String USER_SESSION_KEY			= "USER_PERTECH";

    //立功项目
    //立功项目:保存 deleted
    int LG_PROJECT_USE              =1;
    //立功项目:删除 deleted
    int LG_PROJECT_NOTUSE           =0;
    // 未审核  state
    int LG_STATE_WAIT_CHECK         =10;
    //同意
    int LG_STATE_AGREE_CHECK        =20;
    //驳回
    int LG_STATE_NOT_AGREE_CHECK    =30;


    //安康杯赛事
    //安康杯赛事:保存
    int AKB_PROJECT_USE             =1;
    //安康杯赛事:删除
    int AKB_PROJECT_NOTUSE          =0;


    //技能赛事
    //赛事保存  delete
    int JN_PROJECT_USE              =1;
    //赛事删除 delete
    int JN_PROJECT_NOTUSE           =0;
    //赛事申请  state
    int JN_PROJECT_STATE_APPLY     =10;
    //赛事审核同意 state
    int JN_PROJECT_STATE_AGREE     =20;
    //赛事审核拒绝  state
    int JN_PROJECT_STATE_REFUSE    =30;
    //赛事发布   state
    int JN_PROJECT_STATE_PUBLISH   =40;


     //赛事-用户：
    //用户未报名
     int USER_NOT_APPLY_MATCH       =0;
    //用户报名赛事初始未删除状态(默认状态)  state=10  delete=1
    //赛事创建的最高级别审核直接赋值20
    int USER_APPLY_MATCH            =10;
    //基层工会审核通过 4
    int COMPANY_USER_CHECK_AGREE    =12;
    //街道乡镇审核通过 3
    int STREET_USER_CHECK_AGREE     =14;
    //区县级会审核通过 2
   int COUNT_USER_CHECK_AGREE       =16;
    //市总工会审核通过 1
   int CITY_USER_CHECK_AGREE        =18;
    //同意用户报名 state
    int USER_CHECK_AGREE            =20;

    //驳回用户报名  state
    int USER_CHECK_NOT_AGREE        =30;

    //用户取消报名  stste
    int USER_CANCLE_MATCH           =-1;

    //管理员删除赛事报名  delete=-1
    int ADMIN_DELETE_USER_MATCH     =-1;

    //用户删除报名   delete=-2
    int USER_DELETE_MATCH_REJIST    =-2;

    //报名用户保存
    int MATCH_USER_RESERVE          =1;


    //用户级别
    //普通用户
    int DOMESTIC_USER               =0;
    //vip用户用户
    int VIP_USER                    =1;
    //svip用户
    int SVIP_USER                   =2;

    //技能竞赛个人报名
    int USER_REGISTER               =1;
    //企业统一报名
    int COMPANY_REGISTER            =2;

    String SYSTEM_MODULE="base:基础模块,user:用户管理,sys:系统管理,lg:立功竞赛,akb:安康杯,match:赛事管理";

    //Banner
    //banner展示
    int BANNER_STATE_USE            =1;
    //banner隐藏
    int BANNER_STATE_BOTUSE         =0;
    //本公会创建
    int IS_MY_UNION_CREATE          =0;
    //直属机构 DirectlyUnion
    int IS_DIRECT_UNION             =1;
    // 非直属机构
    int IS_NOT_DIRECT_UNION         =-1;
    //辖区机构  Indirect
    int IS_INDIRECT_UNION           =1;
    //非辖区机构
    int IS_NOT_INDIRECT_UNION       =-1;


    //精英赛
    int CLASSIC_MATCH               =1;
    //群英赛
    int JENERAL_MATCH               =2;


    //公告
    //直属可见y
    int ANNOUNCE_DIRECTLY_VIEW      =1;
    //辖区可见
    int ANNOUNCE_INDIRECT_VIEW      =2;

}
