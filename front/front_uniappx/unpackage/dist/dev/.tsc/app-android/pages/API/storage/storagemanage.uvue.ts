type StorageItem = { __$originalPosition?: UTSSourceMapPosition<"StorageItem", "pages/API/storage/storagemanage.uvue", 91, 7>;
		key : string
		value : string
		type : string
	}
	type StorageList = Array<StorageItem>
	
const __sfc__ = defineComponent({
  __name: 'storagemanage',
  setup(__props, { expose: __expose }: SetupContext): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

	const storageList = ref([] as StorageItem[])
	const newKey = ref('')
	const newValue = ref('')
	const isEditing = ref(false)
	const editIndex = ref(-1)
	const detailItem = ref({ key: '', value: '', type: '' } as StorageItem)
	const editKey = ref('')
	const editValue = ref('')
	const editValueType = ref('Number')
	const showDetailDialog = ref(false)
	const showEditDialog = ref(false)
	const valueTypeOptions = ['String', 'Number', 'Boolean', 'Object', 'Array']
	const valueTypeDefaultMap = new Map<string, string>()
	valueTypeDefaultMap.set('String', '')
	valueTypeDefaultMap.set('Number', '1')
	valueTypeDefaultMap.set('Boolean', 'true')
	valueTypeDefaultMap.set('Object', `{"name": "张三","age": 12}`)
	valueTypeDefaultMap.set('Array', `[1, "hello", true, { "key": "value" }]`)
  // 自动化测试使用
	const isTestMode = ref(false)

	function getStorageList() : StorageList {
		const list : StorageList = []
		const storageInfo = uni.getStorageInfoSync()
		storageInfo.keys.forEach((key : string) => {
			const value = uni.getStorageSync(key)
			let strValue : string | null = null
			let typeStr : string = typeof value
			if (value != null) {
				if (typeStr == 'object') {
					const jsonStr = JSON.stringify(value)
					strValue = jsonStr
					if (Array.isArray(UTSAndroid.consoleDebugError(JSON.parse(jsonStr), " at pages/API/storage/storagemanage.uvue:129"))) {
						typeStr = 'Array'
					} else {
						typeStr = 'Object'
					}
				} else if (typeStr == 'boolean') {
					strValue = value == true ? 'true' : 'false'
					typeStr = 'Boolean'
				} else if (typeStr == 'number') {
					strValue = value.toString()
					typeStr = 'Number'
				} else {
					strValue = value.toString()
					typeStr = 'String'
				}
			}
			if (strValue != null) {
				list.push({
					key: key,
					value: strValue,
					type: typeStr
				})
			}
		})
		return list
	}

	function setStorage(key : string, value : any) {
		try {
			uni.setStorageSync(key, value)
		} catch (e) {
			console.error('Storage set error:', e, " at pages/API/storage/storagemanage.uvue:160")
		}
	}
	function removeStorage(key : string) {
		try {
			uni.removeStorageSync(key)
		} catch (e) {
			console.error('Storage remove error:', e, " at pages/API/storage/storagemanage.uvue:167")
		}
	}
	function clearStorage() {
		try {
			uni.clearStorageSync()
		} catch (e) {
			console.error('Storage clear error:', e, " at pages/API/storage/storagemanage.uvue:174")
		}
	}
	function getStorage(key : string) : string | null {
		try {
			const value = uni.getStorageSync(key)
			return value != null ? value.toString() : null
		} catch (e) {
			console.error('Storage get error:', e, " at pages/API/storage/storagemanage.uvue:182")
			return null
		}
	}

	function refreshList() {
		const list = getStorageList()
		console.log('list: ',list, " at pages/API/storage/storagemanage.uvue:189");
		if (!isEditing.value && editKey.value != '') {
			const idx = list.findIndex(item => item.key === editKey.value)
			if (idx > 0) {
				const spliced = list.splice(idx, 1)
				if (spliced.length > 0) {
					list.unshift(spliced[0])
				}
			}
		}
		storageList.value = list
	}

	function truncate(value : string) : string {
		if (value.length > 100) {
			return value.slice(0, 100) + '...'
		}
		return value
	}

	function showDetail(item : StorageItem) {
		detailItem.value = item
		showDetailDialog.value = true
	}
	function closeDetail() {
		showDetailDialog.value = false
	}
	function openEditDialogNew() {
		editKey.value = ''
		editValueType.value = 'String'
		editValue.value = valueTypeDefaultMap.get('String') ?? ''
		isEditing.value = false
		editIndex.value = -1
		showEditDialog.value = true
	}
	function openEditDialogEdit(item : StorageItem, index : number) {
		editKey.value = item.key
		editValue.value = item.value
		isEditing.value = true
		editIndex.value = index
		editValueType.value = valueTypeOptions.indexOf(item.type) >= 0 ? item.type : 'String'
		showEditDialog.value = true
		closeDetail()
	}
	function saveEdit() {
		if (!isTestMode.value && (editKey.value.trim() === '' || editValue.value.trim() === '')) {
			uni.showModal({
				title: '提示',
				content: 'Key 和 Value 都不能为空',
				showCancel: false,
			})
			return
		}
		let storeValue : any
		switch (editValueType.value) {
			case 'Number':
				storeValue = parseFloat(editValue.value)
				break
			case 'Boolean':
				storeValue = (editValue.value === 'true' || editValue.value === '1')
				break
			case 'Object':
				try {
					const obj = UTSAndroid.consoleDebugError(JSON.parse(editValue.value), " at pages/API/storage/storagemanage.uvue:252")
					storeValue = obj as UTSJSONObject
				} catch {
					storeValue = {} as UTSJSONObject
				}
				break
			case 'Array':
				try {
					const arr = UTSAndroid.consoleDebugError(JSON.parse(editValue.value), " at pages/API/storage/storagemanage.uvue:260") as Array<any>
					storeValue = arr // 直接存储数组
				} catch {
					storeValue = [] as Array<any>
				}
				break
			default:
				storeValue = editValue.value
		}
		if (editIndex.value >= 0) {
			const oldItem = storageList.value[editIndex.value]
			if (oldItem.key != editKey.value) {
				removeStorage(oldItem.key)
			}
			setStorage(editKey.value, storeValue)
		} else {
			setStorage(editKey.value, storeValue)
		}
		refreshList()
		isEditing.value = false
		editIndex.value = -1
		editKey.value = ''
		editValue.value = ''
		editValueType.value = 'String'
		showEditDialog.value = false
	}
	function closeEdit() {
		showEditDialog.value = false
	}
	function handleDelete(index : number) {
		if (index >= 0 && index < storageList.value.length) {
			const item = storageList.value[index]
			removeStorage(item.key)
			refreshList()
			if (isEditing.value && editIndex.value == index) {
				isEditing.value = false
				editIndex.value = -1
				editKey.value = ''
				editValue.value = ''
			}
		}
	}
	function confirmDelete(index : number) {
    // 自动化测试时不显示模态弹窗
		if (!isTestMode.value) {
			uni.showModal({
				title: '确认操作',
				content: '确定要删除该项吗？',
				showCancel: true,
				cancelText: '取消',
				confirmText: '确定',
				success: (res) => {
					if (res.confirm) {
						showDetailDialog.value = false
						handleDelete(index)
					}
				}
			})
		} else {
			showDetailDialog.value = false
			handleDelete(index)
		}
	}
	function handleClear() {
		clearStorage()
		refreshList()
		isEditing.value = false
		editIndex.value = -1
		editKey.value = ''
		editValue.value = ''
	}
	function confirmClear() {
		// 自动化测试时不显示模态弹窗
		if (!isTestMode.value) {
			uni.showModal({
				title: '确认操作',
				content: '确定要清空所有数据吗？',
				showCancel: true,
				cancelText: '取消',
				confirmText: '确定',
				success: (res) => {
					if (res.confirm) {
						showDetailDialog.value = false
						handleClear()
					}
				}
			})
		} else {
			showDetailDialog.value = false
			handleClear()
		}
	}
	function getDetailIndex() : number {
		return storageList.value.findIndex(item => item.key === detailItem.value.key)
	}
	function onValueTypeChange(e : UniRadioGroupChangeEvent) {
		const type = e.detail.value
		editValueType.value = type
		if (valueTypeDefaultMap.has(type)) {
			editValue.value = valueTypeDefaultMap.get(type) ?? ''
		} else {
			editValue.value = ''
		}
	}
	onLoad(() => {
		refreshList()
	})

	/**
	 * 仅供自动化测试用例调用，设置测试模式
	 */
	function setTestMode(val: boolean) {
		isTestMode.value = val
	}

	__expose({
		editValue,
		editValueType,
		getStorageList,
		setTestMode, // 仅供自动化测试用例调用
	})

return (): any | null => {

const _component_radio = resolveComponent("radio")
const _component_radio_group = resolveComponent("radio-group")

  return createElementVNode("view", utsMapOf({ class: "container" }), [
    createElementVNode("view", utsMapOf({ class: "header" }), [
      createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "Storage管理器"),
      createElementVNode("button", utsMapOf({
        class: "btn btn-create",
        type: "primary",
        onClick: openEditDialogNew
      }), "新建"),
      unref(storageList).length>0
        ? createElementVNode("button", utsMapOf({
            key: 0,
            class: "btn btn-clear",
            type: "default",
            onClick: confirmClear
          }), "清空所有")
        : createCommentVNode("v-if", true)
    ]),
    unref(storageList).length>0
      ? createElementVNode("list-view", utsMapOf({
          key: 0,
          class: "list-view"
        }), [
          createElementVNode(Fragment, null, RenderHelpers.renderList(unref(storageList), (item, index, __index, _cached): any => {
            return createElementVNode("list-item", utsMapOf({
              key: item.key,
              onClick: () => {showDetail(item)}
            }), [
              createElementVNode("view", utsMapOf({ class: "item-block" }), [
                createElementVNode("view", utsMapOf({ class: "item-row" }), [
                  createElementVNode("text", utsMapOf({ class: "item-label" }), "Key："),
                  createElementVNode("text", utsMapOf({ class: "item-key" }), toDisplayString(item.key), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "item-row" }), [
                  createElementVNode("text", utsMapOf({ class: "item-label" }), "Data："),
                  createElementVNode("text", utsMapOf({ class: "item-key" }), toDisplayString(truncate(item.value)), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "item-row" }), [
                  createElementVNode("text", utsMapOf({ class: "item-label" }), "Type："),
                  createElementVNode("text", utsMapOf({ class: "item-key" }), toDisplayString(item.type), 1 /* TEXT */)
                ]),
                createElementVNode("view", utsMapOf({ class: "item-row item-actions-row" }), [
                  createElementVNode("button", utsMapOf({
                    class: "btn btn-delete",
                    type: "default",
                    onClick: withModifiers(() => {confirmDelete(index)}, ["stop"])
                  }), "删除", 8 /* PROPS */, ["onClick"]),
                  createElementVNode("button", utsMapOf({
                    class: "btn btn-edit",
                    type: "primary",
                    onClick: withModifiers(() => {openEditDialogEdit(item, index)}, ["stop"])
                  }), "编辑", 8 /* PROPS */, ["onClick"])
                ])
              ])
            ], 8 /* PROPS */, ["onClick"])
          }), 128 /* KEYED_FRAGMENT */)
        ])
      : createElementVNode("view", utsMapOf({
          key: 1,
          class: "uni-center"
        }), [
          createElementVNode("text", utsMapOf({ class: "uni-hello-text" }), "暂无数据")
        ]),
    isTrue(unref(showDetailDialog))
      ? createElementVNode("view", utsMapOf({
          key: 2,
          class: "dialog-mask",
          onClick: closeDetail
        }), [
          createElementVNode("view", utsMapOf({
            class: "dialog-content",
            onClick: withModifiers(() => {}, ["stop"])
          }), [
            createElementVNode("text", utsMapOf({ class: "dialog-title" }), "详情"),
            createElementVNode("view", utsMapOf({ class: "detail-row" }), [
              createElementVNode("text", utsMapOf({ class: "item-label" }), "Key："),
              createElementVNode("text", utsMapOf({ class: "item-key" }), toDisplayString(unref(detailItem).key), 1 /* TEXT */)
            ]),
            createElementVNode("view", utsMapOf({ class: "detail-row" }), [
              createElementVNode("text", utsMapOf({ class: "item-label" }), "Data："),
              createElementVNode("text", utsMapOf({ class: "item-key uni-list-cell-db-text" }), toDisplayString(unref(detailItem).value), 1 /* TEXT */)
            ]),
            createElementVNode("view", utsMapOf({ class: "detail-row" }), [
              createElementVNode("text", utsMapOf({ class: "item-label" }), "Type："),
              createElementVNode("text", utsMapOf({ class: "item-key" }), toDisplayString(unref(detailItem).type), 1 /* TEXT */)
            ]),
            createElementVNode("view", utsMapOf({ class: "uni-common-mt popup-actions" }), [
              createElementVNode("button", utsMapOf({
                class: "btn mr-20",
                type: "primary",
                onClick: () => {openEditDialogEdit(unref(detailItem), getDetailIndex())}
              }), "编辑", 8 /* PROPS */, ["onClick"]),
              createElementVNode("button", utsMapOf({
                class: "btn mr-20",
                type: "warn",
                onClick: () => {confirmDelete(getDetailIndex())}
              }), "删除", 8 /* PROPS */, ["onClick"]),
              createElementVNode("button", utsMapOf({
                class: "btn",
                onClick: closeDetail
              }), "关闭")
            ])
          ], 8 /* PROPS */, ["onClick"])
        ])
      : createCommentVNode("v-if", true),
    withDirectives(createElementVNode("view", utsMapOf({
      class: "dialog-mask",
      onClick: closeEdit
    }), [
      createElementVNode("view", utsMapOf({
        class: "dialog-content",
        onClick: withModifiers(() => {}, ["stop"])
      }), [
        createElementVNode("text", utsMapOf({ class: "dialog-title" }), toDisplayString(unref(isEditing) ? '编辑' : '新建'), 1 /* TEXT */),
        createElementVNode("view", utsMapOf({ class: "edit-row" }), [
          createElementVNode("text", utsMapOf({ class: "edit-label" }), "Key"),
          createElementVNode("input", utsMapOf({
            modelValue: unref(editKey),
            onInput: ($event: InputEvent) => {trySetRefValue(editKey, $event.detail.value)},
            placeholder: "请输入key",
            class: "edit-input"
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["modelValue"])
        ]),
        createElementVNode("view", utsMapOf({ class: "edit-row" }), [
          createElementVNode("text", utsMapOf({ class: "edit-label" }), "Value"),
          createElementVNode("textarea", utsMapOf({
            modelValue: unref(editValue),
            onInput: ($event: InputEvent) => {trySetRefValue(editValue, $event.detail.value)},
            placeholder: "请输入value",
            class: "edit-textarea"
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["modelValue"])
        ]),
        isTrue(!unref(isEditing))
          ? createElementVNode("view", utsMapOf({
              key: 0,
              class: "edit-row"
            }), [
              createElementVNode("text", utsMapOf({ class: "edit-label" }), "类型"),
              createVNode(_component_radio_group, utsMapOf({
                class: "edit-type-group",
                onChange: onValueTypeChange
              }), utsMapOf({
                default: withSlotCtx((): any[] => [
                  createElementVNode(Fragment, null, RenderHelpers.renderList(valueTypeOptions, (vt, __key, __index, _cached): any => {
                    return createVNode(_component_radio, utsMapOf({
                      key: vt,
                      value: vt,
                      checked: unref(editValueType)===vt,
                      class: "edit-type-radio"
                    }), utsMapOf({
                      default: withSlotCtx((): any[] => [
                        createElementVNode("text", null, toDisplayString(vt), 1 /* TEXT */)
                      ]),
                      _: 2 /* DYNAMIC */
                    }), 1032 /* PROPS, DYNAMIC_SLOTS */, ["value", "checked"])
                  }), 64 /* STABLE_FRAGMENT */)
                ]),
                _: 1 /* STABLE */
              }))
            ])
          : createCommentVNode("v-if", true),
        createElementVNode("view", utsMapOf({ class: "popup-actions" }), [
          createElementVNode("button", utsMapOf({
            class: "btn mr-20 btn-cancel",
            type: "default",
            onClick: closeEdit
          }), "取消"),
          createElementVNode("button", utsMapOf({
            class: "btn btn-save",
            type: "primary",
            onClick: saveEdit
          }), "保存")
        ])
      ], 8 /* PROPS */, ["onClick"])
    ], 512 /* NEED_PATCH */), [
      [vShow, unref(showEditDialog)]
    ])
  ])
}
}

})
export default __sfc__
const GenPagesAPIStorageStoragemanageStyles = [utsMapOf([["container", padStyleMapOf(utsMapOf([["flexDirection", "column"], ["backgroundImage", "none"], ["backgroundColor", "#f7f8fa"], ["flex", 1]]))], ["header", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["alignItems", "center"], ["justifyContent", "space-between"], ["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10]]))], ["btn", padStyleMapOf(utsMapOf([["height", 40], ["fontSize", 16]]))], ["mr-20", padStyleMapOf(utsMapOf([["marginRight", 20]]))], ["list-view", padStyleMapOf(utsMapOf([["flex", 1], ["width", "100%"], ["backgroundImage", "none"], ["backgroundColor", "#ffffff"], ["borderTopLeftRadius", 0], ["borderTopRightRadius", 0], ["borderBottomRightRadius", 0], ["borderBottomLeftRadius", 0], ["paddingTop", 0], ["paddingRight", 0], ["paddingBottom", 0], ["paddingLeft", 0]]))], ["item-block", padStyleMapOf(utsMapOf([["paddingTop", 13], ["paddingRight", 13], ["paddingBottom", 13], ["paddingLeft", 13], ["borderBottomWidth", 1], ["borderBottomStyle", "solid"], ["borderBottomColor", "#e5e5e5"], ["backgroundImage", "none"], ["backgroundColor", "#ffffff"]]))], ["item-row", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["alignItems", "center"], ["marginBottom", 4]]))], ["item-label", padStyleMapOf(utsMapOf([["color", "#888888"], ["fontSize", 14], ["marginRight", 4], ["width", 50]]))], ["item-key", padStyleMapOf(utsMapOf([["color", "#333333"], ["fontSize", 15], ["flex", 1]]))], ["item-actions-row", padStyleMapOf(utsMapOf([["justifyContent", "space-between"], ["marginTop", 6], ["marginBottom", 0]]))], ["dialog-mask", padStyleMapOf(utsMapOf([["position", "fixed"], ["left", 0], ["top", 0], ["right", 0], ["bottom", 0], ["backgroundImage", "none"], ["backgroundColor", "rgba(0,0,0,0.18)"], ["display", "flex"], ["alignItems", "center"], ["justifyContent", "center"], ["zIndex", 999]]))], ["dialog-content", padStyleMapOf(utsMapOf([["backgroundImage", "none"], ["backgroundColor", "#ffffff"], ["borderTopLeftRadius", 10], ["borderTopRightRadius", 10], ["borderBottomRightRadius", 10], ["borderBottomLeftRadius", 10], ["paddingTop", 20], ["paddingRight", 16], ["paddingBottom", 16], ["paddingLeft", 16], ["minWidth", 270], ["maxWidth", 345], ["flexDirection", "column"], ["alignItems", "stretch"], ["position", "relative"], ["boxShadow", "none"]]))], ["dialog-title", padStyleMapOf(utsMapOf([["fontSize", 16], ["fontWeight", "bold"], ["marginBottom", 14], ["textAlign", "center"], ["color", "#222222"], ["letterSpacing", 1]]))], ["detail-row", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["alignItems", "flex-start"], ["marginBottom", 9]]))], ["popup-actions", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["justifyContent", "flex-end"], ["marginTop", 16], ["marginRight", 8]]))], ["error-tip", padStyleMapOf(utsMapOf([["color", "#FF3B30"], ["fontSize", 15], ["marginBottom", 8], ["textAlign", "center"], ["fontWeight", "bold"]]))], ["edit-row", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["alignItems", "center"], ["marginBottom", 9]]))], ["edit-label", padStyleMapOf(utsMapOf([["minWidth", 35], ["color", "#888888"], ["fontSize", 15], ["marginRight", 6]]))], ["edit-input", padStyleMapOf(utsMapOf([["flex", 1], ["height", 40], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#cccccc"], ["borderRightColor", "#cccccc"], ["borderBottomColor", "#cccccc"], ["borderLeftColor", "#cccccc"], ["borderTopLeftRadius", 4], ["borderTopRightRadius", 4], ["borderBottomRightRadius", 4], ["borderBottomLeftRadius", 4], ["paddingTop", 0], ["paddingRight", 6], ["paddingBottom", 0], ["paddingLeft", 6], ["fontSize", 15], ["backgroundImage", "none"], ["backgroundColor", "#ffffff"]]))], ["edit-textarea", padStyleMapOf(utsMapOf([["flex", 1], ["minHeight", 80], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#cccccc"], ["borderRightColor", "#cccccc"], ["borderBottomColor", "#cccccc"], ["borderLeftColor", "#cccccc"], ["borderTopLeftRadius", 4], ["borderTopRightRadius", 4], ["borderBottomRightRadius", 4], ["borderBottomLeftRadius", 4], ["paddingTop", 10], ["paddingRight", 6], ["paddingBottom", 10], ["paddingLeft", 6], ["fontSize", 15], ["backgroundImage", "none"], ["backgroundColor", "#ffffff"]]))], ["edit-type-group", padStyleMapOf(utsMapOf([["display", "flex"], ["flexDirection", "row"], ["alignItems", "center"], ["flexWrap", "wrap"], ["width", "90%"]]))], ["edit-type-radio", padStyleMapOf(utsMapOf([["marginRight", 12], ["marginBottom", 6]]))]])]
