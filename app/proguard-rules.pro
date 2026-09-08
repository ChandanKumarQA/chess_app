# ===== KOTLIN =====
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {
    <fields>;
}

# ===== KOTLINX COROUTINES =====
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}
-dontwarn kotlinx.coroutines.**

# ===== FIREBASE FIRESTORE =====
# Keep model classes used by Firestore for serialization/deserialization
-keepattributes Signature
-keepattributes *Annotation*
-keepclassmembers class com.chessmaster.play.** {
    @com.google.firebase.firestore.PropertyName <fields>;
    @com.google.firebase.firestore.PropertyName <methods>;
}
-keep class com.google.firebase.firestore.** { *; }
-dontwarn com.google.firebase.**
-dontwarn com.google.android.gms.**

# ===== ADMOB =====
-dontwarn com.google.android.gms.ads.**

# ===== GOOGLE PLAY BILLING =====
-keep class com.android.billingclient.api.** { *; }
-dontwarn com.android.billingclient.**

# ===== COIL =====
-dontwarn coil.**

# ===== SERIALIZATION =====
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}
-keep,includedescriptorclasses class com.chessmaster.play.**$$serializer { *; }
-keepclassmembers class com.chessmaster.play.** {
    *** Companion;
}
-keepclasseswithmembers class com.chessmaster.play.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# ===== DATA MODELS (for Firebase/Firestore) =====
-keep class com.chessmaster.play.model.** { *; }
-keepclassmembers class com.chessmaster.play.model.** {
    <init>();
    <fields>;
}
-keep class com.chessmaster.play.data.** { *; }
-keepclassmembers class com.chessmaster.play.data.** {
    <init>();
    <fields>;
}

# ===== GENERAL =====
-keepattributes SourceFile,LineNumberTable
-keepattributes Exceptions
-renamesourcefileattribute SourceFile

# ===== WORKMANAGER / ROOM =====
-keep class * extends androidx.room.RoomDatabase { *; }
-keep class * extends androidx.work.impl.WorkDatabase { *; }
-keep class androidx.work.impl.WorkDatabase_Impl { *; }
