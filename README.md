# NestedScrollingDemo

NestedScrollingDemo 是一款帮助理解 Android NestedScrolling 机制的最佳实战项目。通过学习该项目你可以了解：

- 传统事件分发机制实现嵌套滑动的局限性。
- 谷歌 NestedScrolling 与 NesetdScrolling2 机制的原理实现。
- NestedScrollingChild 与 NestedScrollingParent 实战。
- NestedScrollingChild2 与 NestedScrollingParent2 实战。
- CoordinatorLayout 与 Behavior 实战。
- CoordinatorLayout 与 AppBarLayout 配合使用例子。
- CoordinatorLayout 与 AppBarLayout 、CollapsingToolbarLayout 三者配合使用例子。

## 项目中展示的例子

### 传统事件分发机制处理嵌套滑动 🐣

<img src = "https://upload-images.jianshu.io/upload_images/2824145-bbfad8e448c64369.gif?imageMogr2/auto-orient/strip|imageView2/2/w/768" width =33% />
  
相关类：

- [NestedTraditionActivity](https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/ui/nested/NestedTraditionActivity.java)
- [NestedTraditionLayout](https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/view/NestedTraditionLayout.java)

### NestedScrolling 与 NestedScrolling2 机制 🐘

<table>

 <tr>
    <td> NestedScrolling 机制</td>
    <td> NestedScrolling2 机制</td>
    <td> NestedScrolling2 机制实战例子</td>
</tr>

<tr>
    <td>
        <img src = "https://upload-images.jianshu.io/upload_images/2824145-bd2f335f1bc836f6.gif?imageMogr2/auto-orient/strip|imageView2/2/w/768"/>
    </td>
    <td>
        <img src = "https://upload-images.jianshu.io/upload_images/2824145-c349758a58c31a70.gif?imageMogr2/auto-orient/strip|imageView2/2/w/768"/>
    </td>
    <td>
        <img src ="https://upload-images.jianshu.io/upload_images/2824145-43906a69074a562b.gif?imageMogr2/auto-orient/strip|imageView2/2/w/768"/>
    </td>
</tr>

<tr>
    <td>
        <ul>
            <li>
             <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/ui/nested/NestedScrollingParentActivity.java">NestedScrollingParentActivity</a>
            </li>
            <li>
             <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/ui/nested/normal_form/NestedScrollingChildView.java">NestedScrollingChildView</a>
            </li>
             <li>
             <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/view/NestedScrollingParentLayout.java">NestedScrollingParentLayout</a>
            </li>
        </ul>
    </td>
    <td>
         <ul>
            <li>
             <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/ui/nested/NestedScrollingParent2Activity.java">NestedScrollingParent2Activity</a>
            </li>
            <li>
             <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/ui/nested/normal_form/NestedScrollingChild2View.java">NestedScrollingChild2View</a>
            </li>
             <li>
             <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/view/NestedScrollingParent2Layout.java">NestedScrollingParent2Layout</a>
            </li>
        </ul>
    </td>
     <td>
        <ul>
            <li>
             <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/ui/nested/NestedScrolling2DemoActivity.java">NestedScrolling2DemoActivity</a>
            </li>
             <li>
             <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/view/StickyNavLayout.java">StickyNavLayout</a>
            </li>
        </ul>
    </td>
</tr>
</table>

### CoordinatorLayout 与 Behavior、AppBarLayout、CollapsingToolbarLayout 🐠

