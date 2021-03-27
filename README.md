# SignInSystem
Java课程设计 仿QQ界面 疫情打卡签到系统

## 界面展示
### 登录界面
![Image error](https://github.com/Hj7e2/SignInSystem/raw/master/演示图片/登录界面.png)
### 主界面
![Image error](https://github.com/Hj7e2/SignInSystem/raw/master/演示图片/主界面.png)
### 个人设置
![Image error](https://github.com/Hj7e2/SignInSystem/raw/master/演示图片/个人设置.png)

## 程序运行说明
* 使用数据库加载signinsystem.sql 创建数据库
* 修改dao包下DBAccess类下的参数
* 先运行server类
* 再运行client类

## 程序说明
> 大三课程设计 基于socket 没有使用任何框架 GUI全手绘代码全手打
* 界面完全模仿QQ
* 登录界面 注册和二维码功能没写(懒)
* 根据输入的账号获取头像(很模糊 使用gui重绘图像)
* 同样实现了吸附功能(拖到电脑边界隐藏)
* 主界面背景跟随个人信息的背景图改变(一定要点保存，图片会被自动拉伸)
* 登陆的账号分权限 在打卡、请假、历史记录模块的界面和功能不太一致
* 账号的获取 只能通过数据库插入信息
* 如果对方在线 提醒/请假消息能立即受到 否则在对方上线时才能收到
* 服务器端的功能除了启动停止关闭 都没有做(gu gu gu)
