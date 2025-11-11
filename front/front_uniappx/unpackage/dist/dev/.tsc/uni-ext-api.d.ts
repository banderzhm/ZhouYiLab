
interface Uni {
  getBatteryInfo: typeof import("@/uni_modules/uni-getbatteryinfo")["getBatteryInfo"]
  getBatteryInfoSync: typeof import("@/uni_modules/uni-getbatteryinfo")["getBatteryInfoSync"]
  openLocation: typeof import("@/uni_modules/uni-openLocation")["openLocation"]
  report: typeof import("@/uni_modules/uni-stat")["report"]
  onUserCaptureScreen: typeof import("@/uni_modules/uni-usercapturescreen")["onUserCaptureScreen"]
  offUserCaptureScreen: typeof import("@/uni_modules/uni-usercapturescreen")["offUserCaptureScreen"]
  setUserCaptureScreen: typeof import("@/uni_modules/uni-usercapturescreen")["setUserCaptureScreen"]
}