<table>
    <tr>
        <td>自定义 Behavior 事件拦截与处理</td>
        <td>自定义 Behavior 测量与布局</td>
        <td>Behavior 嵌套滑动交互效果</td>
    </tr>
    <tr>
        <td> 
            <img src = "https://upload-images.jianshu.io/upload_images/2824145-20393bdaea298a65.gif?imageMogr2/auto-orient/strip|imageView2/2/w/768"/>
        </td>
        <td> 
            <img src = "https://upload-images.jianshu.io/upload_images/2824145-9a7e4b5c1ff161ce.gif?imageMogr2/auto-orient/strip"/>
        </td>
        <td> 
            <img src = "https://upload-images.jianshu.io/upload_images/2824145-dd0938819defe740.gif?imageMogr2/auto-orient/strip"/>
        </td>
    </tr>
    <tr>
        <td>
            <ul>
                 <li> 
                    <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/ui/cdl/CoordinatorLayoutDemo1Activity.java">CoordinatorLayoutDemo1Activity</a>
                </li>
                <li> 
                    <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/view/DependedView.java">DependedView</a>
                </li>
                <li> 
                    <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/ui/cdl/behavior/BrotherChameleonBehavior.java">BrotherChameleonBehavior</a>
                </li>
                <li> 
                    <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/ui/cdl/behavior/BrotherFollowBehavior.java">BrotherFollowBehavior</a>
                </li>
            <ul>
        </td>
        <td>
            <ul>
                 <li> 
                    <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/ui/cdl/CoordinatorLayoutDemo3Activity.java">CoordinatorLayoutDemo3Activity</a>
                </li>
                <li> 
                    <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/ui/cdl/behavior/MeasureLayoutBehavior.java">MeasureLayoutBehavior</a>
                </li>
            </ul>
        </td>
        <td>
           <ul>
                <li> 
                    <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/ui/cdl/CoordinatorLayoutDemo4Activity.java">CoordinatorLayoutDemo4Activity</a>
                </li>
                <li> 
                    <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/ui/cdl/behavior/NestedHeaderBehavior.java">NestedHeaderBehavior</a>
                </li>
                <li> 
                    <a href ="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/ui/cdl/behavior/ScrollingViewBehavior.java">ScrollingViewBehavior</a>
                </li>
            </ul>
        </td>
    </tr>
</table>

### CoordinatorLayout 与 AppBarLayout、CollapsingToolbarLayout 🎉

<table>
    <tr>
        <td>CoordinatorLayout 与 AppBarLayout 结合使用</td>
        <td>CoordinatorLayout 与 AppBarLayout、CollapsingToolbarLayout 结合使用</td>
    </tr>
    <tr>
        <td>
        <img src = "https://upload-images.jianshu.io/upload_images/2824145-35945dc8352afe76.gif?imageMogr2/auto-orient/strip"/>
        </td>
        <td>
          <img src="https://upload-images.jianshu.io/upload_images/2824145-6bd61616f3164373.gif?imageMogr2/auto-orient/strip"/>
        </td>
    </tr>
    <tr>
        <td>
            <ul>
                <li>
                 <a href="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/ui/abl/CdlWithAppBarActivity.java">CdlWithAppBarActivity</a>
                </li>
            </ul>
        </td>
        <td>
            <ul>
                <li>
                 <a href="https://github.com/AndyJennifer/NestedScrollingDemo/blob/master/app/src/main/java/com/jennifer/andy/nestedscrollingdemo/ui/abl/CdlWithAppBarWithCollActivity.java">CdlWithAppBarWithCollActivity</a>
                </li>
            </ul>
        </td>
    </tr>
</table>

## 了解更多

对项目中的代码有疑惑，可以查看文章：

- [自定义View事件之进阶篇(一)-NestedScrolling(嵌套滑动)机制](https://juejin.im/post/5d3e639e51882508dc163606)
- [自定义View事件篇进阶篇(二)-自定义NestedScrolling实战](https://juejin.im/post/5d3e860be51d454f6f16ecf0)
- [自定义View事件篇进阶篇(三)-CoordinatorLayout与Behavior](https://juejin.im/post/5d430c5a6fb9a06aeb109d56)
- [自定义View事件之进阶篇(四)-自定义Behavior实战](https://juejin.im/post/5d43be5af265da03c81501a1)

## 最后

如果你觉得项目不错，欢迎点击 star ❤️或 follow，也可以帮忙分享给你更多的朋友。你的支持与鼓励是给我继续做好该项目的最大动力。

## 联系我

- QQ:443696320
- 简书：[AndyandJennifer](https://www.jianshu.com/users/921c778fb5e1/timeline)
- 掘金：[AndyandJennifer](https://juejin.im/user/5acc1ea06fb9a028bc2e0fc1)
- Email: [andyjennifer@126.com](andyjennifer@126.com)

