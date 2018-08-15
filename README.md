﻿<p align="center">
  <img align="middle" src="https://raw.githubusercontent.com/zingat/rateme-android/readmebranch/art/ratemelogo.png">
</p>

<p align="center">
  <a href="https://bintray.com/zingatmobil/Rateme/rateme/1.2.0">
    <img src="https://api.bintray.com/packages/zingatmobil/Rateme/rateme/images/download.svg">
  </a>
  <a target="_blank" href="https://android-arsenal.com/api?level=15">
    <img src="https://img.shields.io/badge/API-15%2B-orange.svg">
  </a>
</p>

RateMe is a powerful system to get rates from users in Android applications.

RateMe takes cares to show garish dialogs to collect rates from users so you don't have to.
One thing you do is to define some rules and send events to RateMe library.

# GRADLE DEPENDENCY
The minimum API level supported by this library is API 15.
Add the dependency to your `build.gradle`:

```Gradle
dependencies {
    implementation 'com.zingat:rateme:1.3.0'
}
```

#Quick start

### Initialize `RateMe` in your `Application` class
```kotlin
   class App : Application() {
   
       override fun onCreate() {
           super.onCreate()
   
           Rateme.getInstance(this@App)
                   .addCondition("touch_me_event", 3)
                   .reminderDuration(3)
       }
   
   }
```

Now we are telling RateMe that you can show a dialog when 3 times `touch_me_event` is sent.

### How `touch_me_event` is sent to RateMe.
```kotlin
    Rateme.getInstance(this)
                .addEvent("touch_me_event")
```

The event can be sent for each statement. For example when a user opens the app 4 times you can send `app_opened` event or user can like 
a product in your app 2 times you can send `product_liked` event. For all statements you can send event seperately.

The default appearance is like picture below.

<p align="center">
  <img align="middle" src="https://raw.githubusercontent.com/zingat/rateme-android/readmebranch/art/defaultRatemeDialogWindow.png">
</p>

`When dialog shown on screen, user have to choose one of these options to close the window. 
To touch background and back button don't close the window.`

# How Buttons works

### Rate Us

RateMe library detects your applicationId(for example com.zingat.rateme) and
 When user clicks the `Rate Us` button, your app's Google Play page opens automaticly 
 and user can rate your app easily.
 
### Remind Me Later

When user clicks the `Remind Me Later` button, the dialog disappears until the days finished 
you defined in initialization code. `reminderDuration(day:Int)` is used define the necessary time.
The parameter is given in days.

### Don't Ask Again

When user clicks the `Don't Ask Again` button, the dialog disappears and never appear again.

# Arranging delay time

RateMe supports to arrange the delay time
`delay(time : Long)` indicates the time to display dialog after all events completed.
Default value is 0.


```kotlin
    Rateme.getInstance(this@App)
                       .addCondition("touch_me_event", 3)
                       .reminderDuration(3)
                       .delay(1000)
```
 
# Callbacks

To know when the user selects an action button, you set callbacks:

```kotlin
    Rateme.getInstance(this@App)
                 .addCondition("touch_me_event", 3)
                 .delay(1000)
                 .onRateCallback( object : RMEventCallback{
                     override fun onEvent() {
                         // TODO
                     }
                 })
                 .onDontAskCallback(object : RMEventCallback{
                     override fun onEvent() {
                         // TODO
                     }
                 })
                 .onRemindLaterCallback(object : RMEventCallback{
                     override fun onEvent() {
                         // TODO
                     }
                 })
                 .onShowCallback(object : RMEventCallback{
                     override fun onEvent() {
                         // TODO
                     }
                 })
                 .onRMCallback(object : RMCallback{
                     override fun onEvent(eventName: String, count: Int, which: Int) {
                         // TODO
                     }
                 })
  ```
If you are listening for all three action buttons, you could just use `onRMCallback()`.
 
 * `eventName (String)` parameter tells completed event name. In our case this is `touch_me_event`
 * `count (Int)` parameter tells completed event count value. In our case this is `3`. 
 The count value is defined by developer in `addCondition()` method.
 * `which (Int)` parameter tells which action is happening. Each number indicates different state.
    * STARTED = -1
    * POSITIVE = 0
    * NEUTRAL = 1
    * NEGATIVE = 2
   
# Custom Views

// Not customView için örnek fotoğraf çekilip yüklenmedi. custom() metodu çalıştırıldktan sonra
// ekran görüntüsü alıp img kısmına koyacağız.
// Ardından custom ekrana buton vs. konulmaması için uyarı koyacağız çünkü buradan gelecek etkileşimler için
// callback almıyoruz. Amacımız rateme dialogu göstermek.

Custom views are very easy to implement.

```kotlin
    Rateme.getInstance(this@App)
                    .addCondition("touch_me_event", 3)
                    .reminderDuration(3)
                    .delay(1000)
                    .custom(R.layout.layout_dialog)
```

After custom view is added appearance is like picture below. 

<p align="center">
  <img align="middle" src="https://raw.githubusercontent.com/zingat/rateme-android/readmebranch/art/customImageDialogWindow.png">
</p>

When `custom()` method is used, default title and content disappears. Only the you layout file will display on screen.

It is recommended not to use buttons and different type views in custom layout.
Because you can not provide callbacks for buttons. 

# Custom Buttons

You can use `customButton()` and `customButtonReverse()` methods to provide pretty much colored buttons.

```kotlin
    Rateme.getInstance(this@App)
                 .addCondition("touch_me_event", 3)
                 .delay(1000)
                 .custom(R.layout.layout_dialog)
                 .customButton()
  ```

`customButton()` and `customButtonReverse()` methods shouldn't be used together. 
They have different type interfaces.
   
After `custombutton()` is added appearance is like picture below. 

<p align="center">
  <img align="middle" src="https://raw.githubusercontent.com/zingat/rateme-android/readmebranch/art/customButtonDialogWindow.png">
</p>

You can use the `customButtonReverse()` method with same way.

# Changing colors, and texts

You can change all values by creating new color items with same name in your applications.

``colors.xml``
````xml
<resources>
    
    <!--Default background color for all buttons. It is active when customButton() is used.-->
    <color name="rm_defaultButtonBackground">#fff</color>
    
    <!--Default text color for buttons. It is become active when customButtonReverse() is used.-->
    <color name="rm_defaultTextColor">#fff</color>
    

    <!--Text, Border and Background color for Rate Us Button-->
    <color name="rm_BtnRateTextColor">#02a8fe</color>
    

    <!--Text, Border and Background color for Remind Me Later Button-->
    <color name="rm_BtnLaterTextColor">#4bca5e</color>
    
    <!--Text, Border and Background color for Don't Ask Again Button-->
    <color name="rm_BtnNeverTextColor">#ff6175</color>
    
</resources>
````

``strings.xml``

````xml
<resources>
    <!--Default text for Rate Us button-->
    <string name="rateme_btn_rate_text">Rate us</string>
    

    <!--Default text for Remind Me Later button-->
    <string name="rateme_btn_later_text">Remind Me Later</string>
    

    <!--Default text for Don't Ask again button-->
    <string name="rateme_btn_never_text">Don\'t ask again</string>
    
    <!--Default dialog title. It is deactive when custom() method is used!-->
    <string name="rateme_dialog_title">How was your experience?</string>
    
    <!--Default dialog context. It is deactive when custom() method is used!-->
    <string name="rateme_dialog_message">Recommend us to others by rating us on Play Store</string>
    

</resources>
````









  


