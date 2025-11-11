
/**
 * hex颜色转rgba
 */
export const hexToRgba = (hex : string, alpha : number) : string => {
	// 去除 # 符号（如果有的话）
	hex = hex.replace('#', '');
	let hexArray = hex.split('');
	// 检查颜色值长度，如果不符合预期则返回默认值或者抛出错误
	if (hexArray.length != 3 && hexArray.length != 6) {
		// 返回默认值或者抛出错误，这里使用默认值为黑色
		return 'rgba(0,0,0,1)';
		// 或者抛出错误
		// throw new Error('Invalid hex color value');
	}

	let extendedHex : string[] = [];

	if (hex.length == 3) {
		for (let i = 0; i < hexArray.length; i++) {
			extendedHex.push(hexArray[i]);
			extendedHex.push(hexArray[i]);
		}
		hexArray = extendedHex;
	}
	hex = ''
	for (let h = 0; h < hexArray.length; h++) {
		hex += hexArray[h]
	}

	// // 拆分颜色值为 R、G、B
	const r = parseInt(hex.substring(0, 2), 16);
	const g = parseInt(hex.substring(2, 4), 16);
	const b = parseInt(hex.substring(4, 6), 16);

	// // 返回 rgba 值
	return `rgba(${r},${g},${b},${alpha})`;
}

export const easeInOutCubic = (t : number) : number => {
	return t < 0.5 ? 4 * t * t * t : (t - 1) * (2 * t - 2) * (2 * t - 2) + 1;
}
