import _easycom_page_head from '@/components/page-head/page-head.vue';
const __sfc__ = defineComponent({
    __name: 'api-set-tabbar',
    emits: ['unmount'],
    setup(__props): any | null {
        const __ins = getCurrentInstance()!;
        const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
        const _cache = __ins.renderCache;
        const title = ref('tababr');
        const hasSetTabBarBadge = ref(false);
        const hasShownTabBarRedDot = ref(false);
        const hasCustomedStyle = ref(false);
        const hasCustomedItem = ref(false);
        const hasHiddenTabBar = ref(false);
        const hasHiddenTabBarItem = ref(false);
        const hasSetLongTitle = ref(false);
        onUnmounted(() => {
            if (hasSetTabBarBadge.value) {
                uni.removeTabBarBadge({
                    index: 1
                });
            }
            if (hasShownTabBarRedDot.value) {
                uni.hideTabBarRedDot({
                    index: 1
                });
            }
            if (hasHiddenTabBar.value) {
                uni.showTabBar();
            }
            if (hasCustomedStyle.value) {
                uni.setTabBarStyle({
                    color: '#7A7E83',
                    selectedColor: '#007AFF',
                    backgroundColor: '#F8F8F8',
                    borderStyle: 'black'
                });
            }
            if (hasCustomedItem.value) {
                let tabBarOptions = {
                    index: 1,
                    text: '接口',
                    iconPath: '/static/api.png',
                    selectedIconPath: '/static/apiHL.png'
                } as SetTabBarItemOptions;
                uni.setTabBarItem(tabBarOptions);
            }
            if (hasHiddenTabBarItem.value || hasSetLongTitle.value) {
                let tabBarOptions = {
                    visible: true,
                    index: 1,
                    text: '接口',
                    iconPath: '/static/api.png',
                    selectedIconPath: '/static/apiHL.png'
                } as SetTabBarItemOptions;
                uni.setTabBarItem(tabBarOptions);
            }
        });
        const setTabBarTitle = () => {
            let tabBarOptions = {
                visible: true,
                index: 1,
                text: '接口',
                iconPath: '/static/api.png',
                selectedIconPath: '/static/apiHL.png'
            } as SetTabBarItemOptions;
            if (!hasSetLongTitle.value) {
                tabBarOptions.text = "超长标题内容超长标题内容超长标题内容超长标题测试";
                tabBarOptions.iconPath = "";
                tabBarOptions.selectedIconPath = "";
            }
            else {
                tabBarOptions.text = "接口";
                tabBarOptions.iconPath = "/static/api.png";
                tabBarOptions.selectedIconPath = "/static/apiHL.png";
            }
            uni.setTabBarItem(tabBarOptions);
            hasSetLongTitle.value = !hasSetLongTitle.value;
        };
        const hideTabBarItem = () => {
            let tabBarOptions = {
                visible: true,
                index: 1,
                text: '接口',
                iconPath: '/static/api.png',
                selectedIconPath: '/static/apiHL.png'
            } as SetTabBarItemOptions;
            if (!hasHiddenTabBarItem.value) {
                tabBarOptions.visible = false;
            }
            else {
                tabBarOptions.visible = true;
            }
            uni.setTabBarItem(tabBarOptions);
            hasHiddenTabBarItem.value = !hasHiddenTabBarItem.value;
        };
        function emit(event: string, ...do_not_transform_spread: Array<any | null>) {
            __ins.emit(event, ...do_not_transform_spread);
        }
        const navigateBack = () => {
            emit('unmount');
        };
        const setTabBarBadge = () => {
            if (hasShownTabBarRedDot.value) {
                uni.hideTabBarRedDot({
                    index: 1
                });
                hasShownTabBarRedDot.value = !hasShownTabBarRedDot.value;
            }
            if (!hasSetTabBarBadge.value) {
                uni.setTabBarBadge({
                    index: 1,
                    text: '1'
                });
            }
            else {
                uni.removeTabBarBadge({
                    index: 1
                });
            }
            hasSetTabBarBadge.value = !hasSetTabBarBadge.value;
        };
        const showTabBarRedDot = () => {
            if (hasSetTabBarBadge.value) {
                uni.removeTabBarBadge({
                    index: 1
                });
                hasSetTabBarBadge.value = !hasSetTabBarBadge.value;
            }
            if (!hasShownTabBarRedDot.value) {
                uni.showTabBarRedDot({
                    index: 1
                });
            }
            else {
                uni.hideTabBarRedDot({
                    index: 1
                });
            }
            hasShownTabBarRedDot.value = !hasShownTabBarRedDot.value;
        };
        const hideTabBar = () => {
            if (!hasHiddenTabBar.value) {
                uni.hideTabBar();
            }
            else {
                uni.showTabBar();
            }
            hasHiddenTabBar.value = !hasHiddenTabBar.value;
        };
        const customStyle = () => {
            if (hasCustomedStyle.value) {
                uni.setTabBarStyle({
                    color: '#7A7E83',
                    selectedColor: '#007AFF',
                    backgroundColor: '#F8F8F8',
                    borderStyle: 'black',
                    // 新增 borderColor，优先级高于 borderStyle
                    // borderColor:'red'
                });
            }
            else {
                uni.setTabBarStyle({
                    color: '#FFF',
                    selectedColor: '#007AFF',
                    backgroundColor: '#000000',
                    borderStyle: 'black',
                });
            }
            hasCustomedStyle.value = !hasCustomedStyle.value;
        };
        const customItem = () => {
            let tabBarOptions = {
                index: 1,
                text: '接口',
                iconPath: '/static/api.png',
                selectedIconPath: '/static/apiHL.png'
            } as SetTabBarItemOptions;
            if (hasCustomedItem.value) {
                uni.setTabBarItem(tabBarOptions);
            }
            else {
                tabBarOptions.text = 'API';
                uni.setTabBarItem(tabBarOptions);
            }
            hasCustomedItem.value = !hasCustomedItem.value;
        };
        return (): any | null => {
            const _component_page_head = resolveEasyComponent("page-head", _easycom_page_head);
            return createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
                createVNode(_component_page_head, utsMapOf({ title: unref(title) }), null, 8 /* PROPS */, ["title"]),
                createElementVNode("button", utsMapOf({
                    class: "button",
                    onClick: setTabBarBadge
                }), toDisplayString(!unref(hasSetTabBarBadge) ? '设置tab徽标' : '移除tab徽标'), 1 /* TEXT */),
                createElementVNode("button", utsMapOf({
                    class: "button",
                    onClick: showTabBarRedDot
                }), toDisplayString(!unref(hasShownTabBarRedDot) ? '显示红点' : '移除红点'), 1 /* TEXT */),
                createElementVNode("button", utsMapOf({
                    class: "button",
                    onClick: customStyle
                }), toDisplayString(!unref(hasCustomedStyle) ? '自定义Tab样式' : '移除自定义样式'), 1 /* TEXT */),
                createElementVNode("button", utsMapOf({
                    class: "button",
                    onClick: customItem
                }), toDisplayString(!unref(hasCustomedItem) ? '自定义Tab信息' : '移除自定义信息'), 1 /* TEXT */),
                createElementVNode("button", utsMapOf({
                    class: "button",
                    onClick: hideTabBar
                }), toDisplayString(!unref(hasHiddenTabBar) ? '隐藏TabBar' : '显示TabBar'), 1 /* TEXT */),
                createElementVNode("button", utsMapOf({
                    class: "button",
                    onClick: hideTabBarItem
                }), toDisplayString(!unref(hasHiddenTabBarItem) ? '隐藏接口Item' : '显示接口Item'), 1 /* TEXT */),
                createElementVNode("button", utsMapOf({
                    class: "button",
                    onClick: setTabBarTitle
                }), toDisplayString(!unref(hasSetLongTitle) ? '自定义超长标题' : '移除自定义信息'), 1 /* TEXT */),
                createElementVNode("view", utsMapOf({ class: "btn-area" }))
            ]);
        };
    }
});
export default __sfc__;
export type ApiSetTabbarComponentPublicInstance = InstanceType<typeof __sfc__>;
const GenComponentsApiSetTabbarApiSetTabbarStyles = [utsMapOf([["button", padStyleMapOf(utsMapOf([["marginTop", 15], ["marginLeft", 0], ["marginRight", 0]]))], ["btn-area", padStyleMapOf(utsMapOf([["paddingTop", 15]]))]])];
//# sourceMappingURL=api-set-tabbar.vue.map