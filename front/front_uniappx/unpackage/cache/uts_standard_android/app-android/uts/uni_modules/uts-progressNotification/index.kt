@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uts.sdk.modules.utsProgressNotification
import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import io.dcloud.uniapp.*
import io.dcloud.uniapp.extapi.*
import io.dcloud.uniapp.framework.*
import io.dcloud.uniapp.runtime.*
import io.dcloud.uniapp.vue.*
import io.dcloud.uniapp.vue.shared.*
import io.dcloud.unicloud.*
import io.dcloud.uts.*
import io.dcloud.uts.Map
import io.dcloud.uts.Set
import io.dcloud.uts.UTSAndroid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import uts.sdk.modules.utsProgressNotification.R
open class CreateNotificationProgressOptions (
    open var title: String? = null,
    @JsonNotNull
    open var content: String,
    @JsonNotNull
    open var progress: Number,
    open var onClick: (() -> Unit)? = null,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("CreateNotificationProgressOptions", "uni_modules/uts-progressNotification/utssdk/interface.uts", 1, 13)
    }
}
open class FinishNotificationProgressOptions (
    open var title: String? = null,
    @JsonNotNull
    open var content: String,
    open var onClick: () -> Unit,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("FinishNotificationProgressOptions", "uni_modules/uts-progressNotification/utssdk/interface.uts", 21, 13)
    }
}
val ACTION_DOWNLOAD_FINISH = "ACTION_DOWNLOAD_FINISH"
val ACTION_DOWNLOAD_PROGRESS = "ACTION_DOWNLOAD_PROGRESS"
@JvmField
var globalNotificationProgressCallBack: (() -> Unit)? = fun(){}
@JvmField
var globalNotificationProgressFinishCallBack: (() -> Unit)? = fun(){}
fun setGlobalNotificationProgressCallBack(callBack: (() -> Unit)?): Unit {
    globalNotificationProgressCallBack = callBack
}
fun getGlobalNotificationProgressCallBack(): (() -> Unit)? {
    return globalNotificationProgressCallBack
}
fun setGlobalNotificationProgressFinishCallBack(callBack: (() -> Unit)?): Unit {
    globalNotificationProgressFinishCallBack = callBack
}
fun getGlobalNotificationProgressFinishCallBack(): (() -> Unit)? {
    return globalNotificationProgressFinishCallBack
}
open class TransparentActivity : Activity, IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("TransparentActivity", "uni_modules/uts-progressNotification/utssdk/app-android/TransparentActivity.uts", 9, 14)
    }
    constructor() : super() {}
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.fullScreen(this)
        val action = this.getIntent().getAction()
        if (action == ACTION_DOWNLOAD_FINISH) {
            setTimeout(fun(){
                getGlobalNotificationProgressFinishCallBack()?.invoke()
                setGlobalNotificationProgressFinishCallBack(fun(){})
            }
            , 100)
            this.overridePendingTransition(0, 0)
        }
        if (action == ACTION_DOWNLOAD_PROGRESS) {
            setTimeout(fun(){
                getGlobalNotificationProgressCallBack()?.invoke()
                setGlobalNotificationProgressCallBack(fun(){})
            }
            , 100)
            this.overridePendingTransition(0, 0)
        }
        setTimeout(fun(){
            this.finish()
        }
        , 20)
    }
    @Suppress("DEPRECATION")
    private fun fullScreen(activity: Activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= 21) {
                val window = activity.getWindow()
                val decorView = window.getDecorView()
                val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                decorView.setSystemUiVisibility(option)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.setStatusBarColor(Color.TRANSPARENT)
            } else {
                val window = activity.getWindow()
                val attributes = window.getAttributes()
                val flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                attributes.flags = attributes.flags or flagTranslucentStatus
                window.setAttributes(attributes)
            }
        }
    }
}
val DOWNLOAD_PROGRESS_NOTIFICATION_ID: Int = 7890
val DC_DOWNLOAD_CHANNEL_ID = "下载文件"
val DC_DOWNLOAD_CHANNEL_NAME = "应用升级更新"
var notificationBuilder: Notification.Builder? = null
var timeId: Number = -1
var histroyProgress: Number = 0
var isProgress = false
fun createNotificationProgress(options: CreateNotificationProgressOptions): Unit {
    val content = options.content
    val progress = options.progress
    val onClick = options.onClick
    if (progress == 100) {
        clearTimeout(timeId)
        val context = UTSAndroid.getAppContext() as Context
        realCreateNotificationProgress(options.title ?: getAppName(context), content, progress, onClick)
        reset()
        return
    }
    histroyProgress = progress
    if (timeId != -1) {
        return
    }
    val context = UTSAndroid.getAppContext() as Context
    if (!isProgress) {
        realCreateNotificationProgress(options.title ?: getAppName(context), content, histroyProgress, onClick)
        isProgress = true
    } else {
        timeId = setTimeout(fun(){
            realCreateNotificationProgress(options.title ?: getAppName(context), content, histroyProgress, onClick)
            timeId = -1
        }
        , 1000)
    }
}
fun cancelNotificationProgress(): Unit {
    val context = UTSAndroid.getAppContext() as Context
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.cancel(DOWNLOAD_PROGRESS_NOTIFICATION_ID)
    reset()
}
fun realCreateNotificationProgress(title: String, content: String, progress: Number, cb: (() -> Unit)?): Unit {
    setGlobalNotificationProgressCallBack(cb)
    val context = UTSAndroid.getAppContext() as Context
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    createDownloadChannel(notificationManager)
    val builder = createNotificationBuilder(context)
    builder.setProgress(100, progress.toInt(), false)
    builder.setContentTitle(title)
    builder.setContentText(content)
    builder.setContentIntent(createPendingIntent(context, ACTION_DOWNLOAD_PROGRESS))
    notificationManager.notify(DOWNLOAD_PROGRESS_NOTIFICATION_ID, builder.build())
}
fun finishNotificationProgress(options: FinishNotificationProgressOptions) {
    setGlobalNotificationProgressFinishCallBack(options.onClick)
    val context = UTSAndroid.getAppContext() as Context
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    createDownloadChannel(notificationManager)
    val builder = createNotificationBuilder(context)
    builder.setProgress(0, 0, false)
    builder.setContentTitle(options.title ?: getAppName(context))
    builder.setContentText(options.content)
    builder.setAutoCancel(true)
    builder.setContentIntent(createPendingIntent(context, ACTION_DOWNLOAD_FINISH))
    notificationManager.notify(DOWNLOAD_PROGRESS_NOTIFICATION_ID, builder.build())
    reset()
}
fun reset() {
    isProgress = false
    notificationBuilder = null
    histroyProgress = 0
    if (timeId != -1) {
        clearTimeout(timeId)
        timeId = -1
    }
}
fun createPendingIntent(context: Context, action: String): PendingIntent {
    val i = Intent(action)
    i.setComponent(ComponentName(context.getPackageName(), "uts.sdk.modules.utsProgressNotification.TransparentActivity"))
    var flags = PendingIntent.FLAG_ONE_SHOT
    if (Build.VERSION.SDK_INT >= 23) {
        flags = PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
    }
    return PendingIntent.getActivity(context, DOWNLOAD_PROGRESS_NOTIFICATION_ID, i, flags)
}
fun createDownloadChannel(notificationManager: NotificationManager) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(DC_DOWNLOAD_CHANNEL_ID, DC_DOWNLOAD_CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW)
        notificationManager.createNotificationChannel(channel)
    }
}
@Suppress("DEPRECATION")
fun createNotificationBuilder(context: Context): Notification.Builder {
    if (notificationBuilder == null) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder = Notification.Builder(context, DC_DOWNLOAD_CHANNEL_ID)
        } else {
            notificationBuilder = Notification.Builder(context)
        }
        notificationBuilder!!.setSmallIcon(context.getApplicationInfo().icon)
        notificationBuilder!!.setOngoing(true)
        notificationBuilder!!.setSound(null)
    }
    return notificationBuilder!!
}
@Suppress("DEPRECATION")
fun getAppName(context: Context): String {
    var appName = ""
    try {
        val packageManager = context.getPackageManager()
        val applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0)
        appName = packageManager.getApplicationLabel(applicationInfo) as String
    }
     catch (e: Exception) {
        e.printStackTrace()
    }
    return appName
}
