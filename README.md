# ArgPolygonView
Android Custom ViewGroup Library

Builds: [![](https://jitpack.io/v/mergehez/ArgPolygonView.svg)](https://jitpack.io/#mergehez/ArgPolygonView)

## Gradle
To always build from the latest commit with all updates. Add the JitPack repository:

(path:\to\your\projects\MainFolderOfYourProject\build.gradle)
```
repositories {
    maven { url "https://jitpack.io" }
}
```
Add the following dependency:

```
dependencies {
	compile 'com.github.mergehez:ArgPolygonView:-SNAPSHOT'
}
```


## How to use

### Xml
```xml
<com.arges.sepan.argPolygonView.ArgPolygonView
        android:id="@+id/myView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#fff"
        android:padding="20dp"
        app:rotate="0"
        app:childs_height="75dp"
        app:childs_width="75dp"
        app:use_center="true">
        
        ...child views...
        
</com.arges.sepan.argPolygonView.ArgPolygonView>
```

----
### An Example
```java
int rotate, childHeight, childWidth;
boolean useCenter;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final ArgPolygonView polygonView = (ArgPolygonView)findViewById(R.id.myView);

    childHeight = polygonView.getChildHeight();
    childWidth = polygonView.getChildWidth();
    rotate = polygonView.getRotate();
    useCenter = polygonView.getUseCenter();
    findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            polygonView.setChildHeight((int)(childHeight *1.2));
            polygonView.setChildWidth((int)(childWidth *1.5));
            polygonView.setRotate(rotate += 15);
            polygonView.setUseCenter(useCenter = !useCenter);
            polygonView.applyChanges();
        }
    });
}
```


## Images
### Usage in Android Studio:
![Usage in Android Studio](https://raw.githubusercontent.com/mergehez/ArgPolygonView/master/ScreenShots/StudioCapture.png "Usage in Android Studio")
---
### ScreenShots:
![](https://raw.githubusercontent.com/mergehez/ArgPolygonView/master/ScreenShots/screenshot.PNG "")
![](https://raw.githubusercontent.com/mergehez/ArgPolygonView/master/ScreenShots/screenshot2.PNG "")
---
### ArgPolygonView's detailed appearance
![](https://raw.githubusercontent.com/mergehez/ArgPolygonView/master/ScreenShots/Polygon.png "")
---
### The logic of placing child views
![](https://raw.githubusercontent.com/mergehez/ArgPolygonView/master/ScreenShots/PolygonDetail.png "")

