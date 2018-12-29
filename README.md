**bmadmin-commons** 是BmAdmin 项目的一个分支
<br/>
是一个常用工具类的集合.没什么技术含量,纯粹是为了方便,欢迎大家来丰富.
<br/>
很多新人找不到靠谱方法,被网上不靠谱帖子各种坑,部分老人开新项目需要各种复制黏贴,特适合使用此工具类.

**maven 地址：**

`<dependency>`  
`<groupId>com.github.bomberjin</groupId>`   
`<artifactId>bmadmin-commons</artifactId>`
    `<version>1.2.0</version>`  
    `</dependency>`

**版本信息：**
- 最新版本：1.2.0 
    - 升级手机号校验规则
    - 增加验证码校验(6位数字)
    - 增加身份证格式校验
    - 增加DES 加解密
    - 增加rsa 加解密
- 1.1.0
     - 增加新工具类：DataSourceUtil （动态链接数据库及动态执行sql语句）;
    - 规范部分类名称;      
- 历史版本：1.0.0 

**项目结构**
bmadmin-commons  
|-HttpUtil  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------doGet  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------doPost  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------doPut  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------doDelete  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------buildUrl  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------wrapClient  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------sslClient  
|-MathUtil  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------Plus(两数相乘后保留两位小数)  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------add(加)  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------sub(减)  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------mul(乘)  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------div(除)  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------round(四舍五入)  
|-RandomUtil  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------getUUID(获取不带-的uuid)  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------randomChar(随机生成由 0-9 a-z A-Z 组合的字符串)  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------getRandmonVerifyCode(生成验证码)  
|-SecurityUtil  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------Md5With32(32 位md5 加密)  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------Md5With16(16 位md5 加密)  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------BASE64Decrypt(base 64 解密)    
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------BASE64Encrypt(base 64 加密)      
|-SwitchUtil  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------numFormat(数字转换为中文大写数字)    
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------NulltoString(null 转 空字符串)    
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------BASE64Encrypt(null 转 0)    
|-TimerUtil  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------getNowTimeForString(获取当前时间 yyyy-MM-dd HH:mm:ss，可传参获取指定返回格式)     
|-VerifyUtil  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------isNumber(判断是否为数字)     
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------isNumberDotTwo(判断是否带小数点后两位)     
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------isNotNull(判断一个字符串是否为空)     
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------isNullOrEmpty(判断传入对象是否为空)     
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------checkMobileNumber(验证手机号码)     
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------is18ByteIdCardComplex(18位身份证校验,比较严格校验)     
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------isMobileVerification(是否为验证码(6位数字))     
|-DataSourceUtil  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------getQueryResult(动态连接数据库，并执行sql查询语句)   
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------getUpdateResult(动态连接数据库，并执行sql非查询语句)   
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------resultSetToJson(配合 getQueryResult 方法，将ResultSet 转换为json)   
|-RSAEncrypt  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------encrypty(加密 RSA1024) 
|-RSADecrypt  
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------decrypty(解密 RSA1024) 
|-DESedeUtil 
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------encode(加密 ) 
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|------decrypt(解密 RSA1024)  