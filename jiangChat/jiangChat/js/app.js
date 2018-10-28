window.app{
	/**
	 * 判断是否为空：ture-不为空；false-为空
	 */
	isNotNull = function(str){
		if(str!=null && str!="" && str =!undefined){
			return true;
		}else{
			return false;
		}
	},
	showToast = function(msg,type){
		plus.nativeUI.toast(msg,{icon:"image/"+ type + ".png",verticalAlign:"center"})
	}
}