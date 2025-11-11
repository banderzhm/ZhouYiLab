export const chooseLocationPlugin = () => {
	uni.chooseLocation({
    latitude: 39.951372,
    longitude: 116.39747,
    keyword: '公园',
    success(res){
      __f__('log','at uni_modules/uni-chooseLocation-plugin/utssdk/app-ios/index.uts:7','chooseLocation in plugin complete success', res);
    },
    fail(err){
      __f__('log','at uni_modules/uni-chooseLocation-plugin/utssdk/app-ios/index.uts:10','chooseLocation in plugin complete fail', err);
    },
    complete(res){
      __f__('log','at uni_modules/uni-chooseLocation-plugin/utssdk/app-ios/index.uts:13','chooseLocation in plugin complete res', res);
    }
  })
};
