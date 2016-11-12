# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#-------------------------------------------定制化区域----------------------------------------------
#---------------------------------1.实体类---------------------------------
-keep class com.glooory.calligraphy.entity.** { *; }
-keep class com.glooory.calligraphy.activities.MainActivity {
    void getWriteExternalYes();
    void getWriteExternalNo();
}

-keep class com.glooory.calligraphy.activities.UniversalActivity {
    void getWriteExternalYes();
    void getWriteExternalNo();
}


#-------------------------------------------------------------------------

#---------------------------------2.第三方包-------------------------------
-dontwarn com.squareup.okhttp.**

-dontwarn it.neokree.materialtabs.**
-keep class it.neokree.materialtabs.{*;}

-dontwarn com.orhanobut.logger.**
-keep class com.orhanobut.logger.{*;}

## okhttp
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.{*;}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# picasso
-keep class com.squareup.picasso.** {*; }
-dontwarn com.squareup.picasso.**

-dontwarn com.squareup.**
-dontwarn okio.**

#retrofit
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-dontwarn okio.**

# copyright zhonghanwen
#-------------------------------------------基本不用动区域--------------------------------------------
#---------------------------------基本指令区----------------------------------
-optimizationpasses 5
-dontskipnonpubliclibraryclassmembers
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
#继承activity,application,service,broadcastReceiver,contentprovider....不进行混淆
#---------------------------------2.第三方包-------------------------------
#retrofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-dontwarn okhttp3.**
-keep class okhttp3.** { *;}
-dontwarn okio.*
-keep class okio.** { *; }
# fresco
# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

# Keep native methods
-keepclassmembers class * {
    native <methods>;
}

-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

##---------------End: proguard configuration for Gson  ----------

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# rxjava
-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# 友盟统计分析
-keepclassmembers class * { public <init>(org.json.JSONObject); }
-keepclassmembers enum com.umeng.analytics.** {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 友盟自动更新
-keepclassmembers class * { public <init>(org.json.JSONObject); }
-keep public class cn.irains.parking.cloud.pub.R$*{ public static final int *; }
-keep public class * extends com.umeng.**
-keep class com.umeng.** { *; }

-dontwarn com.chad.library.adapter.**
-keep class com.chad.library.adapter.** { *; }

-dontwarn com.orhanobut.logger.**
-keep class com.orhanobut.logger.** { *; }

-dontwarn com.jakewharton.rxbinding.**
-keep class com.jakewharton.rxbinding.** { *; }

-dontwarn com.yanzhenjie.permission.**
-keep class com.yanzhenjie.permission.** { *; }

-dontwarn uk.co.senab.**
-keep class uk.co.senab.** { *; }
