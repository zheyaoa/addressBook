# 通讯录系统

> 基于SpringBoot构建的　通讯录系统

### 项目部署

### 项目结构

### 需求分析

>  ***要求为计算机系历届的各专业学生建一网上通讯录系统。学生必须先注册基本信息，通过管理员审核后才能登录该系统；之后学生便可完善自己的通讯录信息，且能查询到其他同学的通讯录信息；管理员可删除未通过审核的账户，禁用通过审核的账户，但无权修改学生的通讯录信息。学生通讯录信息应包括所读专业、所在班级、入校年份、毕业年份、就业单位、所在城市、联系方式、电子邮箱等，此外系统还可记录用户最近的一次登录时间以及登录系统的次数。（提示：管理员还可对系统的一些基本信息进行维护，比如专业信息管理，不要求实现，但可在设计中考虑）。***

* 用户需求
  1. 登入/注册功能
  2. 修改信息
  3. 查看通讯录列表
  4. 查询同学通讯录信息
* 管理员需求
  1. 审核账号
  2. 禁用账号

  3. 根据专业查询　用户信息

### 接口文档

>table User
>
>> * api/login	
>>
>>   * web => server
>>
>>   ```json
>>   data:{
>>       'userName':'6130116240',
>>       'password':'123456'
>>   }
>>   ```
>>
>>   * server => web 
>>
>>   ```json
>>   data:{
>>       'status':0|1|2|3|4 //审核未通过｜成功｜账号被禁用|未注册账号｜密码错误
>>   }
>>   ```
>>
>> * api/register
>>
>>   * web => server
>>
>>   ```json
>>   data:{
>>   	'userName':'6130116240',
>>       'password':'123456'
>>   }
>>   ```
>>
>>   * server => web
>>
>>   ```json
>>   data:{
>>       status:0|1|2  //网络错误｜成功|账号已被注册
>>   }
>>   ```
>>
>> * api/setUser
>>
>>   * web=>server
>>
>>   ```json
>>   data:{
>>       "name":"yyx",
>>       "birth":"1990-03-13",
>>       "profession":"计科",
>>       "class":"计科165",
>>       "entranceTime":"2016-09-01",
>>       "leaveTime":"2020-07-01",
>>       "employmentUnit":"ncu",
>>       "location":"jxnc",
>>       "photo":"???",
>>       "phoneNumber":"15477167971",
>>       "email":"123456789@qq.com",
>>       "description":"大家看法都是科技股份和肯定给合肥科技的身份科技合适的话"
>>   }
>>   ```
>>
>>   * server => web 
>>
>>   ```json
>>   data:{
>>       status:0|1|2 //网络错误｜修改成功|未登入
>>   }
>>   ```
>>
>> * api/getUserList   //获取好友列表
>>
>>   * web => server
>>
>>   ```
>>   
>>   ```
>>
>>   * server => web
>>
>>   ```json
>>   data:{
>>       [userName:'1234567890',name:'xyz',class:"计科165","status":0],
>>       [userName:'1234567890',name:'xyz',class:"计科165"]
>>   }
>>   ```
>>
>> * api/getUserByUserName
>>
>>   * web => server
>>
>>   ```json
>>   data:{
>>   	"userName":"6130116240"
>>   }
>>   ```
>>
>>   * server => web
>>
>>   ```json
>>   data:{
>>       'name':'yyx',
>>       'birth':'1990-03-13',
>>       'profession':'计科',
>>       'class':'计科165',
>>       'entranceTime':'2016-09-01',
>>       'leaveTime':'2020-07-01',
>>       'employmentUnit':"ncu",
>>       'location':"jxnc",
>>       "photo":"???",
>>       "phoneNumber":"15477167971",
>>       "email":"123456789@qq.com",
>>       "lastLogin":"2018-11-14",
>>       "loginTimes":"2",
>>       "description:"大家看法都是科技股份和肯定给合肥科技的身份科技合适的话"
>>   }
>>   ```
>>
>> * api/getUserListByApprovalStatus
>>
>>   * web => server 
>>
>>   ```json
>>   data:{
>>       approvalStatus:0|1|2 //未审核｜审核通过｜禁用
>>   }
>>   ```
>>
>> * api/examineAccount
>>
>>   * web => server 
>>
>>   ```json
>>   data:{
>>       userName:"123456789"
>>   }
>>   ```
>>
>>   * server => web
>>
>>   ```json
>>   data:{
>>       status:0|1|2  //网络错误｜成功|未登入
>>   }
>>   ```
>>
>> * api/disabledAccount 
>>
>>   - web => server 
>>
>>     ```json
>>     data:{
>>         userName:"123456789"
>>     }
>>     ```
>>
>>   - web => server 
>>
>>     ```json
>>     data:{
>>          status:0|1|2  //网络错误｜成功|未登入
>>     }
>>     ```
>>
>>

### 数据库设计

* User 表

<table>
    <tr>
        <th>属性列</th>
        <th>数据类型</th>
        <th>约束条件</th>
        <th>desc</th>
    </tr>
    <tr>
        <th>userName</th>
        <th>String</th>
        <th>主键</th>
        <th>用户名</th>
    </tr>
    <tr>
        <th>password</th>
        <th>String</th>
        <th>不可空</th>
        <th>密码</th>
    </tr>
    <tr>
        <th>approvalStatus</th>
        <th>Integer</th>
        <th>不可空</th>
        <th>审核状态　　0|1|2未审核|审核|禁用</th>
    </tr>
    <tr>
    	<th>name</th>
        <th>String</th>
        <th>可空</th>
        <th>姓名</th>
    </tr>
    <tr>
    	<th>birth</th>
        <th>date</th>
        <th>可空</th>
        <th>出生年月</th>
    </tr>
    <tr>
    	<th>profession</th>
        <th>Integer</th>
        <th>可空</th>
        <th>信息专业ID</th>
    </tr>
    <tr>
    	<th>className</th>
        <th>String</th>
        <th>可空</th>
        <th>班级</th>
    </tr>
    <tr>
    	<th>entranceTime</th>
        <th>date</th>
        <th>可空</th>
        <th>入校时间</th>
    </tr>
    <tr>
    	<th>leaveTime</th>
        <th>String</th>
        <th>可空</th>
        <th>离校时间</th>
    </tr>
    <tr>
    	<th>employmentUnit</th>
        <th>String</th>
        <th>可空</th>
        <th>就业单位</th>
    </tr>
    <tr>
    	<th>location</th>
        <th>String</th>
        <th>可空</th>
        <th>所在城市</th>
    </tr>
    <tr>
    	<th>photo</th>
        <th>String</th>
        <th>可空</th>
        <th>照片介绍</th>
    </tr>
    <tr>
    	<th>phoneNumber</th>
        <th>String</th>
        <th>可空</th>
        <th>联系方式</th>
    </tr>
    <tr>
    	<th>email</th>
        <th>String</th>
        <th>可空</th>
        <th>电子邮箱</th>
    </tr>
    <tr>
    	<th>lastLogin</th>
        <th>date</th>
        <th>可空</th>
        <th>最后登入</th>
    </tr>
    <tr>
    	<th>loginTimes</th>
        <th>String</th>
        <th>可空</th>
        <th>登入次数</th>
    </tr>
    <tr>
    	<th>description</th>
        <th>String</th>
        <th>可空</th>
        <th>自我介绍</th>
    </tr>
</table>	




