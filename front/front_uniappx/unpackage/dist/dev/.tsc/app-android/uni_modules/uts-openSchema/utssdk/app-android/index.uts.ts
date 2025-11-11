import Intent from 'android.content.Intent'
import Uri from 'android.net.Uri'
import { OpenSchema, CanOpenURL } from '../interface.uts'

export const openSchema : OpenSchema = function (url : string) {
  if (canOpenURL(url)) {
    const context = UTSAndroid.getUniActivity()!
    const uri = Uri.parse(url)
    const intent = new Intent(Intent.ACTION_VIEW, uri)
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent.setData(uri)
    context.startActivity(intent)
  } else {
    __f__('error','at uni_modules/uts-openSchema/utssdk/app-android/index.uts:14','[uts-openSchema] url param Error：', JSON.stringify(url))
  }
}

export const canOpenURL : CanOpenURL = function (url : string) : boolean {
  if (typeof url === 'string' && url.length > 0) {
    const context = UTSAndroid.getUniActivity()!
    const uri = Uri.parse(url)
    const intent = new Intent(Intent.ACTION_VIEW, uri)
    return intent.resolveActivity(context.packageManager) != null ? true : false
  } else {
    return false
  }
}
