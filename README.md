# 我的知乎，既有知识，又有美图

## 目录
1. [简介](#简介)
1. [截图](#截图)
1. [知识](#可以学到)
1. [依赖](#使用到的库)
1. [支持](#支持我)

## 简介
这是一个仿知乎的客户端，使用了知乎和干货集中营的api,
使用了 [material design](http://wiki.jikexueyuan.com/project/material-design/) 设计语言
运用了 [MVP](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0202/2397.html) 模式实现，网络请求部分使用了当前流行的retrofir + RXjava + okhttp,使用GSON解析数据，glide获取图片，butterknife自动生成view注解等，
项目做了较多的注释，非常适合初学者学习运用

## 截图

<a href="./screenshots/device-2016-07-09-141603.png"><img src="./screenshots/device-2016-07-09-141603.png" width="40%"/></a><a href="./screenshots/device-2016-07-09-141642.png"><img src="./screenshots/device-2016-07-09-141642.png" width="40%"/></a>
<a href="./screenshots/device-2016-07-09-141743.png"><img src="./screenshots/device-2016-07-09-141743.png" width="40%"/></a><a href="./screenshots/device-2016-07-09-141816.png"><img src="./screenshots/device-2016-07-09-141816.png" width="40%"/></a>
<a href="./screenshots/device-2016-07-09-141854.png"><img src="./screenshots/device-2016-07-09-141854.png" width="40%"/></a><a href="./screenshots/device-2016-07-09-141958.png"><img src="./screenshots/device-2016-07-09-141958.png" width="40%"/></a>



## 可以学到
- Android Design库的使用
- 自定义你的基类Activity，Fragment
- 应用MVP模式到项目中
- 使用retrofit2 + Rxjava + OKhttp配合请求网络
- 用jackson解析网络数据
- 用Glide加载图片
- 用PhotoView缩放图片
- 用RecyclerView实现瀑布流及多种布局的实践
- 使用协调布局实现Tab,FAB,Toolbar动态隐藏与上下滑动
- 使用最简单的方法实现社会化分享功能
- 更多惊喜等待你发现（手动斜眼）

## 使用到的库
````
   dependencies {
       compile fileTree(dir: 'libs', include: ['*.jar'])
       testCompile 'junit:junit:4.12'
       compile 'com.android.support:appcompat-v7:23.4.0'
       compile 'com.android.support:design:23.4.0'
       compile 'com.android.support:support-v4:23.4.0'
       compile 'com.android.support:cardview-v7:23.4.0'
       compile 'com.jakewharton:butterknife:7.0.1'
       compile 'com.squareup.retrofit2:retrofit:2.0.0-beta4'
       compile 'com.squareup.retrofit2:adapter-rxjava:2.0.0-beta4'
       compile 'io.reactivex:rxjava:1.1.0'
       compile 'io.reactivex:rxandroid:1.1.0'
       compile 'com.github.bumptech.glide:glide:3.7.0'
       compile 'com.squareup.retrofit2:converter-jackson:2.0.0-beta4'
       compile 'com.commit451:PhotoView:1.2.4'
       compile 'de.hdodenhof:circleimageview:2.1.0'
   }
 
  ``` 
  
  
  
  
## 支持我
如果觉得我的项目对您有用，请随意打赏。您的支持将鼓励我继续创作！  


<a href="./screenshots/pay.png"><img src="./screenshots/pay.png" width="40%"/>